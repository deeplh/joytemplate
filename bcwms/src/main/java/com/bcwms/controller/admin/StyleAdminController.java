package com.bcwms.controller.admin;


import com.alibaba.fastjson.JSONArray;
import com.bcwms.bean.style.SearchStyle;
import com.bcwms.entity.TblDanceStyle;
import com.bcwms.service.impl.StyleServiceImpl;
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
@RequestMapping(value = "/admin/style",method = {RequestMethod.POST,RequestMethod.GET})
public class StyleAdminController {


    @Autowired
    private StyleServiceImpl styleService;


    @RequestMapping(value = "/create")
    public void createStyle(@RequestParam(value = "name", required = true) String name,
                            @RequestParam(value = "fileAttrBeanList", required = false) String fileAttrBeanList,
                            @RequestParam(value = "info", required = false) String info) {
        List<FileAttrBean> fabl = null;
        if (StringUtils.isNotEmpty(fileAttrBeanList)) {
            fabl = JSONArray.parseArray(fileAttrBeanList, FileAttrBean.class);
        }
        styleService.createStyle(name,fabl,info);

    }

    @RequestMapping(value = "/search")
    public Object searchStyle(@RequestParam(value = "name", required = false) String name) throws Exception {
        SearchStyle ssb=new SearchStyle();
        ssb.setName(name);
        KeepJoyPagination kjp=new KeepJoyPagination(ssb);
        return kjp.doPagination();
    }

    @RequestMapping(value = "/get")
    public TblDanceStyle getStyle(@RequestParam(value = "id", required = true) Integer id){
        return styleService.getStyle(id);
    }

    @RequestMapping(value = "/update")
    public void updateStyle(@RequestParam(value = "style", required = false) TblDanceStyle style,
                            @RequestParam(value = "fileAttrBeanList", required = false) String fileAttrBeanList){

        List<FileAttrBean> fabl = null;
        if (StringUtils.isNotEmpty(fileAttrBeanList)) {
            fabl = JSONArray.parseArray(fileAttrBeanList, FileAttrBean.class);
        }
        styleService.updateStyle(style,fabl);
    }
}



