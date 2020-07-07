package com.bcwms.controller.app.user;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bcwms.bean.style.SearchStyle;
import com.bcwms.entity.TblDanceStyle;
import com.bcwms.entity.TblDanceUser;
import com.bcwms.service.impl.StyleServiceImpl;
import com.bcwms.service.impl.user.UserStyleServiceImpl;
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
@RequestMapping(value = "/app/user/style",method = {RequestMethod.POST,RequestMethod.GET})
public class UserStyleController {


    @Autowired
    private UserStyleServiceImpl userStyleService;



    @RequestMapping(value = "/me/bind.action")
    public void bindStyleMe(@RequestParam(value = "styleId", required = true) Integer styleId){
        userStyleService.bindStyleMe(styleId);
    }



    @RequestMapping(value = "/me/unbind.action")
    public TblDanceUser unbindStyleMe(@RequestParam(value = "id", required = true) Integer id){
        return userStyleService.unbindStyleMe(id);
    }
}



