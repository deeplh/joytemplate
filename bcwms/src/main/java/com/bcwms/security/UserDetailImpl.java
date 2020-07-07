package com.bcwms.security;

import com.bcwms.entity.TblDanceUser;
import com.keepjoy.core.context.KeepJoySpringContext;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.security.SecurityConstant;
import com.keepjoy.security.core.IUserDetailsService;
import com.keepjoy.security.core.SecurityUser;
import com.keepjoy.security.entity.BasUser;
import com.keepjoy.security.util.PasswordUtil;


public class UserDetailImpl implements IUserDetailsService {

	@Override
	public SecurityUser loadUser(String userName) throws Exception{
		KeepJoyDaoImpl dao=KeepJoySpringContext.getKeepJoyDao();
		userName=userName==null?"":userName.trim();
		if(userName.length()==0)throw new KeepJoyServiceException("");

		BasUser bui=dao.findObjectFromListByHql(BasUser.class, "from BasUser where userName=?", userName);
		//取得用户的密码  
		if (bui ==null){  
			throw new KeepJoyServiceException("用户"+userName+"不存在",SecurityConstant.EXCEPTIONCODE_USERNAME_NOT_FOUND);
		}

		TblDanceUser u=dao.get(TblDanceUser.class,bui.getUserId());
		if(u==null)u=new TblDanceUser();


		BcwUser user=new BcwUser();
		user.setUserName(bui.getUserName());
		user.setUserId(bui.getUserId());
		user.setPassword(bui.getPassword());
		user.setDanceUserInfo(u);
//		UserDetailImpl.setRoleMenuResource(user,bui.getUserId());
		return user;
	}




	@Override
	public boolean validatePassword(SecurityUser user, String inputPwd) throws Exception {
		return user.getPassword().equals(PasswordUtil.getPassword(user.getUserName(), inputPwd));
	}



}
