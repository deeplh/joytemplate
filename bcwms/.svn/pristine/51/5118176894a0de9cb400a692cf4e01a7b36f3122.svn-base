package com.bcwms.bean.event;


import com.bcwms.BcwConstant;
import com.bcwms.security.BcwUserHolder;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

/**
 * 查询活动的结果
 */
@PaginationAction(isUseMapModel = true
		,isNativeSql = true,isPagination = false,
		sql=" select " +
		" uer.*," +
		" tdu.aka,tdu.trueName,tdu.avatarUrl " +
		" from TblDanceUserEventResult uer " +
		" left join TblDanceUser tdu on uer.userId=tdu.userId" +
		" where 1=1 ",sqlTail = " order by uer.userConfirmDateTime desc ")
public class SearchEventUserResult {

	@PaginationField(sql=" and uer.status=:status ")
	private String status;


	@PaginationField(sql=" and uer.eventId =:eventId ")
	private Integer eventId;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
}
