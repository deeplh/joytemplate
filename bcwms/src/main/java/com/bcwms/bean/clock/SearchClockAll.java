package com.bcwms.bean.clock;

import com.bcwms.entity.TblDanceClock;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(listBeanClass=TblDanceClock.class
        ,sql=" select new TblDanceClock(tdc.id, tdc.userId, tdc.info, tdc.createDateTime,"
        + " tdc.continueClockNum, tdc.video, tdc.address,"
        + " tdu.aka,tdu.avatarUrl) "
        + " from TblDanceClock tdc,TblDanceUser tdu where tdu.userId=tdc.userId "
        ,sqlTail = " order by tdc.id desc ")
public class SearchClockAll {

    @PaginationField(sql=" and tdc.status=:status ")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}