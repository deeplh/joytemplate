package com.bcwms.bean.clock;


import com.bcwms.BcwConstant;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;
import com.keepjoy.core.util.DateUtil;

@PaginationAction(isReturnSingleObject = true,
		sql="select id,continueClockNum from TblDanceClock " +
				" where userId=:userId and clockDate=:clockDate and status!="+BcwConstant.STATUS_3_REMOVE,listBeanClass = Long.class)
public class GetClockMeToday {


	@PaginationField()
	private Integer userId;

	@PaginationField(dateFormatSDF=DateUtil.DATEFORMAT_YYYYMMDD)
	private String clockDate;

	public Integer getUserId() {
		return BcwUserHolder.getDanceUser().getUserId();
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getClockDate() {
		return DateUtil.dateToStringByDateFormat(BcwCommonService.getNow(),DateUtil.DATEFORMAT_YYYYMMDD);
	}

	public void setClockDate(String clockDate) {
		this.clockDate = clockDate;
	}
}
