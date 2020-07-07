package com.keepjoy.core.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class EncryptUtil {

	private static String bytes2HexForMD5(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i]&0xff));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public static String encryptByMD5(byte[] b) throws UnsupportedEncodingException, NoSuchAlgorithmException  {
		MessageDigest alga =  MessageDigest.getInstance("MD5");
		alga.update(b);
		byte[] digest = alga.digest();
		return new String(bytes2HexForMD5(digest));
	}
	
	public static String encryptByMD5(File file) throws NoSuchAlgorithmException, IOException {
		return FileUtil.getFileMD5(file);
	}
	
	public static String encryptByMD5(String text,String charset) throws UnsupportedEncodingException, NoSuchAlgorithmException  {
		return encryptByMD5(text.getBytes(charset));
	}
}
