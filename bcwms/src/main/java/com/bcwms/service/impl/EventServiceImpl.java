package com.bcwms.service.impl;


import com.bcwms.BcwConstant;
import com.bcwms.entity.*;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.bean.pagination.Pagination;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.core.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EventServiceImpl {
    @Autowired
    private KeepJoyDaoImpl dao;


    public void create(TblDanceEvent event, List<FileAttrBean> fileAttrBeanList,
                       String beginTime, String endTime) throws Exception {
        TblDanceEvent obj = new TblDanceEvent();
        BeanUtils.copyProperties(event, obj);

        obj.setCreateDateTime(BcwCommonService.getNow());
        obj.setCreateUserId(BcwUserHolder.getDanceUser().getUserId());
        obj.setStatus(BcwConstant.STATUS_0_ING);
        if (null != fileAttrBeanList && fileAttrBeanList.size() > 0) {
            obj.setImg(fileAttrBeanList.get(0).getNewName());
        }

        if (StringUtils.isNotEmpty(beginTime)) {
            obj.setBeginTime(DateUtil.stringToDateByDateFormat(beginTime, DateUtil.DATEFORMAT_HH_MM));
        }
        if (StringUtils.isNotEmpty(endTime)) {
            obj.setEndTime(DateUtil.stringToDateByDateFormat(endTime, DateUtil.DATEFORMAT_HH_MM));
        }

        if(TblDanceEvent.EVENTTYPE_9_PERFORMANCE.equals(event.getEventType())){//商演
            if(null==event.getMaxPersonNum() || 0==event.getMaxPersonNum() ){
                throw new KeepJoyServiceException("商演活动请选择最大人数");
            }
            if(null==event.getApplyEndTime()){
                throw new KeepJoyServiceException("商演活动请选择报名截止时间");
            }
        }

        dao.save(obj);

        BcwCommonService.saveAttach(fileAttrBeanList, obj.getId(), BcwConstant.TYPE_EVENT);

    }


    public TblDanceEvent get(Integer id) throws Exception {
        TblDanceEvent tde = dao.get(TblDanceEvent.class, id);
        if (null == tde) {
            throw new KeepJoyServiceException("id数据异常");
        }

        List<TblDanceEventAttach> attachs = dao.find(" from TblDanceEventAttach where eventId=? ", id);


        tde.setAttachs(attachs);

        Integer userId = BcwUserHolder.getDanceUser().getUserId();


        //判断我是否申请过
        TblDanceUserEventRelation tr = dao.findObjectFromListByHql(TblDanceUserEventRelation.class,
                " from TblDanceUserEventRelation  " +
                        " where eventId=? and userId=? and (status=? or status=?)",
                tde.getId(), BcwUserHolder.getDanceUser().getUserId(),
                BcwConstant.STATUS_2_YES, BcwConstant.STATUS_0_ING);

        if (null != tr) {
            tde.setRelation(tr);
            tde.setIsMeApply(true);

            //如果是考团
            //设置考团打分按钮开关
            if (TblDanceEvent.EVENTTYPE_1_GROUP_EXAM.equals(tde.getEventType())
                    && null != tr.getSignDateTime()) {//签到过
                TblDanceCrewTermUserDetail t = dao.findObjectFromListByHql(TblDanceCrewTermUserDetail.class,
                        "from TblDanceCrewTermUserDetail where eventId=? and crewId=? " +
                                " and userId=? ",
                        tde.getId(), tde.getFounderId(), userId);


                if (null != t) {
                    if (t.getCrewUserType().equals(TblDanceCrewTermUserDetail.CREWUSERTYPE_1_TEACHER)) {//老师
                        if (tde.getStatus().equals(BcwConstant.STATUS_2_YES)) {//审核通过并且正在进行的
                            tde.setScoreInCrewExamingId(t.getId());
                        } else if (tde.getStatus().equals(BcwConstant.STATUS_4_END)) {//本次考团已经结束
                            tde.setSearchExamCrewScoreId(t.getId());
                        }

                    }
                }
            }

            //申请通过,并且举办日期是小于等于今天,并且没有签到过
            //设置签到按钮开关
            if (null == tr.getSignDateTime() && BcwConstant.STATUS_2_YES.equals(tr.getStatus()) &&
                    BcwCommonService.getNowYYYYMMDD().compareTo(tde.getStageDate()) >= 0) {
                tde.setSignId(tr.getId());
            }

        }

        //判断是不是我发布的,我发布的才能看
        if (tde.getCreateUserId().equals(userId)) {
            tde.setIsMine(true);

            //查询参加的人
            //设置报名的名单
            List<TblDanceUser> ms = dao.find("select new TblDanceUserEventRelation(tdu.userId, tdu.aka, tdu.avatarUrl,tduer.status)" +
                            " from TblDanceUser tdu,TblDanceUserEventRelation tduer " +
                            " where tdu.userId=tduer.userId and tduer.eventId=? " +
                            " and (tduer.status=? or tduer.status=? or tduer.status=?)",
                    tde.getId(), BcwConstant.STATUS_2_YES, BcwConstant.STATUS_0_ING, BcwConstant.STATUS_4_END);
            tde.setMembers(ms);
        }

        //如果是结算成功的
        if(BcwConstant.STATUS_4_END.equals(tde.getStatus())){

        }

        return tde;
    }

    public void update(TblDanceEvent event, List<FileAttrBean> fileAttrBeanList,
                       String beginTime, String endTime) throws ParseException, IOException, InterruptedException {
        if (event.getId() == null) {
            throw new KeepJoyServiceException("id格式异常");
        }

        TblDanceEvent obj = dao.get(TblDanceEvent.class, event.getId());
        if (null == obj) {
            throw new KeepJoyServiceException("id数据异常");
        }

        boolean isUpdate = false;
        if (StringUtils.isNotEmpty(event.getName()) && !event.getName().equals(obj.getName())) {
            isUpdate = true;
            obj.setName(event.getName());
        }

        if (null != event.getStageDate()) {
            if (null != obj.getStageDate()) {
                if (event.getStageDate().compareTo(obj.getStageDate()) != 0) {
                    isUpdate = true;
                }
            } else {
                isUpdate = true;
            }
            obj.setStageDate(event.getStageDate());
        }

        if (StringUtils.isNotEmpty(event.getEventType()) && !event.getEventType().equals(obj.getEventType())) {
            isUpdate = true;
            obj.setEventType(event.getEventType());
        }

        if (StringUtils.isNotEmpty(event.getAddress()) && !event.getAddress().equals(obj.getAddress())) {
            isUpdate = true;
            obj.setAddress(event.getAddress());
        }

        if (StringUtils.isNotEmpty(event.getInfo()) && !event.getInfo().equals(obj.getInfo())) {
            isUpdate = true;
            obj.setInfo(event.getInfo());
        }

        if (StringUtils.isNotEmpty(event.getImg()) && !event.getImg().equals(obj.getImg())) {
            isUpdate = true;
            obj.setImg(event.getImg());
        }

        if (StringUtils.isNotEmpty(event.getCity()) && !event.getCity().equals(obj.getCity())) {
            isUpdate = true;
            obj.setCity(event.getCity());
        }

        if (StringUtils.isNotEmpty(event.getDistrict()) && !event.getDistrict().equals(obj.getDistrict())) {
            isUpdate = true;
            obj.setDistrict(event.getDistrict());
        }

        if (StringUtils.isNotEmpty(event.getLatitude()) && !event.getLatitude().equals(obj.getLatitude())) {
            isUpdate = true;
            obj.setLatitude(event.getLatitude());
        }

        if (StringUtils.isNotEmpty(event.getLongitude()) && !event.getLongitude().equals(obj.getLongitude())) {
            isUpdate = true;
            obj.setLongitude(event.getLongitude());
        }

        if (event.getMaxPersonNum() != null && !event.getMaxPersonNum().equals(obj.getMaxPersonNum())) {
            isUpdate = true;
            obj.setMaxPersonNum(event.getMaxPersonNum());
        }

        if (StringUtils.isNotEmpty(beginTime)) {
            Date bt = DateUtil.stringToDateByDateFormat(beginTime, DateUtil.DATEFORMAT_HH_MM);
            if (null == obj.getBeginTime()) {
                isUpdate = true;
                obj.setBeginTime(bt);
            } else if (bt.compareTo(obj.getBeginTime()) != 0) {
                isUpdate = true;
                obj.setBeginTime(bt);
            }

        }
        if (StringUtils.isNotEmpty(endTime)) {
            Date et = DateUtil.stringToDateByDateFormat(endTime, DateUtil.DATEFORMAT_HH_MM);
            if (null == obj.getEndTime()) {
                isUpdate = true;
                obj.setEndTime(et);
            } else if (et.compareTo(obj.getEndTime()) != 0) {
                isUpdate = true;
                obj.setEndTime(et);
            }
        }

        if (isUpdate) dao.update(obj);

        if (fileAttrBeanList != null && fileAttrBeanList.size() >0) {
            dao.bulkUpdate(" delete from TblDanceEventAttach where eventId=? ", event.getId());
            BcwCommonService.saveAttach(fileAttrBeanList, obj.getId(), BcwConstant.TYPE_EVENT);
            obj.setImg(fileAttrBeanList.get(0).getNewName());
        }


    }

    public Pagination search(String name, String keyword, String eventType,
                             Integer currentPage, Integer pageSize) {

        if (null == currentPage) {
            currentPage = 1;
        }

        if (null == pageSize || pageSize > 50) {
            pageSize = 5;
        }


        Query queryCount;
        Query query;

        List<String> names = new ArrayList<String>();
        List<Object> vals = new ArrayList<Object>();

        String sqlCount = " select count(tde.id) from TblDanceEvent tde where 1=1 ";

        String sql = " select "
                + " new TblDanceEvent(" +
                " tde.id,tde.name,tde.createUserId,tde.createDateTime,"
                + " tde.address,tde.stageDate,tde.beginTime,tde.endTime, "
                + " tde.eventType,tde.status,tde.latitude,tde.longitude,tde.maxPersonNum,"
                + " tde.contractAddress,tde.settleDateTime ) "
                + " from TblDanceEvent tde where 1=1 ";


        String where = "  and (tde.status= " + BcwConstant.STATUS_2_YES+" or tde.status= "+BcwConstant.STATUS_4_END+")";

        if (StringUtils.isNotEmpty(eventType)) {
            names.add("eventType");
            vals.add(eventType);
            where = where + " and tde.eventType=:eventType ";
        }
        if (StringUtils.isNotEmpty(keyword)) {
            names.add("keyword1");
            vals.add("%" + keyword + "%");
            names.add("keyword2");
            vals.add("%" + keyword + "%");
            names.add("keyword3");
            vals.add("%" + keyword + "%");
            where = where + " and (tde.name like:keyword1 or tde.address like:keyword2 or tde.info like:keyword3) ";
        }

        Session s = dao.getCurrentSession();
        queryCount = s.createQuery(sqlCount + where);

        query = s.createQuery(sql + where + " order by tde.stageDate desc ");


        query.setFirstResult((currentPage - 1) * pageSize);
        query.setMaxResults(pageSize);
        BcwCommonService.setQueryParameter(queryCount, names, vals);
        BcwCommonService.setQueryParameter(query, names, vals);


        Long count = (long) queryCount.list().get(0);

        if (count == 0) {
            return new Pagination(new ArrayList<TblDanceEvent>(), 0, currentPage, pageSize);
        }

        if ((currentPage - 1) * pageSize >= count) {
            throw new KeepJoyServiceException("已经最后一页了");
        }


        List<TblDanceEvent> events = query.list();

        Query queryAttach = s.createQuery("from TblDanceEventAttach where eventId=?");
        queryAttach.setFirstResult(0);
        queryAttach.setMaxResults(3);


        for (TblDanceEvent tde : events) {
            queryAttach.setParameter(0, tde.getId());
            tde.setAttachs(queryAttach.list());
        }


        return new Pagination(events, count.intValue(), currentPage, pageSize);

    }


    public void remove(Integer id)  {
        TblDanceEvent t=dao.get(TblDanceEvent.class,id);

        if(null==t)throw new KeepJoyServiceException("id数据异常");

        t.setStatus(BcwConstant.STATUS_3_REMOVE);
        t.setUpdateDateTime(BcwCommonService.getNow());
        t.setUpdateUserId(BcwUserHolder.getDanceUser().getUserId());
        dao.update(t);
    }

}
