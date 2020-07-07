package com.bcwms.controller.app;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bcwms.bean.crew.*;
import com.bcwms.entity.TblDanceCrew;
import com.bcwms.service.impl.crew.CrewServiceImpl;

import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/app/crew",method = {RequestMethod.POST,RequestMethod.GET})
public class CrewController {

    @Autowired
    private CrewServiceImpl crewService;



    @RequestMapping(value = "/create")
    public void create(String crew,String fileAttrBeanList) throws Exception {
        List<FileAttrBean> fabl = null;
        if (StringUtils.isNotEmpty(fileAttrBeanList)) {
            fabl = JSONArray.parseArray(fileAttrBeanList, FileAttrBean.class);
        }
        crewService.create(JSONObject.parseObject(crew,TblDanceCrew.class),fabl);
    }

    @RequestMapping(value = "/get")
    public TblDanceCrew get(Integer id) throws Exception {
        return crewService.get(id);
    }




    @RequestMapping(value = "/update")
    public void update(String crew,String fileAttrBeanList) throws Exception {
        List<FileAttrBean> fabl = null;
        if (StringUtils.isNotEmpty(fileAttrBeanList)) {
            fabl = JSONArray.parseArray(fileAttrBeanList, FileAttrBean.class);
        }
        crewService.update(JSONObject.parseObject(crew,TblDanceCrew.class),fabl);
    }

    /**
     * 查询生效的团
     * @param ss
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search")
    public Object searchCrew(SearchCrew ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    /**
     * 查询所有的团
     * @param ss
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/all/search")
    public Object searchCrewAll(SearchCrewAll ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }


    /**
     * 查询本期录取名单
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/term/user/search")
    public Object searchCrewTermUser(SearchCrewTermUser ss) throws Exception {
        KeepJoyPagination kjp = new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    @RequestMapping(value = "/remove")
    public void remove(Integer id) {
        crewService.remove(id);
    }
}
