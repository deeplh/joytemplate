package com.bcwms.entity;

import com.keepjoy.core.util.DateUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 舞团每次考团记录
 *
 */
@Entity
public class TblDanceCrewTerm extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "crewId")
    public Integer crewId;

    @Column(name = "eventId")
    public Integer eventId;//入团正考的活动id

    @Column(name = "examId")
    public Integer examId;//考试id

    @Column(name = "term")
    public Integer term;//期号

    @Column(name = "examDate",columnDefinition = "DATE")
    public Date examDate;//本期考团日期

    @Column(name = "examBeginTime")
    public Date examBeginTime;//本期考团开始日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
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

    //    @Column(name = "makeUpEventId")
//    public Integer makeUpEventId;//补考的活动id
//
//    @Column(name = "midtermEventId")
//    public Integer midtermEventId;//期中考试活动id
//
//    @Column(name = "finalExamEventId")
//    public Integer finalExamEventId;//期末考试活动id
//    public Integer getMakeUpEventId() {
//        return makeUpEventId;
//    }
//
//    public void setMakeUpEventId(Integer makeUpEventId) {
//        this.makeUpEventId = makeUpEventId;
//    }

//    public Integer getMidtermEventId() {
//        return midtermEventId;
//    }
//
//    public void setMidtermEventId(Integer midtermEventId) {
//        this.midtermEventId = midtermEventId;
//    }
//
//    public Integer getFinalExamEventId() {
//        return finalExamEventId;
//    }
//
//    public void setFinalExamEventId(Integer finalExamEventId) {
//        this.finalExamEventId = finalExamEventId;
//    }


    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    @Transient
    public String getExamDateDis(){
        return DateUtil.dateToStringByDateFormat(this.examDate,DateUtil.DATEFORMAT_YYYY_MM_DD);
    }
}
