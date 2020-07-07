package com.bcwms.bean.crew;


import com.bcwms.bean.TranslateBean;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(isNativeSql = true,isUseMapModel = true,
        sql=" select tdctud.id,tdctud.userId,tdctud.crewUserType,tdctud.contractAddress,tdctud.settleDateTime," +
        " tdu.trueName,tdu.aka,tdu.avatarUrl  " +
        " from TblDanceCrewTermUserDetail tdctud " +
        " left join TblDanceUser tdu on tdu.userId=tdctud.userId " +
        " where 1=1 ",sqlTail = " order by tdctud.score desc ",translateBeanClass = TranslateBean.class)
public class SearchCrewTermUser {

    @PaginationField(sql=" and tdctud.crewTermId=:crewTermId ")
    public Integer crewTermId;

    @PaginationField(sql=" and tdctud.eventId=:eventId ")
    public Integer eventId;

    @PaginationField(sql=" and tdctud.crewId=:crewId ")
    public Integer crewId;

    @PaginationField(sql=" and tdctud.crewUserType=:crewUserType ")
    public String crewUserType;

    @PaginationField(sql=" and tdctud.status=:status ")
    public String status;

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

    public String getCrewUserType() {
        return crewUserType;
    }

    public void setCrewUserType(String crewUserType) {
        this.crewUserType = crewUserType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
