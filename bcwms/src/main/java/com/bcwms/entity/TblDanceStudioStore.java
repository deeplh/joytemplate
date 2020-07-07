package com.bcwms.entity;

import com.bcwms.BcwConstant;
import com.bcwms.service.BcwCommonService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;


/**
 * 工作室对应的店面-具体的线下店,1个studio对应多个门店
 */
@Entity
public class TblDanceStudioStore extends TblDanceCommonAttrGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "studioId")
    private Integer studioId;


    public TblDanceStudioStore(){

    }


    @Transient
    public String getImgDis(){
        return BcwCommonService.getImgDis(this.img,BcwConstant.TYPE_STUDIO,null);
    }


    @Transient
    public String getImgDisMin() {
        return BcwCommonService.getImgDisMin(getImgDis());
    }


    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }
}
