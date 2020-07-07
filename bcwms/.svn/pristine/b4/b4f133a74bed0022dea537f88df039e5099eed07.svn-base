package com.keepjoy.weixin.security.smallplatform;

import com.keepjoy.security.core.SecurityUser;
import com.keepjoy.weixin.entity.WeixinUser;

public class SmallPlatformUser extends SecurityUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer smallPlatformUserId;
	private String token;
	private String openId;
	private String sessionKey;
	private Boolean isAssociate;//是否与接入平台的user模型已经关联
	
	private WeixinUser weixinUser;
	
	public Integer getSmallPlatformUserId() {
		return smallPlatformUserId;
	}
	public void setSmallPlatformUserId(Integer smallPlatformUserId) {
		this.smallPlatformUserId = smallPlatformUserId;
	}
	
	public Boolean getIsAssociate() {
		if(null==isAssociate){
			return false;
		}
		return isAssociate;
	}
	public void setIsAssociate(Boolean isAssociate) {
		this.isAssociate = isAssociate;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	public WeixinUser getWeixinUser() {
		return weixinUser;
	}
	public void setWeixinUser(WeixinUser weixinUser) {
		this.weixinUser = weixinUser;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((openId == null) ? 0 : openId.hashCode());
		result = prime * result + ((smallPlatformUserId == null) ? 0 : smallPlatformUserId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmallPlatformUser other = (SmallPlatformUser) obj;
		if (openId == null) {
			if (other.openId != null)
				return false;
		} else if (!openId.equals(other.openId))
			return false;
		if (smallPlatformUserId == null) {
			if (other.smallPlatformUserId != null)
				return false;
		} else if (!smallPlatformUserId.equals(other.smallPlatformUserId))
			return false;
		return true;
	}
	
	
	
	
}
