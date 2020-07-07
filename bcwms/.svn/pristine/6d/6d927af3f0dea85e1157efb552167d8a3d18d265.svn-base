package com.bcwms.entity;



import com.keepjoy.core.util.DateUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
public class TblDanceCommonAttrGroup extends TblDanceCommonAttrLocation implements Serializable {



    @Column(name = "name", nullable = true, length = 30)
    public String name;


    @Column(name = "establishDate", nullable = true,columnDefinition = "DATE")
    public Date establishDate;


    @Column(name = "founder", nullable = true, length = 30)
    public String founder;//中文


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    @Transient
    public String getEstablishDateDis(){
        return DateUtil.dateToStringByDateFormat(this.establishDate,DateUtil.DATEFORMAT_YYYY_MM_DD);
    }




}
