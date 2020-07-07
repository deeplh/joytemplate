package com.keepjoy.core.module.datadict.bean;

import com.keepjoy.core.entity.BasDataDict;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(listBeanClass=BasDataDict.class,
		isPagination=false,sql=" from BasDataDict where  dataDictKey is not null "
			,sqlTail="order by createTime desc ")
public class SearchDataDictTypeValue {
	
	@PaginationField(sql=" and dataDictType in (:dataDictType) ")
	private String dataDictType;

	public String getDataDictType() {
		return dataDictType;
	}

	public void setDataDictType(String dataDictType) {
		this.dataDictType = dataDictType;
	}

	
	
	
}
