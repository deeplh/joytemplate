package com.bcwms.service.impl;


import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceUserPoint;
import com.bcwms.entity.TblDanceClock;
import com.bcwms.entity.TblDanceClockAttach;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.bcwms.service.impl.user.UserPointServiceImpl;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.bean.pagination.Pagination;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional(rollbackFor=Exception.class)
public class ClockServiceImpl {

    @Resource
    private KeepJoyDaoImpl dao;

    @Autowired
    private UserPointServiceImpl userPointService;

    public Map countClockMe() throws Exception {
        Map map=new HashMap<>();
        Integer maxContinue=dao.findObjectFromListByHql(Integer.class,"select max(continueClockNum) from TblDanceClock where userId=? and status!="+BcwConstant.STATUS_3_REMOVE,
                BcwUserHolder.getDanceUser().getUserId());
        if(null!=maxContinue){
            map.put("maxContinue",maxContinue);
        }
        List<Integer> count=dao.find("select count(id) from TblDanceClock where userId=? and status!="+BcwConstant.STATUS_3_REMOVE,
                BcwUserHolder.getDanceUser().getUserId());
        if(null!=count && count.size()>0){
            map.put("count",count.get(0));
        }

        return map;

    }


    public TblDanceClock createClock(TblDanceClock clock, List<FileAttrBean> fileAttrBeanList,
                            String beginTime, String endTime) throws Exception {
        if (null==fileAttrBeanList || fileAttrBeanList.size() == 0) {
            throw new KeepJoyServiceException("请至少上传一张图片");
        }

        Integer userId=BcwUserHolder.getDanceUser().getUserId();
        List<TblDanceClock> last = dao.getJdbcTemplate().query(
                " select clockDate,continueClockNum from TblDanceClock where userId=? order by id desc limit 0,1 ",
                new BeanPropertyRowMapper<>(TblDanceClock.class), userId);


        TblDanceClock obj = new TblDanceClock();
        BeanUtils.copyProperties(clock, obj);

        Date now = BcwCommonService.getNow();



        int point = 1;//默认第一次是1分
        if (null != last && last.size() > 0) {//有历史签到信息
            if(last.get(0).getClockDate().compareTo(BcwCommonService.getNowYYYYMMDD())==0){
                throw new KeepJoyServiceException("您今天已经打过卡了");
            }
            //如果最后一次签到日期和当前日期相差一天,说明是连续签到,积分翻倍
            if (DateUtils.addDays(last.get(0).getClockDate(), 1).compareTo(BcwCommonService.getNowYYYYMMDD()) == 0) {
                obj.setContinueClockNum(last.get(0).getContinueClockNum() + 1);
                point=2;
            } else {
                obj.setContinueClockNum(1);
            }
        } else {
            obj.setContinueClockNum(1);
        }
        userPointService.createUserPoint(userId,TblDanceUserPoint.POINTTYPE_2_CLOCK,
                BcwConstant.TYPE_CLOCK,obj.getId(),point);


        obj.setCreateDateTime(now);
        obj.setClockDate(obj.getCreateDateTime());
        obj.setUserId(BcwUserHolder.getDanceUser().getUserId());
        obj.setStatus(BcwConstant.STATUS_2_YES);
        dao.save(obj);


        obj.setAttachs((List<TblDanceClockAttach>) BcwCommonService.saveAttach(fileAttrBeanList, obj.getId(), BcwConstant.TYPE_CLOCK));

        return obj;
    }


    public TblDanceClock getClock(Integer id) throws Exception {
        TblDanceClock tdc=dao.get(TblDanceClock.class,id);
        if(null==tdc){
            throw new KeepJoyServiceException("id数据异常");
        }
        if(tdc.getUserId().equals(BcwUserHolder.getDanceUser().getUserId())){
            tdc.setMine(true);
        }
        tdc.setAttachs(dao.find("from TblDanceClockAttach where clockId=? ",tdc.getId()));


        //查询这个日期的比我大连续打卡次数少的用户数量
        Long lessNum=dao.findObjectFromListByHql(Long.class," select count(userId) " +
                "  from TblDanceClock where continueClockNum<? and clockDate=? and status!=? ",tdc.getContinueClockNum(),tdc.getClockDate(),BcwConstant.STATUS_3_REMOVE);

        Long count=dao.findObjectFromListByHql(Long.class," select count(userId) " +
                "  from TblDanceClock where clockDate=? and status!=? ",tdc.getClockDate(),BcwConstant.STATUS_3_REMOVE);


        Long a;
        if(1==count){//说明这个日期就我一个人打卡
            a=100l;
        }else{
            lessNum=(lessNum==null?0:lessNum);
            a=(lessNum/(count-1))*100;
        }

        tdc.setContinueLessMePercent(a.intValue());

        return tdc;
    }

    public void removeClock(Integer id)  {
        TblDanceClock c=dao.get(TblDanceClock.class,id);
        if(null==c)throw new KeepJoyServiceException("id数据异常");

        c.setStatus(BcwConstant.STATUS_3_REMOVE);
        dao.update(c);

    }

    public Pagination searchClock(String keyword,
                                  Integer currentPage,Integer pageSize){

        if(null==currentPage){
            currentPage=1;
        }

        if(null==pageSize || pageSize>50) {
            pageSize=5;
        }



        Query queryCount;
        Query query;

        List<String> names=new ArrayList<String>();
        List<Object> vals=new ArrayList<Object>();

        String sqlCount=" select count(tdc.id) from TblDanceClock tdc,TblDanceUser tdu " +
                " where tdu.userId=tdc.userId and tdc.status!="+BcwConstant.STATUS_3_REMOVE;

        String sql=" select "
                + " new TblDanceClock(tdc.id, tdc.userId, tdc.info, tdc.createDateTime,"
                + " tdc.continueClockNum, tdc.video, tdc.address,"
                + " tdu.aka,tdu.avatarUrl,tdc.width,tdc.height) "
                + " from TblDanceClock tdc,TblDanceUser tdu " +
                " where tdu.userId=tdc.userId and tdc.status!="+BcwConstant.STATUS_3_REMOVE;



        String where=" " ;


        if(StringUtils.isNotEmpty(keyword)){
            names.add("keyword");
            vals.add("%"+keyword+"%");

            where=where+" and (tdc.info like:keyword) ";
        }

        Session s=dao.getCurrentSession();
        queryCount=s.createQuery(sqlCount+where);

        query=s.createQuery(sql+where+
                " order by tdc.id desc ");


        query.setFirstResult((currentPage-1)*pageSize);
        query.setMaxResults(pageSize);
        BcwCommonService.setQueryParameter(queryCount,names,vals);
        BcwCommonService.setQueryParameter(query,names,vals);



        Long count=(long) queryCount.list().get(0);

        if(count==0){
            return new Pagination(new ArrayList<TblDanceClock>(),0,currentPage,pageSize);
        }

        if((currentPage-1)*pageSize>=count){
            throw new KeepJoyServiceException("已经最后一页了");
        }


        List<TblDanceClock> clocks=query.list();

        Query queryAttach=s.createQuery("from TblDanceClockAttach where clockId=?");
        queryAttach.setFirstResult(0);


        for(TblDanceClock tdc:clocks){
            queryAttach.setParameter(0,tdc.getId());
            if(StringUtils.isNotEmpty(tdc.getVideoDis())){
                queryAttach.setMaxResults(1);
            }else{
                queryAttach.setMaxResults(3);
            }
            tdc.setAttachs(queryAttach.list());
        }



        return new Pagination(clocks,count.intValue(),currentPage,pageSize);
    }
}
