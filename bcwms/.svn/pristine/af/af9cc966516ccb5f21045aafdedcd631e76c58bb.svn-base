package com.bcwms.bean.user;

import com.bcwms.entity.TblDanceUser;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(listBeanClass=TblDanceUser.class
        ,sql=" select new TblDanceUser( tdu.userId, tdu.aka, tdu.avatarUrl,jwu.nickName)"
        + " from WeixinUser jwu,TblDanceUser tdu,BasUser bu " +
        " where jwu.userId=tdu.userId and bu.userId=tdu.userId "
        ,sqlTail = " order by tdu.userId desc ")
public class SearchUserAll {

    @PaginationField(sql=" and name=:name ")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
