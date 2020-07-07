package com.bcwms.entity;

import com.bcwms.BcwConstant;
import com.bcwms.service.BcwCommonService;

import com.keepjoy.core.util.DateUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


/**
 * 打卡表
 */
@Entity
public class TblDanceClock implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer userId;
    private String info;
    private Date createDateTime;
    private Date clockDate;//打卡日期
    private Integer continueClockNum;//连续打卡次数
    private String video;
    private String address;
    private String latitude;//纬度
    private String longitude;//经度
    private String card;//打卡后生成的舞者片

    private String status;


    @Column(name = "width", nullable = true)
    public Integer width;

    @Column(name = "height", nullable = true)
    public Integer height;


    public TblDanceClock(){

    }

    public TblDanceClock(Integer id,String info,Integer userId,Date createDateTime){
        this.id=id;
        this.info=info;
        this.userId=userId;
        this.createDateTime=createDateTime;
    }

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId",nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    @Basic
    @Column(name = "createDateTime")
    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Basic
    @Column(name = "clockDate",columnDefinition = "DATE",length = 10)
    public Date getClockDate() {
        return clockDate;
    }

    public void setClockDate(Date clockDate) {
        this.clockDate = clockDate;
    }


    @Basic
    @Column(name = "continueClockNum")
    public Integer getContinueClockNum() {
        return continueClockNum;
    }

    public void setContinueClockNum(Integer continueClockNum) {
        this.continueClockNum = continueClockNum;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 50)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "latitude", nullable = true, length = 20)
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longitude", nullable = true, length = 20)
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    @Basic
    @Column(name = "info", nullable = true, length = 100)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {

        this.info = info;
    }

    @Basic
    @Column(name = "video", nullable = true, length = 100)
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Basic
    @Column(name = "card", nullable = true, length = 100)
    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Column(name = "status", nullable = true,length = 1,columnDefinition = "CHAR")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Transient
    public String getVideoDis() {
        return BcwCommonService.getVideoDis(this.video,BcwConstant.TYPE_CLOCK);
    }

    @Transient
    private String aka;

    @Transient
    private  String avatarUrl;

    @Transient
    public String getAka() {
        return aka;
    }
    @Transient
    public void setAka(String aka) {
        this.aka = aka;
    }
    @Transient
    public String getAvatarUrl() {
        return avatarUrl;
    }
    @Transient
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Transient
    private List<TblDanceClockAttach> attachs;
    @Transient
    public List<TblDanceClockAttach> getAttachs() {
        return attachs;
    }
    @Transient
    public void setAttachs(List<TblDanceClockAttach> attachs) {
        this.attachs = attachs;
    }

    //SearchClock
    public TblDanceClock(Integer id, Integer userId, String info, Date createDateTime,
                         Integer continueClockNum, String video, String address,
                         String aka, String avatarUrl,Integer width,Integer height) {
        this.id = id;
        this.userId = userId;
        this.info = info;
        this.createDateTime = createDateTime;
        this.continueClockNum = continueClockNum;
        this.video = video;
        this.address = address;
        this.aka = aka;
        this.avatarUrl = avatarUrl;
        this.width=width;
        this.height=height;
    }

    @Transient
    public String getTimeBeforeDis() throws ParseException {
        return BcwCommonService.getTimeBefore(this.createDateTime);
    }

    @Transient
    public String getName(){
        return this.info;
    }

    @Transient
    public String getCreateDateTimeDis(){
        return DateUtil.dateToStringByDateFormat(this.createDateTime,DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    @Transient
    public Long getClockDateLong() {

        if(null!=this.clockDate){
            return this.clockDate.getTime();
        }else return null;

    }

    @Transient
    public String getClockDateDis(){
        return  DateUtil.dateToStringByDateFormat(this.clockDate,DateUtil.DATEFORMAT_YYYY_MM_DD);
    }

    @Transient
    private String imgDisMin;
    @Transient
    public String getImgDisMin() {
        return imgDisMin;
    }
    @Transient
    public void setImgDisMin(String imgDisMin) {
        this.imgDisMin = imgDisMin;
    }

    @Transient
    public String getCardDis() {
        return BcwCommonService.getCardDis(this.card,BcwConstant.TYPE_CLOCK);
    }

    @Transient
    public Boolean isMine;

    @Transient
    public Boolean getMine() {
        return isMine;
    }

    @Transient
    public void setMine(Boolean mine) {
        isMine = mine;
    }

    @Transient
    private Integer continueLessMePercent;
    @Transient
    public Integer getContinueLessMePercent() {
        return continueLessMePercent;
    }
    @Transient
    public void setContinueLessMePercent(Integer continueLessMePercent) {
        this.continueLessMePercent = continueLessMePercent;
    }
}

