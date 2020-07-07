package com.keepjoy.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@ConfigurationProperties(prefix="keepjoy.security")
public class SecurityProperties implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private static Integer sessionTimeout=30;//session超时时间,单位:分钟,默认30分钟
	private static Integer rememberMeTime;//记住我的时间,单位:天

	private static String userDetailsServiceClassName="com.keepjoy.security.core.impl.UserDetailImpl";//登陆流程的实现类

	private static Boolean captchaMode=false;//验证码模式
	private static Boolean menuMode=false;
	private static Boolean ssoMode=false;
	private static String accessDeniedPage="";

	private static String securityUserClassName="com.keepjoy.security.core.SecurityUser";//继承SecurityUser类的bean

	private static List<Map<String,String>> resources=new ArrayList<Map<String,String>>();

	public static Integer getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(Integer sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	public static Integer getRememberMeTime() {
		return rememberMeTime;
	}

	public void setRememberMeTime(Integer rememberMeTime) {
		this.rememberMeTime = rememberMeTime;
	}

	public static String getUserDetailsServiceClassName() {
		return userDetailsServiceClassName;
	}

	public void setUserDetailsServiceClassName(String userDetailsServiceClassName) {
		this.userDetailsServiceClassName = userDetailsServiceClassName;
	}

	public static Boolean getCaptchaMode() {
		return captchaMode;
	}

	public void setCaptchaMode(Boolean captchaMode) {
		this.captchaMode = captchaMode;
	}

	public static String getSecurityUserClassName() {
		return securityUserClassName;
	}

	public void setSecurityUserClassName(String securityUserClassName) {
		this.securityUserClassName = securityUserClassName;
	}


	public static Boolean getMenuMode() {
		return menuMode;
	}

	public void setMenuMode(Boolean menuMode) {
		SecurityProperties.menuMode = menuMode;
	}

	public static List<Map<String, String>> getResources() {
		return resources;
	}

	public void setResources(List<Map<String, String>> resources) {
		SecurityProperties.resources = resources;
	}


    public static Boolean getSsoMode() {
        return ssoMode;
    }

    public void setSsoMode(Boolean ssoMode) {
        SecurityProperties.ssoMode = ssoMode;
    }


	public static String getAccessDeniedPage() {
		return accessDeniedPage;
	}

	public static void setAccessDeniedPage(String accessDeniedPage) {
		SecurityProperties.accessDeniedPage = accessDeniedPage;
	}
}
