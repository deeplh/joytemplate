package com.keepjoy.core.util.http;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.keepjoy.core.KeepJoyConstant;
import org.apache.commons.lang.StringUtils;

public class HttpHeaderUtil {



	public static String getIpAddr(HttpServletRequest request){
		String unknownKey="unknown";
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || unknownKey.equalsIgnoreCase (ip)){
			ip = request.getHeader("Proxy-Client-IP");
		} 
		if(ip == null || ip.length() == 0 || unknownKey.equalsIgnoreCase (ip)){ 
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || unknownKey.equalsIgnoreCase(ip)){ 
			ip = request.getRemoteAddr();
		} 
		return ip;
	} 


	public static boolean isContentTypeImage(String contentType){
		if(contentType.equals(KeepJoyConstant.CONTENT_TYPE_GIF)
				||contentType.equals(KeepJoyConstant.CONTENT_TYPE_PNG)
				||contentType.equals(KeepJoyConstant.CONTENT_TYPE_JPG)){
			return true;
		}else{
			return false;
		}
	}

	//	public static boolean isContentTypeF(String contentType){
	//		if(contentType.equals(KeepJoyConstant.CONTENT_TYPE_GIF)
	//				||contentType.equals(KeepJoyConstant.CONTENT_TYPE_PNG)
	//				||contentType.equals(KeepJoyConstant.CONTENT_TYPE_JPG)){
	//			return true;
	//		}else{
	//			return false;
	//		}
	//	}

	public static String getContentTypeByFileName(String fileName){
		if(StringUtils.isEmpty(fileName))return null;
		fileName=fileName.toLowerCase();
		int index=fileName.indexOf("?");
		if(index>-1)fileName=fileName.substring(0,index);
		String contentType=null;
		if(fileName.endsWith(KeepJoyConstant.XLS.getValue())||fileName.endsWith(KeepJoyConstant.XLSX.getValue())){
			contentType=KeepJoyConstant.CONTENT_TYPE_EXCEL.getValue();
		}else if(fileName.endsWith(KeepJoyConstant.PDF.getValue())){
			contentType=KeepJoyConstant.CONTENT_TYPE_PDF.getValue();
		}else if(fileName.endsWith(KeepJoyConstant.HTML.getValue())){
			contentType=KeepJoyConstant.CONTENT_TYPE_HTML.getValue();
		}else if(fileName.endsWith(KeepJoyConstant.PNG.getValue())){
			contentType=KeepJoyConstant.CONTENT_TYPE_PNG.getValue();;
		}else if(fileName.endsWith(KeepJoyConstant.JPG.getValue())){
			contentType=KeepJoyConstant.CONTENT_TYPE_JPG.getValue();
		}else if(fileName.endsWith(KeepJoyConstant.GIF.getValue())){
			contentType=KeepJoyConstant.CONTENT_TYPE_GIF.getValue();
		}else if(fileName.endsWith(KeepJoyConstant.CSS.getValue())){
			contentType=KeepJoyConstant.CONTENT_TYPE_CSS.getValue();
		}else if(fileName.endsWith(KeepJoyConstant.JS.getValue())||fileName.endsWith(KeepJoyConstant.MAP.getValue())){
			contentType=KeepJoyConstant.CONTENT_TYPE_JS.getValue();
		}else if(fileName.endsWith(KeepJoyConstant.DOC.getValue())||fileName.endsWith(KeepJoyConstant.DOCX.getValue())){
			contentType=KeepJoyConstant.CONTENT_TYPE_DOC.getValue();
		}else if(fileName.endsWith(KeepJoyConstant.WOFF.getValue())
				||fileName.endsWith(KeepJoyConstant.WOFF2.getValue())){
			contentType=KeepJoyConstant.CONTENT_TYPE_WOFF.getValue();
		}else if(fileName.endsWith(KeepJoyConstant.SVG.getValue())){
			contentType=KeepJoyConstant.CONTENT_TYPE_SVG.getValue();
		}else if(fileName.endsWith(KeepJoyConstant.TTF.getValue())){
			contentType=KeepJoyConstant.CONTENT_TYPE_TTF.getValue();
		}
		return contentType;
	}


	public static String setResponseHeader(HttpServletResponse response,String responseContentType,String responseCharacterEncoding){
		response.setHeader("Cache-Control", "no-datadict");
		response.setHeader("Expires", "0");
		response.setHeader("Pragma", "no-datadict");
		if(StringUtils.isEmpty(response.getContentType())){
			//vtab.getJta().responseContentType()
			response.setContentType(responseContentType);
		}

		String charset=null;
//				JsonTagConfigFactory.getRequestCharacterEncoding();
		//vtab.getJta().responseCharacterEncoding()
		if(StringUtils.isNotEmpty(responseCharacterEncoding)){
			charset=responseCharacterEncoding;
		}

		//必须要用这样一个临时变量contentType,因为调用setCharacterEncoding后,会把ContentType里面的内容也改变
		String contentType=response.getContentType();
		int c=-1;
		if(null!=contentType)c=contentType.lastIndexOf(";");
		if(c>-1){
			contentType=contentType.substring(0,c);
			int d=contentType.lastIndexOf("=");
			if(d>-1)charset=contentType.substring(d+1);
		}
		response.setCharacterEncoding(charset);
		return contentType;
	}


}
