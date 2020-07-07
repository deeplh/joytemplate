package com.bcwms.bean.crew;


import com.bcwms.entity.TblDanceUserCrewRelation;
import com.bcwms.security.BcwUserHolder;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(listBeanClass=TblDanceUserCrewRelation.class
		,sql=" select new TblDanceUserCrewRelation(tducr.id, tducr.crewId,tdc.name, tdc.img," +
		" tducr.userId,tducr.createDateTime,tducr.status) from TblDanceUserCrewRelation tducr," +
		" TblDanceCrew tdc " +
		" where tducr.crewId=tdc.id and tducr.userId=:userId  ",sqlTail = " order by tducr.id desc")
public class SearchCrewApplyMe {



	@PaginationField
	private Integer userId;

	public Integer getUserId() {
		return BcwUserHolder.getDanceUser().getUserId();
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
