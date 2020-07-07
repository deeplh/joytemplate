package com.bcwms.entity;

import com.bcwms.BcwConstant;

import com.bcwms.service.BcwCommonService;
import com.keepjoy.core.module.datadict.DataDictFactory;
import com.keepjoy.core.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Entity
public class TblDanceEvent extends TblDanceCommonAttrLocation implements Serializable {

    private static final long serialVersionUID = 1L;


    public static final String EVENTTYPE_0_OTHER="0";//其他
    public static final String EVENTTYPE_1_GROUP_EXAM="1";//考团
    public static final String EVENTTYPE_2_MATCH="2";//比赛
    public static final String EVENTTYPE_3_ASSEMBLE_TRAIN="3";//集训
    public static final String EVENTTYPE_4_MASTER="4";//大师课
    public static final String EVENTTYPE_9_PERFORMANCE="9";//商演

    public static final int FOUNDERTYPE_1_USER=1;//个人
    public static final int FOUNDERTYPE_2_CREW=2;//crew
    public static final int FOUNDERTYPE_3_STUDIO=3;//studio

    private String name;
    private Date stageDate;
    @DateTimeFormat(pattern="HH:mm")
    private Date beginTime;
    @DateTimeFormat(pattern="HH:mm")
    private Date endTime;
    private String eventType;
    private Integer maxPersonNum;

    @Column(name = "founderId")
    private Integer founderId;//活动发起方的id

    @Column(name = "founderType")
    private Integer founderType;//活动发起方的类型


    @Column(name = "applyEndTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applyEndTime;//申请截止时间

    @Transient
    private String yearMonth;
    @Transient
    public String getYearMonth() {
        return yearMonth;
    }
    @Transient
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public TblDanceEvent(){

    }

    //search
    public TblDanceEvent(Integer id, String name, Integer createUserId, Date createDateTime,
                         String address, Date stageDate, Date beginTime, Date endTime,
                         String eventType, String status,String latitude,
                         String longitude,Integer maxPersonNum,String contractAddress,Date settleDateTime) {
        this.id = id;
        this.name = name;
        this.createUserId = createUserId;
        this.createDateTime = createDateTime;
        this.address = address;
        this.stageDate = stageDate;
        this.beginTime=beginTime;
        this.endTime=endTime;
        this.eventType = eventType;
        this.maxPersonNum = maxPersonNum;
        this.status=status;
        this.latitude=latitude;
        this.longitude=longitude;
        this.contractAddress=contractAddress;
        this.settleDateTime=settleDateTime;

    }

    public TblDanceEvent(Integer id, String name, Integer createUserId, Date createDateTime,
                         String address, Date stageDate, Date beginTime, Date endTime,
                         String eventType, Integer maxPersonNum, String status,
                         String nickName, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.createUserId = createUserId;
        this.createDateTime = createDateTime;
        this.address = address;
        this.stageDate = stageDate;
        this.beginTime=beginTime;
        this.endTime=endTime;
        this.eventType = eventType;
        this.maxPersonNum = maxPersonNum;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.status=status;
    }


    //SearchEventMe
    public TblDanceEvent(Integer id,String name, Date stageDate,String img,String city,
                         String eventType, Integer maxPersonNum,
                         Integer founderId, Integer founderType,String status,
                         Date createDateTime,String yearMonth,
            String contractAddress,Date settleDateTime) {
        this.id=id;
        this.name = name;
        this.stageDate = stageDate;
        this.img=img;
        this.city=city;
        this.eventType = eventType;
        this.maxPersonNum = maxPersonNum;
        this.founderId = founderId;
        this.founderType = founderType;
        this.status=status;
        this.createDateTime=createDateTime;
        this.yearMonth=yearMonth;
        this.contractAddress=contractAddress;
        this.settleDateTime=settleDateTime;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Basic
    @Column(name = "beginTime", nullable = true)
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Basic
    @Column(name = "eventType", nullable = true, length = 2)
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Basic
    @Column(name = "endTime", nullable = true)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "stageDate", nullable = true,columnDefinition = "DATE")
    public Date getStageDate() {
        return stageDate;
    }

    public void setStageDate(Date stageDate) {
        this.stageDate = stageDate;
    }

    @Basic
    @Column(name = "maxPersonNum", nullable = true)
    public Integer getMaxPersonNum() {
        return maxPersonNum;
    }

    public void setMaxPersonNum(Integer maxPersonNum) {
        this.maxPersonNum = maxPersonNum;
    }


    public Integer getFounderId() {
        return founderId;
    }

    public void setFounderId(Integer founderId) {
        this.founderId = founderId;
    }

    public Integer getFounderType() {
        return founderType;
    }

    public void setFounderType(Integer founderType) {
        this.founderType = founderType;
    }

    public Date getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(Date applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    @Transient
    public String getBeginTimeDis(){
        return DateUtil.dateToStringByDateFormat(this.beginTime,"HH:mm");
    }

    @Transient
    public String getEndTimeDis() {
        return DateUtil.dateToStringByDateFormat(this.endTime,"HH:mm");
    }

    @Transient
    public String getStageDateDis(){
        return DateUtil.dateToStringByDateFormat(this.stageDate,"yyyy-MM-dd");
    }


    @Transient
    public String getTimeAfter() throws ParseException {
        return BcwCommonService.getTimeAfter(this.stageDate);
    }

    @Transient
    public String getTimeBeforeDis() throws ParseException {
        return BcwCommonService.getTimeBefore(this.createDateTime);
    }

    @Transient
    public String getEventTypeDis(){
        return DataDictFactory.getDataDictTypeKeyValue("eventType",this.eventType);
    }

    @Transient
    private List<TblDanceEventAttach> attachs;
    @Transient
    public List<TblDanceEventAttach> getAttachs() {
        return attachs;
    }
    @Transient
    public void setAttachs(List<TblDanceEventAttach> attachs) {
        this.attachs = attachs;
    }


    @Transient
    public Integer signId;//签到按钮id-TblDanceUserEventRelation表的主键id
    @Transient
    public Integer getSignId() {
        return signId;
    }
    @Transient
    public void setSignId(Integer signId) {
        this.signId = signId;
    }

    @Transient
    public Integer scoreInCrewExamingId;//考团打分按钮id-考团中出现,TblDanceCrewTermUserDetail表的主键id
    @Transient
    public Integer getScoreInCrewExamingId() {
        return scoreInCrewExamingId;
    }
    @Transient
    public void setScoreInCrewExamingId(Integer scoreInCrewExamingId) {
        this.scoreInCrewExamingId = scoreInCrewExamingId;
    }

    @Transient
    public Integer searchExamCrewScoreId;//查询考团分数按钮id-考团结束后出现,TblDanceCrewTermUserDetail表的主键id
    @Transient
    public Integer getSearchExamCrewScoreId() {
        return searchExamCrewScoreId;
    }
    @Transient
    public void setSearchExamCrewScoreId(Integer searchExamCrewScoreId) {
        this.searchExamCrewScoreId = searchExamCrewScoreId;
    }

    @Transient
    public String getImgDis(){

        return BcwCommonService.getImgDis(this.img,BcwConstant.TYPE_EVENT,this.yearMonth);
    }


    @Transient
    public String getImgDisMin() {
        return BcwCommonService.getImgDisMin(getImgDis());
    }

    @Transient
    public TblDanceUserEventRelation relation;
    @Transient
    public TblDanceUserEventRelation getRelation() {
        return relation;
    }
    @Transient
    public void setRelation(TblDanceUserEventRelation relation) {
        this.relation = relation;
    }


    @Transient
    public List<TblDanceUserEventResult> results;
    @Transient
    public List<TblDanceUserEventResult> getResults() {
        return results;
    }
    @Transient
    public void setResults(List<TblDanceUserEventResult> results) {
        this.results = results;
    }

//    @Transient
//    public TradeInfo tradeInfo;
//    @Transient
//    public TradeInfo getTradeInfo() {
//        return tradeInfo;
//    }
//    @Transient
//    public void setTradeInfo(TradeInfo tradeInfo) {
//        this.tradeInfo = tradeInfo;
//    }
//    @Transient
//    private String seqNo;
//    @Transient
//    public String getSeqNo() {
//        return seqNo;
//    }
//    @Transient
//    public void setSeqNo(String seqNo) {
//        this.seqNo = seqNo;
//    }
}