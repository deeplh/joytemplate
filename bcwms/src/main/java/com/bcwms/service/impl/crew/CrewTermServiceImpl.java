package com.bcwms.service.impl.crew;


import com.bcwms.BcwConstant;

import com.bcwms.entity.TblDanceCrewTerm;
import com.bcwms.entity.TblDanceCrewTermUserDetail;
import com.bcwms.entity.TblDanceEvent;
import com.bcwms.entity.TblDanceExam;

import com.bcwms.properties.BcwProperties;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.core.util.MyLogUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class CrewTermServiceImpl {

    @Autowired
    private KeepJoyDaoImpl dao;


    /**
     * 新增团期
     * @param term
     * @return
     * @throws Exception
     */
    public TblDanceCrewTerm create(TblDanceCrewTerm term) throws Exception {
        TblDanceCrewTerm obj=new TblDanceCrewTerm();
        BeanUtils.copyProperties(term,obj);
        obj.setCreateDateTime(BcwCommonService.getNow());
        obj.setCreateUserId(BcwUserHolder.getDanceUser().getUserId());
        if(null==obj.getTerm()){
            Long num=dao.findObjectFromListByHql(Long.class,
                    "select count(id) from TblDanceCrewTerm where crewId=? ",term.getCrewId());
            if(null!=num && num>0){
                num++;
                obj.setTerm(num.intValue());
            }else{
                obj.setTerm(1);
            }
        }

        dao.save(obj);
        return obj;
    }


    public List<TblDanceCrewTerm> listByCrew(Integer crewId) throws Exception {
        List<TblDanceCrewTerm> ctList=dao.find(" from TblDanceCrewTerm where crewId=? ",crewId);

        return ctList;
    }


    public void update(TblDanceCrewTerm term) throws Exception {



    }

    public TblDanceCrewTerm get(Integer id)  {
        TblDanceCrewTerm t=dao.get(TblDanceCrewTerm.class,id);
        if(null==t){
            throw new KeepJoyServiceException("id数据异常");
        }
        return t;
    }



    /**
     * 本期入团考试相关信息结算
     * 上链位置
     * @param crewTermId
     */
    public void settleCrewTermExam(Integer crewTermId, List<TblDanceCrewTermUserDetail> crewTermUserDetails) throws URISyntaxException {
//        Date now=BcwCommonService.getNow();
//        Integer userId=BcwUserHolder.getDanceUser().getUserId();
//
//        TblDanceCrewTerm t=get(crewTermId);
//
//
//
//        TblDanceEvent e=dao.get(TblDanceEvent.class,t.getEventId());
//        if(!e.getStatus().equals(BcwConstant.STATUS_2_YES)){
//            throw new KeepJoyServiceException("状态异常,无法结算");
//        }
//        e.setStatus(BcwConstant.STATUS_4_END);
//        e.setSettleDateTime(now);
//        e.setUpdateUserId(userId);
//        e.setUpdateDateTime(now);
//        dao.update(e);
//        //本期的老师记录上链
//        List<TblDanceCrewTermUserDetail> tl=dao.getJdbcTemplate().query(
//                "select u.aka,ctud.* " +
////                        "ctud.id,ctud.userId " +
//                        " from TblDanceCrewTermUserDetail ctud left join TblDanceUser u on u.userId=ctud.userId " +
//                        " where crewTermId=? and eventId=? and crewUserType=? ",
//                new BeanPropertyRowMapper(TblDanceCrewTermUserDetail.class),
//                t.getId(),t.getEventId(),TblDanceCrewTermUserDetail.CREWUSERTYPE_1_TEACHER);
//        for(TblDanceCrewTermUserDetail td:tl){
//            MemberVO mv=new MemberVO();
//            mv.setAka(td.getAka()).setCreateTime(e.getSettleDateTime().getTime()).setEventId(t.getEventId())
//                    .setMemberId(td.getId()).setUserId(td.getUserId()).setExamId(t.getExamId()).setScore(100)
//                    .setUserType(TblDanceCrewTermUserDetail.CREWUSERTYPE_1_TEACHER)
//                    .setSeqNo(BcwCommonService.getChainSeqNo(BcwConstant.TYPE_CREW_TERM_EXAM,td.getId()));
//            BoogieClient c = new BoogieClient(BcwProperties.getBlockchainUrl());
//            CommonDataResponse<MemberVO> res = c.createNewMember(mv);
//            String resStr=JacksonUtils.toJson(res);
//            MyLogUtil.printlnInfo("crew老师信息流水上链返回:" + resStr);
//            if (res.getStatus() != 0) throw new KeepJoyServiceException(resStr);
//
////            td.setEventId(t.getEventId());
////            td.setCrewId(t.getCrewId());
////            td.setCrewTermId(t.getId());
//            td.setStatus(BcwConstant.STATUS_4_END);
//            td.setContractAddress(res.getData().getContractAddress());
//            td.setSettleDateTime(now);
////            td.setCreateUserId(userId);
////            td.setCreateDateTime(now);
//            td.setUpdateUserId(userId);
//            td.setUpdateDateTime(now);
//            dao.update(td);
//        }
//
//
//        TblDanceCrewTermUserDetail o=null;
//        //学生团员的记录
//        for(TblDanceCrewTermUserDetail d:crewTermUserDetails){
//            o=dao.get(TblDanceCrewTermUserDetail.class,d.getId());
//            if(null==o){
//                throw new KeepJoyServiceException("id数据异常");
//            }
//            if(!o.getStatus().equals(BcwConstant.STATUS_2_YES)){
//                throw new KeepJoyServiceException("id:["+o.getId()+"]状态异常,无法结算");
//            }
//            o.setStatus(BcwConstant.STATUS_4_END);
//            o.setScore(d.getScore());//前端送过来的时候已经算好了
//
//
//            //团员信息流水上链
//            MemberVO mv=new MemberVO();
//            mv.setAka(d.getAka()).setCreateTime(e.getSettleDateTime().getTime()).setEventId(t.getEventId())
//                    .setMemberId(o.getId()).setUserId(o.getUserId()).setExamId(t.getExamId()).setScore(o.getScoreInt())
//                    .setUserType(TblDanceCrewTermUserDetail.CREWUSERTYPE_2_MEMBER)
//                    .setSeqNo(BcwCommonService.getChainSeqNo(BcwConstant.TYPE_CREW_TERM_EXAM,o.getId()));
//            BoogieClient c = new BoogieClient(BcwProperties.getBlockchainUrl());
//            CommonDataResponse<MemberVO> res = c.createNewMember(mv);
//            String resStr=JacksonUtils.toJson(res);
//            MyLogUtil.printlnInfo("crew团员信息流水上链返回:" + resStr);
//            if (res.getStatus() != 0) throw new KeepJoyServiceException(resStr);
//
//            o.setSettleDateTime(now);
//            o.setUpdateUserId(userId);
//            o.setUpdateDateTime(now);
//            o.setContractAddress(res.getData().getContractAddress());
//            dao.update(o);
//        }
//
////        //前面所有期的人员名单设置为过期
////        dao.bulkUpdate("update TblDanceCrewTermUserDetail set status=? where crewId=? and eventId<>? and crewTermId<>? ",
////                BcwConstant.STATUS_3_REMOVE,t.getCrewId(),t.getEventId(),t.getId());
//
//
//        //所有的信息记录上链
//        //团期信息-考试
//        ExamVO ev = new ExamVO();
//        ev.setTerm(t.getTerm()).setCreateTime(t.getExamBeginTime()==null?now.getTime():t.getExamBeginTime().getTime())
//                .setCrewId(t.getCrewId()).setEventId(t.getEventId()).setExamId(t.getExamId())
//                .setExamDate(t.getExamDate().getTime())
//                .setSeqNo(BcwCommonService.getChainSeqNo(BcwConstant.TYPE_CREW_TERM,t.getExamId()));
//        BoogieClient c = new BoogieClient(BcwProperties.getBlockchainUrl());
//        CommonDataResponse<ExamVO> res = c.createNewExam(ev);
//        String resStr=JacksonUtils.toJson(res);
//        MyLogUtil.printlnInfo("crew团期信息上链返回:" + resStr);
//        if (res.getStatus() != 0) throw new KeepJoyServiceException(resStr);
//
//        TblDanceExam exam=dao.get(TblDanceExam.class,t.getExamId());
//        exam.setStatus(BcwConstant.STATUS_4_END);
//        exam.setUpdateUserId(userId);
//        exam.setUpdateDateTime(now);
//        exam.setSettleDateTime(now);
//        exam.setContractAddress(res.getData().getContractAddress());
//        dao.update(exam);
//
//        t.setStatus(BcwConstant.STATUS_4_END);
//        t.setUpdateUserId(userId);
//        t.setUpdateDateTime(now);
//        t.setSettleDateTime(now);
//        t.setContractAddress(res.getData().getContractAddress());
//        dao.update(t);

    }

}
