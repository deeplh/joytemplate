package com.keepjoy.weixin.security.smallplatform;

import java.util.HashMap;
import java.util.Map;

import com.keepjoy.core.context.KeepJoyHttpContext;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.security.core.SecurityUserHolder;
import org.apache.commons.lang.StringUtils;

//本地缓存模式
public class SmallPlatformUserHolder {
   
	private static final Map<String,SmallPlatformUser> userMap=new HashMap<String,SmallPlatformUser>();
	
	
	public static SmallPlatformUser getUser() {
		String token=KeepJoyHttpContext.getRequest().getParameter("token");
		if(StringUtils.isEmpty(token)){
			token=KeepJoyHttpContext.getRequest().getHeader("token");
		}
		if(StringUtils.isEmpty(token)){
			throw new KeepJoyServiceException("token参数异常",97);
		}
		
		SmallPlatformUser spu=userMap.get(token);
		if(null==spu){
			spu=(SmallPlatformUser) SecurityUserHolder.getCurrentUser();
			if(null==spu)throw new KeepJoyServiceException("token超时",90);
		}
		return spu;
	}


	public static void putUserMap(String token, SmallPlatformUser user) {
		userMap.put(token, user);
	}

	public static void removeUser(String token) {
		userMap.remove(token);
	}

	public static void removeUser(SmallPlatformUser user){
		if(userMap.containsValue(user)){
			userMap.remove(user.getToken());
		}
	}



}
