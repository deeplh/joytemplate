package com.bcwms.service;


import com.bcwms.BcwConstant;

import com.bcwms.entity.*;


import com.bcwms.factory.CosClientFactory;

import com.bcwms.properties.BcwProperties;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.service.impl.user.UserCrewServiceImpl;
import com.bcwms.service.impl.user.UserPointServiceImpl;
import com.bcwms.util.GaussianBlurUtil;
import com.bcwms.util.GenericUtil;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.context.KeepJoyHttpContext;
import com.keepjoy.core.context.KeepJoySpringContext;
import com.keepjoy.core.dao.KeepJoyDaoImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.core.util.DateUtil;

import com.keepjoy.core.util.UrlUtil;
import com.keepjoy.security.properties.SecurityProperties;
import com.keepjoy.security.proxy.RedisSsoProxy;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;


/**
 * B端主要为舞房、舞团、厂牌等机构使用，通过街舞在线排课消课系统、街舞活动(比赛、考团、大师课等等)管理系统等功能，记录街舞爱好者的上课信息、街舞老师的授课信息、舞者活动获奖信息、街舞从业者的信用信息等等一系列街舞日常基础数据，基于区块链的特性，构造一个公平公正公开的街舞生态圈。
 * C端主要针对街舞爱好者，街舞从业者和街舞粉丝等，可以让他们及时了解到最新的街舞相关活动，追到自己喜欢的舞者老师的课，找到适合自己的舞房、舞团等。
 */
public class BcwCommonService {


    public static final Date getNow() {
        return new Date();
    }

    public static final Date getNowYYYYMMDD() throws ParseException {
        Date now = getNow();
        String nowStr = DateUtil.dateToStringByDateFormat(now, DateUtil.DATEFORMAT_YYYYMMDD);

        return DateUtil.stringToDateByDateFormat(nowStr, DateUtil.DATEFORMAT_YYYYMMDD);
    }

    public static final String getUploadRootFolder() {
        return UrlUtil.addLastSlash(BcwProperties.getUploadFileFolder());
    }

    public static final String getUploadRootUrl() {
        if (BcwProperties.getDebugMode()) {
            return UrlUtil.addLastSlash(KeepJoyHttpContext.getHttpBeginUrl()) + UrlUtil.addLastSlash(BcwProperties.getUploadFileUrl());
        } else {
            if(StringUtils.isNotEmpty(BcwProperties.getBucketName())){
                return UrlUtil.addLastSlash("https://keepjoy-1257215555.cos.ap-chengdu.myqcloud.com" + KeepJoyHttpContext.getRequest().getContextPath()) + UrlUtil.addLastSlash(BcwProperties.getUploadFileUrl());
            }else{
                return UrlUtil.addLastSlash("https://www.wu5wu.com" + KeepJoyHttpContext.getRequest().getContextPath()) + UrlUtil.addLastSlash(BcwProperties.getUploadFileUrl());
            }
         }
    }


    private static final boolean isHttpUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return false;
        } else {
            //redis中的问题
            return url.indexOf("http://") > -1 || url.indexOf("https://") > -1;
        }
    }

    public static final String getVideoDis(String video, String type) {
        if (StringUtils.isNotEmpty(video)) {
            return BcwCommonService.getUploadRootUrl() + BcwConstant.FOLDER_VIDEO + File.separator + type + File.separator + video;
        } else {
            return null;
        }
    }

    public static final String getCardDis(String card, String type) {
        if (StringUtils.isNotEmpty(card)) {
            return null;
//            return  BcwCommonService.getUploadRootUrl()+BcwConstant.FOLDER_CARD+File.separator+type+File.separator+BcwUserHolder.getDanceUser().getUserId() +File.separator+card;
        } else {
            return null;
        }
    }

    public static final String getImgDis(String img, String type, String yearMonth) {
        if (StringUtils.isNotEmpty(img)) {
            if (isHttpUrl(img)) {//redis中的问题
                return img;
            }
            String rootFilePath = BcwCommonService.getUploadRootUrl();
            if (StringUtils.isNotEmpty(yearMonth)) {
                return rootFilePath + BcwConstant.FOLDER_IMG + File.separator + type + File.separator + yearMonth + File.separator + img;
            } else {
                return rootFilePath + BcwConstant.FOLDER_IMG + File.separator + type + File.separator + img;
            }

        } else {
            return null;
        }
    }

    public static final String getImgDisMin(String imgDis) {
        if (StringUtils.isEmpty(imgDis)) {
            return null;
        } else {
            if (imgDis.indexOf(BcwConstant.ATTACH_MIN_IMG) > -1) {
                return imgDis;
            }
            return UrlUtil.addAttachUrlBeforeLastDot(imgDis, "_" + BcwConstant.ATTACH_MIN_IMG);
        }
    }

    public static final String getImgDisMin(String img, String type, String yearMonth) {
        return getImgDisMin(getImgDis(img, type, yearMonth));

    }

    public static final List<?> saveAttach(List<FileAttrBean> fileAttrBeanList, Integer id, String type) throws IOException, InterruptedException {
        TblDanceCommonAttrAttach tda = null;
        List<TblDanceCommonAttrAttach> tdaList = new ArrayList<TblDanceCommonAttrAttach>();
        if (null != fileAttrBeanList && fileAttrBeanList.size() > 0) {

            int i = 0;
            for (FileAttrBean fab : fileAttrBeanList) {
                String tmp = FilenameUtils.getFullPathNoEndSeparator(fab.getAbsolutePath());
                String ym = tmp.substring(tmp.length() - 6, tmp.length());
                //判断图片方向

                if (type.equals(BcwConstant.TYPE_EVENT)) {
                    TblDanceEventAttach tdea = new TblDanceEventAttach();
                    tdea.setEventId(id);
                    tda = tdea;
                } else if (type.equals(BcwConstant.TYPE_CLOCK)) {
                    TblDanceClockAttach tdca = new TblDanceClockAttach();
                    tdca.setClockId(id);
                    tda = tdca;
                    //第一张图生成高斯模糊图片
                    if (i == 0) {
                        String fileUrl = fab.getAbsolutePath();
                        if (StringUtils.isNotEmpty(fab.getSourceName())) {
                            fileUrl = UrlUtil.addAttachUrlBeforeLastDot(fab.getAbsolutePath(), "_" + BcwConstant.ATTACH_MIN_IMG);
                        }
                        BufferedImage img = ImageIO.read(new File(fileUrl));
                        img = GaussianBlurUtil.blur(img, 12);
                        File gsFile = new File(UrlUtil.addAttachUrlBeforeLastDot(fileUrl, "_GS"));
                        ImageIO.write(img, "jpg", gsFile);
                        tdca.setImgGs(gsFile.getName());

                        String key = BcwProperties.getUploadFileUrl()+File.separator+BcwConstant.FOLDER_IMG + File.separator+BcwConstant.TYPE_CLOCK+
                                File.separator+ym+File.separator+gsFile.getName();
                        PutObjectRequest putObjectRequest = new PutObjectRequest(BcwProperties.getBucketName(), key,gsFile);
                        CosClientFactory.getCosClient().putObject(putObjectRequest);

                    }

                }


                tda.setSize(fab.getSize());
                tda.setWidth(fab.getWidth());
                tda.setHeight(fab.getHeight());

                tda.setImg(fab.getNewName());
                tda.setYearMonth(ym);


                KeepJoySpringContext.getKeepJoyDao().save(tda);


                tda.setType(type);
                tdaList.add(tda);

                i++;
            }

        }
        return tdaList;
    }

    public static final void setDanceUser(TblDanceUser tdu) {
        if (null == tdu) {
            throw new KeepJoyServiceException("userId数据异常");
        }
        setDanceUserCrews(tdu);
        setDanceUserStudios(tdu);
        setDanceUserStyles(tdu);
        setDanceUserMyCrews(tdu);
        setDanceUserTeachDuration(tdu);
        setDanceUserPoint(tdu);
    }

    //设置关注的crews
    public static final void setDanceUserCrews(TblDanceUser tdu) {
        tdu.setCrews(KeepJoySpringContext.getKeepJoyDao().find("select new TblDanceUserCrewRelation(tducr.id,tdc.id,tdc.name,tdc.img) from TblDanceCrew tdc,TblDanceUserCrewRelation tducr where tdc.id=tducr.crewId and tducr.userId=? and tducr.status=? ", tdu.getUserId(), BcwConstant.STATUS_2_YES));
    }

    //设置我的crews
    public static final void setDanceUserMyCrews(TblDanceUser tdu) {
        UserCrewServiceImpl s=KeepJoySpringContext.getSpringContext().getBean(UserCrewServiceImpl.class);

        tdu.setMyCrews(s.listUserCurrentCrew(tdu.getUserId()));
    }

    public static final void setDanceUserTeachDuration(TblDanceUser tdu){

        JdbcTemplate jdbcTemplate = KeepJoySpringContext.getJdbcTemplate();
        //TIMESTAMPSTAFF
        BigDecimal crewTermLessonDetailmins = (BigDecimal)jdbcTemplate.queryForList("select SUM(TIMESTAMPDIFF(MINUTE,beginDateTime,endDateTime)) as mins from TblDanceCrewTermLessonDetail where teacherId = ? ",tdu.getUserId()).get(0).get("mins");
        BigDecimal storeLessonDetailmins = (BigDecimal)jdbcTemplate.queryForList("select SUM(TIMESTAMPDIFF(MINUTE,beginDateTime,endDateTime)) as mins from TblDanceStudioStoreLessonDetail where teacherId = ? ",tdu.getUserId()).get(0).get("mins");
        if(crewTermLessonDetailmins == null){
            crewTermLessonDetailmins = new BigDecimal(0);
        }
        if(storeLessonDetailmins == null){
            storeLessonDetailmins = new BigDecimal(0);
        }
        int minsSum = crewTermLessonDetailmins.intValue()+storeLessonDetailmins.intValue();
        int hour = (int)Math.floor(minsSum/60);
        int minute = minsSum%60;
        tdu.setTeachDuration(hour+"");
    }

    public static final void setDanceUserPoint(TblDanceUser tdu){
        UserPointServiceImpl service  = KeepJoySpringContext.getSpringContext().getBean(UserPointServiceImpl.class);
        tdu.setPoint(service.getUserPoint(tdu.getUserId()));
    }


    public static final void setDanceUserStudios(TblDanceUser tdu) {
        tdu.setStudios(KeepJoySpringContext.getKeepJoyDao().find("select new TblDanceUserStudioRelation(tdusr.id,tds.id,tds.name,tds.img)  from TblDanceStudio tds,TblDanceUserStudioRelation tdusr where tds.id=tdusr.studioId and tdusr.userId=? and tdusr.status=? ", tdu.getUserId(), BcwConstant.STATUS_2_YES));
    }

    public static final void setDanceUserStyles(TblDanceUser tdu) {
        tdu.setStyles(KeepJoySpringContext.getKeepJoyDao().find("select new TblDanceUserStyleRelation(tdusr.id,tds.id,tds.name,tds.img,tds.initial)  from TblDanceStyle tds,TblDanceUserStyleRelation tdusr where tds.id=tdusr.styleId and tdusr.userId=? ", tdu.getUserId()));
    }

    public static final TblDanceCommonAttrGroup commonGroupUpdate(TblDanceCommonAttrGroup srcObj, TblDanceCommonAttrGroup input,
                                                                  List<FileAttrBean> fileAttrBeanList) {
        boolean isUpdate = false;

        if (StringUtils.isNotEmpty(input.getName()) && !input.getName().equals(srcObj.getName())) {
            isUpdate = true;
            srcObj.setName(input.getName());
        }

        if (null != input.getEstablishDate()) {
            if (null != srcObj.getEstablishDate()) {
                if (input.getEstablishDate().compareTo(srcObj.getEstablishDate()) != 0) {
                    isUpdate = true;
                }
            } else {
                isUpdate = true;
            }
            srcObj.setEstablishDate(input.getEstablishDate());
        }

        if (StringUtils.isNotEmpty(input.getFounder()) && !input.getFounder().equals(srcObj.getFounder())) {
            isUpdate = true;
            srcObj.setFounder(input.getFounder());
        }

        if (StringUtils.isNotEmpty(input.getAddress()) && !input.getAddress().equals(srcObj.getAddress())) {
            isUpdate = true;
            srcObj.setAddress(input.getAddress());
        }

        if (StringUtils.isNotEmpty(input.getInfo()) && !input.getInfo().equals(srcObj.getInfo())) {
            isUpdate = true;
            srcObj.setInfo(input.getInfo());
        }

        if (StringUtils.isNotEmpty(input.getImg()) && !input.getImg().equals(srcObj.getImg())) {
            isUpdate = true;
            srcObj.setImg(input.getImg());
        }

        if (StringUtils.isNotEmpty(input.getCity()) && !input.getCity().equals(srcObj.getCity())) {
            isUpdate = true;
            srcObj.setCity(input.getCity());
        }

        if (StringUtils.isNotEmpty(input.getDistrict()) && !input.getDistrict().equals(srcObj.getDistrict())) {
            isUpdate = true;
            srcObj.setDistrict(input.getDistrict());
        }

        if (StringUtils.isNotEmpty(input.getLatitude()) && !input.getLatitude().equals(srcObj.getLatitude())) {
            isUpdate = true;
            srcObj.setLatitude(input.getLatitude());
        }

        if (StringUtils.isNotEmpty(input.getLongitude()) && !input.getLongitude().equals(srcObj.getLongitude())) {
            isUpdate = true;
            srcObj.setLongitude(input.getLongitude());
        }

        if (null != fileAttrBeanList && fileAttrBeanList.size() > 0
                && !fileAttrBeanList.get(0).getNewName().equals(srcObj.getImg())) {
            isUpdate = true;
            srcObj.setImg(fileAttrBeanList.get(0).getNewName());
        }

        if (isUpdate) {
            KeepJoySpringContext.getKeepJoyDao().update(srcObj);
        }

        return srcObj;

    }


    public static final void setQueryParameter(Query q, List<String> names, List<Object> vals) {
        for (int i = 0; i < names.size(); i++) {
            q.setParameter(names.get(i), vals.get(i));
        }
    }

    public static final String getTimeAfter(Date stageDate) throws ParseException {
        if(null==stageDate)return null;
        Long time = stageDate.getTime() - getNowYYYYMMDD().getTime();
        if (time > 0) {//还没有开始
            Date date = new Date(time);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            Long milli = cal.getTimeInMillis();


            if (milli != null) {
                Long localSecond = (milli / 1000);
                Long hour = localSecond / 60 / 60;
                Long day = hour / 24;


                if (day >= 7) {
                    Long week = day / 7;
                    Long d = day % 7;
                    if (d > 0) {
                        return week + "周" + d + "天后";
                    } else {
                        return week + "周后";
                    }
                }
                if (day > 0) {
                    return day + "天后";
                } else {
                    return "今天";
                }
            } else {
                return null;
            }
        } else {//已经开始了
            return getTimeBefore(stageDate);
        }

    }

    public static final String getTimeBefore(Date createDateTime) throws ParseException {
        if(null==createDateTime)return null;
        Long time = getNow().getTime() - createDateTime.getTime();
        Date date = new Date(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Long milli = cal.getTimeInMillis();

        Long millisecond = milli.longValue();

        if (millisecond != null) {
            Long localSecond = millisecond / 1000;
            Long hour = localSecond / 60 / 60;
            Long day = hour / 24;

            Long minute = (localSecond - hour * 60 * 60) / 60;
            Long second = localSecond - minute * 60 - hour * 60 * 60;
            if (day > 0) {
                return day + "天前";
            }
            if (hour > 0 && minute > 0) {
                return hour + "小时" + minute + "分钟前";
            } else if (hour == 0 && minute > 0) {
                return minute + "分钟前";
            } else {
                return "刚刚";
            }
        } else {
            return null;
        }
    }

    public final static String getNoticeKeyMe() {
        return SecurityProperties.getSecurityUserClassName() + "_" + BcwUserHolder.getDanceUser().getUserId();
    }

    public final static void sendNotice(String noticeType, String type, Integer receiveUserId, Integer typeId) {
        RedisTemplate rt = RedisSsoProxy.getRedisTemplate();
        String key = getNoticeKeyMe();
        List<Integer> ids = null;
        Map<String, List<Integer>> noticeTypeMap = null;
        if (rt.hasKey(key)) {
            if (rt.boundHashOps(key).hasKey(noticeType)) {
                noticeTypeMap = (Map<String, List<Integer>>) rt.boundHashOps(key).get(noticeType);

                if (noticeTypeMap.containsKey(type)) {
                    ids = noticeTypeMap.get(type);
                } else {
                    ids = new ArrayList<Integer>();
                }

            } else {
                noticeTypeMap = new HashMap<String, List<Integer>>();
                ids = new ArrayList<Integer>();

            }
        } else {
            noticeTypeMap = new HashMap<String, List<Integer>>();
            ids = new ArrayList<Integer>();

        }
        ids.add(typeId);
        noticeTypeMap.put(type, ids);
        rt.opsForHash().put(key, noticeType, noticeTypeMap);
    }

    public final static void clearNoticeMe() {
        RedisTemplate rt = RedisSsoProxy.getRedisTemplate();
        String key = getNoticeKeyMe();
        rt.boundHashOps(key).expireAt(new Date());
    }

    public final static void removeNoticeMeByTypeId(String noticeType, String type, Integer typeId) {
        RedisTemplate rt = RedisSsoProxy.getRedisTemplate();
        String key = getNoticeKeyMe();
        Object obj = rt.opsForHash().get(key, noticeType);
        if (null != obj) {
            Map<String, List<Integer>> noticeTypeMap = (Map<String, List<Integer>>) obj;
            List<Integer> ids = noticeTypeMap.get(type);
            ids.remove(typeId);
            noticeTypeMap.put(type, ids);
            rt.opsForHash().put(key, noticeType, noticeTypeMap);
        }

    }


    public static void validateUserSchedule(Integer userId, Date beginDateTime, Date endDateTime) {
        //判断传入的时间内,对应的老师在其他的studio是不是有课
        Integer studioLocalId =
                KeepJoySpringContext.getKeepJoyDao().findObjectFromListByHql(Integer.class,
                        " select id from TblDanceStudioStoreLessonDetail " +
                                " where teacherId=? and not (endDateTime<? or beginDateTime>?) ",
                        userId, beginDateTime, endDateTime);
        if (null != studioLocalId) {
            throw new KeepJoyServiceException("当前时间段,该用户已经有安排");
        }

        //判断传入的时间内,对应的老师在其他的studio是不是有课
        Integer crewLocalId =
                KeepJoySpringContext.getKeepJoyDao().findObjectFromListByHql(Integer.class,
                        " select id from TblDanceCrewTermLessonDetail " +
                                " where teacherId=? and not (endDateTime<? or beginDateTime>?) ",
                        userId, beginDateTime, endDateTime);

        if (null != crewLocalId) {
            throw new KeepJoyServiceException("当前时间段,该用户已经有安排");
        }
    }

    public  static final String getChainSeqNo(String type,Integer id){
        return type+"_"+id;
    }


}
