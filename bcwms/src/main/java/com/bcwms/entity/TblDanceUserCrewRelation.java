package com.bcwms.entity;

import com.bcwms.BcwConstant;
import com.bcwms.service.BcwCommonService;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;



/***
 * 用户在手机端申请加入过的crew-建立类似朋友的关系
 * 主要是为了过滤一层,方便管理端的查询
 */
@Entity
public class TblDanceUserCrewRelation extends TblDanceCommonAttrRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer crewId;

    public TblDanceUserCrewRelation(){

    }


    @Basic
    @Column(name = "crewId", nullable = true)
    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }


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
    @Transient
    public String getCrewImg() {
        return BcwCommonService.getImgDis(this.crewImg,BcwConstant.TYPE_CREW,null);
    }
    @Transient
    public void setCrewImg(String crewImg) {
        this.crewImg = crewImg;
    }

    //BcwCommonService.java-setDanceUser
    public TblDanceUserCrewRelation(Integer id, Integer crewId, String crewName, String crewImg) {
        this.id = id;
        this.crewId = crewId;
        this.crewName = crewName;
        this.crewImg = crewImg;
    }




    //SearchCrewApply
    public TblDanceUserCrewRelation(Integer id, Integer crewId, String crewName, String crewImg,
                                    String aka, String avatarUrl,Integer userId,Date createDateTime) {
        this.id = id;
        this.crewId = crewId;
        this.crewName = crewName;
        this.crewImg = crewImg;
        this.aka=aka;
        this.avatarUrl=avatarUrl;
        this.userId=userId;
        this.createDateTime=createDateTime;
    }

    //SearchCrewApplyMe
    public TblDanceUserCrewRelation(Integer id, Integer crewId, String crewName, String crewImg,
                                    Integer userId,Date createDateTime,String status) {
        this.id = id;
        this.crewId = crewId;
        this.crewName = crewName;
        this.crewImg = crewImg;
        this.userId=userId;
        this.createDateTime=createDateTime;
        this.status=status;
    }

    //GetCrew
    public TblDanceUserCrewRelation(Integer userId,String aka, String avatarUrl,String status){
        this.userId=userId;
        this.aka=aka;
        this.avatarUrl=avatarUrl;
        this.status=status;
    }


    @Transient
    public String getNameDis() {
        return this.crewName;
    }

    @Transient
    public String getImgDis() {
        return getCrewImg();
    }

    @Transient
    public String getImgDisMin() {
        return BcwCommonService.getImgDisMin(this.crewImg,BcwConstant.TYPE_CREW,null);
    }
}
