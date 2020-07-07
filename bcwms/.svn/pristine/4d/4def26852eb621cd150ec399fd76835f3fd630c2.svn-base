//package com.keepjoy.security.taglib;
//
//
//
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.tagext.TagSupport;
//
//import com.keepjoy.core.util.MyLogUtil;
//import com.keepjoy.security.core.SecurityUser;
//import com.keepjoy.security.core.SecurityUserHolder;
//import org.apache.commons.lang.StringUtils;
//
//
///**
// * @author liuheng
// *
// */
//public class SecurityAuthorizeTag extends TagSupport {
//	private static final long serialVersionUID = 1L;
//
//	private String ifAnyGranted;//如果有这个权限组,可以访问
//
//	private String ifAnyGrantedNot;//如果有这个权限组,不可以访问,优先
//
//
//	@Override
//	public int doStartTag() throws JspException {
//
//		try {
//			HttpServletRequest request=(HttpServletRequest) super.pageContext.getRequest();
//			SecurityUser user=SecurityUserHolder.getCurrentUser(request);
//			if(StringUtils.isNotEmpty(this.ifAnyGrantedNot)){
//				String[] angns=this.ifAnyGrantedNot.split(",");
//				for(String auth:user.getRoles().keySet()){
//					if(null==auth)continue;
//					for(String ang:angns){
//						if(null==ang)continue;
//						if(auth.trim().equals(ang.trim())){
//							return SKIP_BODY;
//						}
//					}
//				}
//			}
//			if(StringUtils.isNotEmpty(this.ifAnyGranted)){
//				String[] angs=this.ifAnyGranted.split(",");
//				for(String auth:user.getRoles().keySet()){
//					if(null==auth)continue;
//					for(String ang:angs){
//						if(null==ang)continue;
//						if(auth.trim().equals(ang.trim())){
//							return EVAL_BODY_INCLUDE;
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			MyLogUtil.printlnException(e, "权限标签异常");
//		}
//
//
//		return SKIP_BODY;
//	}
//
//	@Override
//	public int doEndTag() throws JspException {
//		return EVAL_PAGE;
//	}
//
//	public String getIfAnyGranted() {
//		return ifAnyGranted;
//	}
//
//	public void setIfAnyGranted(String ifAnyGranted) {
//		this.ifAnyGranted = ifAnyGranted;
//	}
//
//
//
//
//}
