package com.keepjoy.security.bean.user;

public class UserBasRoleMenuResourceRelationBean implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer basRoleId;
	private Integer basResourceId;
	private Integer basMenuId;
	private String roleKey;
	private String menuName;
	private String menuUrl;
	private String parentId;
	private String resourceUrl;
	private String resourceName;
	
	public Integer getBasRoleId() {
		return basRoleId;
	}
	public void setBasRoleId(Integer basRoleId) {
		this.basRoleId = basRoleId;
	}
	public Integer getBasResourceId() {
		return basResourceId;
	}
	public void setBasResourceId(Integer basResourceId) {
		this.basResourceId = basResourceId;
	}
	public Integer getBasMenuId() {
		return basMenuId;
	}
	public void setBasMenuId(Integer basMenuId) {
		this.basMenuId = basMenuId;
	}
	public String getRoleKey() {
		return roleKey;
	}
	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	
	
}
