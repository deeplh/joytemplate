package com.bcwms.bean.clock;


import com.bcwms.BcwConstant;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;

@PaginationAction(isAutoBeanModel = true,isNativeSql = true,pageSize = 20
		,sql=" select count(tdc.userId) clockSum,tdc.userId,tdu.avatarUrl,tdu.aka " +
		" from TblDanceClock tdc " +
		" left join TblDanceUser tdu on tdu.userId=tdc.userId " +
		" where tdc.status!="+BcwConstant.STATUS_3_REMOVE
		,sqlTail = " group by tdc.userId,tdu.avatarUrl,tdu.aka order by clockSum desc ")
public class SearchClockRank {





}
