package com.keepjoy.core.util;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

public class UrlUtil {


	
	//给url最后添加斜杠
	public static String addLastSlash(String url){
		if(StringUtils.isEmpty(url))return url;
		// 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'  
		if (url.charAt(url.length() - 1) != '/' &&
				url.charAt(url.length() - 1) != '\\') {  
			url += "/";  
		} 
		return url;
	}
	
	//在url最前面添加斜杠
	public static String addFirstSlash(String url){
		if(StringUtils.isEmpty(url))return url;
		// 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'  
		if (url.charAt(0) != '/' &&
				url.charAt(0) != '\\') {  
			url= "/"+url;  
		} 
		return url;
	}
	
	//得到最后一个斜杠后面的内容
	public static String getAfterLastSlashUrl(String url){
		if(StringUtils.isEmpty(url))return url;
		int index=url.lastIndexOf("/");
		if(index==-1){
			index=url.lastIndexOf("\\");
		}
		return url.substring(index+1);
	}
	
	//得到最后一个斜杠前面的内容
	public static String getBeforeLastSlashUrl(String url){
		if(StringUtils.isEmpty(url))return url;
		int index=url.lastIndexOf("/");
		if(index==-1){
			index=url.lastIndexOf("\\");
		}
		return url.substring(0,index);
	}
	
	//统一转换成正斜杠(将反斜杠统一转换成正斜杠)
	public static String unifyToForwardSlash(String url){
		url=url.replaceAll("\\\\", "/");
		return url;
	}
	
	//统一转换成反斜杠(将正斜杠统一转换成反斜杠)
	public static String unifyToBackSlash(String url){
		url=url.replaceAll("/", "\\\\");
		return url;
	}
	
	//判断是否为文件夹路径
	public static boolean isFileFolderUrl(String url){
		if(StringUtils.isEmpty(url))return false;
		String lstr=getAfterLastSlashUrl(url);
		if(StringUtils.isNotEmpty(lstr)){//最后一个斜杠后面有东西
			if(lstr.indexOf(".")>0){//说明是文件路径
				return false;
			}else{
				return true;
			}
		}else{//没东西
			return true;
		}
	}
	
	//对url最后一个.前面加额外的字符串
	public static String addAttachUrlBeforeLastDot(String srcUrl,String attachUrl){
		if(StringUtils.isEmpty(srcUrl)){
			return null;
		}
		return srcUrl.substring(0,srcUrl.lastIndexOf('.'))+attachUrl+srcUrl.substring(srcUrl.lastIndexOf('.'),srcUrl.length());
	}
	
	public static void main(String[] args) throws IOException{
//		String url="http://localhost:8080\\ba\\";
//		String path="d:\\test/test.txt";
//		System.out.println(UrlUtil.addAttachUrlBeforeLastDot(path,"_12321"));
//		File file=new File(path);
//		System.out.println(FileUtil.read(path));
//		System.out.println(UrlUtil.unifyToBackSlash(url));
	}
}
