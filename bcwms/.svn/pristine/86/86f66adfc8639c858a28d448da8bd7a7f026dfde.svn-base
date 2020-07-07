package com.keepjoy.security.proxy;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.keepjoy.core.context.KeepJoySpringContext;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.core.util.DateUtil;
import com.keepjoy.core.util.MyLogUtil;
import com.keepjoy.security.SecurityConstant;
import com.keepjoy.security.properties.SecurityProperties;
import com.keepjoy.security.core.SecurityUser;
import org.apache.commons.lang.StringUtils;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;



public class RedisSsoProxy {
	
	
	@SuppressWarnings("rawtypes")
	public static final RedisTemplate<String,SecurityUser> getRedisTemplate(){
		return  KeepJoySpringContext.getKeepJoyDao().getRedisTemplate();
	}
	

	public static void setUserToRedis(SecurityUser user, int expiredTime){
		if(StringUtils.isEmpty(user.getToken())){
			throw new RuntimeException("传入setUserToRedis方法的token异常");
		}
//		if(SecurityProperties.getKickMode()){
//			Set<String> keys=getRedisTemplate().keys(UserDetailImpl.getTokenEndMatch());
//
//			ValueOperations<String, SecurityUser> vo=getRedisTemplate().opsForValue();
//
//			for(String key:keys){
//				if(user.getUserId().equals(vo.get(key).getUserId())){
//					getRedisTemplate().delete(key);
//				}
//			}
//
//		}

		ValueOperations<String, SecurityUser> vo=getRedisTemplate().opsForValue();
		vo.set(user.getToken(), user,expiredTime,TimeUnit.MINUTES);
	}

	/**
	 * 默认为security-config里面的超时时间
	 * @param user
	 */
	public static void setUserToRedis(SecurityUser user){
		setUserToRedis(user,SecurityProperties.getSessionTimeout());
	}

	/**
	 * 存入对应的子用户
	 * @param subUser
	 */
	@SuppressWarnings("unchecked")
	public static void setSubUserToRedis(SecurityUser subUser){
		if(StringUtils.isEmpty(subUser.getToken())){
			throw new RuntimeException("传入setSubUserToRedis方法的token异常");
		}
		SecurityUser jtUser=getUserFromRedis(subUser.getToken());
		jtUser.getSubSecurityUsers().put(SecurityProperties.getSecurityUserClassName(), JSONObject.toJSONString(subUser));
		getRedisTemplate().opsForValue().set(jtUser.getToken(), jtUser,SecurityProperties.getSessionTimeout(),TimeUnit.MINUTES);
	}




	public static SecurityUser getUserFromRedis(String token){
		SecurityUser user=null;
		try {
			user=getRedisTemplate().opsForValue().get(token);
		} catch (Exception e) {
			MyLogUtil.printlnException(e,"根据token获取user异常");
			throw new KeepJoyServiceException("根据token获取user异常",SecurityConstant.EXCEPTIONCODE_SSO_ERROR);
		}
		if(null==user)throw new KeepJoyServiceException("token已过期",SecurityConstant.EXCEPTIONCODE_SSO_EXPIRED);
		//判断超时时间
		if(DateUtils.addMinutes(new Date(), 5).compareTo(user.getTimeoutDateTime())>0){
			user.setTimeoutDateTime(DateUtil.addMinuteForDate(new Date(), SecurityProperties.getSessionTimeout()));
			setUserToRedis(user);
		}
		return user;
	}

	public static SecurityUser getUserFromRedisNoThrow(String token){
		Object obj=null;
		try {
			obj=getRedisTemplate().opsForValue().get(token);
		} catch (Exception e) {
			return null;
		}
		if(null==obj){
			return null;
		}else{
			return (SecurityUser) obj;
		}
	}

	public static SecurityUser getUserFromRedis(SecurityUser user){
		return getUserFromRedis(user.getToken());
	}



	public static SecurityUser getSubUserFromSecurityUserMap(SecurityUser user, String key) throws ClassNotFoundException{
		if(null!=user){
			String objStr=user.getSubSecurityUsers().get(key);
			if(StringUtils.isEmpty(objStr)){
				throw new KeepJoyServiceException("对应的子用户不存在");
			}
			Class<?> useClass=Class.forName(key);
			Object cuObj= JSONObject.parseObject(objStr,useClass );
			return (SecurityUser) cuObj;
		}else{
			return null;
		}
	}

	public static SecurityUser getSubUserFromSecurityUserMap(SecurityUser user) throws ClassNotFoundException{
		if(StringUtils.isEmpty(SecurityProperties.getSecurityUserClassName())){
			throw new KeepJoyServiceException("请配置正确的[core-user-class-name]的路径");
		}
		return getSubUserFromSecurityUserMap(user,SecurityProperties.getSecurityUserClassName());
	}


	//redis模式的情况,需要嵌套子系统的user
	//每次重新登录都是创建一个新的token,对应了一个redis中的业务对象缓存
	public static SecurityUser loginByRedis(SecurityUser user, int expiredTime){

		SecurityUser jtUser=RedisSsoProxy.getUserFromRedisNoThrow(user.getToken());
		if(null==jtUser){//所有子系统中第一次登录
			jtUser=new SecurityUser(user.getUserId(),user.getUserName(),user.getGroupId(), user.getToken());
		}
		jtUser.getSubSecurityUsers().put(SecurityProperties.getSecurityUserClassName(), JSONObject.toJSONString(user));

		jtUser.setTimeoutDateTime(DateUtil.addMinuteForDate(new Date(), expiredTime));

		setUserToRedis(jtUser,expiredTime);

		return jtUser;
	}
}
