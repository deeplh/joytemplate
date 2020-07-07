package com.keepjoy.security.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="BasRoleMenuRelation")
public class BasRoleMenuRelation  implements java.io.Serializable {
	// Fields    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer basRoleId;
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



	/**
	 * @return the createTime
	 */
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




	@Column(name="basRoleId",columnDefinition="INTEGER")
	public Integer getBasRoleId() {
		return basRoleId;
	}

	public void setBasRoleId(Integer basRoleId) {
		this.basRoleId = basRoleId;
	}

	@Column(name="basMenuId",columnDefinition="INTEGER")
	public Integer getBasMenuId() {
		return basMenuId;
	}

	public void setBasMenuId(Integer basMenuId) {
		this.basMenuId = basMenuId;
	}



}