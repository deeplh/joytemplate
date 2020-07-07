package com.bcwms.service.impl.user;

import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceUser;
import com.bcwms.entity.TblDanceUserPoint;
import com.bcwms.security.BcwUser;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.core.util.DateUtil;
import com.keepjoy.security.proxy.RedisSsoProxy;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Service
@Transactional(rollbackFor=Exception.class)
public class UserPointServiceImpl {

    @Autowired
    private KeepJoyDaoImpl dao;

    public void createUserPoint(Integer userId,String pointType,String sourceType,
                                Integer sourceId,Integer point){
        if(userId==null) throw new KeepJoyServiceException("point->createUserPoint->Illegal userId !");
        Date nowDate = BcwCommonService.getNow();
        TblDanceUserPoint userPoint = new TblDanceUserPoint();
        //base
        userPoint.setPoint(point);
        userPoint.setPointType(pointType);
        userPoint.setSourceId(sourceId);
        userPoint.setSourceType(sourceType);
        userPoint.setUserId(userId);
        userPoint.setCreateDateTime(nowDate);
        userPoint.setCreateUserId(BcwUserHolder.getDanceUser().getUserId());
        userPoint.setStatus(BcwConstant.STATUS_2_YES);

//        if(TblDanceUserPoint.POINTTYPE_1_STUDIO_LESSON.equals(pointType)){
//
//        }else if(TblDanceUserPoint.POINTTYPE_2_CLOCK.equals(pointType)){//打卡产生的积分,1次1分,连续签到1次2分
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(nowDate);
//            cal.add(Calendar.DATE,-1);
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//
//            //先找上一天有无打卡,默认认为最多一条
//            List<Map<String,Object>> pointList = dao.findObjectFromListByHql(TblDanceUserPoint.class,
//                    "from TblDanceUserPoint where DATE_FORMAT(createDateTime,'%Y%m%d') = ? ",sdf.format(cal.getTime()));
//            //连续打卡记2分
//            if(pointList.size()>0){
//                point = 2;
//            }else{
//                point = 1;
//            }
//        }


        //bussiness
        userPoint.setPoint(point);
        //上链相关
        userPoint.setSettleDateTime(nowDate);

        dao.save(userPoint);

    }

    public Integer getUserPoint(Integer userId){
        Integer pointSum = 0;
        BigDecimal pointSumDec = (BigDecimal)dao.getJdbcTemplate().queryForList("select SUM(point) as pointSum from TblDanceUserPoint where userId = ? ",userId).get(0).get("pointSum");
        if(pointSumDec == null) pointSumDec = new BigDecimal(0);
        return pointSumDec.intValue();
    }
}
