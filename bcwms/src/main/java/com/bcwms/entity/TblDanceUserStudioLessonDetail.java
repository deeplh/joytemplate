package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户在studio上课的流水表
 */
@Entity
public class TblDanceUserStudioLessonDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "studioStoreLessonDetailId", nullable = false)
    private Integer studioStoreLessonDetailId;//门店的课程流水id

    @Column(name = "studioId")
    private Integer studioId;//冗余字段

    @Column(name = "studioStoreId")
    private Integer studioStoreId;//冗余字段

    @Column(name = "userStudioCardDetailId", nullable = false)
    private Integer userStudioCardDetailId;//用的是哪张卡上课扣费的

    @Column(name = "userId", nullable = false)
    private Integer userId;//用户id

    @Column(name = "signDateTime", nullable = false)
    private Date signDateTime;//上课签到时间

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudioStoreLessonDetailId() {
        return studioStoreLessonDetailId;
    }

    public void setStudioStoreLessonDetailId(Integer studioStoreLessonDetailId) {
        this.studioStoreLessonDetailId = studioStoreLessonDetailId;
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

    public Integer getUserStudioCardDetailId() {
        return userStudioCardDetailId;
    }

    public void setUserStudioCardDetailId(Integer userStudioCardDetailId) {
        this.userStudioCardDetailId = userStudioCardDetailId;
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
}
