package com.keepjoy.security.core.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.keepjoy.core.context.KeepJoySpringContext;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.security.SecurityConstant;
import com.keepjoy.security.bean.user.MenuBean;
import com.keepjoy.security.bean.user.ResourceBean;
import com.keepjoy.security.bean.user.RoleBean;
import com.keepjoy.security.bean.user.UserBasRoleMenuResourceRelationBean;
import com.keepjoy.security.entity.BasUser;
import com.keepjoy.security.properties.SecurityProperties;
import com.keepjoy.security.core.IUserDetailsService;
import com.keepjoy.security.core.SecurityUser;
import com.keepjoy.security.util.PasswordUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


public class UserDetailImpl implements IUserDetailsService {

	@Override
	public SecurityUser loadUser(String userName) throws Exception{
		KeepJoyDaoImpl dao=KeepJoySpringContext.getKeepJoyDao();
		userName=userName==null?"":userName.trim();
		if(userName.length()==0)throw new KeepJoyServiceException("");

		BasUser bui=dao.findObjectFromListByHql(BasUser.class, "from BasUser where userName=?", userName);
		//取得用户的密码  
		if (bui ==null){  
			throw new KeepJoyServiceException("用户"+userName+"不存在",SecurityConstant.EXCEPTIONCODE_USERNAME_NOT_FOUND);
		}  

		SecurityUser user=new SecurityUser();
		user.setUserName(bui.getUserName());
		user.setUserId(bui.getUserId());
		user.setPassword(bui.getPassword());
		setRoleMenuResource(user,bui.getUserId());
		return user;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void setRoleMenuResource(SecurityUser user, Integer userId){
		//得到角色
		List<RoleBean> userRoleList=KeepJoySpringContext.getJdbcTemplate().
				query(" select bri.id,bri.roleKey,burr.serviceId,burr.serviceType " +
						" from BasRole bri,BasUserRoleRelation burr "
						+ " where burr.basRoleId=bri.id and burr.userId=? ",
						new BeanPropertyRowMapper(RoleBean.class),userId);

		if(null==userRoleList || userRoleList.size()==0){
			return;
		}

		Map<String,RoleBean> roles=new HashMap<String,RoleBean>();
		for(RoleBean rb:userRoleList){
			roles.put(rb.getRoleKey(), rb);
		}
		user.setRoles(roles);

		//得到菜单
		if(SecurityProperties.getMenuMode()){
			List<MenuBean> userMenuList=KeepJoySpringContext.getJdbcTemplate().
					query("select bm.id,bm.menuName,bm.menuUrl,bm.parentId from BasMenu bm "
							+ " left join BasRoleMenuRelation brmr on brmr.basMenuId=bm.id where brmr.basRoleId in ("+user.getRoleIdsForIn()+") ",
							new BeanPropertyRowMapper(MenuBean.class));
			if(null==userMenuList || userMenuList.size()==0){
				return;
			}

			Map<String,MenuBean> menus=new HashMap<String,MenuBean>();
			for(MenuBean meb:userMenuList){
				menus.put(meb.getId()+"", meb);
			}
			user.setMenus(menus);

			//得到操作权限
			List<UserBasRoleMenuResourceRelationBean> ubrList=KeepJoySpringContext.getJdbcTemplate().
					query(
							"select brmrr.basRoleId,brmrr.basMenuId,brmrr.basResourceId,"
									+ " bro.roleKey, "
									+ " bm.menuName,bm.menuUrl,bm.parentId, "
									+ " bre.resourceUrl,bre.resourceName "
									+ " from BasRoleMenuResourceRelation brmrr "
									+ " left join BasRole bro on bro.id=brmrr.basRoleId "
									+ " left join BasMenu bm on bm.id=brmrr.basMenuId "		
									+ " left join BasResource bre on bre.id=brmrr.basResourceId"
									+ " where bro.id in ("+user.getRoleIdsForIn()+") ", 
									new BeanPropertyRowMapper(UserBasRoleMenuResourceRelationBean.class));

			if(null!=ubrList && ubrList.size()>0){

				ResourceBean rb=null;
				//先赋值menus
				for(UserBasRoleMenuResourceRelationBean ubr:ubrList){
					for(String menuId:user.getMenus().keySet()){
						if(menuId.equals(ubr.getBasMenuId())){
							rb=new ResourceBean();
							rb.setId(ubr.getBasResourceId());
							rb.setResourceUrl(ubr.getResourceUrl());
							rb.setResourceName(ubr.getResourceName());

							if(null==user.getMenus().get(ubr.getBasMenuId()).getResources()){
								user.getMenus().get(ubr.getBasMenuId()).setResources(new HashMap<Integer, ResourceBean>());
							}
							user.getMenus().get(ubr.getBasMenuId()).getResources().put(ubr.getBasResourceId(), rb);
						}
					}
				}
			}
		}

	}


	@Override
	public boolean validatePassword(SecurityUser user, String inputPwd) throws Exception {
		return user.getPassword().equals(PasswordUtil.getPassword(user.getUserName(), inputPwd));
	}



}
