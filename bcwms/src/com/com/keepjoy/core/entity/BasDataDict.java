package com.keepjoy.core.entity;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * JtSequence entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="BasDataDict")

public class BasDataDict implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String dataDictType;
	private String dataDictKey;
	private String dataDictValue;
	private Date createTime;
	private Integer parentId;
	private String shortName;//简称
	
	@Id 
	@Column(name="id", nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="dataDictType", length=100)
	public String getDataDictType() {
		return dataDictType;
	}
	public void setDataDictType(String dataDictType) {
		this.dataDictType = dataDictType;
	}
	
	@Column(name="dataDictValue", length=500)
	public String getDataDictValue() {
		return dataDictValue;
	}
	public void setDataDictValue(String dataDictValue) {
		this.dataDictValue = dataDictValue;
	}
	
	@Column(name="createTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="dataDictKey", length=100)
	public String getDataDictKey() {
		return dataDictKey;
	}
	public void setDataDictKey(String dataDictKey) {
		this.dataDictKey = dataDictKey;
	}
	
	@Column(name="parentId")
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	
	@Column(name="shortName", length=20)
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	







}