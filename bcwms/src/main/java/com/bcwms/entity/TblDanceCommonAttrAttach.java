package com.bcwms.entity;

import com.bcwms.service.BcwCommonService;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class TblDanceCommonAttrAttach implements Serializable {

    private static final long serialVersionUID = 1L;

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "img", nullable = true, length = 100)
    public String img;

    @Column(name = "size", nullable = true)
    public Long size;

    @Column(name = "width", nullable = true)
    public Integer width;

    @Column(name = "height", nullable = true)
    public Integer height;

    @Column(name = "yearMonth", nullable = true, length = 6)
    public String yearMonth;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }




    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }


    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }


    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }


    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }


    @Transient
    private String type;
    @Transient
    public String getType() {
        return type;
    }
    @Transient
    public void setType(String type) {
        this.type = type;
    }

    @Transient
    public String getImgDis(){
        return BcwCommonService.getImgDis(this.img,this.type,this.yearMonth);
    }
    @Transient
    public String getImgDisMin(){
        return BcwCommonService.getImgDisMin(getImgDis());
    }
}
