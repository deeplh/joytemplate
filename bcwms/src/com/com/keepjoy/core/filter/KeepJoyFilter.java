//package com.keepjoy.core.filter;
//
//import com.keepjoy.core.context.KeepJoyHttpContext;
//import com.keepjoy.core.properties.KeepJoyProperties;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@WebFilter(urlPatterns = "*.action",filterName = "keepJoyFilter")
//public class KeepJoyFilter implements Filter{
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response,
//			FilterChain chain) throws IOException, ServletException {
//		request.setCharacterEncoding(KeepJoyProperties.getRequestCharacterEncoding());
//
//		HttpServletRequest httpRequest=(HttpServletRequest) request;
//		HttpServletResponse httpResponse=(HttpServletResponse)response;
//
//		KeepJoyHttpContext.setCurrentRequest(httpRequest);
//		KeepJoyHttpContext.setCurrentResponse(httpResponse);
//		chain.doFilter(request,response);
//	}
//
//	@Override
//	public void init(FilterConfig config) throws ServletException {
//
//	}
//
//
//
//
//
//
//}
