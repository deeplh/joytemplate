package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户在crew团训上课的流水表
 */
@Entity
public class TblDanceUserCrewLessonDetail extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "crewTermLessonDetailId")
    public Integer crewTermLessonDetailId;

    @Column(name = "crewTermId")
    public Integer crewTermId;//冗余

    @Column(name = "crewId")
    private Integer crewId;//冗余

    @Column(name = "userId", nullable = false)
    private Integer userId;//用户id

    @Column(name = "signDateTime")
    private Date signDateTime;//上课签到时间


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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getSignDateTime() {
        return signDateTime;
    }

    public void setSignDateTime(Date signDateTime) {
        this.signDateTime = signDateTime;
    }

    public Integer getCrewTermLessonDetailId() {
        return crewTermLessonDetailId;
    }

    public void setCrewTermLessonDetailId(Integer crewTermLessonDetailId) {
        this.crewTermLessonDetailId = crewTermLessonDetailId;
    }
}
