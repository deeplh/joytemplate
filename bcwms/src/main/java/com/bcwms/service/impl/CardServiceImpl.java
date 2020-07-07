package com.bcwms.service.impl;


import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceCard;
import com.bcwms.entity.TblDanceStudioCard;
import com.bcwms.entity.TblDanceUserStudioCardDetail;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/*
 *
 * 基础课卡SERVICE
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CardServiceImpl {

    @Autowired
    private KeepJoyDaoImpl dao;

    public void create(TblDanceCard obj) throws Exception {

        dao.save(obj);
    }

    public TblDanceCard get(Integer id){
        return dao.get(TblDanceCard.class,id);
    }

    public void update(TblDanceCard obj, List<FileAttrBean> fileAttrBeanList) throws Exception {

        if (obj.getId() == null) {
            throw new KeepJoyServiceException("id格式异常");
        }

        TblDanceCard objLocal = dao.get(TblDanceCard.class, obj.getId());
        if (null == objLocal) {
            throw new KeepJoyServiceException("id数据异常");
        }

        if (StringUtils.isNotEmpty(obj.getStatus()) && !objLocal.getStatus().equals(obj.getStatus())) {
            objLocal.setStatus(obj.getStatus());
        }

        if (StringUtils.isNotEmpty(obj.getName()) && !objLocal.getName().equals(obj.getName())) {
            objLocal.setName(obj.getName());
        }

        if (null != obj.getTotal() && !objLocal.getTotal().equals(obj.getTotal())) {
            objLocal.setTotal(obj.getTotal());
        }

        dao.update(objLocal);
    }

    public void remove(Integer id){

        dao.remove(dao.get(TblDanceCard.class,id));
    }




}
