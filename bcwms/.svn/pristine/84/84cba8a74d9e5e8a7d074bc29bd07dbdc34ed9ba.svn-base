package com.bcwms.bean.crew;


import com.alibaba.fastjson.JSONArray;
import com.bcwms.entity.TblDanceCrewTermUserDetail;
import com.bcwms.security.BcwUserHolder;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

/**
 * 查询所有老师打的分数
 */
@PaginationAction(isNativeSql = true,isUseMapModel = true,
sql=" select ctuds.userId,ctuds.crewTermUserDetailId," +
        " ctuds.score,ctuds.foundationScore,ctuds.routineScore,ctuds.soloScore,ctuds.otherScore, " +
        " um.aka memberAka,um.trueName memberTrueName,um.avatarUrl memberAvatarUrl, " +
        " ut.aka teacherAka,ut.trueName teacherTrueName,ut.avatarUrl teacherAvatarUrl " +
        " from TblDanceCrewTermUserDetailScore ctuds  " +
        " left join TblDanceUser um on um.userId=ctuds.userId " +
        " left join TblDanceUser ut on ut.userId=ctuds.teacherId " +
        " where 1=1 ",sqlTail = " order by ctuds.userId  ",
        isPagination = false)

public class SearchCrewTermUserScore {

    @PaginationField(sql=" and ctuds.crewTermId=:crewTermId ")
    public Integer crewTermId;

    @PaginationField(sql=" and ctuds.crewId=:crewId ")
    public Integer crewId;

    @PaginationField(sql=" and ctuds.teacherId=:teacherId ")
    public Integer teacherId;


    @PaginationField(sql=" and ctuds.score>=0 ")
    public String scoreStatus;

    @PaginationField(sql=" and (ctuds.score is null or ctuds.score='') ")
    public String notScoreStatus;

    public Integer getCrewTermId() {
        return crewTermId;
    }

    public void setCrewTermId(Integer crewTermId) {
        this.crewTermId = crewTermId;
    }

    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }

    public String getScoreStatus() {
        return scoreStatus;
    }

    public void setScoreStatus(String scoreStatus) {
        this.scoreStatus = scoreStatus;
    }

    public String getNotScoreStatus() {
        return notScoreStatus;
    }

    public void setNotScoreStatus(String notScoreStatus) {
        this.notScoreStatus = notScoreStatus;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }


}
