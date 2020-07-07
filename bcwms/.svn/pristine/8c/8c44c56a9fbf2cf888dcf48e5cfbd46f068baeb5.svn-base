package com.bcwms.service.impl.studio;


import com.bcwms.BcwConstant;
import com.bcwms.entity.TblDanceCard;
import com.bcwms.entity.TblDanceStudioCard;
import com.bcwms.entity.TblDanceUserStudioCardDetail;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StudioCardServiceImpl  {

    @Autowired
    private KeepJoyDaoImpl dao;

    //创建STUDIO下的课卡
    public void create(TblDanceStudioCard obj) throws Exception {

        dao.save(obj);
    }


    public TblDanceStudioCard get(Integer id) throws Exception {
        TblDanceStudioCard objLocal = dao.get(TblDanceStudioCard.class, id);
        if (null == objLocal) {
            throw new KeepJoyServiceException("id数据异常");
        }
        return objLocal;
    }


    public void updateStudioCard(TblDanceCard obj, List<FileAttrBean> fileAttrBeanList) throws Exception {

        if (obj.getId() == null) {
            throw new KeepJoyServiceException("id格式异常");
        }

        TblDanceCard objLocal = dao.get(TblDanceCard.class, obj.getId());
        if (null == objLocal) {
            throw new KeepJoyServiceException("id数据异常");
        }

        if (StringUtils.isNotEmpty(obj.getStatus()) && !objLocal.getStatus().equals(obj.getStatus())) {
            objLocal.setStatus(obj.getStatus());
        }

        if (StringUtils.isNotEmpty(obj.getName()) && !objLocal.getName().equals(obj.getName())) {
            objLocal.setName(obj.getName());
        }

        if (null != obj.getTotal() && !objLocal.getTotal().equals(obj.getTotal())) {
            objLocal.setTotal(obj.getTotal());
        }

        dao.update(objLocal);
    }

    /***
     *
     * 用户充卡
     * @param userId
     * @param studioId
     * @param studioStoreId 门店id
     * @param studioCardId 卡id
     * @param beginDate 用户指定了开卡日期,只有周期卡可以指定
     */
    public void chargeUserCard(Integer userId, Integer studioId, Integer studioStoreId,
                               Integer studioCardId, Date beginDate) {


        TblDanceCard card = dao.get(TblDanceCard.class, studioCardId);
        if (null == card) {
            throw new KeepJoyServiceException("studioCardId数据异常");
        }

        Date now = BcwCommonService.getNow();

        TblDanceUserStudioCardDetail newCard = new TblDanceUserStudioCardDetail();
        newCard.setCreateDateTime(now);
        newCard.setCreateUserId(BcwUserHolder.getDanceUser().getUserId());

        newCard.setStudioCardType(card.getCardType());
        newCard.setUserId(userId);
        newCard.setStudioId(studioId);
        newCard.setStudioCardId(studioCardId);
        newCard.setStudioStoreId(studioStoreId);

        TblDanceUserStudioCardDetail his = null;

        if (null == beginDate) {//没有指定开卡日期的话,默认为当天
            beginDate = now;
        }

        if (card.getCardType().equals(TblDanceCard.CARDTYPE_1_DATE)) {//充周期卡

            //判断还有没有状态正常的时卡(包含过期和未过期)
            his = dao.findObjectFromListByHql(TblDanceUserStudioCardDetail.class,
                    " from TblDanceUserStudioCardDetail where studioId=? and studioCardType=? " +
                            " and userId=? ", studioId, TblDanceCard.CARDTYPE_1_DATE, userId);

            if (null == his) {//第一次充周期卡
                setEndDateByCardTypeDate(newCard, card, beginDate);
            } else {
                //判断是否已经过期
                if(his.getEndDate().compareTo(now)>=0){//没有过期
                    his.setStatus(BcwConstant.STATUS_3_REMOVE);
                }else{//已经过期
                    his.setStatus(BcwConstant.STATUS_4_END);
                }

                if(his.getEndDate().compareTo(beginDate)>0){//大于指定的开卡日期
                    beginDate=DateUtils.addDays(his.getEndDate(),1);
                }
                setEndDateByCardTypeDate(newCard, card, beginDate);

            }

        } else if (card.getCardType().equals(TblDanceCard.CARDTYPE_2_NUM)) {//充次卡

            //判断还有没有状态正常的次卡(包含过期和未过期)
            his = dao.findObjectFromListByHql(TblDanceUserStudioCardDetail.class,
                    " from TblDanceUserStudioCardDetail where studioId=? and studioCardType=? " +
                            " and userId=? ", studioId, TblDanceCard.CARDTYPE_2_NUM, userId);

            if (null == his) {//第一次充次卡
                setEndDateByCardTypeNum(newCard, card, beginDate, card.getTotal());

            } else {
                //判断是否已经过期
                if(his.getEndDate().compareTo(now)>0){//没有过期
                    //beginDate=DateUtils.addDays(his.getEndDate(),1);
                    his.setStatus(BcwConstant.STATUS_3_REMOVE);
                }else{//已经过期
                    beginDate=now;
                    his.setStatus(BcwConstant.STATUS_4_END);
                }

                int total=card.getTotal();
                if(null!=his.getTotal()){
                    total=total+his.getTotal();
                }
                his.setTotal(0);//历史可用数量置为0,因为新卡会累加上这个树值

                setEndDateByCardTypeNum(newCard, card, beginDate, total);
            }
        } else {
            throw new KeepJoyServiceException("卡类型异常");
        }
        if(null!=his){

            his.setUpdateDateTime(now);
            his.setUpdateUserId(BcwUserHolder.getDanceUser().getUserId());
            dao.update(his);
        }
        newCard.setBeginDate(beginDate);

        if(newCard.getBeginDate().compareTo(now)>0){//还没有开卡
            newCard.setStatus(BcwConstant.STATUS_1_NO);
        }else{
            newCard.setStatus(BcwConstant.STATUS_2_YES);
        }

        dao.save(newCard);



    }

    public void setEndDateByCardTypeNum(TblDanceUserStudioCardDetail newCard, TblDanceCard card, Date beginDate, Integer total) {
        if (null != card.getValidityTime()) {//次卡需要有效期时间
            newCard.setEndDate(DateUtils.addMonths(beginDate, card.getValidityTime()));
        } else {
            throw new KeepJoyServiceException("次卡有效期不能为空");
        }
        //第一次,初始都是总数量
        newCard.setRemaind(total);
        newCard.setTotal(total);
    }

    public void setEndDateByCardTypeDate(TblDanceUserStudioCardDetail newCard, TblDanceCard card, Date beginDate) {
        if (card.getCardType().equals(TblDanceCard.CARDTIMETYPE_1_YEAR)) {//年卡
            newCard.setEndDate(DateUtils.addYears(newCard.getBeginDate(), 1));//加1年
        } else if (card.getCardType().equals(TblDanceCard.CARDTIMETYPE_2_SEASON)) {//季卡
            newCard.setEndDate(DateUtils.addMonths(newCard.getBeginDate(), 3));//加3个月
        } else if (card.getCardType().equals(TblDanceCard.CARDTIMETYPE_3_MONTH)) {//月卡
            newCard.setEndDate(DateUtils.addMonths(newCard.getBeginDate(), 1));//加1个月
        } else if (card.getCardType().equals(TblDanceCard.CARDTIMETYPE_4_WEEK)) {//周卡
            newCard.setEndDate(DateUtils.addDays(newCard.getBeginDate(), 7));//加7天
        } else {
            throw new KeepJoyServiceException("卡时类型异常");
        }
    }

}
