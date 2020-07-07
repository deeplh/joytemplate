package com.keepjoy.core.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import com.keepjoy.core.module.pagination.KeepJoyPagination;
import com.keepjoy.core.module.pagination.annotation.ListBeanClassAlias;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

public class SplitSqlUtil {
	private static final Log log=LogFactory.getLog(SplitSqlUtil.class);


	public static String getSqlByListBeanClassAlias(String sql,Class<?> cl){
		if(sql.toLowerCase().indexOf("select * ")>-1){//用*的时候必须要用ListBeanClassAlias
			Field[] fields=cl.getDeclaredFields();
			StringBuffer stb=new StringBuffer("");
			ListBeanClassAlias ta=null;
			for(Field field:fields){
				if(field.isAnnotationPresent(ListBeanClassAlias.class)){
					ta=field.getAnnotation(ListBeanClassAlias.class);
					if(ta.tableAliasName().length()>0){
						stb.append(ta.tableAliasName()+".");
					}
					if(ta.columnTrueName().length()>0){
						stb.append(ta.columnTrueName()+",");
					}else{
						stb.append(field.getName()+",");
					}
				}else{
					log.debug("["+field.getName()+"] is only field");
				}
			}
			String beginSql=stb.toString();
			if(beginSql.length()>0){
				beginSql=beginSql.substring(0, beginSql.length()-1);
				sql=sql.replace(" * ", " "+beginSql+" ");
			}
		}
		log.debug("final begin sql is:"+sql);
		return sql;
	}

	/**
	 * 如果使用到ListBeanClassAlias注解,用此方法,得到javabean中属性对应到数据库中实际应该查询的字段的列名
	 * @param field
	 * @return
	 */
	private static String getTrueColumnName(Field field){
		String columnName=field.getName();
		if(field.isAnnotationPresent(ListBeanClassAlias.class)){//有注解
			ListBeanClassAlias lbca=field.getAnnotation(ListBeanClassAlias.class);
			if(null!=lbca && lbca.columnTrueName().length()>0){
				columnName=lbca.columnTrueName();
			}
		}
		return columnName;
	}

	/**
	 * @param column column因为是按逗号分隔,会有多余的别名,as之类的东西,需要清除掉
	 * @return
	 */
	private static String getPureColumnName(String column){
		column=column.trim();
		if(StringUtils.isEmpty(column))return null;
		int c=column.indexOf("(");
		if(c==0){//说明是子查询
			int d=column.lastIndexOf(")");
			column=column.substring(d+1);
		}
		c=column.indexOf(".");
		if(c>-1){
			column=column.substring(c+1);
		}
		c=column.indexOf(KeepJoyPagination.AS_KEY);
		if(c>-1){
			column=column.substring(c+KeepJoyPagination.AS_KEY.length());
		}
		c=column.indexOf(" ");
		if(c>-1){
			column=column.substring(c+1);
		}
		return column.trim();
	}

	/**
	 * 设置对应到Hibernate的字段type
	 * @param fieldArray
	 * @param query
	 * @return
	 */
	public static void setFieldHibernateType(Field fieldArray[],Query query,String[] sqlColumnArrayLower){
		for(int j=0;j<fieldArray.length;j++){

			String columnName=getTrueColumnName(fieldArray[j]);

			//判断是否SQL中查询了这个字段
			if(!hasTheColumnName(sqlColumnArrayLower,columnName))continue;

			Type hibernateType=null;
			if(fieldArray[j].getType().isInstance(new Date())){
				hibernateType=StandardBasicTypes.TIMESTAMP;
			}else if(fieldArray[j].getType().isInstance(new String())){
				hibernateType=StandardBasicTypes.STRING;
			}else if(fieldArray[j].getType().isInstance(new Integer("1"))){
				hibernateType=StandardBasicTypes.INTEGER;
			}else if(fieldArray[j].getType().isInstance(new Long("1"))){
				hibernateType=StandardBasicTypes.LONG;
			}else if(fieldArray[j].getType().isInstance(new Float("1"))){
				hibernateType=StandardBasicTypes.FLOAT;
			}else if(fieldArray[j].getType().isInstance(new BigDecimal("1"))){
				hibernateType=StandardBasicTypes.BIG_DECIMAL;
			}else if(fieldArray[j].getType().isInstance(new Byte("1"))){
				hibernateType=StandardBasicTypes.BYTE;
			}else if(fieldArray[j].getType().isInstance(new Boolean("1"))){
				hibernateType=StandardBasicTypes.BOOLEAN;
			}else if(fieldArray[j].getType().isInstance(new Double("1"))){
				hibernateType=StandardBasicTypes.DOUBLE;
			}
			if(null!=hibernateType){
				((SQLQuery) query).addScalar(columnName,hibernateType);
			}else{
				((SQLQuery) query).addScalar(columnName);
			}

		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  static List<?> getObjectListForAutoBean(String finalSql,Query query,PaginationAction jtpa) throws Exception{

		String finalSqlLower=finalSql.toLowerCase();
		int selectIndex=finalSqlLower.indexOf(KeepJoyPagination.SELECT_KEY);
		int fromIndex=finalSqlLower.lastIndexOf(KeepJoyPagination.FROM_KEY);
		if(finalSqlLower.split(KeepJoyPagination.FROM_KEY).length>2){
			fromIndex=finalSqlLower.indexOf(KeepJoyPagination.FROM_KEY);
		}


		String sqlColumns=finalSql.substring((selectIndex+KeepJoyPagination.SELECT_KEY.length()+1),fromIndex);
		sqlColumns=sqlColumns.trim();
		String sqlColumnWithAsArray[]=sqlColumns.split(",");

		String sqlColumnArray[]=new String[sqlColumnWithAsArray.length];
		boolean isNeedAddHibernateType=false;
		for(int i=0;i<sqlColumnWithAsArray.length;i++){
			String column=getPureColumnName(sqlColumnWithAsArray[i].trim());
			if(column==null)continue;
			sqlColumnArray[i]=column;
			//用了as
			if(sqlColumnWithAsArray[i].indexOf(KeepJoyPagination.AS_KEY)>-1){
				isNeedAddHibernateType=true;
			}
		}
		//生成属性
		BeanGenerator generator = new BeanGenerator();    
		for(String column:sqlColumnArray){
			generator.addProperty(column, Object.class);    
			//后期加入注解配置as属性

			if(isNeedAddHibernateType && jtpa.isNativeSql()){

				if(column.toLowerCase().endsWith("time")||column.toLowerCase().endsWith("date")){
					((SQLQuery) query).addScalar(column,StandardBasicTypes.TIMESTAMP);
				}else if(column.toLowerCase().endsWith("number")){
					((SQLQuery) query).addScalar(column,StandardBasicTypes.BIG_DECIMAL);
				}else{
					((SQLQuery) query).addScalar(column,StandardBasicTypes.STRING);
				}
			}
		}

		List objectArrayList=query.list();

		List list = new  ArrayList();  

		for(int i=0;i<objectArrayList.size();i++){
			if(!jtpa.isReturnArray()){
				BeanMap bm=BeanMap.create(generator.create());
				if(objectArrayList.get(i) instanceof Object[]){//查询的结果里面是多列的时候
					Object valArray[]=(Object[]) objectArrayList.get(i);

					for(int j=0;j<sqlColumnArray.length;j++){
						bm.put(sqlColumnArray[j], valArray[j]);
					}
				}else{
					for(int j=0;j<sqlColumnArray.length;j++){
						bm.put(sqlColumnArray[j], objectArrayList.get(i));
					}
				}
				list.add(bm);
			}else{//查询单列的时候
				list.add(objectArrayList.get(i));
			}
		}
		return  list;  
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  static List<?> getObjectList(String finalSql,Query query,PaginationAction jtpa) throws Exception{

		Object obj=null;
		Class cl=jtpa.listBeanClass();
		Field fieldArray[]=cl.getDeclaredFields();//得到所有的属性
		if(fieldArray==null||fieldArray.length==0){
			throw new NullPointerException(cl+" is null ");
		}

		//得到查询的所有字段
		String finalSqlLower=finalSql.toLowerCase();
		String sqlColumnsLower=finalSqlLower.substring((finalSqlLower.indexOf(KeepJoyPagination.SELECT_KEY)+KeepJoyPagination.SELECT_KEY.length()+1),finalSqlLower.indexOf(KeepJoyPagination.FROM_KEY));
		String sqlColumnArrayLower[]=sqlColumnsLower.split(",");//查询的列


		//native语句时设置HibernateType
		if(jtpa.isNativeSql()){
			setFieldHibernateType(fieldArray,query,sqlColumnArrayLower);
		}

		List objectArrayList=query.list();
		Object[] objectArray=null;

		List list = new  ArrayList();  
		for(int i=0;i<objectArrayList.size();i++){
			if(!jtpa.isReturnArray()){
				obj=cl.newInstance();
				if(objectArrayList.get(i) instanceof Object[]){//查询的结果里面是多列的时候
					objectArray=(Object[]) objectArrayList.get(i);
					for(int j=0;j<sqlColumnArrayLower.length;j++){
						String column=getPureColumnName(sqlColumnArrayLower[j]);

						for(int k=0;k<fieldArray.length;k++){
							String columnName=getTrueColumnName(fieldArray[k]);

							if(column.equals(columnName.toLowerCase())){
								if(i==0 && objectArray[j]!=null){
									log.debug("*****columnName:["+columnName+"] and fieldName:["+fieldArray[k].getName()+"] ");
								}

								ReflectUtil.invokeSetMethod(cl,fieldArray[k],obj,objectArray[j]);
								break;
							}
						}
					}
				}else{
					for(int j=0;j<fieldArray.length;j++){
						//判断是否SQL中查询了这个字段
						if(!hasTheColumnName(sqlColumnArrayLower,fieldArray[j].getName()))continue;

						ReflectUtil.invokeSetMethod(cl,fieldArray[j],obj,objectArrayList.get(i));
					}
				}
				list.add(obj);

			}else{//查询单列的时候
				list.add(objectArrayList.get(i));
			}
		}

		return  list;  
	}


	//判断是否查询语句中包含这个查询列
	private static boolean hasTheColumnName(String[] selectColumnLowerArray,String columnName){
		if(null==columnName)return false;
		columnName=columnName.trim().toLowerCase();

		for(String column:selectColumnLowerArray){
			column=getPureColumnName(column);
			if(columnName.equals(column)){
				return true;
			}
		}
		return false;
	}



	/******************************test***************************/
	public static void main(String[] args){
		//		System.out.println(hasTheColumnName("SELECT　T.NAME ,T.SEX,T.AGE,T.mobile as mo,C.mobile as mo1","mo1"));
		//		System.out.println(SplitSqlUtil.getSqlByNewConstructor("select * from com.yt.jrb.kingsway.po.TblBcjk tb,com.yt.jrb.kingsway.po.TblYzdhzh ty where tb.yzdhZh=ty.yzdhZh and tb.bcjkId=?",TblDict.class,"tb"));
	}
}
