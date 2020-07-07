package com.bcwms.entity;

import com.keepjoy.core.module.datadict.DataDictFactory;
import com.keepjoy.core.util.DateUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "createUserId")
    public Integer createUserId;

    @Column(name = "createDateTime")
    public Date createDateTime;

    @Column(name = "updateUserId")
    public Integer updateUserId;

    @Column(name = "updateDateTime")
    public Date updateDateTime;

    @Column(name = "status", nullable = true,length = 1,columnDefinition = "CHAR")
    public String status;

    @Column(name = "contractAddress",length = 50)
    public String contractAddress;//合约地址

    @Column(name = "settleDateTime")
    public Date settleDateTime;//结算时间-上链时间,上链后数据无法修改即为结算


    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public Date getSettleDateTime() {
        return settleDateTime;
    }

    public void setSettleDateTime(Date settleDateTime) {
        this.settleDateTime = settleDateTime;
    }

    @Transient
    public String getCreateDateTimeDis(){
        return DateUtil.dateToStringByDateFormat(this.createDateTime,DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS);
    }
    @Transient
    public String getStatusDis(){
        return DataDictFactory.getDataDictTypeKeyValue("status",this.status);
    }
    @Transient
    public String getSettleDateTimeDis(){
        return DateUtil.dateToStringByDateFormat(this.settleDateTime,DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS);
    }

}
