package com.bcwms.service.impl;


import com.bcwms.entity.TblDanceExam;
import com.bcwms.entity.TblDanceStyle;
import com.bcwms.entity.TblDanceUserStyleRelation;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class ExamServiceImpl {
    @Autowired
    private KeepJoyDaoImpl dao;

    public TblDanceExam create(TblDanceExam obj){
        obj.setCreateDateTime(BcwCommonService.getNow());
        obj.setCreateUserId(BcwUserHolder.getDanceUser().getUserId());
        dao.save(obj);

        return obj;
    }







}
