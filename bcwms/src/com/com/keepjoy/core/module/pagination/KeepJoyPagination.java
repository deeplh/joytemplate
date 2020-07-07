package com.keepjoy.core.module.pagination;

import com.keepjoy.core.context.KeepJoyHttpContext;
import com.keepjoy.core.context.KeepJoySpringContext;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;
import com.keepjoy.core.module.pagination.annotation.TranslateField;
import com.keepjoy.core.properties.KeepJoyProperties;
import com.keepjoy.core.util.MyLogUtil;
import com.keepjoy.core.util.ReflectUtil;

import com.keepjoy.core.util.SplitSqlUtil;
import com.keepjoy.core.util.StringUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class KeepJoyPagination {

    public static final String PREFIX_COUNT_SQL="select count(*) ";
    public static final String NUMBER_REGEXP="^\\d+$";
    public static final String CURRENTPAGE_KEY="currentPage";
    public static final String EXPORT_KEY="export";
    public static final String PAGESIZE_KEY="pageSize";
    public static final String PAGINATION_KEY="pagination";
    public static final String DISTINCT_KEY=" distinct ";
    public static final String GROUP_BY_KEY=" group by ";
    public static final String SELECT_KEY="select";
    public static final String FROM_KEY=" from ";
    public static final String ORDER_BY_KEY=" order by ";
    public static final String AS_KEY=" as ";
    public static final String IN_KEY=" in ";
    public static final String LIKE_KEY=" like ";


    private StringBuffer stbSql;
    private List<String> paramNamesList;
    private List<Object> valuesList;

    //构造方法传入的
    private PaginationAction jtpa;
    private Object paramObject;//查询参数
    private Integer currentPage;
    private Integer pageSize;
    private Boolean isPagination;


    public KeepJoyPagination(Object params) {
        this.paramObject = params;
        this.jtpa=params.getClass().getAnnotation(PaginationAction.class);
        setPageSize();
        setCurrentPage();
    }

    private void createParamAndValue(String sql) throws Exception{
        paramNamesList=new ArrayList<String>();
        valuesList=new ArrayList<Object>();
        stbSql=new StringBuffer(sql);
        if(paramObject!=null){
            Method method = null;
            String getMethod=null;
            Object val=null;
            PaginationField pf=null;

            //组装查询条件
            for(Field field:paramObject.getClass().getDeclaredFields()){
                pf=field.getAnnotation(PaginationField.class);
                getMethod=ReflectUtil.getGetMethodString(field.getName());

                MyLogUtil.printlnDebug("getMethod:"+getMethod);
                method = paramObject.getClass().getDeclaredMethod(getMethod);
                if(null==method){
                    MyLogUtil.printlnException("======method:"+method+"is null");
                }else{
                    MyLogUtil.printlnDebug("=====method:"+method);
                    val=method.invoke(paramObject);
                }
                val=(val==null||val.equals(""))?null:val;

                //进行占位符替换
                if(val!=null){

                    String pfSqlLower=sql.toLowerCase();
                    if(val instanceof Object[]){
                        if(pf.fieldNames().length>0
                                && StringUtils.isNotEmpty(pf.operator())
                                && StringUtils.isNotEmpty(pf.connector())){//需要根据数组的传值进行拼装的情况

                            StringBuffer finalSql=new StringBuffer(" and (");

                            //根据传值动态添加
                            Object[] objs=(Object[]) val;
                            String ph=null;
                            for(int i=0;i<objs.length;i++){
                                for(int j=0;j<pf.fieldNames().length;j++){
                                    ph=field.getName()+i+j;
                                    finalSql.append(pf.fieldNames()[j]+" "+pf.operator()+" :"+ph);
                                    if(j!=pf.fieldNames().length-1){
                                        finalSql.append(" "+pf.connector()+" ");
                                    }
                                    paramNamesList.add(ph);
                                    valuesList.add(objs[i]);
                                }
                                if(i!=objs.length-1){
                                    finalSql.append(" "+pf.connector()+" ");
                                }
                            }
                            stbSql.append(" "+finalSql+" ) ");
                        }
                    }
                    else{
                        String valStr=val.toString();
                        if("%%".equals(valStr)||"%".equals(valStr)||"%null%".equals(valStr.toLowerCase()))continue;//使用like的时候,没有传值,所以只有%,这个时候不考虑作为参数查询

                        //为date类型时
                        if(pf.dateFormatSDF().length()>0){
                            SimpleDateFormat sdf= new SimpleDateFormat(pf.dateFormatSDF());
                            String dataStr=valStr;
                            if(pf.timeAppend().length()>0){
                                dataStr=dataStr+" "+pf.timeAppend().trim();
                            }
                            val=sdf.parse(dataStr);
                        }
                        //包含in字段查询时
                        if(pfSqlLower.indexOf(IN_KEY)>-1){
                            List ids = new ArrayList();
                            String[] valArray=valStr.split(",");
                            //long类型
                            if(pf.dataTypeWhenWithIn()==Long.class){
                                for(String valA:valArray){
                                    ids.add(Long.parseLong(valA));
                                }
                            }
                            //int类型
                            else if(pf.dataTypeWhenWithIn()==Integer.class){
                                for(String valA:valArray){
                                    ids.add(Integer.parseInt(valA));
                                }
                            }
                            //string类型
                            else{
                                for(String valA:valArray){
                                    ids.add(valA);
                                }
                            }
                            val=ids;
                        }
                        //拼装sql
                        if(StringUtils.isNotEmpty(pf.sql())){
                            stbSql.append(" "+pf.sql());
                        }
                        //多个域使用同一个查询值
                        int num=StringUtil.countKeyOfString(pf.sql(), ":");
                        if(num>1){
                            for(int i=1;i<=num;i++){
                                paramNamesList.add(field.getName()+i);
                                valuesList.add(val);
                            }
                        }
                        //jdbc模版方式用?
                        else if(pf.sql().indexOf("?")>-1){
                            valuesList.add(val);
                        }
                        else{
                            if(pfSqlLower.indexOf(field.getName().toLowerCase())>-1
                                    || pf.sql().toLowerCase().indexOf(field.getName().toLowerCase())>-1){//直接在上面sql里面写的
                                paramNamesList.add(field.getName());
                                valuesList.add(val);
                            }
                        }

                    }




                }
            }

        }
    }

    //得到当前页数
    private void setCurrentPage(){
        //当前页数
        String cpKey=KeepJoyProperties.getPaginationCurrentPageKey();
        if(StringUtils.isEmpty(cpKey)){
            cpKey=CURRENTPAGE_KEY;
        }
        String currentPageStr=KeepJoyHttpContext.getRequest().getParameter(cpKey);
        if(StringUtils.isEmpty(currentPageStr)
                ||!currentPageStr.matches(NUMBER_REGEXP)){
            MyLogUtil.printlnWarn("param current page is invalid,init current page=1");
            currentPage=1;
        }else{
            currentPage=Integer.parseInt(currentPageStr);
        }

    }

    //得到页大小
    private void setPageSize(){
        //页大小
        String psKey=KeepJoyProperties.getPaginationPageSizeKey();
        if(StringUtils.isEmpty(psKey)){
            psKey=PAGESIZE_KEY;
        }
        String pageSizeStr=KeepJoyHttpContext.getRequest().getParameter(psKey);
        if(StringUtils.isEmpty(pageSizeStr)
                || !pageSizeStr.matches(NUMBER_REGEXP)
                || Integer.parseInt(pageSizeStr)>500){
            MyLogUtil.printlnWarn("param page size is valid ");
            pageSize=10;
        }else{
            pageSize=Integer.parseInt(pageSizeStr);
        }
    }

    private Query getQuery(Session session, PaginationAction jtpa, String finalSql){
        Query query=null;
        if(jtpa.isUseMapModel()){//使用map返回模式
            query=session.createSQLQuery(finalSql);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }else if(jtpa.isNativeSql()){//使用nativeSql返回模式
            if(jtpa.listBeanClass()!=Object.class){
                finalSql=SplitSqlUtil.getSqlByListBeanClassAlias(finalSql, jtpa.listBeanClass());
            }
            query=session.createSQLQuery(finalSql);
        }else if(jtpa.isComplexPagination()){//使用复杂多表联合hql返回模式
            if(jtpa.listBeanClass()!=Object.class){
                finalSql=SplitSqlUtil.getSqlByListBeanClassAlias(finalSql, jtpa.listBeanClass());
            }
            query=session.createQuery(finalSql);
        }
        else{
            query=session.createQuery(finalSql);
        }
        return query;
    }

    private Query getCountQuery(Session session,String sqlNow){
        String countSql=null;
        String sqlNowLowerCase=sqlNow.toLowerCase();
        //去掉sqlNowLowerCase中最后的order by
        int orderByIndex=sqlNowLowerCase.lastIndexOf(ORDER_BY_KEY);
        String sqlNowForCount=sqlNow;
        if(orderByIndex>-1){
            sqlNowLowerCase=sqlNowLowerCase.substring(0,orderByIndex);

            sqlNowForCount=sqlNow.substring(0,orderByIndex);
        }
        //判断是否有distinct或者group by
        int distinctAndGroupByIndex=sqlNowLowerCase.indexOf(DISTINCT_KEY);
        if(distinctAndGroupByIndex==-1){
            distinctAndGroupByIndex=sqlNowLowerCase.indexOf(GROUP_BY_KEY);
        }

        if(distinctAndGroupByIndex>-1
                ||sqlNowForCount.indexOf(FROM_KEY)!=sqlNowForCount.lastIndexOf(FROM_KEY)//判断是否有个多个from
                ||sqlNowForCount.indexOf(SELECT_KEY)!=sqlNowForCount.lastIndexOf(SELECT_KEY)//判断是否有个多个select
                ){
            countSql=" select count(*) from( "+sqlNowForCount+" ) distinctAndGroupByAlias ";
        }else{
            //得到统计数量的sql
            int selectIndex=sqlNowLowerCase.indexOf(SELECT_KEY);
            if(selectIndex >= 0 && selectIndex < 4){
                countSql=PREFIX_COUNT_SQL+sqlNowForCount.substring(sqlNowLowerCase.indexOf(FROM_KEY),sqlNowForCount.length());
            }else{
                countSql=PREFIX_COUNT_SQL+sqlNowForCount;
            }
        }
        //计算数量的时候过滤group by
        String countSqlLowerCase=countSql.toLowerCase();
        if(jtpa.isShieldGroupBy() && countSqlLowerCase.lastIndexOf(GROUP_BY_KEY)>-1){
            int endNum=countSqlLowerCase.lastIndexOf(GROUP_BY_KEY);
            countSql=countSql.substring(0,endNum);
        }

        Query countQuery;
        if(jtpa.isNativeSql()||jtpa.isUseMapModel()){
            countQuery=session.createSQLQuery(countSql);
        }else{
            countQuery=session.createQuery(countSql);
        }
        return countQuery;
    }

    //统一分页方法
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Object getPag(final PaginationAction jtpa,
                          final String sqlNow,final Object[] paramNames,final Object[] values
    ) throws NoSuchMethodException, SecurityException{
        //替换sql中的位置占位符
        HibernateTemplate ht=null;

            if(StringUtils.isNotEmpty(jtpa.templateBeanName())){
                ht=(HibernateTemplate) KeepJoySpringContext.getSpringContext().getBean(jtpa.templateBeanName());
            }else{
                ht=KeepJoySpringContext.getHibernateTemplate();
            }


        final Constructor constructor=jtpa.paginationBeanClass().getDeclaredConstructor(List.class,int.class,int.class,int.class);

        return ht.execute(new HibernateCallback(){

            public Object doInHibernate(Session session) throws HibernateException {
                Query query=getQuery(session,jtpa,sqlNow);

                //赋值
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        applyNamedParameterToQuery(query, paramNames[i].toString(), values[i]);
                    }
                }

                List list=new ArrayList();
                int counts=0;//总数量

                if(isPagination()){//需要分页的时候

                    Query countQuery=getCountQuery(session,sqlNow);

                    if (values != null) {
                        for (int i = 0; i < values.length; i++) {
                            applyNamedParameterToQuery(countQuery, paramNames[i].toString(), values[i]);
                        }
                    }
                    //得到数量
                    List countList=countQuery.list();
                    Number countsN=(countList==null||countList.size()==0)?0:(Number)(countList.get(0));
                    counts=countsN.intValue();
                    if(counts==0){//数量为空的时候
                        try {
                            if(jtpa.isReturnSingleObject()){
                                return null;
                            }else{
                                if(isPagination()){
                                    return constructor.newInstance(null,counts,currentPage,pageSize);
                                }else{
                                    return new ArrayList();
                                }
                            }
                        } catch (Exception e) {
                            MyLogUtil.printlnException( e, "constructor.newInstance error");
                            return null;
                        }
                    }else{
                        query.setFirstResult((currentPage-1)*pageSize);
                        query.setMaxResults(pageSize);
                    }
                }
                //设置最终结果集合
                try {
                    if(jtpa.isUseMapModel()){//是否使用map返回模式
                        list=query.list();
                        //进行翻译
                        if(list!=null
                                &&list.size()>0
                                &&jtpa.translateBeanClass()!=Object.class){
                            list=translateByTranslateBean(jtpa.translateBeanClass(),list);
                        }
                    }else{//bean返回模式

                        if(jtpa.isAutoBeanModel()){
                            list=SplitSqlUtil.getObjectListForAutoBean(sqlNow,query,jtpa);
                            list=translateByTranslateBean(jtpa.translateBeanClass(),list);
                        }
                        else if(jtpa.isComplexPagination()
                                ||jtpa.isNativeSql()){
                            list=SplitSqlUtil.getObjectList(sqlNow,query,jtpa);
                        }
                        else{
                            list=query.list();
                        }
                    }
                } catch (Exception e) {
                    MyLogUtil.printlnException(e, "JsonTagPaginationActionHandler error:");
                }
                //只返回单个对象的时候
                if(jtpa.isReturnSingleObject()){
                    if(list!=null && list.size()>0){
                        return list.get(0);
                    }else{
                        return null;
                    }
                }else{
                    if(isPagination()){//需要分页的时候
                        //返回值
                        try {
                            return constructor.newInstance(list,counts,currentPage,pageSize);
                        } catch (Exception e) {
                            MyLogUtil.printlnException( e, "constructor.newInstance error");
                            return null;
                        }
                    }else{
                        return list;
                    }
                }
            }});
    }

    private boolean isPagination(){
        if(null!=isPagination){
            return this.isPagination;
        }else{
            if(jtpa.isPaginationByRequestFlag()){
                if(StringUtils.isNotEmpty(KeepJoyHttpContext.getRequest().getParameter(PAGINATION_KEY))){
                    return true;
                }else return false;
            }else{
                return jtpa.isPagination();
            }
        }

    }

    @SuppressWarnings("rawtypes")
    public static void applyNamedParameterToQuery(Query queryObject, String paramName, Object value)throws HibernateException {
        if (value instanceof Collection) {
            queryObject.setParameterList(paramName, (Collection) value);
        }
        else if (value instanceof Object[]) {
            queryObject.setParameterList(paramName, (Object[]) value);
        }
        else {
            queryObject.setParameter(paramName, value);
        }
    }

    //根据bean进行翻译
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private List translateByTranslateBean(Class translateCl, List<Map> list)throws Exception{
        Object obj=translateCl.newInstance();

        String fieldName=null;
        List<Map> newTranslateList=new ArrayList<Map>();

        for(int i=0;i<list.size();i++){
            Map newTranslateMap=new HashMap();
            if(null==list.get(i))continue;
            for (Object key : list.get(i).keySet()) {
                fieldName=key.toString();

                newTranslateMap.put(key, list.get(i).get(key));
                for(Method method:translateCl.getDeclaredMethods()){


                    if(method.isAnnotationPresent(TranslateField.class)){
                        TranslateField jttf=method.getAnnotation(TranslateField.class);
                        if(!ArrayUtils.contains(jttf.translateClassArray(), paramObject.getClass())){
                            continue;
                        }
                        String mapNewKey=method.getName().replace("get","");
                        mapNewKey=mapNewKey.substring(0, 1).toLowerCase()+mapNewKey.substring(1);
                        for(String fieldNameJttf:jttf.fieldNameArray()){
                            if(fieldName.equals(fieldNameJttf)){
                                if(null!=list.get(i)){
                                    Object val=list.get(i).get(key);
                                    if(null!=val){
                                        MyLogUtil.printlnDebug("===translate,fieldName:"+fieldName+"===type:"+val.getClass()+"===val:"+val);
                                        newTranslateMap.put(mapNewKey, method.invoke(obj, val));
                                    }
                                }
                            }
                        }
                    }else{
                        String fieldUpName=fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);//首字母为大写的fieldName
                        if(method.getName().indexOf("get"+fieldUpName)>-1){
                            String mapNewKey=fieldName+method.getName().replace("get"+fieldUpName,"");
                            if(null!=list.get(i)){
                                Object val=list.get(i).get(key);
                                if(null!=val){
                                    MyLogUtil.printlnDebug("===translate,fieldName:"+fieldName+"===type:"+val.getClass());
                                    newTranslateMap.put(mapNewKey, method.invoke(obj, val));
                                }
                            }
                        }
                    }
                }
            }
            newTranslateList.add(newTranslateMap);
        }
        return newTranslateList;
    }


    public Object doPagination() throws Exception {

        //创建查询条件相关
        createParamAndValue(jtpa.sql());
        final String sqlNow=stbSql.toString()+" "+jtpa.sqlTail();

        final Object[] paramNames=paramNamesList.toArray();
        final Object[] values=valuesList.toArray();

        MyLogUtil.printlnDebug("source sql:["+sqlNow+"]");

        Object obj=null;

        if(jtpa.isJdbcTemplateModel()){
            obj=getObjByJdbcTemplateModel(jtpa,sqlNow,values);
        }else{//分页结构
            obj=getPag(jtpa,sqlNow,paramNames,values);
        }

        if(null==obj){
            Map map=new HashMap();
            map.put("root",obj);
            return map;
        }else return obj;


    }

    private Object getObjByJdbcTemplateModel(PaginationAction jtpa,String sqlNow,Object[] values){
        JdbcTemplate jdbc=null;

            if(StringUtils.isNotEmpty(jtpa.templateBeanName())){
                jdbc=(JdbcTemplate) KeepJoySpringContext.getSpringContext().getBean(jtpa.templateBeanName());
            }else{
                jdbc=KeepJoySpringContext.getJdbcTemplate();
            }

        if(null==jdbc)throw new IllegalArgumentException(" pls set jdbcTemplate in spring config ");
        if(Object.class==jtpa.listBeanClass()){
            if(values.length>0){
                return jdbc.queryForList(sqlNow,values);
            }else{
                return jdbc.queryForList(sqlNow);
            }
        }else{
            if(values.length>0){
                return jdbc.query(sqlNow,new BeanPropertyRowMapper<>(jtpa.listBeanClass()),values);
            }else{
                return jdbc.query(sqlNow,new BeanPropertyRowMapper<>(jtpa.listBeanClass()));
            }
        }
    }


    public void setPagination(Boolean pagination) {
        isPagination = pagination;
    }
}
