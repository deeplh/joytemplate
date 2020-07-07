package com.bcwms.bean.studio;


import com.bcwms.BcwConstant;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(isUseMapModel = true,isPagination = false,
        sql=" select u.userId,u.trueName,u.aka " +
                " from TblDanceUserStudioRelation usr " +
                " left join TblDanceUser u on u.userId=usr.userId  " +
                " where 1=1 and usr.status='"+BcwConstant.STATUS_2_YES +"'" )
public class SearchStudioTeacher {

    @PaginationField(sql=" and usr.studioId=:studioId ")
    private Integer studioId;//工作室id-冗余字段


    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }


}
