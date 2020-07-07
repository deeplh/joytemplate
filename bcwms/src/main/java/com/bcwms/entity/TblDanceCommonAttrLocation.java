package com.bcwms.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@MappedSuperclass
public class TblDanceCommonAttrLocation extends TblDanceCommonAttr implements Serializable {

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;



    @Column(name = "info", nullable = true, length = 300)
    public String info;

    @Column(name = "img",length = 120)
    public String img;//封面


    @Column(name = "approveUserId")
    public Integer approveUserId;

    @Column(name = "approveDateTime")
    public Date approveDateTime;

    @Column(name = "city",length = 8)
    public String city;

    @Column(name = "district",length = 8)
    public String  district;

    @Column(name = "address", nullable = true, length = 50)
    public String address;

    @Column(name = "latitude",length = 20)
    public String latitude;//纬度

    @Column(name = "longitude",length = 20)
    public String longitude;//经度


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

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    @Transient
    public String nickName;

    @Transient
    public String avatarUrl;

    @Transient
    public String aka;


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
    public Boolean isMine;//是否是我发布的

    @Transient
    public Boolean getIsMine() {
        return isMine;
    }

    @Transient
    public void setIsMine(Boolean mine) {
        isMine = mine;
    }

    @Transient
    public String statusColor;

    @Transient
    public String getStatusColor() {
        return statusColor;
    }

    @Transient
    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }

    @Transient
    public Boolean isMeApply;//是否自己已经申请过

    @Transient
    public Boolean getIsMeApply() {
        return isMeApply;
    }

    @Transient
    public void setIsMeApply(Boolean meApply) {
        isMeApply = meApply;
    }
}
