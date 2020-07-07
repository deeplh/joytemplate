package com.bcwms.entity;

import com.keepjoy.core.util.DateUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 考试表
 */
@Entity
public class TblDanceExam extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "eventId")
    public Integer eventId;//对应的活动id

    @Column(name = "examDate",columnDefinition = "DATE")
    public Date examDate;//考试日期

    @Column(name = "examBeginTime")
    public Date examBeginTime;//考试时间

    @Column(name = "examType",length = 10)
    public String examType;//考试类型

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }


    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Date getExamBeginTime() {
        return examBeginTime;
    }

    public void setExamBeginTime(Date examBeginTime) {
        this.examBeginTime = examBeginTime;
    }


    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    @Transient
    public String getExamDateDis(){
        return DateUtil.dateToStringByDateFormat(this.examDate,DateUtil.DATEFORMAT_YYYY_MM_DD);
    }
}
