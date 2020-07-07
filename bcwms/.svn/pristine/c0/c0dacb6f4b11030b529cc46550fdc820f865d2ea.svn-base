package com.keepjoy.security.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * BasUsrRoleRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="BasUserRoleRelation")

public class BasUserRoleRelation  implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userId;
	private Integer basRoleId;
	private Integer serviceId;
	private String serviceType;
	private Date createTime;
	private Integer createUserId;


	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="userId",columnDefinition="INTEGER")
	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name="basRoleId",columnDefinition="INTEGER")
	public Integer getBasRoleId() {
		return basRoleId;
	}
	public void setBasRoleId(Integer basRoleId) {
		this.basRoleId = basRoleId;
	}

	@Column(name="createTime",columnDefinition="DATETIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name="createUserId",columnDefinition="INTEGER")
	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name="serviceId")
	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	@Column(name="serviceType",length=10)
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
}