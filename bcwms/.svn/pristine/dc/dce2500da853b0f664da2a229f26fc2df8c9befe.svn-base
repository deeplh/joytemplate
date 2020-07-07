package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 工作室对应的门店的课程流水
 * 此表只做增加不做修改与硬删
 * 状态见BcwmsConstant,默认为2表示通过
 * 管理员排课的时候,可以按一个周期去排,然后后台固定按这个周期去循环即可
 */
@Entity
public class TblDanceStudioStoreLessonDetail extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;


    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "name", nullable = true, length = 30)
    public String name;//课程名称

    @Column(name = "info", nullable = true, length = 300)
    public String info;//课程简介

    @Column(name = "img",length = 120)
    public String img;

    @Column(name = "status", nullable = true,length = 1,columnDefinition = "CHAR")
    public String status;

    @Column(name = "studioId")
    private Integer studioId;//工作室id-冗余字段

    @Column(name = "studioStoreId")
    private Integer studioStoreId;//工作室门店id

    @Column(name = "styleId")
    private Integer styleId;//舞种id

    @Column(name = "teacherId")
    private Integer teacherId;//老师的用户id

    @Column(name = "srcTeacherId")
    private Integer srcTeacherId;//原始老师的用户id-如果代课或者换课,这个字段会填写

    @Column(name = "lessonDate",length = 8)
    private String lessonDate;//上课日期,ex:20180909

    @Column(name = "lessonMonth",length = 2)
    private String lessonMonth;//上课月份,ex:9


    @Column(name = "beginDateTime")
    private Date beginDateTime;//开始时间

    @Column(name = "endDateTime")
    private Date endDateTime;//结束时间

    @Column(name = "signDateTime")
    private Date signDateTime;//上课签到时间



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public Integer getStudioStoreId() {
        return studioStoreId;
    }

    public void setStudioStoreId(Integer studioStoreId) {
        this.studioStoreId = studioStoreId;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
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

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public Date getSignDateTime() {
        return signDateTime;
    }

    public void setSignDateTime(Date signDateTime) {
        this.signDateTime = signDateTime;
    }

    public Integer getSrcTeacherId() {
        return srcTeacherId;
    }

    public void setSrcTeacherId(Integer srcTeacherId) {
        this.srcTeacherId = srcTeacherId;
    }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public String getLessonMonth() {
        return lessonMonth;
    }

    public void setLessonMonth(String lessonMonth) {
        this.lessonMonth = lessonMonth;
    }
}
