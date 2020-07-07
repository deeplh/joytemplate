package com.keepjoy.security.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * BasMenuResourceRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="BasMenuResourceRelation")

public class BasMenuResourceRelation  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer basResourceId;
	private Integer basMenuId;
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

	@Column(name="basResourceId",columnDefinition="INTEGER")
	public Integer getBasResourceId() {
		return basResourceId;
	}

	public void setBasResourceId(Integer basResourceId) {
		this.basResourceId = basResourceId;
	}

	@Column(name="basMenuId",columnDefinition="INTEGER")
	public Integer getBasMenuId() {
		return basMenuId;
	}

	public void setBasMenuId(Integer basMenuId) {
		this.basMenuId = basMenuId;
	}






}