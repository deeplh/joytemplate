package com.keepjoy.core.module.pagination.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TranslateField {
	Class<?>[] translateClassArray();//需要用这个方法的类
	String[] fieldNameArray();//需要翻译的字段在类里面的名称
}
