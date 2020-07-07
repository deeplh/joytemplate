package com.bcwms.entity;

import com.bcwms.BcwConstant;
import com.bcwms.service.BcwCommonService;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class TblDanceUserStyleRelation implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private Integer styleId;
    private Integer createUserId;
    private Date createDateTime;

    public TblDanceUserStyleRelation(){

    }

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "styleId", nullable = true)
    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    @Basic
    @Column(name = "createUserId", nullable = true)
    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column(name = "createDateTime", nullable = true)
    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }


    @Transient
    private String styleName;
    @Transient
    private String styleImg;
    @Transient
    public String getStyleName() {
        return styleName;
    }
    @Transient
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }
    @Transient
    public String getStyleImg() {
        return BcwCommonService.getImgDisMin(this.styleImg,BcwConstant.TYPE_STYLE,null);
    }
    @Transient
    public void setStyleImg(String styleImg) {
        this.styleImg = styleImg;
    }

    @Transient
    private String initial;
    @Transient
    public String getInitial() {
        return initial;
    }
    @Transient
    public void setInitial(String initial) {
        this.initial = initial;
    }

    //BcwCommonService.java-setDanceUser
    public TblDanceUserStyleRelation(Integer id, Integer styleId, String styleName, String styleImg,
                                     String initial) {
        this.id = id;
        this.styleId = styleId;
        this.styleName = styleName;
        this.styleImg = styleImg;
        this.initial=initial;
    }
}
