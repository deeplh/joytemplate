package com.bcwms.service.impl.crew;


import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceCrewTermLessonDetail;
import com.bcwms.entity.TblDanceCrewTermUserDetail;
import com.bcwms.entity.TblDanceUserCrewLessonDetail;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class CrewTermLessonServiceImpl {

    @Autowired
    private KeepJoyDaoImpl dao;



    public void create(TblDanceCrewTermLessonDetail obj) throws Exception {
        BcwCommonService.validateUserSchedule(obj.getTeacherId(),obj.getBeginDateTime(),obj.getEndDateTime());
        obj.setStatus(BcwConstant.STATUS_0_ING);
        obj.setCreateUserId(BcwUserHolder.getDanceUser().getUserId());
        obj.setCreateDateTime(BcwCommonService.getNow());
        dao.save(obj);

        //学生的上课流水

        TblDanceUserCrewLessonDetail tblDanceUserCrewLessonDetail=null;

        for(Integer studentId:obj.getStudentIds()){
            tblDanceUserCrewLessonDetail=new TblDanceUserCrewLessonDetail();
            tblDanceUserCrewLessonDetail.setCrewTermLessonDetailId(obj.getId());
            tblDanceUserCrewLessonDetail.setCrewId(obj.getCrewId());
            tblDanceUserCrewLessonDetail.setCrewTermId(obj.getCrewTermId());
            tblDanceUserCrewLessonDetail.setUserId(studentId);
            tblDanceUserCrewLessonDetail.setCreateDateTime(obj.getCreateDateTime());
            tblDanceUserCrewLessonDetail.setCreateUserId(obj.getCreateUserId());
            tblDanceUserCrewLessonDetail.setStatus(BcwConstant.STATUS_0_ING);
            dao.save(tblDanceUserCrewLessonDetail);
        }

    }

    public TblDanceCrewTermLessonDetail get(Integer id){
        TblDanceCrewTermLessonDetail tblDanceCrewTermLessonDetail = dao.get(TblDanceCrewTermLessonDetail.class,id);
        if(tblDanceCrewTermLessonDetail == null) throw new KeepJoyServiceException("未找到指定团课！");

        List<Map<String,Object>> cMapList = dao.getJdbcTemplate().queryForList(
                "select " +
                        " tducld.*,tdu.trueName " +
                        " from TblDanceUserCrewLessonDetail tducld " +
                        " left join TblDanceUser tdu on tdu.id = tducld.userId " +
                        " where tducld.crewTermLessonDetailId = ? ",
                tblDanceCrewTermLessonDetail.getId());

        tblDanceCrewTermLessonDetail.setLessionStudentList(cMapList);
        return tblDanceCrewTermLessonDetail;
    }

    public void remove(Integer id){
        TblDanceCrewTermLessonDetail t=dao.get(TblDanceCrewTermLessonDetail.class,id);
        if(null==id){
            throw new KeepJoyServiceException("id数据异常");
        }
        t.setStatus(BcwConstant.STATUS_3_REMOVE);
        dao.update(t);
    }


    public String doSignTeacher(Integer id){
        TblDanceCrewTermLessonDetail tblDanceCrewTermLessonDetail = dao.get(TblDanceCrewTermLessonDetail.class,id);
        if(tblDanceCrewTermLessonDetail == null) throw new KeepJoyServiceException("未找到指定团课！");
        tblDanceCrewTermLessonDetail.setSignDateTime(BcwCommonService.getNow());
        dao.update(tblDanceCrewTermLessonDetail);
        return "教师签到成功！";
    }

    public String doSignStudent(Integer id){
        TblDanceUserCrewLessonDetail tblDanceUserCrewLessonDetail = dao.get(TblDanceUserCrewLessonDetail.class,id);
        if(tblDanceUserCrewLessonDetail == null) throw new KeepJoyServiceException("未找到指定上课学生！");
        tblDanceUserCrewLessonDetail.setSignDateTime(BcwCommonService.getNow());
        dao.update(tblDanceUserCrewLessonDetail);
        return "学生签到成功！";
    }


    public void update(TblDanceCrewTermLessonDetail obj) throws Exception {

        dao.update(obj);

    }

}
