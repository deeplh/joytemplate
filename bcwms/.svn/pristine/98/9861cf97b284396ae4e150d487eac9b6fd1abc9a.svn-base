package com.keepjoy.core.module.pagination.annotation;

import com.keepjoy.core.bean.pagination.Pagination;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PaginationAction {
	String sql();
	String sqlTail() default "";//整个sql的尾部,一般用于写order by或者group by等
	boolean isNativeSql() default false;//是否为本地sql
	int pageSize() default 10;//每页大小
	Class<?> listBeanClass() default Object.class;//分页数组中的bean
	boolean isComplexPagination() default false;//是否复杂分页,复杂分页是指多表链接之类的
	boolean isPagination() default true;//是否分页,默认为分页
	boolean isReturnSingleObject() default false;//是否返回单独的对象
	boolean isShieldGroupBy() default false;//是否计算数量的时候,需要屏蔽group by语句
	boolean isReturnArray()default false;//是否返回数组,只查一列的时候使用
	boolean isUseMapModel() default false;//是否使用Map模式
	boolean isAutoBeanModel() default false;//是否自动生成bean模式
	boolean isJdbcTemplateModel() default false;//是否是jdbc模版模式,不支持分页
	Class<?> translateBeanClass() default Object.class;//翻译使用的bean,里面的所有方法以get开头,标准java方法命名方式,方法名称中必须包含需要翻译的字段的名称
	Class<?> paginationBeanClass() default Pagination.class;//分页返回的bean,有时候前台需要不同的json格式时,这个类需要继承Pagination.class
	String[] hibernateTypeArray()  default {};//当出现as的时候,指定数据类型用,例子:{"usrName:string","createTime:date"}
	boolean isExportByCustomTemplate() default false;//根据自定义模板导出文件
	boolean isPaginationByRequestFlag() default false;//是否根据请求中的分页flag判断是否执行分页
	String templateBeanName() default "";//用于切换到其他spring中到模版类,例如:切换到sharding-jdbc相关的jdbcTemplate
}
