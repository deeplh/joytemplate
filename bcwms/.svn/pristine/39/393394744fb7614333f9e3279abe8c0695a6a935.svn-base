package com.bcwms.service.impl.user;

import com.bcwms.entity.TblDanceUser;

import com.bcwms.security.BcwUserHolder;
import com.bcwms.security.BcwUser;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.core.util.DateUtil;
import com.keepjoy.security.proxy.RedisSsoProxy;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;

@SuppressWarnings("ALL")
@Service
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl {

    @Autowired
    private KeepJoyDaoImpl dao;

    public TblDanceUser getDanceUserInfo(Integer userId){
        TblDanceUser tdu = dao.get(TblDanceUser.class, userId);
        if (null == tdu) {
            throw new KeepJoyServiceException("id数据异常");
        }

        BcwCommonService.setDanceUser(tdu);
        return tdu;
    }

    public BcwUser updateUserMe(String img, String trueName, Integer age, String sex, String beginDanceDate,
                                String mobile, String aka) throws ParseException {
        BcwUser du=BcwUserHolder.getDanceUser();
        TblDanceUser obj=dao.get(TblDanceUser.class,du.getDanceUserInfo().getUserId());

        if(null==obj){
            throw new KeepJoyServiceException("数据异常");
        }

        boolean isUpdate=false;
        if(StringUtils.isNotEmpty(img)){
            isUpdate=true;
            obj.setImg(img);
            du.getDanceUserInfo().setImg(img);
        }
        if(StringUtils.isNotEmpty(trueName)){
            isUpdate=true;
            obj.setTrueName(trueName);
            du.getDanceUserInfo().setTrueName(trueName);
        }
        if(null!=age){
            isUpdate=true;
            obj.setAge(age);
            du.getDanceUserInfo().setAge(age);

        }
        if(StringUtils.isNotEmpty(sex)){
            isUpdate=true;
            obj.setSex(sex);
            du.getDanceUserInfo().setSex(sex);
        }
        if(StringUtils.isNotEmpty(beginDanceDate)){
            isUpdate=true;
            Date d=DateUtil.stringToDateByDateFormat(beginDanceDate,DateUtil.DATEFORMAT_YYYY_MM_DD);
            obj.setBeginDanceDate(d);
            du.getDanceUserInfo().setBeginDanceDate(d);
        }
        if(StringUtils.isNotEmpty(mobile)){
            isUpdate=true;
            obj.setMobile(mobile);
            du.getDanceUserInfo().setMobile(mobile);

        }
        if(StringUtils.isNotEmpty(aka)){
            isUpdate=true;
            obj.setAka(aka);
            du.getDanceUserInfo().setAka(aka);

        }
        if(isUpdate) {
            dao.update(obj);

            RedisSsoProxy.setSubUserToRedis(du);
        }
        return du;
    }

    public BcwUser refreshUserMe() {
        BcwUser du=BcwUserHolder.getDanceUser();
        TblDanceUser tdu = dao.get(TblDanceUser.class, du.getDanceUserInfo().getUserId());

        BcwCommonService.setDanceUser(tdu);

        du.setDanceUserInfo(tdu);

        RedisSsoProxy.setSubUserToRedis(du);
        return du;
    }
}
