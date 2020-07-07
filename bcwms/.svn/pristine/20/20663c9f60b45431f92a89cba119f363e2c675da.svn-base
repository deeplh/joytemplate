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
 * 用户在手机端申请加入过的studio-建立类似朋友的关系
 * 主要是为了过滤一层,方便管理端的查询
 */
@Entity
public class TblDanceUserStudioRelation  extends TblDanceCommonAttrRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer studioId;


    public TblDanceUserStudioRelation(){

    }



    @Basic
    @Column(name = "studioId", nullable = true)
    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }



    @Transient
    private String studioName;
    @Transient
    private String studioImg;
    @Transient
    public String getStudioName() {
        return studioName;
    }
    @Transient
    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }
    @Transient
    public String getStudioImg() {
        return BcwCommonService.getImgDis(this.studioImg,BcwConstant.TYPE_STUDIO,null);
    }
    @Transient
    public void setStudioImg(String studioImg) {
        this.studioImg = studioImg;
    }

    //BcwCommonService.java-setDanceUser
    public TblDanceUserStudioRelation(Integer id, Integer studioId, String studioName, String studioImg) {
        this.id = id;
        this.studioId = studioId;
        this.studioName = studioName;
        this.studioImg = studioImg;
    }





    //SearchStudioApply
    public TblDanceUserStudioRelation(Integer id, Integer studioId, String studioName, String studioImg,
                                    String aka, String avatarUrl,Integer userId,Date createDateTime) {
        this.id = id;
        this.studioId = studioId;
        this.studioName = studioName;
        this.studioImg = studioImg;
        this.aka=aka;
        this.avatarUrl=avatarUrl;
        this.userId=userId;
        this.createDateTime=createDateTime;
    }

    //SearchStudioApplyMe
    public TblDanceUserStudioRelation(Integer id, Integer studioId, String studioName, String studioImg,
                                    Integer userId,Date createDateTime,String status) {
        this.id = id;
        this.studioId = studioId;
        this.studioName = studioName;
        this.studioImg = studioImg;
        this.userId=userId;
        this.createDateTime=createDateTime;
        this.status=status;
    }

    //GetStudio
    public TblDanceUserStudioRelation(Integer userId,String aka, String avatarUrl,String status){
        this.userId=userId;
        this.aka=aka;
        this.avatarUrl=avatarUrl;
        this.status=status;
    }


    @Transient
    public String getNameDis() {
        return this.studioName;
    }

    @Transient
    public String getImgDis() {
        return getStudioImg();
    }

    @Transient
    public String getImgDisMin() {
        return BcwCommonService.getImgDisMin(this.studioImg,BcwConstant.TYPE_STUDIO,null);
    }
}
