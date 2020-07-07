package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


/**
 * 学生在studio办的卡记录
 * 每次续卡或者充卡,就把前一次的卡记录设置为已经过期
 * 时卡和次卡需要分开,所以一个人最多有两条可用(状态为2)的记录
 */
@Entity
public class TblDanceUserStudioCardDetail extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "userId")
    public Integer userId;

    @Column(name = "studioId")
    private Integer studioId;

    @Column(name = "studioStoreId")
    private Integer studioStoreId;

    @Column(name = "studioCardId")
    public Integer studioCardId;

    @Column(name = "studioCardType",columnDefinition = "CHAR",length = 1)
    public String studioCardType;//冗余字段-卡类型



    @Column(name = "beginDate",columnDefinition ="DATE" )
    public Date beginDate;//开卡日期

    @Column(name = "endDate",columnDefinition ="DATE" )
    public Date endDate;//到期日期

    @Column(name = "remaind")
    public Integer remaind;//剩余次数-当冲新卡的时候,剩余次数不变

    @Column(name = "total")
    public Integer total;//总次数-当冲新卡的时候,把旧卡的总次数变为0,并加到新卡的可用次数里面,其他时候与剩余次数同时运算


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public Integer getStudioStoreId() {
        return studioStoreId;
    }

    public void setStudioStoreId(Integer studioStoreId) {
        this.studioStoreId = studioStoreId;
    }

    public Integer getStudioCardId() {
        return studioCardId;
    }

    public void setStudioCardId(Integer studioCardId) {
        this.studioCardId = studioCardId;
    }

    public String getStudioCardType() {
        return studioCardType;
    }

    public void setStudioCardType(String studioCardType) {
        this.studioCardType = studioCardType;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getRemaind() {
        return remaind;
    }

    public void setRemaind(Integer remaind) {
        this.remaind = remaind;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
