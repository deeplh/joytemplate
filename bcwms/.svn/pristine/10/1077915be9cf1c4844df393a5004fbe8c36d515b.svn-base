package com.bcwms.controller.app;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bcwms.bean.studio.*;
import com.bcwms.entity.TblDanceStudio;
import com.bcwms.service.impl.studio.StudioServiceImpl;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/app/studio",method = {RequestMethod.POST,RequestMethod.GET})
public class StudioController {

    @Autowired
    private StudioServiceImpl studioService;



    @RequestMapping(value = "/create")
    public void createStudio(String studio,String fileAttrBeanList) throws Exception {
        List<FileAttrBean> fabl = null;
        if (StringUtils.isNotEmpty(fileAttrBeanList)) {
            fabl = JSONArray.parseArray(fileAttrBeanList, FileAttrBean.class);
        }
        studioService.createStudio(JSONObject.parseObject(studio,TblDanceStudio.class),fabl);
    }

    @RequestMapping(value = "/get")
    public TblDanceStudio getStudio(Integer id) throws Exception {
        return studioService.getStudio(id);
    }


    @RequestMapping(value = "/update")
    public void updateStudio(String studio,String fileAttrBeanList) throws Exception {
        List<FileAttrBean> fabl = null;
        if (StringUtils.isNotEmpty(fileAttrBeanList)) {
            fabl = JSONArray.parseArray(fileAttrBeanList, FileAttrBean.class);
        }
        studioService.updateStudio(JSONObject.parseObject(studio,TblDanceStudio.class),fabl);
    }

    @RequestMapping(value = "/search")
    public Object searchStudio(SearchStudio ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    @RequestMapping(value = "/all/search")
    public Object searchStudioAll(SearchStudioAll ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    @RequestMapping(value = "/remove")
    public void remove(Integer id) {
        studioService.remove(id);
    }
    
}
