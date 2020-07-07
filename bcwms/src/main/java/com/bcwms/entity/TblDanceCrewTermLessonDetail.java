package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 每期团训课程流水
 *
 */
@Entity
public class TblDanceCrewTermLessonDetail  extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @NotNull(message = "crewTermId不能为空！")
    @Min(value = 1,message = "crewTermId不能小于1")
    @Column(name = "crewTermId")
    public Integer crewTermId;

    @NotNull(message = "crewId不能为空！")
    @Min(value = 1,message = "crewId不能小于1")
    @Column(name = "crewId")
    public Integer crewId;//冗余

    @NotNull(message = "teacherId不能为空！")
    @Min(value = 1,message = "teacherId不能小于1")
    @Column(name = "teacherId")
    public Integer teacherId;//团训老师id

    @NotNull(message = "trainLessonDate不能为空！")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "trainLessonDate")
    public Date trainLessonDate;//团训日期

    @NotNull(message = "beginDateTime不能为空！")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "beginDateTime")
    public Date beginDateTime;//实际开始时间

    @NotNull(message = "endDateTime不能为空！")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "endDateTime")
    public Date endDateTime;//实际结束时间

    @Column(name = "signDateTime")
    private Date signDateTime;//上课签到时间


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


    public Integer getCrewTermId() {
        return crewTermId;
    }

    public void setCrewTermId(Integer crewTermId) {
        this.crewTermId = crewTermId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Date getBeginDateTime() {
        return beginDateTime;
    }

    public void setBeginDateTime(Date beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Date getTrainLessonDate() {
        return trainLessonDate;
    }

    public void setTrainLessonDate(Date trainLessonDate) {
        this.trainLessonDate = trainLessonDate;
    }

    public Date getSignDateTime() {
        return signDateTime;
    }

    public void setSignDateTime(Date signDateTime) {
        this.signDateTime = signDateTime;
    }
    @Transient
    private List<Integer> studentIds;
    @Transient
    public List<Integer> getStudentIds() {
        return studentIds;
    }
    @Transient
    public void setStudentIds(List<Integer> studentIds) {
        this.studentIds = studentIds;
    }
    @Transient
    private List<Map<String,Object>> lessionStudentList;
    @Transient
    public List<Map<String, Object>> getLessionStudentList() {
        return lessionStudentList;
    }
    @Transient
    public void setLessionStudentList(List<Map<String, Object>> lessionStudentList) {
        this.lessionStudentList = lessionStudentList;
    }
}
