package com.keepjoy.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ReflectUtil {

	
	
	public static String getSetMethodString(String fieldName){
		return "set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
	}
	
	public static String getGetMethodString(String fieldName){
		return "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
	}
	
	public static void invokeSetMethod(Class<?> cl,Field field,Object obj,Object val) throws Exception{
		Method method = cl.getDeclaredMethod(getSetMethodString(field.getName()),field.getType());  
		method.invoke(obj,val);
	}
	
	public static void invokeSetMethod(Class<?> cl,String fieldName,Class<?> type,Object obj,Object val) throws Exception{
		Method method = cl.getDeclaredMethod(getSetMethodString(fieldName),type);  
		method.invoke(obj,val);
	}
	
	public static Object invokeGetMethod(Class<?> cl,Field field,Object obj) throws Exception{
		Method method = cl.getDeclaredMethod(getGetMethodString(field.getName()));  
		return method.invoke(obj);
	}
	
	public static Object invokeGetMethod(Class<?> cl,String fieldName,Object obj) throws Exception{
		Method method = cl.getDeclaredMethod(getGetMethodString(fieldName));  
		return method.invoke(obj);
	}
	
	/**
	 * 调用方法
	 * @param className 　类的全限命名
	 * @param methodName  方法名称
	 * @throws Exception
	 */
	public static void invokeMethod(String className,String methodName,Class<?>[] paramCls,Object[] paramObjs) throws Exception{
		Class<?> cl= Class.forName(className);
		Object obj=cl.newInstance();
		Method method = cl.getDeclaredMethod(methodName,paramCls);
		method.invoke(obj,paramObjs);
	}
}
