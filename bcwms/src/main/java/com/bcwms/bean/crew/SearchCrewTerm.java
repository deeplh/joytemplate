package com.bcwms.bean.crew;


import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(isNativeSql = true,isUseMapModel = true,isPagination = false,
        sql=" select ct.id,ct.crewId,ct.eventId,ct.term,ct.examDate," +
        " e.name,e.status  " +
        " from TblDanceCrewTerm ct " +
        " left join TblDanceEvent e on e.id=ct.eventId " +
        " where 1=1 ",sqlTail = " order by ct.id desc ")
public class SearchCrewTerm {


    @PaginationField(sql=" and ct.crewId=:crewId ")
    public Integer crewId;


    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }
}
