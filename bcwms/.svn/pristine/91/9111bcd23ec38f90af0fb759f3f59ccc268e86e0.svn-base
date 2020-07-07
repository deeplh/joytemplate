package com.bcwms.controller.app;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bcwms.bean.event.*;
import com.bcwms.entity.TblDanceEvent;
import com.bcwms.service.impl.EventServiceImpl;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/app/event",method = {RequestMethod.POST,RequestMethod.GET})
public class EventController {

    @Autowired
    private EventServiceImpl eventService;


    @RequestMapping(value = "/create")
    public void create(String event, String fileAttrBeanList,
                            @RequestParam(value = "beginTime", required = false) String beginTime,
                            @RequestParam(value = "endTime", required = false)String endTime) throws Exception {

        List<FileAttrBean> fabl = null;
        if (StringUtils.isNotEmpty(fileAttrBeanList)) {
            fabl = JSONArray.parseArray(fileAttrBeanList, FileAttrBean.class);
        }
        eventService.create(JSONObject.parseObject(event,TblDanceEvent.class),fabl,beginTime,endTime);
    }

    @RequestMapping(value = "/get")
    public TblDanceEvent get(Integer id) throws Exception {
        return eventService.get(id);
    }



    @RequestMapping(value = "/update")
    public void update(String event,String fileAttrBeanList,
                            String beginTime,String endTime) throws Exception {
        List<FileAttrBean> fabl = null;
        if (StringUtils.isNotEmpty(fileAttrBeanList)) {
            fabl = JSONArray.parseArray(fileAttrBeanList, FileAttrBean.class);
        }
        eventService.update(JSONObject.parseObject(event,TblDanceEvent.class),fabl,beginTime,endTime);
    }


    @RequestMapping(value = "/search")
    public Object search(String name, String keyword, String eventType,
                              Integer currentPage,Integer pageSize) throws Exception {
        return eventService.search(name,keyword,eventType,currentPage,pageSize);
    }

    @RequestMapping(value = "/all/search")
    public Object searchEventAll(SearchEventAll ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    @RequestMapping(value = "/remove")
    public void remove(Integer id) {
        eventService.remove(id);
    }
}
