package com.bcwms.entity;

import com.bcwms.BcwConstant;
import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.util.DateUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/***
 * 用户报名活动的关系表-临时活动与用户关系总表,后期根据类型分到具体的业务表中
 */
@Entity
public class TblDanceUserEventRelation extends TblDanceCommonAttrRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String EVENTUSERTYPE_1_JUDGE="1";//裁判
    public static final String EVENTUSERTYPE_2_GUEST="2";//guest
    public static final String EVENTUSERTYPE_3_STAFF="3";//工作人员

    @Column(name = "eventId")
    private Integer eventId;

    @Column(name = "signDateTime")
    private Date signDateTime;//活动当场签到时间

    @Column(name = "eventUserType",columnDefinition = "CHAR",length = 2)
    public String eventUserType;//活动的用户类型,ex:裁判,guest,现场工作人员等等




    public  TblDanceUserEventRelation(){

    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Date getSignDateTime() {
        return signDateTime;
    }

    public void setSignDateTime(Date signDateTime) {
        this.signDateTime = signDateTime;
    }

    public String getEventUserType() {
        return eventUserType;
    }

    public void setEventUserType(String eventUserType) {
        this.eventUserType = eventUserType;
    }



    @Transient
    private String eventName;
    @Transient
    private String eventImg;
    @Transient
    private String yearMonth;

    @Transient
    public String getEventName() {
        return eventName;
    }
    @Transient
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    @Transient
    public String getEventImg() {
        return BcwCommonService.getImgDis(this.eventImg,BcwConstant.TYPE_EVENT,null);
    }
    @Transient
    public void setEventImg(String eventImg) {
        this.eventImg = eventImg;
    }





    //SearchEventApply
    public TblDanceUserEventRelation(Integer id, Integer eventId, String eventName, String eventImg,
                                    String aka, String avatarUrl,Integer userId,
                                     Date createDateTime,String status,Date signDateTime) {
        this.id = id;
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventImg = eventImg;
        this.aka=aka;
        this.avatarUrl=avatarUrl;
        this.userId=userId;
        this.createDateTime=createDateTime;
        this.status=status;
        this.signDateTime=signDateTime;
    }

    //SearchEventApplyMe
    public TblDanceUserEventRelation(Integer id, Integer eventId, String eventName, String eventImg,
                                    Integer userId,Date createDateTime,String status,String yearMonth) {
        this.id = id;
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventImg = eventImg;
        this.userId=userId;
        this.createDateTime=createDateTime;
        this.status=status;
        this.yearMonth=yearMonth;
    }

    //GetEvent
    public TblDanceUserEventRelation(Integer userId,String aka, String avatarUrl,String status){
        this.userId=userId;
        this.aka=aka;
        this.avatarUrl=avatarUrl;
        this.status=status;
    }

    @Transient
    public String getNameDis() {
        return this.eventName;
    }

    @Transient
    public String getImgDis() {
        return getEventImg();
    }

    @Transient
    public String getImgDisMin() {
        return BcwCommonService.getImgDisMin(this.eventImg,BcwConstant.TYPE_EVENT,this.yearMonth);

    }


    @Transient
    public String getSignDateTimeDis(){
        return DateUtil.dateToStringByDateFormat(this.signDateTime,DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS);
    }
}
