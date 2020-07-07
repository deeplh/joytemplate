package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 *  积分明细表
 */
@Entity
public class TblDanceUserPoint extends TblDanceCommonAttr implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String POINTTYPE_1_STUDIO_LESSON="1";//课时积分
    public static final String POINTTYPE_2_CLOCK="2";//打卡产生的积分,1次1分,连续签到1次2分

    private Integer id;
    private Integer userId;
    private Integer point;//积分

    private String pointType;//积分类型

    private String sourceType;//来源类型
    private Integer sourceId;//来源的业务id


    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "userId",nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "point")
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }


    @Column(name = "pointType",length = 1,columnDefinition = "CHAR")
    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    @Basic
    @Column(name = "sourceType",length = 10)
    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    @Basic
    @Column(name = "sourceId")
    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }
}
