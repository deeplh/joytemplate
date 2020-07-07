package com.bcwms.bean.studio;

import com.bcwms.bean.studio.translate.SearchStudioStoreLessonBean;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(isUseMapModel = true,isPagination = false,
        sql=" select ssld.beginDateTime,ssld.endDateTime,ssld.teacherId,ssld.lessonDate,ssld.lessonMonth," +
                " u.trueName,u.aka,u.userId," +
                " s.name styleName " +
                " from TblDanceStudioStoreLessonDetail ssld" +
                " left join TblDanceUser u on u.userId=ssld.teacherId " +
                " left join TblDanceStyle s on s.id=ssld.styleId " +
                " where 1=1 ",
        sqlTail = " order by ssld.beginDateTime asc ",
        translateBeanClass = SearchStudioStoreLessonBean.class)
public class SearchStudioStoreLesson {

    @PaginationField(sql=" and ssld.studioId=:studioId ")
    private Integer studioId;//工作室id-冗余字段

    @PaginationField(sql=" and ssld.studioStoreId=:studioStoreId ")
    private Integer studioStoreId;//工作室门店id

    @PaginationField(sql=" and ssld.lessonDate=:lessonDate ")
    private String lessonDate;//上课日期

    @PaginationField(sql=" and ssld.lessonMonth=:lessonMonth ")
    private String lessonMonth;


    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public Integer getStudioStoreId() {
        return studioStoreId;
    }

    public void setStudioStoreId(Integer studioStoreId) {
        this.studioStoreId = studioStoreId;
    }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }


    public String getLessonMonth() {
        return lessonMonth;
    }

    public void setLessonMonth(String lessonMonth) {
        this.lessonMonth = lessonMonth;
    }
}
