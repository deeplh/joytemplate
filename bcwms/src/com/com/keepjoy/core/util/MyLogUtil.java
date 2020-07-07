package com.keepjoy.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public final class MyLogUtil {
	private static final Log log=LogFactory.getLog(MyLogUtil.class);

	private MyLogUtil(){}
	
	public static String getExceptionStr(Throwable e){
		StackTraceElement[] st=e.getStackTrace();
		StringBuffer stb= new StringBuffer(128);
		try {
//			stb.append("\r\n"+" on server " + InetAddress.getLocalHost() + ",using version " + VersionUtil.getVersion()+"\r\n");
			stb.append("\r\n"+e+"\r\n ");
			for(int i=0;i<st.length;i++){
				stb.append(st[i]+"\r\n");
			}
			//得到cause栈中的
			if(null!=e.getCause()){
				StackTraceElement[] causeSt=e.getCause().getStackTrace();
				stb.append("CAUSE:"+e.getCause()+"\r\n ");
				for(int i=0;i<causeSt.length;i++){
					stb.append(causeSt[i]+"\r\n");
				}
			}
		} catch (Exception e1) {
			log.error("输出日志时出现未知异常:",e1);
		}
		return stb.toString();
	}

	public static void printlnException(Log log,Throwable e,String reason){
		if(null!=log){
			log.error(reason+"\r\n ");
			log.error(getExceptionStr(e));
		}
	}
	
	public static void printlnException(Log log,String reason){
		if(null!=log){
			log.error(reason+"\r\n ");
		}
	}


	public static void printlnException(Throwable e,String reason){
		log.error(reason+"\r\n ");
		log.error(getExceptionStr(e));
	}
	
	public static void printlnException(String reason){
		log.error(reason);
	}
	
	public static void printlnDebug(String reason){
		log.debug(reason);
	}
	
	public static void printlnInfo(String reason){
		log.info(reason);
	}
	
	public static void printlnWarn(String reason){
		log.warn(reason);
	}
}
