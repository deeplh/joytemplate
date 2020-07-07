package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 上链数据
 *
 */

@MappedSuperclass
public class TblDanceCommonAttrRelation extends TblDanceCommonAttr implements Serializable {

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "userId")
    public Integer userId;


    @Column(name = "approveUserId")
    public Integer approveUserId;

    @Column(name = "approveDateTime")
    public Date approveDateTime;



    public Integer getApproveUserId() {
        return approveUserId;
    }

    public void setApproveUserId(Integer approveUserId) {
        this.approveUserId = approveUserId;
    }

    public Date getApproveDateTime() {
        return approveDateTime;
    }

    public void setApproveDateTime(Date approveDateTime) {
        this.approveDateTime = approveDateTime;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Transient
    public String avatarUrl;

    @Transient
    public String aka;

    @Transient
    public String nickName;

    @Transient
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Transient
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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
    public String getNickName() {
        return nickName;
    }

    @Transient
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }



    @Transient
    public List<TblDanceUser> members;

    @Transient
    public List<TblDanceUser> getMembers() {
        return members;
    }
    @Transient
    public void setMembers(List<TblDanceUser> members) {
        this.members = members;
    }

    @Transient
    public Boolean isMine;

    @Transient
    public Boolean getMine() {
        return isMine;
    }

    @Transient
    public void setMine(Boolean mine) {
        isMine = mine;
    }

}
