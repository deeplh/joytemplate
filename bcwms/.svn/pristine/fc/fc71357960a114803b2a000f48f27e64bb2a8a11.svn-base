package com.keepjoy.weixin.entity;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


/**
 * TblUser entity. @author MyEclipse Persistence Tools
 * 小程序平台用户表
 */
@Entity
public class WeixinSmallPlatformUser implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String openid;
	private Date createTime;
	
	
	

	@Id
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	@Column(name="openid", length=64)
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}


	@Column(name="createTime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	






}