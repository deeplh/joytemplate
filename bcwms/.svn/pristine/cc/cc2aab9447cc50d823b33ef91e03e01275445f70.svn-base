package com.keepjoy.core.module.pagination.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ListBeanClassAlias {
	String tableAliasName() default "";//表的别称,如果不用select * 方式,直接自己码查询字段的时候,可以为空
	String columnTrueName() default "";//列的真实名称,为sql类型时使用
}
