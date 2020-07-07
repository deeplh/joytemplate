package com.keepjoy.security.core;

import java.util.*;

import com.keepjoy.security.SecurityConstant;
import com.keepjoy.security.bean.user.MenuBean;
import com.keepjoy.security.bean.user.RoleBean;
import org.apache.commons.lang.ArrayUtils;



public class SecurityUser implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String userName; //用户登录名称
	private String password;//数据库中的密码
	private Integer userId;//用户id
	private Integer groupId;//组id,ex:公司,部门等等,只要是分组的都可以使用
	private String userNo;//用户编号
	private String token;//令牌
	private Date timeoutDateTime;//超时时间

	private Map<String,RoleBean> roles;//角色
	private Map<String,MenuBean> menus;//能够访问的菜单和菜单下的资源
	
	private Map<String,String> subSecurityUsers=new HashMap<String, String>();//redis模式的下的子业务系统的user,需要和session-custom-key共用

	
	
	
	
	public SecurityUser() {
	}

	public SecurityUser(Integer userId, String userName, Integer groupId, String token) {
		this.userId = userId;
		this.userName = userName;
		this.groupId=groupId;
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}


	public Map<String, RoleBean> getRoles() {
		return roles;
	}

	public void setRoles(Map<String, RoleBean> roles) {
		this.roles = roles;
	}

	public Map<String, MenuBean> getMenus() {
		return menus;
	}

	public void setMenus(Map<String, MenuBean> menus) {
		this.menus = menus;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean hasAuthority(String authority){
		if(authority==null)return false;
		for(String auth:roles.keySet()){
			if(authority.equals(auth)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isSuperAdmin(){
		if(roles==null)return false;
		if(roles.size()>=1 && roles.containsKey(SecurityConstant.SUPER_ADMIN_ROLE)){
			return true;
		}else return false;
	}
	
	public List<Integer> getRoleIdList(){
		if(null==this.roles){
			return null;
		}
		List<Integer> roleIds=new ArrayList<Integer>();
		for(String roleKey:this.roles.keySet()){
			roleIds.add(this.roles.get(roleKey).getId());
		}
		return roleIds;
	}
	

	public String getRoleIds(){
		String roleIds=ArrayUtils.toString(getRoleIdList());
		return roleIds.substring(1,roleIds.length()-1);
	}

	
	
	
	public String getRoleIdsForIn(){
		List<Integer> roleList=getRoleIdList();
		if(null==roleList || roleList.size()==0){
			return null;
		}
		String roleIds="";
		for(Integer roleId:getRoleIdList()){
			roleIds=roleIds+"'"+roleId+"',";
		}
		return roleIds.substring(0,roleIds.length()-1);
	}

	public Map<String, String> getSubSecurityUsers() {
		return subSecurityUsers;
	}

	public void setSubSecurityUsers(Map<String, String> subSecurityUsers) {
		this.subSecurityUsers = subSecurityUsers;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public Date getTimeoutDateTime() {
		return timeoutDateTime;
	}

	public void setTimeoutDateTime(Date timeoutDateTime) {
		this.timeoutDateTime = timeoutDateTime;
	}
}
