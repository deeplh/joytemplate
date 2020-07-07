package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


/**
 * 针对用户在studio上课的流水的评论-方便后期开发与分开上链
 */
@Entity
public class TblDanceUserStudioLessonDetailComment extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "userStudioLessonDetailId", nullable = false)
    private Integer userStudioLessonDetailId;//用户上的课程流水id

    @Column(name = "userId")
    private Integer userId;//冗余字段

    @Column(name = "studioId")
    private Integer studioId;//冗余字段

    @Column(name = "studioStoreId")
    private Integer studioStoreId;//冗余字段

    @Column(name = "score")
    private Integer score;//评分

    @Column(name = "info",length = 200)
    private String info;//评论

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserStudioLessonDetailId() {
        return userStudioLessonDetailId;
    }

    public void setUserStudioLessonDetailId(Integer userStudioLessonDetailId) {
        this.userStudioLessonDetailId = userStudioLessonDetailId;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
