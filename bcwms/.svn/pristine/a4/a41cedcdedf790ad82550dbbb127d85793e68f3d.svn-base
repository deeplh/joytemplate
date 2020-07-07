package com.bcwms.security.weixin;


import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceCrew;
import com.bcwms.entity.TblDanceUser;
import com.bcwms.security.BcwUser;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.bcwms.service.impl.user.UserCrewServiceImpl;
import com.keepjoy.core.context.KeepJoySpringContext;
import com.keepjoy.security.SecurityConstant;
import com.keepjoy.security.bean.user.RoleBean;
import com.keepjoy.security.core.impl.UserDetailImpl;
import com.keepjoy.security.entity.BasUser;
import com.keepjoy.weixin.security.smallplatform.ISmallPlatformUserDetailsService;
import com.keepjoy.weixin.security.smallplatform.SmallPlatformUser;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class SmallPlatformUserDetailImpl implements ISmallPlatformUserDetailsService {

    @Override
    public SmallPlatformUser loadUser(SmallPlatformUser suser) {

        BcwUser du = new BcwUser();

        BeanUtils.copyProperties(suser, du);
        TblDanceUser tdu = KeepJoySpringContext.getKeepJoyDao().findObjectFromListByHql(TblDanceUser.class,
                " from TblDanceUser where userId=? ", suser.getSmallPlatformUserId());


        if (null == tdu) {//第一次
            BasUser bu = KeepJoySpringContext.getKeepJoyDao().get(BasUser.class, suser.getSmallPlatformUserId());


            tdu = new TblDanceUser();
            tdu.setUserId(bu.getUserId());

            tdu.setCreateDateTime(BcwCommonService.getNow());
            tdu.setAvatarUrl(suser.getWeixinUser().getAvatarUrl());
            KeepJoySpringContext.getKeepJoyDao().save(tdu);

            bu.setUserName(suser.getWeixinUser().getNickName());
            KeepJoySpringContext.getKeepJoyDao().update(bu);
        }
        if (!suser.getWeixinUser().getAvatarUrl().equals(tdu.getAvatarUrl())) {
            tdu.setAvatarUrl(suser.getWeixinUser().getAvatarUrl());
            KeepJoySpringContext.getKeepJoyDao().update(tdu);
        }


        du.setUserId(tdu.getUserId());
        BcwCommonService.setDanceUser(tdu);
        du.setDanceUserInfo(tdu);


        setRoles(du);

        return du;

    }


    public static void setRoles(BcwUser du) {
        UserDetailImpl.setRoleMenuResource(du, du.getUserId());


        if (null != du.getRoles() && du.getRoles().size() > 0) {

            du.setAdmin(true);



            if (du.getRoles().containsKey(SecurityConstant.SUPER_ADMIN_ROLE)) {
                du.setSuperAdmin(true);
                du.setCrewAdmin(true);
                du.setStudioAdmin(true);
                du.getDanceUserInfo().setAdminCrews(KeepJoySpringContext.getKeepJoyDao()
                        .find(" from TblDanceCrew where status=? ",BcwConstant.STATUS_2_YES));
            }else{
                if (du.getRoles().containsKey(BcwConstant.ROLE_CREW_ADMIN)) {
                    du.setCrewAdmin(true);
                    RoleBean rb = du.getRoles().get(BcwConstant.ROLE_CREW_ADMIN);
                    du.getDanceUserInfo().getAdminCrews().add(KeepJoySpringContext.getKeepJoyDao().
                            get(TblDanceCrew.class,rb.getServiceId()));
                }

                if (du.getRoles().containsKey(BcwConstant.ROLE_STUDIO_ADMIN)) {
                    du.setStudioAdmin(true);
                }
            }


        }
    }

}
