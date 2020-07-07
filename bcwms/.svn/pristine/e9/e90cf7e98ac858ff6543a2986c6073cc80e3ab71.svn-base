package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 舞团每期参与考团的用户的得分信息
 *
 */
@Entity
public class TblDanceCrewTermUserDetailScore extends TblDanceCommonAttr implements Serializable {


    private static final long serialVersionUID = 1L;

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "crewTermId")
    public Integer crewTermId;//冗余

    @Column(name = "crewId")
    public Integer crewId;//冗余

    @Column(name = "teacherId")
    public Integer teacherId;

    @Column(name = "crewTermUserDetailId")
    public Integer crewTermUserDetailId;

    @Column(name = "userId")
    public Integer userId;//冗余

    @Column(name = "eventId")
    public Integer eventId;

    //每个老师打的分数
    @Column(name = "score")
    public Integer score;//总分

    @Column(name = "routineScore")
    public Integer routineScore;

    @Column(name = "foundationScore")
    public Integer foundationScore;

    @Column(name = "soloScore")
    public Integer soloScore;

    @Column(name = "otherScore")
    public Integer otherScore;

    @Column(name = "remark")
    public String remark;//备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCrewTermId() {
        return crewTermId;
    }

    public void setCrewTermId(Integer crewTermId) {
        this.crewTermId = crewTermId;
    }

    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }




    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCrewTermUserDetailId() {
        return crewTermUserDetailId;
    }

    public void setCrewTermUserDetailId(Integer crewTermUserDetailId) {
        this.crewTermUserDetailId = crewTermUserDetailId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getRoutineScore() {
        return routineScore;
    }

    public void setRoutineScore(Integer routineScore) {
        this.routineScore = routineScore;
    }

    public Integer getFoundationScore() {
        return foundationScore;
    }

    public void setFoundationScore(Integer foundationScore) {
        this.foundationScore = foundationScore;
    }

    public Integer getSoloScore() {
        return soloScore;
    }

    public void setSoloScore(Integer soloScore) {
        this.soloScore = soloScore;
    }

    public Integer getOtherScore() {
        return otherScore;
    }

    public void setOtherScore(Integer otherScore) {
        this.otherScore = otherScore;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
