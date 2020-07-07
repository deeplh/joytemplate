package com.bcwms.service.impl.crew;


import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceCrew;
import com.bcwms.entity.TblDanceStudio;
import com.bcwms.entity.TblDanceUser;

import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class CrewServiceImpl {

    @Autowired
    private KeepJoyDaoImpl dao;


    public void create(TblDanceCrew crew, List<FileAttrBean> fileAttrBeanList) throws Exception {
        TblDanceCrew obj=new TblDanceCrew();
        BeanUtils.copyProperties(crew,obj);
        obj.setCreateDateTime(BcwCommonService.getNow());
        obj.setCreateUserId(BcwUserHolder.getDanceUser().getUserId());
        obj.setStatus(BcwConstant.STATUS_0_ING);


        if (null!=fileAttrBeanList && fileAttrBeanList.size()>0) {
            obj.setImg(fileAttrBeanList.get(0).getNewName());
        }

        dao.save(obj);
    }


    public TblDanceCrew get(Integer id) throws Exception {
        TblDanceCrew tdc=dao.get(TblDanceCrew.class,id);
        if(null==tdc){
            throw new KeepJoyServiceException("id数据异常");
        }

        List<TblDanceUser> ms=dao.find("select new TblDanceUserCrewRelation(tdu.userId, tdu.aka, tdu.avatarUrl,tducr.status)" +
                        " from TblDanceUser tdu,TblDanceUserCrewRelation tducr " +
                        " where tdu.userId=tducr.userId and tducr.crewId=? and (tducr.status=? or tducr.status=?)",
                tdc.getId(),BcwConstant.STATUS_2_YES,BcwConstant.STATUS_0_ING);
        tdc.setMembers(ms);

        long allApplySize=dao.getJdbcTemplate().queryForObject(
                "select count(tducr.id) from TblDanceUserCrewRelation tducr " +
                        " where tducr.crewId=? and tducr.userId=? and (tducr.status=? or tducr.status=?) ",
                Long.class,tdc.getId(),BcwUserHolder.getDanceUser().getUserId(),
                BcwConstant.STATUS_2_YES,BcwConstant.STATUS_0_ING);

        if(allApplySize>0){
            tdc.setIsMeApply(true);
        }

        if(tdc.getCreateUserId().equals(BcwUserHolder.getDanceUser().getUserId())){
            tdc.setIsMine(true);
        }

        return tdc;
    }




    public void update(TblDanceCrew crew, List<FileAttrBean> fileAttrBeanList) throws Exception {

        if(crew.getId()==null){
            throw new KeepJoyServiceException("id格式异常");
        }

        TblDanceCrew obj = dao.get(TblDanceCrew.class,crew.getId());
        if(null==obj){
            throw new KeepJoyServiceException("id数据异常");
        }

        BcwCommonService.commonGroupUpdate(obj,crew,fileAttrBeanList);


    }



    public void remove(Integer id)  {
        TblDanceCrew t=dao.get(TblDanceCrew.class,id);

        if(null==t)throw new KeepJoyServiceException("id数据异常");

        t.setStatus(BcwConstant.STATUS_3_REMOVE);
        t.setUpdateDateTime(BcwCommonService.getNow());
        t.setUpdateUserId(BcwUserHolder.getDanceUser().getUserId());
        dao.update(t);
    }
}
