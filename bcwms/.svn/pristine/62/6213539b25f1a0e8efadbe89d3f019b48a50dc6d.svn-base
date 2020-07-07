package com.keepjoy.core.module.datadict.bean;

import com.keepjoy.core.entity.BasDataDict;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;


@PaginationAction(listBeanClass=BasDataDict.class,
		isPagination=false,sql=" from BasDataDict where parentId is null and dataDictKey is null"
			,sqlTail="order by createTime desc ")
public class SearchDataDictType {
	
	@PaginationField(sql=" and dataDictType in (:dataDictType) ")
	private String dataDictType;

	@PaginationField(sql=" and dataDictName=:dataDictName ")
	private String dataDictName;

	public String getDataDictType() {
		return dataDictType;
	}

	public void setDataDictType(String dataDictType) {
		this.dataDictType = dataDictType;
	}

	public String getDataDictName() {
		return dataDictName;
	}

	public void setDataDictName(String dataDictName) {
		this.dataDictName = dataDictName;
	}
	
	
	
	
}
