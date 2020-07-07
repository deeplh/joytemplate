package com.bcwms.service.impl.studio;


import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceStudioStoreLessonDetail;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class StudioStoreLessonServiceImpl {

    private final KeepJoyDaoImpl dao;

    @Autowired
    public StudioStoreLessonServiceImpl(KeepJoyDaoImpl dao) {
        this.dao = dao;
    }


    /***
     * 单天排课
     * @param obj 课程
     */
    public void createStudioStoreLesson(TblDanceStudioStoreLessonDetail obj) {
        BcwCommonService.validateUserSchedule(obj.getTeacherId(),obj.getBeginDateTime(),obj.getEndDateTime());
        obj.setCreateDateTime(BcwCommonService.getNow());
        obj.setCreateUserId(BcwUserHolder.getDanceUser().getUserId());
        obj.setStatus(BcwConstant.STATUS_2_YES);
        dao.save(obj);
    }

    /***
     * 根据指定周期循环生成课表
     * @param details 课程
     * @param beginDate 开始时间
     * @param endDate 结束时间
     */
    public void createStudioStoreLessonLoop(List<TblDanceStudioStoreLessonDetail> details, Date beginDate,Date endDate) {


    }


    public TblDanceStudioStoreLessonDetail getStudioStoreLesson(Integer id) {
        TblDanceStudioStoreLessonDetail objLocal = dao.get(TblDanceStudioStoreLessonDetail.class,id);
        if(null==objLocal){
            throw new KeepJoyServiceException("id数据异常");
        }
        return objLocal;
    }



    public void updateStudioStoreLesson(TblDanceStudioStoreLessonDetail obj) {

        if(obj.getId()==null){
            throw new KeepJoyServiceException("id格式异常");
        }

        TblDanceStudioStoreLessonDetail objLocal = dao.get(TblDanceStudioStoreLessonDetail.class,obj.getId());
        if(null==objLocal){
            throw new KeepJoyServiceException("id数据异常");
        }

    }

    /**
     * 结算老师课程流水并上链
     *
     * @param studioStoreId 工作室门店ID
     */
    public void settleStudioStoreLesson(Integer studioStoreId) throws KeepJoyServiceException {
        try {
            dao.bulkUpdate("update TblDanceStudioStoreLessonDetail set status = 4, " +
                            "updateDateTime = ? , " +
                            "updateUserId = ? " +
                            "where studioStoreId = ? " +
                            "and status = 2",
                    BcwCommonService.getNow(),
                    BcwUserHolder.getDanceUser().getUserId(),
                    studioStoreId);
        } catch (Exception e) {
            throw new KeepJoyServiceException("结算老师课程流水失败");
        }
        // TODO: 2018/11/27 调用上链接口
    }

    public void removeStudioStoreLesson(Integer id) {

        TblDanceStudioStoreLessonDetail o = dao.get(TblDanceStudioStoreLessonDetail.class,id);
        if(null==o){
            throw new KeepJoyServiceException("id数据异常");
        }
        dao.remove(o);

    }
}
