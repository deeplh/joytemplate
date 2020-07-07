package com.bcwms.security;


import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.security.core.SecurityUser;
import com.keepjoy.security.core.SecurityUserHolder;

public class BcwUserHolder {

	
	
	public static BcwUser getDanceUser(){
		SecurityUser suser=SecurityUserHolder.getCurrentUser();
		if(null==suser)throw new KeepJoyServiceException("用户信息未绑定",98);
		BcwUser pwu = (BcwUser) suser;
		return pwu;
	}
}
