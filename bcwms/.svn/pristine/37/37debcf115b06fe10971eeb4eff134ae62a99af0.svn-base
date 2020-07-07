package com.bcwms.service.impl.user;

import com.bcwms.BcwConstant;
import com.bcwms.entity.*;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("ALL")
@Service
@Transactional(rollbackFor = Exception.class)
public class UserCrewLessonServiceImpl {

    @Autowired
    private KeepJoyDaoImpl dao;

    /***
     * @param crewTermLessonDetailId
     * @param userId
     */
    public void create(Integer crewTermLessonDetailId, Integer userId) {
        TblDanceCrewTermLessonDetail t = dao.get(TblDanceCrewTermLessonDetail.class, crewTermLessonDetailId);
        if (null==t) {
            throw new KeepJoyServiceException("crewTermLessonDetailId数据异常");
        }
        TblDanceUserCrewLessonDetail d=new TblDanceUserCrewLessonDetail();
        d.setCrewId(t.getCrewId());
        d.setCrewTermId(t.getCrewTermId());
        d.setCrewTermLessonDetailId(crewTermLessonDetailId);
        d.setSignDateTime(BcwCommonService.getNow());
        d.setUserId(userId);
        d.setCreateDateTime(d.getSignDateTime());
        dao.save(d);

    }

    /**
     * 老师签到-上链
     * @param crewTermLessonDetailId
     */
    public void teacherSign(Integer crewTermLessonDetailId){
        TblDanceCrewTermLessonDetail t = dao.get(TblDanceCrewTermLessonDetail.class, crewTermLessonDetailId);
        if (null==t) {
            throw new KeepJoyServiceException("crewTermLessonDetailId数据异常");
        }
        t.setSignDateTime(BcwCommonService.getNow());
        t.setStatus(BcwConstant.STATUS_2_YES);
        dao.update(t);
    }

    /**
     * 学生签到-上链
     * @param userCrewLessonDetailId
     */
    public void studentSign(Integer userCrewLessonDetailId){
        TblDanceUserCrewLessonDetail t=dao.get(TblDanceUserCrewLessonDetail.class,userCrewLessonDetailId);
        if (null==t) {
            throw new KeepJoyServiceException("userCrewLessonDetailId数据异常");
        }
        t.setSignDateTime(BcwCommonService.getNow());
        t.setStatus(BcwConstant.STATUS_2_YES);
        dao.update(t);
    }

}
