package com.bcwms.bean.card;

import com.bcwms.entity.TblDanceClock;
import com.bcwms.entity.TblDanceCard;
import com.keepjoy.core.module.pagination.annotation.PaginationAction;
import com.keepjoy.core.module.pagination.annotation.PaginationField;

@PaginationAction(listBeanClass = TblDanceCard.class
        , sql = " from TblDanceCard  where 1=1 "
        , sqlTail = " order by id desc ")
public class SearchCard {

    @PaginationField(sql = " cardType = :cardType ")
    private String cardType;

    @PaginationField(sql = " name like :name ")
    private String name;

    @PaginationField(sql = " cardTimeType = :cardTimeType ")
    private String cardTimeType;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getName() {
        return '%'+name+'%';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardTimeType() {
        return cardTimeType;
    }

    public void setCardTimeType(String cardTimeType) {
        this.cardTimeType = cardTimeType;
    }
}