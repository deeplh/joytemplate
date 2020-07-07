package com.bcwms.bean.crew;


import com.bcwms.bean.crew.translate.CrewTranslateBean;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@PaginationAction(isNativeSql = true,isUseMapModel = true,isPagination = false,translateBeanClass = CrewTranslateBean.class,
        sql=" select " +
                " tdu.trueName,tdu.userId as teacherId," +
                " tdctld.id,tdctld.beginDateTime,tdctld.endDateTime,tdctld.crewId,tdctld.crewTermId " +

                " from TblDanceCrewTermLessonDetail tdctld " +
                " left join TblDanceUser tdu on tdu.userId = tdctld.teacherId " +
                " left join TblDanceCrew tdc on tdc.id = tdctld.crewId " +
                " left join TblDanceCrewTerm tdct on tdct.id = tdctld.crewTermId " +
                " where 1=1 ",
        sqlTail = " order by tdctld.id desc ")
public class SearchCrewTermLesson {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PaginationField(sql=" and tdctld.trainLessonDate=:trainLessonDate ")
    public Date trainLessonDate;

    @NotNull(message = "crewId不能为空！")
    @Min(value = 1,message = "crewId不能小于1")

    @PaginationField(sql=" and tdctld.crewId=:crewId ")
    public Integer crewId;

    @NotNull(message = "crewTermId不能为空！")
    @Min(value = 1,message = "crewTermId不能小于1")
    @PaginationField(sql=" and tdctld.crewTermId=:crewTermId ")
    public Integer crewTermId;

    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }

    public Date getTrainLessonDate() {
        return trainLessonDate;
    }

    public void setTrainLessonDate(Date trainLessonDate) {
        this.trainLessonDate = trainLessonDate;
    }

    public Integer getCrewTermId() {
        return crewTermId;
    }

    public void setCrewTermId(Integer crewTermId) {
        this.crewTermId = crewTermId;
    }
}
