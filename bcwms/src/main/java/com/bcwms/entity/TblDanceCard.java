package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 各种卡的总表
 * 1.课卡表,方便日后的汇总统计-例如:年卡,季卡,等等
 */
@Entity
public class TblDanceCard extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CARDTYPE_1_DATE="1";//周期卡
    public static final String CARDTYPE_2_NUM="2";//次卡

    //当cardType为1时,表示周期性的卡
    public static final String CARDTIMETYPE_1_YEAR="1";//年卡
    public static final String CARDTIMETYPE_2_SEASON="2";//季卡
    public static final String CARDTIMETYPE_3_MONTH="3";//月卡
    public static final String CARDTIMETYPE_4_WEEK="4";//周卡



    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;


    @Column(name = "name",length = 20)
    private String name;//卡的名字


    @Column(name = "total")
    public Integer total;//总次数-当是次卡的时候,需要使用,null表示无次数限制

    @Column(name = "cardType",length = 1,columnDefinition = "CHAR")
    public String cardType;//卡类型,根据不同的类型,到期日期不一样,次数也不一样

    @Column(name = "cardTimeType",length = 2,columnDefinition = "CHAR")
    public String cardTimeType;//卡时类型

    @Column(name = "validityTime")
    public Integer validityTime;//有效期-单位:天


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardTimeType() {
        return cardTimeType;
    }

    public void setCardTimeType(String cardTimeType) {
        this.cardTimeType = cardTimeType;
    }

    public Integer getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(Integer validityTime) {
        this.validityTime = validityTime;
    }
}
