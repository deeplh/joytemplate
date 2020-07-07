package com.bcwms.bean.clock;


import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceClock;
import com.bcwms.security.BcwUserHolder;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(listBeanClass=TblDanceClock.class,isPagination = false
		,sql=" from TblDanceClock where userId=:userId and status!="+BcwConstant.STATUS_3_REMOVE
		,sqlTail = " order by id desc ")
public class SearchClockMe {



	@PaginationField
	private Integer userId;




	private String clockDate;


	@PaginationField(sql=" and MONTH(clockDate)=:clockMonth ")

	private Integer clockMonth;


	@PaginationField(sql=" and YEAR(clockDate)=:clockYear ")

	private Integer clockYear;


	public Integer getUserId() {
		return BcwUserHolder.getDanceUser().getUserId();
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getClockMonth() {
		return clockMonth;
	}

	public void setClockMonth(Integer clockMonth) {
		this.clockMonth = clockMonth;
	}

	public Integer getClockYear() {
		return clockYear;
	}

	public void setClockYear(Integer clockYear) {
		this.clockYear = clockYear;
	}


//	public Date getClockMonth() throws ParseException {
//		if(StringUtils.isNotEmpty(this.clockDate)){
//			return DateUtil.stringToDateByDateFormat(this.clockDate,DateUtil.DATEFORMAT_MM_MONTH);
//		}
//		return null;
//	}
//
//	public Date getClockYear() throws ParseException {
//		if(StringUtils.isNotEmpty(this.clockDate)){
//			return DateUtil.stringToDateByDateFormat(this.clockDate,DateUtil.DATEFORMAT_YYYY);
//		}
//		return null;
//	}

	public String getClockDate() {
		return clockDate;
	}

//	public void setClockDate(String clockDate) {
//		this.clockDate = clockDate;
//	}

	//	public void setClockYear(String clockYear) {
//		this.clockYear = clockYear;
//	}

	//	public void setClockMonth(String clockMonth) {
//		this.clockMonth = clockMonth;
//	}
//


}
