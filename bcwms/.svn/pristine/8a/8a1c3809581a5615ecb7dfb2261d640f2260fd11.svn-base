package com.keepjoy.security.entity;


import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="BasMenu")
public class BasMenu  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer parentId;
	private String menuName;
	private String menuUrl;
	private String menuClass;
	private Date createTime;
	private Integer createUserId;



	@GenericGenerator(name = "generator", strategy = "native")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="parentId",columnDefinition="INTEGER")
	public Integer getParentId() {
		return parentId;
	}


	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name="menuName", length=40)
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	@Column(name="menuClass", length=50)
	public String getMenuClass() {
		return menuClass;
	}
	
	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
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

	@Column(name="menuUrl", length=300)
	public String getMenuUrl() {
		return menuUrl;
	}


	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}


	






}