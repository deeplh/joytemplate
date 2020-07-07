package com.bcwms.bean.event;


import com.bcwms.entity.TblDanceEvent;
import com.bcwms.security.BcwUserHolder;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

/**
 * 查询我发布的所有活动
 */
@PaginationAction(listBeanClass=TblDanceEvent.class
		,sql=" select new TblDanceEvent(e.id,e.name,e.stageDate,e.img,e.city," +
		" e.eventType,e.maxPersonNum,e.founderId,e.founderType,e.status,e.createDateTime," +
		" ea.yearMonth,e.contractAddress,e.settleDateTime)" +
		" from TblDanceEvent e,TblDanceEventAttach ea " +
		" where e.id=ea.eventId and e.createUserId=:userId "
		,sqlTail = " order by e.id desc ")
public class SearchEventMe {


	@PaginationField
	private Integer userId;

	public Integer getUserId() {
		return BcwUserHolder.getDanceUser().getUserId();
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
