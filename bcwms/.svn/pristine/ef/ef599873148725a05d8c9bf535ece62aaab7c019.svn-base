package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 活动结果表
 */
@Entity
public class TblDanceUserEventResult extends TblDanceCommonAttr implements Serializable {

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

    @Column(name = "userConfirm", length = 1,columnDefinition = "CHAR")
    private String userConfirm;//活动结束后用户的确认-部分类型活动

    @Column(name = "userConfirmDateTime")
    public Date userConfirmDateTime;

    @Column(name = "founderConfirm", length = 1,columnDefinition = "CHAR")
    private String founderConfirm;//活动结束后发起方的确认-部分类型活动

    @Column(name = "founderConfirmDateTime")
    public Date founderConfirmDateTime;

    @Column(name = "userComment",length = 100)
    private String userComment;//用户评论

    @Column(name = "userScore")
    private Integer userScore;//用户打分

    @Column(name = "userCommentDateTime")
    private Date userCommentDateTime;

    @Column(name = "founderComment",length = 100)
    private String founderComment;//发起方评论

    @Column(name = "founderScore")
    private Integer founderScore;//打分

    @Column(name = "founderCommentDateTime")
    private Date founderCommentDateTime;


    @Column(name = "prizeId")
    private Integer prizeId;//活动结束后的奖项id

    @Column(name = "prizeInfo",length = 50)
    private String prizeInfo;//奖项特殊描述




    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Integer prizeId) {
        this.prizeId = prizeId;
    }

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

    public String getPrizeInfo() {
        return prizeInfo;
    }

    public void setPrizeInfo(String prizeInfo) {
        this.prizeInfo = prizeInfo;
    }

    public String getUserConfirm() {
        return userConfirm;
    }

    public void setUserConfirm(String userConfirm) {
        this.userConfirm = userConfirm;
    }

    public String getFounderConfirm() {
        return founderConfirm;
    }

    public void setFounderConfirm(String founderConfirm) {
        this.founderConfirm = founderConfirm;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getFounderComment() {
        return founderComment;
    }

    public void setFounderComment(String founderComment) {
        this.founderComment = founderComment;
    }


    public Date getUserConfirmDateTime() {
        return userConfirmDateTime;
    }

    public void setUserConfirmDateTime(Date userConfirmDateTime) {
        this.userConfirmDateTime = userConfirmDateTime;
    }

    public Date getFounderConfirmDateTime() {
        return founderConfirmDateTime;
    }

    public void setFounderConfirmDateTime(Date founderConfirmDateTime) {
        this.founderConfirmDateTime = founderConfirmDateTime;
    }

    public Date getUserCommentDateTime() {
        return userCommentDateTime;
    }

    public void setUserCommentDateTime(Date userCommentDateTime) {
        this.userCommentDateTime = userCommentDateTime;
    }

    public Date getFounderCommentDateTime() {
        return founderCommentDateTime;
    }

    public void setFounderCommentDateTime(Date founderCommentDateTime) {
        this.founderCommentDateTime = founderCommentDateTime;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public Integer getFounderScore() {
        return founderScore;
    }

    public void setFounderScore(Integer founderScore) {
        this.founderScore = founderScore;
    }



    @Transient
    private String trueName;
    @Transient
    private String aka;
    @Transient
    private  String avatarUrl;
    @Transient
    public String getTrueName() {
        return trueName;
    }
    @Transient
    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }
    @Transient
    public String getAka() {
        return aka;
    }
    @Transient
    public void setAka(String aka) {
        this.aka = aka;
    }
    @Transient
    public String getAvatarUrl() {
        return avatarUrl;
    }
    @Transient
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
