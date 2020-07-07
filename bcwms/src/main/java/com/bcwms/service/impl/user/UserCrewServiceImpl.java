package com.bcwms.service.impl.user;


import com.bcwms.BcwConstant;

import com.bcwms.entity.TblDanceCrew;
import com.bcwms.entity.TblDanceUser;
import com.bcwms.entity.TblDanceUserCrewRelation;

import com.bcwms.security.BcwUser;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.core.util.MyLogUtil;
import com.keepjoy.security.proxy.RedisSsoProxy;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserCrewServiceImpl {

    @Autowired
    private KeepJoyDaoImpl dao;

    /**
     * 关注crew
     *
     * @param crewId
     * @throws Exception
     */
    public void applyCrewMe(Integer crewId) throws Exception {
        TblDanceCrew tdc = dao.get(TblDanceCrew.class, crewId);
        if (null == tdc) throw new KeepJoyServiceException("crewId数据异常");

        TblDanceUserCrewRelation t = dao.findObjectFromListByHql(TblDanceUserCrewRelation.class,
                " from TblDanceUserCrewRelation where crewId=? and userId=? and (status=? or status=? or status is null or status='') ",
                crewId, BcwUserHolder.getDanceUser().getUserId(), BcwConstant.STATUS_0_ING, BcwConstant.STATUS_2_YES);
        if (null != t) throw new KeepJoyServiceException("请勿重复申请");

        t = new TblDanceUserCrewRelation();
        t.setCrewId(crewId);
        t.setUserId(BcwUserHolder.getDanceUser().getUserId());
        t.setCreateDateTime(BcwCommonService.getNow());
        t.setStatus(BcwConstant.STATUS_2_YES);//暂时改为不用审批
        t.setCreateUserId(BcwUserHolder.getDanceUser().getUserId());
        dao.save(t);

//        BcwCommonService.sendNotice(BcwConstant.NOTICE_PUBLISH,BcwConstant.TYPE_CREW,tdc.getCreateUserId(),crewId);


    }


    /**
     * 审批crew-修改状态
     *
     * @param id
     * @param status
     * @throws Exception
     */
    public void approveCrew(Integer id, String status) throws Exception {
        TblDanceCrew t = dao.get(TblDanceCrew.class, id);

//        if (null == t) throw new KeepJoyServiceException("id数据异常");
//
//        if (StringUtils.isNotEmpty(t.getStatus()) && t.getStatus().equals(status)) {//送进来的和原来的相同
//            throw new KeepJoyServiceException("审批操作异常");
//        }
//
//
//        Date now = BcwCommonService.getNow();
//        Integer userId = BcwUserHolder.getDanceUser().getUserId();
//
//
//        if (t.getStatus().equals(BcwConstant.STATUS_0_ING) &&
//                status.equals(BcwConstant.STATUS_2_YES)) {//上链
//            if (StringUtils.isNotEmpty(t.getContractAddress())) {
//                throw new KeepJoyServiceException("无法重复上链");
//            }
//            CrewVO vo = new CrewVO();
//            vo.setCity(t.getCity()).setCrewId(t.getId()).setCreateTime(t.getCreateDateTime().getTime()).
//                    setCrewName(t.getName()).setEsDate(t.getEstablishDate().getTime()).
//                    setFounder(t.getFounder()).setSeqNo(BcwConstant.TYPE_CREW + "_" + t.getId());
//            if(t.getFounder()==null){
//                vo.setFounder("");
//            }
//
//            BoogieClient c = new BoogieClient(BcwProperties.getBlockchainUrl());
//            CommonDataResponse<CrewVO> res = c.createNewCrew(vo);
//            String resStr=JacksonUtils.toJson(res);
//            MyLogUtil.printlnInfo("crew第一次上链返回:" + resStr);
//            if (res.getStatus() != 0) throw new KeepJoyServiceException(resStr);
//
//            t.setContractAddress(res.getData().getContractAddress());
//            t.setApproveDateTime(now);
//            t.setApproveUserId(userId);
//        } else {
//            if (StringUtils.isNotEmpty(t.getContractAddress())
//                    && BcwConstant.STATUS_3_REMOVE.equals(status)) {//上链过的,并且删除的情况
//                CommonSetStatusReq req = new CommonSetStatusReq();
//                req.setContractAddress(t.getContractAddress()).setId(t.getId())
//                        .setStatus(Integer.parseInt(status)).setSeqNo(BcwConstant.TYPE_CREW + "_" + t.getId());
//                BoogieClient c = new BoogieClient(BcwProperties.getBlockchainUrl());
//                CommonDataResponse<CrewVO> res = c.setCrewStatus(req);
//                String resStr=JacksonUtils.toJson(res);
//                MyLogUtil.printlnInfo("crew修改链上状态返回:" + resStr);
//                if (res.getStatus() != 0) throw new KeepJoyServiceException(resStr);
//            }
//
//            t.setUpdateDateTime(now);
//            t.setUpdateUserId(userId);
//        }
//
//        t.setStatus(status);
//
//        dao.update(t);
//
//        BcwCommonService.sendNotice(BcwConstant.NOTICE_PUBLISH, BcwConstant.TYPE_CREW, t.getCreateUserId(), t.getId());

    }


    /**
     * 审批加入crew的申请
     *
     * @param id
     * @param status
     * @throws Exception
     */
    public void approveCrewApply(Integer id, String status) throws Exception {
        TblDanceUserCrewRelation t = dao.get(TblDanceUserCrewRelation.class, id);

        if (null == t) throw new KeepJoyServiceException("id数据异常");

        if (!BcwConstant.STATUS_0_ING.equals(t.getStatus())) throw new KeepJoyServiceException("数据status异常");

        t.setStatus(status);
        t.setApproveDateTime(BcwCommonService.getNow());
        t.setApproveUserId(BcwUserHolder.getDanceUser().getUserId());
        dao.update(t);

        BcwUser du = BcwUserHolder.getDanceUser();
        BcwCommonService.setDanceUserCrews(du.getDanceUserInfo());
        RedisSsoProxy.setSubUserToRedis(du);


        BcwCommonService.sendNotice(BcwConstant.NOTICE_TASK, BcwConstant.TYPE_CREW, t.getUserId(), t.getId());

    }


    /**
     * 解除关注
     *
     * @param id
     * @return
     * @throws Exception
     */
    public TblDanceUser unbindCrewMe(Integer id) throws Exception {

        TblDanceUserCrewRelation td = dao.findObjectFromListByHql(TblDanceUserCrewRelation.class,
                " from TblDanceUserCrewRelation where crewId=? and userId=? and status=? ",
                id, BcwUserHolder.getDanceUser().getUserId(), BcwConstant.STATUS_2_YES);
        if (null == td) {
            throw new KeepJoyServiceException("id数据异常");
        }
        td.setStatus(BcwConstant.STATUS_3_REMOVE);
        dao.update(td);

        BcwUser du = BcwUserHolder.getDanceUser();
        BcwCommonService.setDanceUserCrews(du.getDanceUserInfo());
        RedisSsoProxy.setSubUserToRedis(du);

        return du.getDanceUserInfo();
    }


    /**
     * 查询我当前的crew
     * @return
     */
    public List<TblDanceCrew> listUserCurrentCrew(Integer userId){
        return dao.getJdbcTemplate().query(
                " select c.id,c.name,c.img from TblDanceCrewTermUserDetail ctud " +
                        " left join TblDanceCrew c on c.id=ctud.crewId " +
                        " where ctud.userId=? and ctud.status=? group by c.id,c.name,c.img",
                new BeanPropertyRowMapper<>(TblDanceCrew.class),
                userId,BcwConstant.STATUS_4_END);
    }

}
