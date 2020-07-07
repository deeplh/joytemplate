package com.keepjoy.security.util;

import com.keepjoy.core.util.EncryptUtil;
import com.keepjoy.security.SecurityConstant;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;



public class PasswordUtil {

	public static String getPassword(String username,String pwd) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		return EncryptUtil.encryptByMD5(username+"{"+pwd+"}"+SecurityConstant.MD5_KEY, "GBK");
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		System.out.println(getPassword("1722","111111"));
	}
}
