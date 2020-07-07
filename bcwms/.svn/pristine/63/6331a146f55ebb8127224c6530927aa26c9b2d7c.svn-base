package com.bcwms.service.impl.studio;


import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceEvent;
import com.bcwms.entity.TblDanceStudio;
import com.bcwms.entity.TblDanceUser;
import com.bcwms.entity.TblDanceUserStudioRelation;

import com.bcwms.security.BcwUser;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.security.proxy.RedisSsoProxy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class StudioServiceImpl {

    @Autowired
    private KeepJoyDaoImpl dao;

    public void applyStudioMe(Integer studioId) throws Exception {
        TblDanceUserStudioRelation t=dao.findObjectFromListByHql(TblDanceUserStudioRelation.class,
                " from TblDanceUserStudioRelation where studioId=? and userId=? and (status=? or status=? or status is null or status='') ",
                studioId,BcwUserHolder.getDanceUser().getUserId(),BcwConstant.STATUS_0_ING,BcwConstant.STATUS_2_YES);
        if(null!=t)throw new KeepJoyServiceException("审批中,请勿重复申请");

        t=new TblDanceUserStudioRelation();
        t.setStudioId(studioId);
        t.setUserId(BcwUserHolder.getDanceUser().getUserId());
        t.setCreateDateTime(BcwCommonService.getNow());
        t.setStatus(BcwConstant.STATUS_0_ING);
        t.setCreateUserId(BcwUserHolder.getDanceUser().getUserId());
        dao.save(t);

    }



    public void approveCreateStudio(Integer id,String status) throws Exception {
        TblDanceStudio t=dao.get(TblDanceStudio.class,id);

        if(null==t)throw new KeepJoyServiceException("id数据异常");

        t.setStatus(status);
        t.setApproveDateTime(BcwCommonService.getNow());
        t.setApproveUserId(BcwUserHolder.getDanceUser().getUserId());
        dao.update(t);

    }



    public void approveStudio(Integer id,String status) throws Exception {
        TblDanceUserStudioRelation t=dao.get(TblDanceUserStudioRelation.class,id);

        if(null==t)throw new KeepJoyServiceException("id数据异常");

        if(!BcwConstant.STATUS_0_ING.equals(t.getStatus()))throw new KeepJoyServiceException("数据status异常");

        t.setStatus(status);
        t.setApproveDateTime(BcwCommonService.getNow());
        t.setApproveUserId(BcwUserHolder.getDanceUser().getUserId());
        dao.update(t);

        BcwUser du=BcwUserHolder.getDanceUser();
        BcwCommonService.setDanceUserStudios(du.getDanceUserInfo());
        RedisSsoProxy.setSubUserToRedis(du);

    }


    public void createStudio(TblDanceStudio studio, List<FileAttrBean> fileAttrBeanList) throws Exception {
        TblDanceStudio obj = new TblDanceStudio();

        BeanUtils.copyProperties(studio, obj);
        obj.setCreateDateTime(BcwCommonService.getNow());
        obj.setCreateUserId(BcwUserHolder.getDanceUser().getUserId());
        obj.setStatus(BcwConstant.STATUS_0_ING);

        if (null!=fileAttrBeanList && fileAttrBeanList.size()>0) {
            obj.setImg(fileAttrBeanList.get(0).getNewName());
        }
        dao.save(obj);
    }


    public TblDanceStudio getStudio(Integer id) throws Exception {
        TblDanceStudio tds=dao.get(TblDanceStudio.class,id);
        if(null==tds){
            throw new KeepJoyServiceException("id数据异常");
        }

        List<TblDanceUser> ms=dao.find("select new TblDanceUserStudioRelation(tdu.userId, tdu.aka, tdu.avatarUrl,tdusr.status)" +
                        " from TblDanceUser tdu,TblDanceUserStudioRelation tdusr " +
                        " where tdu.userId=tdusr.userId and tdusr.studioId=? and (tdusr.status=? or tdusr.status=?)",
                tds.getId(),BcwConstant.STATUS_2_YES,BcwConstant.STATUS_0_ING);
        tds.setMembers(ms);

        long allApplySize=dao.getJdbcTemplate().queryForObject(
                "select count(id) from TblDanceUserStudioRelation  " +
                        " where studioId=? and userId=? and (status=? or status=?) ",
                Long.class,tds.getId(),BcwUserHolder.getDanceUser().getUserId(),
                BcwConstant.STATUS_2_YES,BcwConstant.STATUS_0_ING);

        if(allApplySize>0){
            tds.setIsMeApply(true);
        }

        if(tds.getCreateUserId().equals(BcwUserHolder.getDanceUser().getUserId())){
            tds.setIsMine(true);
        }


        return tds;
    }

    public TblDanceUser unbindStudioMe(Integer id) throws Exception {

        TblDanceUserStudioRelation td=dao.findObjectFromListByHql(TblDanceUserStudioRelation.class,
                " from TblDanceUserStudioRelation where studioId=? and userId=? and status=? ",
                id,BcwUserHolder.getDanceUser().getUserId(),BcwConstant.STATUS_2_YES);
        if(null==td){
            throw new KeepJoyServiceException("id数据异常");
        }
        td.setStatus(BcwConstant.STATUS_3_REMOVE);
        dao.update(td);


        BcwUser du=BcwUserHolder.getDanceUser();
        BcwCommonService.setDanceUserStudios(du.getDanceUserInfo());
        RedisSsoProxy.setSubUserToRedis(du);

        return du.getDanceUserInfo();
    }



    public void updateStudio(TblDanceStudio studio, List<FileAttrBean> fileAttrBeanList) throws Exception {

        if(studio.getId()==null){
            throw new KeepJoyServiceException("id格式异常");
        }

        TblDanceStudio obj = dao.get(TblDanceStudio.class,studio.getId());
        if(null==obj){
            throw new KeepJoyServiceException("id数据异常");
        }

        BcwCommonService.commonGroupUpdate(obj,studio,fileAttrBeanList);

    }


    public void remove(Integer id)  {
        TblDanceStudio t=dao.get(TblDanceStudio.class,id);

        if(null==t)throw new KeepJoyServiceException("id数据异常");

        t.setStatus(BcwConstant.STATUS_3_REMOVE);
        t.setUpdateDateTime(BcwCommonService.getNow());
        t.setUpdateUserId(BcwUserHolder.getDanceUser().getUserId());
        dao.update(t);
    }
}
