package com.keepjoy.weixin.controller;


import com.alibaba.fastjson.JSONObject;
import com.keepjoy.core.controller.IKeepJoyController;
import com.keepjoy.weixin.entity.WeixinUser;
import com.keepjoy.weixin.service.impl.SmallplatformServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class SmallplatformController  implements IKeepJoyController {

@Autowired
private SmallplatformServiceImpl smallplatformService;

    @RequestMapping(value = "/weixin/small/get_session_key_by_code",method = {RequestMethod.POST, RequestMethod.GET})
    public Map getToken(@RequestParam(value = "code", required = true) String code,
                        String weixinUser) throws Exception {
        Map map=new HashMap();
        map.put("root",smallplatformService.getToken(code,JSONObject.parseObject(weixinUser,WeixinUser.class)));
        return map;
    }



}



