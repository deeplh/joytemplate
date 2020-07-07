package com.bcwms.service.impl.crew;


import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceCrewTermUserDetail;
import com.bcwms.entity.TblDanceCrewTermUserDetailScore;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CrewTermUserServiceImpl {

    @Autowired
    private KeepJoyDaoImpl dao;


    public void create(TblDanceCrewTermUserDetail obj) throws Exception {
        TblDanceCrewTermUserDetail o = dao.findObjectFromListByHql(TblDanceCrewTermUserDetail.class,
                "from TblDanceCrewTermUserDetail where crewTermId=? and crewId=? and userId=?",
                obj.getCrewTermId(), obj.getCrewId(), obj.getUserId());
        if (null != o) {
            throw new KeepJoyServiceException("请勿重复添加团员");
        }
        dao.save(obj);
    }


    public void update(TblDanceCrewTermUserDetail term) throws Exception {


    }


    public TblDanceCrewTermUserDetail get(Integer id) {
        TblDanceCrewTermUserDetail t = dao.get(TblDanceCrewTermUserDetail.class, id);
        if (null == t) {
            throw new KeepJoyServiceException("crewTermUserDetailId格式异常");
        }
        return t;
    }

    public void updateCrewUserType(Integer id, String crewUserType) {
        TblDanceCrewTermUserDetail t = dao.get(TblDanceCrewTermUserDetail.class, id);
        if (null == t) {
            throw new KeepJoyServiceException("id数据异常");
        }

//        Long i=dao.findObjectFromListByHql(Long.class,
//                " select count(id) from TblDanceCrewTermUserDetailScore where crewTermUserDetailId=? ",t.getId());
//
//        if(null!=i && i>0){
//            throw new KeepJoyServiceException("该用户已经存在其他老师的打分记录,无法进行设置");
//        }
        t.setCrewUserType(crewUserType);
        dao.update(t);
    }

    public void updateCrewUserTypeByIds(List<Integer> ids, String crewUserType) {
        for (Integer id : ids) {
            updateCrewUserType(id, crewUserType);
        }
    }

    public void updateCrewUserStatus(Integer id, String status) {
        TblDanceCrewTermUserDetail t = dao.get(TblDanceCrewTermUserDetail.class, id);
        if (null == t) {
            throw new KeepJoyServiceException("id数据异常");
        }
        t.setStatus(status);
        dao.update(t);
    }


    /***
     * 给考团人员打分-允许反复打分
     * @param crewTermUserDetailId
     */
    public TblDanceCrewTermUserDetailScore score(Integer crewTermUserDetailId, TblDanceCrewTermUserDetailScore score) {
        TblDanceCrewTermUserDetail t = get(crewTermUserDetailId);

        Integer userId = BcwUserHolder.getDanceUser().getUserId();
        TblDanceCrewTermUserDetailScore s = dao.findObjectFromListByHql(TblDanceCrewTermUserDetailScore.class,
                " from TblDanceCrewTermUserDetailScore where crewTermId=? and crewId=? and teacherId=? " +
                        " and crewTermUserDetailId=? ", t.getCrewTermId(), t.getCrewId(),
                userId, crewTermUserDetailId);
        Date now = BcwCommonService.getNow();
        if (null != s
                && (
                (null != score.getFoundationScore() && !score.getFoundationScore().equals(s.getFoundationScore()))
                        ||
                        null != score.getRoutineScore() && !score.getRoutineScore().equals(s.getRoutineScore())
                        ||
                        null != score.getSoloScore() && !score.getSoloScore().equals(s.getSoloScore())
                        ||
                        null != score.getOtherScore() && !score.getOtherScore().equals(s.getOtherScore())
        )

                ) {//对这个人不是第一次打分,作修改操作
            if(null != score.getFoundationScore() && !score.getFoundationScore().equals(s.getFoundationScore())){
                s.setFoundationScore(score.getFoundationScore());
            }
            if(null != score.getRoutineScore() && !score.getRoutineScore().equals(s.getRoutineScore())) {
                s.setRoutineScore(score.getRoutineScore());
            }
            if(null != score.getSoloScore() && !score.getSoloScore().equals(s.getSoloScore())){
                s.setSoloScore(score.getSoloScore());
            }
            if(null != score.getOtherScore() && !score.getOtherScore().equals(s.getOtherScore())){
                s.setOtherScore(score.getOtherScore());
            }
            s.setScore(getSumScore(s));
            s.setUpdateDateTime(now);
            s.setUpdateUserId(userId);
            dao.update(s);
        } else {
            s = new TblDanceCrewTermUserDetailScore();
            if(null != score.getFoundationScore()){
                s.setFoundationScore(score.getFoundationScore());
            }
            if(null != score.getRoutineScore() ) {
                s.setRoutineScore(score.getRoutineScore());
            }
            if(null != score.getSoloScore()){
                s.setSoloScore(score.getSoloScore());
            }
            if(null != score.getOtherScore()){
                s.setOtherScore(score.getOtherScore());
            }
            s.setScore(getSumScore(s));
            s.setUserId(t.getUserId());
            s.setEventId(t.getEventId());
            s.setTeacherId(userId);
            s.setCrewTermId(t.getCrewTermId());
            s.setCrewId(t.getCrewId());
            s.setCrewTermUserDetailId(t.getId());
            s.setCreateDateTime(now);
            s.setCreateUserId(userId);
            dao.save(s);

            t.setStatus(BcwConstant.STATUS_2_YES);//已激活
            dao.update(t);
        }
        return s;

    }


    /**
     * 查询分数接口-分为老师和学生
     *
     * @param crewTermUserDetailId
     * @return
     */
    public List<TblDanceCrewTermUserDetailScore> searchScore(Integer crewTermUserDetailId) {
        TblDanceCrewTermUserDetail t = get(crewTermUserDetailId);
        List<TblDanceCrewTermUserDetailScore> scores = null;
        Integer userId = BcwUserHolder.getDanceUser().getUserId();
        if (t.getCrewUserType().equals(TblDanceCrewTermUserDetail.CREWUSERTYPE_1_TEACHER)) {//可以看本期所有人的分数
            scores = dao.find("from TblDanceCrewTermUserDetailScore where crewTermId=? and crewId=? ",
                    t.getCrewTermId(), t.getCrewId());
        } else {//学生能看到所有老师给自己打的分数
            scores = dao.find("from TblDanceCrewTermUserDetailScore where crewTermId=? and crewId=? and crewTermUserDetailId=? ",
                    t.getCrewTermId(), t.getCrewId(), crewTermUserDetailId);
        }
        return scores;
    }


    private Integer getSumScore(TblDanceCrewTermUserDetailScore s){
        Integer scoreSum=0;
        if(null != s.getFoundationScore()){
            scoreSum=scoreSum+s.getFoundationScore();
        }
        if(null != s.getRoutineScore() ) {
            scoreSum=scoreSum+s.getRoutineScore();
        }
        if(null != s.getSoloScore()){
            scoreSum=scoreSum+s.getSoloScore();
        }
        if(null != s.getOtherScore()){
            scoreSum=scoreSum+s.getOtherScore();
        }
        return scoreSum;
    }

    public List<TblDanceCrewTermUserDetail> queryByCrewIdAndTermId(Integer crewId, Integer crewTermId, String status) {
        if (StringUtils.isEmpty(status)) {
            return dao.find("from TblDanceCrewTermUserDetail where crewId=? and crewTermId=?", crewId, crewTermId);
        }
        return dao.find("from TblDanceCrewTermUserDetail where crewId=? and crewTermId=? and status = ?", crewId, crewTermId, status);
    }
}
