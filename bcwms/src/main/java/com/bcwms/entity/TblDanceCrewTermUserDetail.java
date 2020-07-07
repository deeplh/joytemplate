package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 舞团每期参与考团的用户信息
 * status=0 表示签到了,但是老师还没有打分,此时并不代表参加过考试
 * status=2 表示参加考试了,并且老师已经打分了,可以参与结算,需要前台选中
 * status=3 表示前面期的人员
 * status=4 表示已经结算了,本期考团成功,进入crew了
 */
@Entity
public class TblDanceCrewTermUserDetail extends TblDanceCommonAttr implements Serializable {

    public static final String CREWUSERTYPE_1_TEACHER="1";//老师
    public static final String CREWUSERTYPE_2_MEMBER="2";//团员

    private static final long serialVersionUID = 1L;

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "crewTermId")
    public Integer crewTermId;

    @Column(name = "crewId")
    public Integer crewId;//冗余

    @Column(name = "userId")
    public Integer userId;

    @Column(name = "eventId")
    public Integer eventId;

    @Column(name = "crewUserType",columnDefinition = "CHAR",length = 2)
    public String crewUserType;//每期团员的类型,ex:带训老师,团长,副团

    @Column(name = "score")
    public Float score;//考团最终分数


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

    public String getCrewUserType() {
        return crewUserType;
    }

    public void setCrewUserType(String crewUserType) {
        this.crewUserType = crewUserType;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public TblDanceCrewTermUserDetail(){

    }
    @Transient
    private String aka;

    @Transient
    private String crewName;
    @Transient
    private String crewImg;

    @Transient
    public String getCrewName() {
        return crewName;
    }
    @Transient
    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    //setDanceUserMyCrews
    public TblDanceCrewTermUserDetail(Integer id,Integer crewId, String crewUserType,
                                      String crewName,String crewImg) {
        this.id=id;
        this.crewId = crewId;
        this.crewUserType = crewUserType;
        this.crewName = crewName;
        this.crewImg=crewImg;
    }

    @Transient
    public String getCrewUserTypeDis() {
        if(CREWUSERTYPE_1_TEACHER.equals(this.crewUserType)){
            return "老师";
        }else if(CREWUSERTYPE_2_MEMBER.equals(this.crewUserType)){
            return "学生";
        }
        return crewUserType;

    }
    @Transient
    public String getCrewImg() {
        return crewImg;
    }
    @Transient
    public void setCrewImg(String crewImg) {
        this.crewImg = crewImg;
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
    public Integer getScoreInt() {
        if(null!=score){
            return score.intValue()*100;
        }else return null;

    }
}
