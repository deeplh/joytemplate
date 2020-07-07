package com.bcwms.bean.studio;


import com.bcwms.entity.TblDanceStudio;
import com.bcwms.security.BcwUserHolder;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(listBeanClass=TblDanceStudio.class
		,sql=" from TblDanceStudio  where createUserId=:userId ",sqlTail = " order by id desc ")
public class SearchStudioMe {

	@PaginationField
	private Integer userId;

	public Integer getUserId() {
		return BcwUserHolder.getDanceUser().getUserId();
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
