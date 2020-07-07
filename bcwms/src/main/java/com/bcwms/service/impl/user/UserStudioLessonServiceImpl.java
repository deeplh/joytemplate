package com.bcwms.service.impl.user;

import com.bcwms.BcwConstant;
import com.bcwms.entity.*;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional(rollbackFor = Exception.class)
public class UserStudioLessonServiceImpl {

    @Autowired
    private KeepJoyDaoImpl dao;

    /***
     * 用户消费课时
     * @param studioStoreLessonDetailId
     * @param userId
     */
    public void consume(Integer studioStoreLessonDetailId, Integer userId) {
        TblDanceStudioStoreLessonDetail d = dao.get(TblDanceStudioStoreLessonDetail.class, studioStoreLessonDetailId);
        if (null==d) {
            throw new KeepJoyServiceException("studioStoreLessonDetailId数据异常");
        }
        List<TblDanceUserStudioCardDetail> myCardDetailList=dao.find(
                " from TblDanceUserStudioCardDetail where endDate>=CURRENT_DATE() " +
                        " and status=? and userId=? ",BcwConstant.STATUS_2_YES,userId);

        if(null==myCardDetailList || myCardDetailList.size()==0){
            throw new KeepJoyServiceException("卡未激活或者卡已全部过期");
        }else{
            TblDanceUserStudioLessonDetail lessonDetail=new TblDanceUserStudioLessonDetail();
            lessonDetail.setStudioStoreLessonDetailId(d.getId());
            lessonDetail.setSignDateTime(BcwCommonService.getNow());
            lessonDetail.setStudioId(d.getStudioId());
            lessonDetail.setStudioStoreId(d.getStudioStoreId());
            lessonDetail.setUserId(userId);


            if(myCardDetailList.size()>2){
                throw new KeepJoyServiceException("卡明细数据异常");
            }
            TblDanceUserStudioCardDetail cardDate=null;//时卡
            TblDanceUserStudioCardDetail cardNum=null;//次卡

            for(TblDanceUserStudioCardDetail t:myCardDetailList){
                if(t.getStudioCardType().equals(TblDanceCard.CARDTYPE_1_DATE)){
                    cardDate=t;
                }else if(t.getStudioCardType().equals(TblDanceCard.CARDTYPE_2_NUM)){
                    cardNum=t;
                }
            }

            if(null!=cardDate){//优先考虑时卡
                lessonDetail.setUserStudioCardDetailId(cardDate.getId());
            }else{
                if(null!=cardNum){//次卡,需要扣次数

                    if(cardNum.getTotal()==0){
                        throw new KeepJoyServiceException("卡余额为0");
                    }

                    lessonDetail.setUserStudioCardDetailId(cardNum.getId());
                    cardNum.setTotal(cardNum.getTotal()-1);
                    cardNum.setRemaind(cardNum.getRemaind()-1);
                    dao.update(cardNum);

                }else{
                    throw new KeepJoyServiceException("卡数据异常");
                }
            }

           dao.save(lessonDetail);


        }

    }

}
