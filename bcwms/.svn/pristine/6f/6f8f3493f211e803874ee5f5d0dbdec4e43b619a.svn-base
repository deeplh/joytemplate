package com.bcwms.bean.studio;

import com.bcwms.entity.TblDanceStudioStore;
import com.bcwms.entity.TblDanceStyle;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(listBeanClass=TblDanceStudioStore.class,isPagination = false,
        sql=" from TblDanceStudioStore where 1=1 ")
public class SearchStudioStore {

    @PaginationField(sql=" and name=:name ")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
