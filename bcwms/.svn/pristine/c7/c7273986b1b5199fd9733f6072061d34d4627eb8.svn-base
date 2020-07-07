package com.bcwms.entity;

import com.bcwms.BcwConstant;
import com.bcwms.service.BcwCommonService;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class TblDanceStyle extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String img;

    private String info;
    private String initial;

   public  TblDanceStyle(){

   }

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "img", nullable = true, length = 100)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "createUserId", nullable = true)
    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column(name = "updateUserId", nullable = true)
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Basic
    @Column(name = "createDateTime", nullable = true)
    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Basic
    @Column(name = "updateDateTime", nullable = true)
    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @Basic
    @Column(name = "info", nullable = true, length = 300)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "initial", nullable = true, length = 1,columnDefinition = "CHAR")
    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    @Transient
    public String getImgDis(){
        return BcwCommonService.getImgDis(this.img,BcwConstant.TYPE_STYLE,null);
    }


    @Transient
    public String getImgDisMin() {
        return BcwCommonService.getImgDisMin(getImgDis());
    }

    @Transient
    private Boolean isMine;
    @Transient
    public Boolean getMine() {
        return isMine;
    }
    @Transient
    public void setMine(Boolean mine) {
        isMine = mine;
    }
//    @Transient
//    private Integer userStyleRelationId;
//    @Transient
//    public Integer getUserStyleRelationId() {
//        return userStyleRelationId;
//    }
//    @Transient
//    public void setUserStyleRelationId(Integer userStyleRelationId) {
//        this.userStyleRelationId = userStyleRelationId;
//    }
//
//
//
//    public TblDanceStyle(Integer id, String name, String img,Integer userStyleRelationId) {
//        this.id = id;
//        this.name = name;
//        this.img = img;
//        this.userStyleRelationId=userStyleRelationId;
//    }
}


