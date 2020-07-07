package com.bcwms.bean.event;


import com.bcwms.entity.TblDanceUserEventRelation;
import com.bcwms.security.BcwUserHolder;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

/**
 * 查询我发布的所有活动加入申请
 */
@PaginationAction(listBeanClass=TblDanceUserEventRelation.class,pageSize = 10
		,sql=" select new TblDanceUserEventRelation(tduer.id, tduer.eventId,tdc.name, tdc.img," +
		" tduer.userId,tduer.createDateTime,tduer.status,ea.yearMonth) " +
		" from TblDanceUserEventRelation tduer,TblDanceEventAttach ea," +
		" TblDanceEvent tdc " +
		" where tduer.eventId=tdc.id and tduer.userId=:userId and ea.eventId=tduer.eventId ",sqlTail = " order by tduer.id desc")
public class SearchEventApplyMe {


	@PaginationField
	private Integer userId;

	public Integer getUserId() {
		return BcwUserHolder.getDanceUser().getUserId();
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
