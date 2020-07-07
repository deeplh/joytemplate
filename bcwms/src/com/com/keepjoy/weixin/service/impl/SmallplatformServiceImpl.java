package com.keepjoy.weixin.service.impl;

import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.security.controller.LoginController;
import com.keepjoy.security.entity.BasUser;
import com.keepjoy.security.properties.SecurityProperties;
import com.keepjoy.security.proxy.RedisSsoProxy;
import com.keepjoy.security.util.PasswordUtil;
import com.keepjoy.weixin.bean.smallplatform.SessionKeyBean;
import com.keepjoy.weixin.entity.WeixinSmallPlatformUser;
import com.keepjoy.weixin.entity.WeixinUser;
import com.keepjoy.weixin.properties.WeixinProperties;
import com.keepjoy.weixin.rpc.smallplatform.SendGetSessionKeyByCode;
import com.keepjoy.weixin.security.smallplatform.ISmallPlatformUserDetailsService;
import com.keepjoy.weixin.security.smallplatform.SmallPlatformUser;
import com.keepjoy.weixin.security.smallplatform.SmallPlatformUserHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class SmallplatformServiceImpl {

    @Autowired
    private KeepJoyDaoImpl dao;


    public String getToken(String code, WeixinUser weixinUser) throws Exception {

        Date now = new Date();
        SendGetSessionKeyByCode ss = new SendGetSessionKeyByCode(WeixinProperties.getAppId(),
                WeixinProperties.getAppSecret(), code);

        SessionKeyBean skb = ss.send();


        WeixinSmallPlatformUser suser = dao.findObjectFromListByHql(
                WeixinSmallPlatformUser.class, "from WeixinSmallPlatformUser where openid=? ", skb.getOpenid());
        WeixinUser wxuser = null;
        if (null == suser) {//第一次登录

            wxuser = new WeixinUser();
            if (StringUtils.isNotEmpty(skb.getUnionid())) {
                wxuser.setUnionid(skb.getUnionid());
            }

            BasUser bu = new BasUser();
            if (null != weixinUser) {
                bu.setUserName(weixinUser.getNickName());
                BeanUtils.copyProperties(weixinUser, wxuser);
            } else {
                bu.setUserName(wxuser.getAvatarUrl());
            }
            bu.setPassword(PasswordUtil.getPassword(bu.getUserName(), "111111"));
            bu.setCreateTime(now);
            dao.save(bu);

            wxuser.setUserId(bu.getUserId());
            wxuser.setCreateTime(now);
            dao.save(wxuser);


            suser = new WeixinSmallPlatformUser();
            suser.setCreateTime(now);
            suser.setUserId(bu.getUserId());
            suser.setOpenid(skb.getOpenid());
            dao.save(suser);

        } else {
            wxuser = dao.get(WeixinUser.class, suser.getUserId());
            if (null != wxuser) {
                if (null != weixinUser && StringUtils.isNotEmpty(weixinUser.getAvatarUrl())
                        && StringUtils.isNotEmpty(weixinUser.getNickName())) {
                    BeanUtils.copyProperties(weixinUser, wxuser, "userId", "createTime");
                    dao.update(wxuser);
                }
            } else {
                throw new KeepJoyServiceException("微信用户信息表异常");
            }

        }


        SmallPlatformUser finalUser = new SmallPlatformUser();
        finalUser.setSmallPlatformUserId(suser.getUserId());
        finalUser.setOpenId(suser.getOpenid());
        finalUser.setSessionKey(skb.getSession_key());
        finalUser.setWeixinUser(wxuser);

        //回调
        if (StringUtils.isNotEmpty(WeixinProperties.getAssociateUserClass())) {
            Object associateUserClassObj = Class.forName(WeixinProperties.getAssociateUserClass()).newInstance();
            if (null != associateUserClassObj && associateUserClassObj instanceof ISmallPlatformUserDetailsService) {
                ISmallPlatformUserDetailsService isp = (ISmallPlatformUserDetailsService) associateUserClassObj;
                finalUser = isp.loadUser(finalUser);
            } else {
                throw new KeepJoyServiceException("请配置正确的associate-user-class实现类的路径");
            }
        }


        //redis-模式
        finalUser.setToken(LoginController.getToken());
        if (SecurityProperties.getSsoMode()) {
            RedisSsoProxy.loginByRedis(finalUser, SecurityProperties.getSessionTimeout());
        } else {//单机模式
            SmallPlatformUserHolder.removeUser(finalUser);
            SmallPlatformUserHolder.putUserMap(finalUser.getToken(), finalUser);
        }
        return finalUser.getToken();


    }
}
