package com.keepjoy.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.core.util.MyLogUtil;
import com.keepjoy.core.util.http.CookieUtil;
import com.keepjoy.security.SecurityConstant;
import com.keepjoy.security.bean.user.MenuBean;
import com.keepjoy.security.bean.user.ResourceBean;
import com.keepjoy.security.properties.SecurityProperties;
import com.keepjoy.security.core.SecurityUser;
import com.keepjoy.security.core.SecurityUserHolder;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SecurityHandler {
    private static final Log log = LogFactory.getLog(SecurityHandler.class);

    public static final String SECURITY_NONE = "NONE";//不需要权限
    public static final String SECURITY_ALL = "ALL";//至少需要登录
    public static final String SECURITY_SUPER_ADMIN_RESOURCE_BEGIN = "/security/";//超级管理员的资源
    public static final String[] SECURITY_NONE_RESOURCE_BEGIN = new String[]{"/login.action"};//不需要权限的资源

    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain chain;


    public SecurityHandler(HttpServletRequest request,
                           HttpServletResponse response, FilterChain chain) {
        this.request = request;
        this.response = response;
        this.chain = chain;

    }


    private void goToErrorResult(KeepJoyServiceException e) throws IOException {
        MyLogUtil.printlnException(log,e,"判断权限异常:");

        CookieUtil.removeCookieByKey(SecurityConstant.TOKEN, response);



        if(request.getServletPath().indexOf(".action")>-1
                || request.getServletPath().indexOf(".do")>-1
                || (request.getHeader("X-Requested-With")!=null
                &&request.getHeader("X-Requested-With").equals("XMLHttpRequest"))//异步请求
        ){
            response.setContentType("text/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("{\"exception\":\"" + e.getDetail() + "\",\"exceptionCode\":\"" + e.getExceptionCode() + "\"}");
        }else{
            response.sendRedirect(request.getContextPath()+SecurityProperties.getAccessDeniedPage());
        }


    }


    private boolean isCanVisitCustomResource(Map<String, String> attrMap) throws IOException, ServletException {
        if (null != attrMap.get("security")) {
            String securityStr = (String) attrMap.get("security");
            if (SECURITY_NONE.equalsIgnoreCase(securityStr)) {//不要登录就可以访问的资源
                return true;
            } else if (SECURITY_ALL.equalsIgnoreCase(securityStr)) {//至少需要登录才有资格访问的资源
                SecurityUserHolder.getCurrentUser(request);
                return true;
            } else {
                SecurityUser cu = SecurityUserHolder.getCurrentUser(request);
                if (null == cu.getRoles() || cu.getRoles().size() == 0) {
                    throw new KeepJoyServiceException("用户权限错误", SecurityConstant.EXCEPTIONCODE_AUTHORITY_ERROR);
                } else {
                    String[] securityArray = securityStr.split(",");
                    for (String security : securityArray) {
                        for (String role : cu.getRoles().keySet()) {
                            if (role.equalsIgnoreCase(security)) {//当前登录的用户的权限只要有一个和xml的设置的权限相同,就可以访问
                                return true;
                            }
                        }
                    }
                    throw new KeepJoyServiceException("用户权限错误", SecurityConstant.EXCEPTIONCODE_AUTHORITY_ERROR);
                }
            }
        }
        return false;
    }

    //设置超级管理员平台资源的默认权限
    private void setSuperAdminResourceRole(Map<String, Map<String, String>> customResourceListMap) {
        Map<String, String> securityMap = new HashMap<String, String>();
        securityMap.put("security", SecurityConstant.SUPER_ADMIN_ROLE);
        customResourceListMap.put(SECURITY_SUPER_ADMIN_RESOURCE_BEGIN + "*", securityMap);
    }

    public void excute() throws IOException {

        try {
            //判断是否是超级管理员关键字开头,但是不需要登陆的
//			if(request.getServletPath().indexOf(SECURITY_SUPER_ADMIN_RESOURCE_BEGIN)==0){
            for (String re : SECURITY_NONE_RESOURCE_BEGIN) {
                if (request.getServletPath().indexOf(re) >-1) {
                    doFilter();
                    return;
                }
            }
//			}


            //1.先判断配置文件中的该URL访问权限
            Map<String, Map<String, String>> customResourceListMap = null;


            if (null != SecurityProperties.getResources() && SecurityProperties.getResources().size() > 0) {
                customResourceListMap = new HashMap<String, Map<String, String>>();

                for (Map<String, String> rb : SecurityProperties.getResources()) {
                    customResourceListMap.put(rb.get("url"), rb);
                }
            }
            if (null == customResourceListMap) {
                customResourceListMap = new HashMap<String, Map<String, String>>();
            }
            //加入超级管理员相关超级权限
            setSuperAdminResourceRole(customResourceListMap);


            //没有*的URL配置
            if (customResourceListMap.containsKey(request.getServletPath())) {
                if (isCanVisitCustomResource((Map<String, String>) customResourceListMap.get(request.getServletPath()))) {
                    doFilter();
                    return;
                }
            } else {
                //判断是否存在带*的URL配置
                for (String pageStarUrl : customResourceListMap.keySet()) {
                    int num = pageStarUrl.indexOf("*");

                    if (num > -1) {
                        String pageBegin = pageStarUrl.substring(0, num);

                        if ((request.getServletPath().indexOf(pageBegin) == 0
                                || (request.getServletPath() + "/").indexOf(pageBegin) == 0)//表示以pageBegin开头
                                ) {//并且访问的URL不在数据库中,不在数据库配置资源中,才匹配通配符模式

                            //如果包含*.说明可能后面有后缀
                            if (pageStarUrl.indexOf("*.") > -1) {
                                String suffix = pageStarUrl.substring(pageStarUrl.indexOf("*.") + 1);//得到*.后面的后缀名称
                                //如果后面有后缀
                                if (StringUtils.isNotEmpty(suffix)) {
                                    if (request.getServletPath().endsWith(suffix)) {//以这个结尾结束
                                        if (isCanVisitCustomResource((Map<String, String>) customResourceListMap.get(pageStarUrl))) {
                                            doFilter();
                                            return;
                                        }
                                    } else {
                                        doFilter();
                                        return;
                                    }
                                } else {
                                    if (isCanVisitCustomResource((Map<String, String>) customResourceListMap.get(pageStarUrl))) {
                                        doFilter();
                                        return;
                                    }
                                }
                            } else {
                                if (isCanVisitCustomResource((Map<String, String>) customResourceListMap.get(pageStarUrl))) {
                                    doFilter();
                                    return;
                                }
                            }
                        }

                    }
                }
            }

            if (SecurityProperties.getMenuMode()){ //如果是menuMode,需要判断数据库的数据整理出来的资源map
                SecurityUser cu=SecurityUserHolder.getCurrentUser(request);

                //2.再判断数据库中是否设置了该URL的访问权限
                if (cu.getMenus()!=null && cu.getMenus().size()>0) {

                    //判断是否有权限
                    Map<Integer, ResourceBean> resources=null;
                    for (String key:cu.getMenus().keySet()) {
                        resources=cu.getMenus().get(key).getResources();
                        for(Integer resourceKey:resources.keySet()){
                            if(resources.get(resourceKey).getResourceUrl().equals(request.getServletPath())){//如果有这个路径可以访问
                                doFilter();
                                return;
                            }
                        }
                    }
                    throw new KeepJoyServiceException("用户权限错误", SecurityConstant.EXCEPTIONCODE_AUTHORITY_ERROR);
                }
            }


            //3.如果没有配置当前访问的URL的权限,则直接跳转
            doFilter();
        } catch (KeepJoyServiceException e) {
            goToErrorResult(e);
        } catch (Exception e) {
            MyLogUtil.printlnException(log, e, "判断权限时出现未知异常");
        }
    }

    private void doFilter() throws IOException, ServletException {
        if (!response.isCommitted()) {
            chain.doFilter(request, response);
        }
    }
}
