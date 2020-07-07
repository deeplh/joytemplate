package com.bcwms.bean.style;

import com.bcwms.entity.TblDanceStyle;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(listBeanClass=TblDanceStyle.class,isPagination = false,
        sql=" from TblDanceStyle where 1=1 ")
public class SearchStyle {

    @PaginationField(sql=" and name=:name ")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
