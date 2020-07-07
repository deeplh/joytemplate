package com.bcwms.entity;

import com.bcwms.BcwConstant;
import com.bcwms.service.BcwCommonService;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
public class TblDanceClockAttach extends TblDanceCommonAttrAttach implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer clockId;


    public String imgGs;

    @Basic
    @Column(name = "clockId", nullable = true)
    public Integer getClockId() {
        return clockId;
    }

    public void setClockId(Integer clockId) {
        this.clockId = clockId;
    }

    @Basic
    @Column(name = "img", nullable = true, length = 100)
    public String getImgGs() {
        return imgGs;
    }

    public void setImgGs(String imgGs) {
        this.imgGs = imgGs;
    }

    @Transient
    public String getImgDis(){

        return BcwCommonService.getImgDis(this.img,BcwConstant.TYPE_CLOCK,this.yearMonth);
     }


    @Transient
    public String getImgDisMin() {
        return BcwCommonService.getImgDisMin(getImgDis());
    }

    @Transient
    public String getImgGsDis(){

        return BcwCommonService.getImgDis(this.imgGs,BcwConstant.TYPE_CLOCK,this.yearMonth);
    }

}
