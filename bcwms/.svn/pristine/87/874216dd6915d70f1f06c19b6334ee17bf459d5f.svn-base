package com.bcwms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 工作室的店面对应的room
 */
@Entity
public class TblDanceStudioStoreRoom extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "name", nullable = true, length = 30)
    public String name;//教室名称

    @Column(name = "studioId")
    private Integer studioId;//冗余

    @Column(name = "studioStoreId")
    private Integer studioStoreId;


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

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public Integer getStudioStoreId() {
        return studioStoreId;
    }

    public void setStudioStoreId(Integer studioStoreId) {
        this.studioStoreId = studioStoreId;
    }
}
