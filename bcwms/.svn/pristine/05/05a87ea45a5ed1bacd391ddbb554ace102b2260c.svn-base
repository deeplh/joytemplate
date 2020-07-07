package com.bcwms.controller.app;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bcwms.bean.clock.GetClockMeToday;
import com.bcwms.bean.clock.SearchClockAll;
import com.bcwms.bean.clock.SearchClockMe;
import com.bcwms.bean.clock.SearchClockRank;

import com.bcwms.entity.TblDanceClock;
import com.bcwms.service.impl.ClockServiceImpl;
import com.keepjoy.core.bean.file.upload.FileAttrBean;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/app/clock", method = {RequestMethod.POST, RequestMethod.GET})
public class ClockController {

    @Autowired
    private ClockServiceImpl clockService;


    @RequestMapping(value = "/me/count")
    public Map countClockMe() throws Exception {
        return clockService.countClockMe();
    }


    @RequestMapping(value = "/remove")
    public void removeClock(Integer id) throws Exception {
        clockService.removeClock(id);
    }

    @RequestMapping(value = "/create")
    public TblDanceClock createClock(String clock, String fileAttrBeanList, String beginTime, String endTime) throws Exception {
        List<FileAttrBean> fabl = null;
        if (StringUtils.isNotEmpty(fileAttrBeanList)) {
            fabl = JSONArray.parseArray(fileAttrBeanList, FileAttrBean.class);
        }
        return clockService.createClock(JSONObject.parseObject(clock,TblDanceClock.class), fabl, beginTime, endTime);
    }

    @RequestMapping(value = "/get")
    public TblDanceClock getClock(Integer id) throws Exception {
        return clockService.getClock(id);
    }


    @RequestMapping(value = "/search")
    public Object searchClock(String keyword,
                              Integer currentPage, Integer pageSize) throws Exception {
        return clockService.searchClock(keyword, currentPage, pageSize);
    }

    @RequestMapping(value = "/me/today")
    public Object getClockMeToday(GetClockMeToday ss) throws Exception {
        KeepJoyPagination kjp = new KeepJoyPagination(ss);
        return kjp.doPagination();

    }

    @RequestMapping(value = "/all/search")
    public Object searchClockAll(SearchClockAll ss) throws Exception {
        KeepJoyPagination kjp = new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    @RequestMapping(value = "/rank")
    public Object searchClockApplyMe(SearchClockRank ss) throws Exception {
        KeepJoyPagination kjp = new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    @RequestMapping(value = "/me/search")
    public Object searchClockMe(SearchClockMe ss) throws Exception {
        KeepJoyPagination kjp = new KeepJoyPagination(ss);
        return kjp.doPagination();
    }
}
