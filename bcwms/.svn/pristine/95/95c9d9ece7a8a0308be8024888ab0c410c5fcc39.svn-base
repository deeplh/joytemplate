package com.bcwms.entity;

import com.bcwms.BcwConstant;
import com.bcwms.service.BcwCommonService;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;


@Entity
public class TblDanceCrew extends TblDanceCommonAttrGroup implements Serializable {

    private static final long serialVersionUID = 1L;





    public TblDanceCrew(){

    }

    public TblDanceCrew(Integer id, String name, Integer createUserId,
                        Date createDateTime, String status,
                        String aka, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.createUserId = createUserId;
        this.createDateTime = createDateTime;
        this.status = status;
        this.aka=aka;
        this.avatarUrl=avatarUrl;
    }




    @Transient
    public String getImgDis(){
        return BcwCommonService.getImgDis(this.img,BcwConstant.TYPE_CREW,null);
    }


    @Transient
    public String getImgDisMin() {
        return BcwCommonService.getImgDisMin(getImgDis());
    }


}
