package com.keepjoy.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keepjoy.core.context.KeepJoyHttpContext;
import com.keepjoy.core.properties.KeepJoyProperties;
import com.keepjoy.security.handler.SecurityHandler;
import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "*",filterName = " keepJoySecurityFilter")
public class SecurityFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(KeepJoyProperties.getRequestCharacterEncoding());
		
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		KeepJoyHttpContext.setCurrentRequest(httpRequest);
		KeepJoyHttpContext.setCurrentResponse(httpResponse);
		SecurityHandler securityHandler=new SecurityHandler(httpRequest,httpResponse,chain);
		securityHandler.excute();

		KeepJoyHttpContext.removeAllThreadLocal();
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}






}
