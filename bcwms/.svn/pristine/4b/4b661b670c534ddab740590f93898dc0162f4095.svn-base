package com.keepjoy.core.util.http;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	public static void removeCookieByKey(String key,HttpServletResponse response){
		Cookie cookie = new Cookie(key, null); 
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	public static String getCookieValueByKey(Cookie[] cookies,String key){
		if(null==key)return "";
		if(null==cookies)return "";
		for(Cookie cookie :cookies){
			if(key.equals(cookie.getName())){
				return cookie.getValue();
			}
		}
		return "";
	}



}
