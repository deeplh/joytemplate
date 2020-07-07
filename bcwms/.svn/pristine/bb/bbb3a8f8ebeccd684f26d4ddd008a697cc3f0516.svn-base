package com.bcwms.entity;

import com.bcwms.BcwConstant;
import com.bcwms.service.BcwCommonService;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
public class TblDanceEventAttach extends TblDanceCommonAttrAttach implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer eventId;



    @Basic
    @Column(name = "eventId", nullable = true)
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }



    @Transient
    public String getImgDis(){

        return BcwCommonService.getImgDis(this.img,BcwConstant.TYPE_EVENT,this.yearMonth);
     }


    @Transient
    public String getImgDisMin() {
        return BcwCommonService.getImgDisMin(getImgDis());
    }
}
