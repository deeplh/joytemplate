package com.keepjoy.core.module.pagination.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PaginationField {
	String sql() default "";//对应这个field的sql
	String dateFormatSDF() default "";//转换成date类型后的格式
	String timeAppend() default "";//查询时前台传入的为年月日的时候,后台的时间有时分秒,需要加上000000或者235959,需要和dataFormatSDF联合使用
	Class<?> dataTypeWhenWithIn() default String.class;//当sql中包含in关键字,且需要查询的条件的类型不为String的时候使用
	int sqlPosition() default -1;//对应sql里面的索引,当大于0时使用
	boolean isFieldPagination() default true;//如果这个标志变为false,当这个条件的语句触发,会使整个方法不再分页

	//根据字段名称(全限命名)和操作符动态拼装sql
	String[] fieldNames() default {};//字段名称
	String operator() default "";//操作符 like或者in等
	String connector() default "";//连接操作符 or或者and
}
