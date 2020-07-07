package com.keepjoy.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import com.keepjoy.core.KeepJoyConstant;
import com.keepjoy.core.properties.KeepJoyProperties;
import com.keepjoy.core.util.http.HttpHeaderUtil;
import org.apache.commons.lang.StringUtils;


/**
 * 解析jar中的静态http资源,如html,css等
 * @author deep
 *
 */
public class JarResourceUtil {
	public final static int DEFAULT_BUFFER_SIZE = 1024 * 4;

	//得到包中的文件的真实名称
	private static String getPureFileNameInPackage(String fileName){
		int index=fileName.indexOf("?");
		if(index>-1)fileName=fileName.substring(0,index);
		return fileName;
	}
	
	public static String read(InputStream in) {
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(in,"utf-8");
			
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
		return read(reader);
	}

	public static String read(Reader reader) {
		try {

			StringWriter writer = new StringWriter();

			char[] buffer = new char[DEFAULT_BUFFER_SIZE];
			int n = 0;
			while (-1 != (n = reader.read(buffer))) {
				writer.write(buffer, 0, n);
			}

			return writer.toString();
		} catch (IOException ex) {
			throw new IllegalStateException("read error", ex);
		}
	}

	public static InputStream readInputStreamFromResource(String resource) throws IOException {
		resource=getPureFileNameInPackage(resource);
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
		if (in == null) {
			in = JarResourceUtil.class.getResourceAsStream(resource);
		}
		return in;
	}

	public static String readTextFromResource(String resource) throws IOException {
		InputStream in = null;
		try{
			in=readInputStreamFromResource(resource);
			String text = read(in);
			return text;
		} finally {
			if(null!=in)in.close();
		}
	}

	public static byte[] readByteArrayFromResource(String resource) throws IOException {
		
		InputStream in = null;
		try {
			resource=getPureFileNameInPackage(resource);
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
			if (in == null) {
				return null;
			}

			return readByteArray(in);
		} finally {
			if(null!=in)in.close();
		}
	}

	public static byte[] readByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy(input, output);
		return output.toByteArray();
	}

	public static long copy(InputStream input, OutputStream output) throws IOException {
		final int EOF = -1;

		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];

		long count = 0;
		int n = 0;
		while (EOF != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	public static boolean isImage(String fileName){
		fileName=getPureFileNameInPackage(fileName);
		if (fileName.endsWith(KeepJoyConstant.PNG.getValue())
				||fileName.endsWith(KeepJoyConstant.JPG.getValue())
				||fileName.endsWith(KeepJoyConstant.JPEG.getValue())
				||fileName.endsWith(KeepJoyConstant.GIF.getValue())) {
			return true;
		}else{
			return false;
		}
	}

	public static boolean isFont(String fileName){
		fileName=getPureFileNameInPackage(fileName);
		if (fileName.endsWith(KeepJoyConstant.TTF.getValue())
				||fileName.endsWith(KeepJoyConstant.WOFF.getValue())
				||fileName.endsWith(KeepJoyConstant.EOT.getValue())
				||fileName.endsWith(KeepJoyConstant.WOFF2.getValue())) {
			return true;
		}else{
			return false;
		}
	}

	public static Object getResourceFile(String filePath) throws IOException{
		if (isImage(filePath) || isFont(filePath)) {
			return readByteArrayFromResource(filePath);
		}else{
			return readTextFromResource(filePath);
		}
	}

	public static void returnResourceFile(String filePath,HttpServletResponse response) throws ServletException,IOException{
		if (isImage(filePath) || isFont(filePath)) {
			byte[] bytes = readByteArrayFromResource(filePath);
			if (bytes != null) {
				response.getOutputStream().write(bytes);
			}
			return;
		}

		response.setContentType(HttpHeaderUtil.getContentTypeByFileName(filePath));
		response.setCharacterEncoding(KeepJoyProperties.getRequestCharacterEncoding());
		String text = readTextFromResource(filePath);
		if(null==text){
			response.sendRedirect("");
		}else{
			if(!response.isCommitted()){
				response.getWriter().write(text);
			}
		}
	}

	public static void returnResourceFileByPlaceholder(String filePath,HttpServletResponse response,String placeholder,String replaceStr) throws ServletException,IOException{
		if (isImage(filePath) || isFont(filePath)) {
			byte[] bytes = readByteArrayFromResource(filePath);
			if (bytes != null) {
				response.getOutputStream().write(bytes);
			}
			return;
		}

		response.setContentType(HttpHeaderUtil.getContentTypeByFileName(filePath));
		response.setCharacterEncoding(KeepJoyProperties.getRequestCharacterEncoding());
		String text = readTextFromResource(filePath);
		if(null==text){
			response.sendRedirect("");
		}else{
			if(!response.isCommitted()){
				text=text.replaceAll("<%="+placeholder+"%>", replaceStr);
				response.getWriter().write(text);
			}
		}
	}
	
	public static String read(InputStream in,String charset) throws IOException{
		String result=null;
		try{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] byteData = new byte[1024];
			int readLength =0;
			while(true){
				readLength = in.read(byteData,0, byteData.length);
				if(readLength == -1) break;
				baos.write(byteData, 0, readLength);
			}
			byte[] resultByte=baos.toByteArray();
//			System.out.println("len:"+resultByte.length);
			
			if(StringUtils.isNotEmpty(charset)){
//				System.out.println("charset:"+charset); 
//				System.out.println("hex:"+ByteUtil.byteArrayToHexString(resultByte)); 
				result=new String(resultByte,charset);
			}else{
				result=new String(resultByte);
			}
		} finally{
//			System.out.println("result:"+result); 
			if(in!=null){
				in.close();
			}
		}
		return result;
	}
}
