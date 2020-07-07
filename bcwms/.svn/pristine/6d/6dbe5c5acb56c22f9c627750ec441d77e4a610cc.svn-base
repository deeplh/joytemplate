package com.keepjoy.security.core;


public interface IUserDetailsService {
	/**
	 * 编写登录逻辑,不判断密码
	 * @param userName
	 * @return SecurityUser 如果需要判断密码,返回的user对象必须set password(MD5后的密码)
	 * @throws Exception
	 */
	public SecurityUser loadUser(String userName)throws Exception;
	
	/**
	 * 编写密码验证逻辑,如果直接返回true,表示不需要验证密码
	 * @param user 
	 * @param inputPwd 用户输入的密码
	 * @return
	 */
	public boolean validatePassword(SecurityUser user, String inputPwd) throws Exception;
}
