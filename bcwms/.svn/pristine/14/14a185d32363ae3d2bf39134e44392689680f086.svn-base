package com.bcwms.service.impl.user;


import com.bcwms.entity.TblDanceStyle;
import com.bcwms.entity.TblDanceUser;
import com.bcwms.entity.TblDanceUserStyleRelation;
import com.bcwms.security.BcwUser;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.security.proxy.RedisSsoProxy;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserStyleServiceImpl {
    @Autowired
    private KeepJoyDaoImpl dao;



    public void bindStyleMe(Integer styleId){
        TblDanceUserStyleRelation t=dao.findObjectFromListByHql(TblDanceUserStyleRelation.class,
                " from TblDanceUserStyleRelation where styleId=? and userId=?  ",
                styleId,BcwUserHolder.getDanceUser().getUserId());
        if(null!=t)throw new KeepJoyServiceException("请勿重复绑定");

        t=new TblDanceUserStyleRelation();
        t.setStyleId(styleId);
        t.setUserId(BcwUserHolder.getDanceUser().getUserId());
        t.setCreateDateTime(BcwCommonService.getNow());
        t.setCreateUserId(BcwUserHolder.getDanceUser().getUserId());
        dao.save(t);

        BcwUser du=BcwUserHolder.getDanceUser();
        BcwCommonService.setDanceUserStyles(du.getDanceUserInfo());
        RedisSsoProxy.setSubUserToRedis(du);
    }


    public TblDanceUser unbindStyleMe(Integer id){
        TblDanceUserStyleRelation td=dao.findObjectFromListByHql(TblDanceUserStyleRelation.class,
                " from TblDanceUserStyleRelation where styleId=? and userId=? ",id,BcwUserHolder.getDanceUser().getUserId());
        if(null==td){
            throw new KeepJoyServiceException("id数据异常");
        }
        dao.remove(td);
        BcwUser du=BcwUserHolder.getDanceUser();
        BcwCommonService.setDanceUserStyles(du.getDanceUserInfo());
        RedisSsoProxy.setSubUserToRedis(du);

        return du.getDanceUserInfo();
    }


}
