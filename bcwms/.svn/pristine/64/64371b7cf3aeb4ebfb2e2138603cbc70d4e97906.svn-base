package com.keepjoy.core.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class KeepJoyHttpContext {
    private static final ThreadLocal<HttpServletRequest> request=new ThreadLocal<HttpServletRequest>();
    private static final ThreadLocal<HttpServletResponse> response=new ThreadLocal<HttpServletResponse>();

    public static void setCurrentRequest(HttpServletRequest req)
    {
        request.set(req);
    }
    public static void removeCurrentRequest()
    {
        request.set(null);
        request.remove();
    }
    public static HttpServletRequest getRequest()
    {
        return request.get();
    }

    public static HttpSession getSession()
    {
        return getRequest().getSession();
    }


    public static void setCurrentResponse(HttpServletResponse req)
    {
        response.set(req);
    }
    public static void removeCurrentResponse()
    {
        response.set(null);
        response.remove();
    }
    public static HttpServletResponse getResponse()
    {
        return response.get();
    }

    public static String getHttpBeginUrl(){
        return getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+getRequest().getContextPath();
    }

    public static String getHttpBeginUrlNoPort(){
        return getRequest().getScheme()+"://"+getRequest().getServerName()+getRequest().getContextPath();
    }

    public static String getRealPath(String path){
        return getRequest().getServletContext().getRealPath(path);
    }

    public static void removeAllThreadLocal(){
        removeCurrentRequest();
        removeCurrentResponse();
    }
}
