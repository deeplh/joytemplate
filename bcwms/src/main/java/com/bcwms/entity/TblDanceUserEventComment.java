package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户活动评论表
 */
@Entity
public class TblDanceUserEventComment extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String USERTYPE_1_USER="1";//参演用户
    public static final String USERTYPE_2_FOUNDER="2";//活动举办方


    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "eventId", nullable = false)
    private Integer eventId;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "comment",length = 100)
    private String comment;//用户评论

    @Column(name = "score")
    private Integer score;//用户打分

    @Column(name = "parentId")
    private Integer parentId;

    @Column(name = "userType",length = 1,columnDefinition = "CHAR")
    private String userType;//是商户的评论还是参演者;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
