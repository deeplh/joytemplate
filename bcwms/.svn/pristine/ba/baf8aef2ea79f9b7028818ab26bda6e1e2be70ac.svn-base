package com.bcwms.bean.studio;


import com.bcwms.entity.TblDanceUserStudioRelation;
import com.bcwms.security.BcwUserHolder;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(listBeanClass=TblDanceUserStudioRelation.class
		,sql=" select new TblDanceUserStudioRelation(tdusr.id, tdusr.studioId,tds.name, tds.img," +
		" tdusr.userId,tdusr.createDateTime,tdusr.status) from TblDanceUserStudioRelation tdusr," +
		" TblDanceStudio tds " +
		" where tdusr.studioId=tds.id and tdusr.userId=:userId  ",sqlTail = " order by tdusr.id desc")
public class SearchStudioApplyMe {


	@PaginationField
	private Integer userId;

	public Integer getUserId() {
		return BcwUserHolder.getDanceUser().getUserId();
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
