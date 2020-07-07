package com.bcwms.bean.crew;


import com.bcwms.entity.TblDanceCrew;
import com.bcwms.security.BcwUserHolder;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(listBeanClass=TblDanceCrew.class,sql=" from TblDanceCrew   where createUserId=:userId "
		,sqlTail = " order by id desc ")
public class SearchCrewMe {



	@PaginationField
	private Integer userId;

	public Integer getUserId() {
		return BcwUserHolder.getDanceUser().getUserId();
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
