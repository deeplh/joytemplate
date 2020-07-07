package com.bcwms.controller.app.user;


import com.bcwms.bean.user.SearchUserAll;
import com.bcwms.entity.TblDanceUser;
import com.bcwms.security.BcwUserHolder;
import com.bcwms.security.BcwUser;
import com.bcwms.service.BcwCommonService;
import com.bcwms.service.impl.user.UserServiceImpl;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import com.keepjoy.security.proxy.RedisSsoProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;


@RestController
@RequestMapping(value = "/app/user",method = {RequestMethod.POST,RequestMethod.GET})
public class UserController {


    @Autowired
    private UserServiceImpl userService;


    @RequestMapping(value = "/get_dance_user_info")
    public TblDanceUser getDanceUserInfo(@RequestParam(value = "userId", required = true) Integer userId) {

        TblDanceUser tdu=userService.getDanceUserInfo(userId);
        return tdu;
    }

    @RequestMapping(value = "/me/update")
    public BcwUser updateUserMe(@RequestParam(value = "img", required = false) String img,
                                @RequestParam(value = "trueName", required = false) String trueName,
                                @RequestParam(value = "age", required = false) Integer age,
                                @RequestParam(value = "sex", required = false) String sex,
                                @RequestParam(value = "beginDanceDate", required = false) String beginDanceDate,
                                @RequestParam(value = "mobile", required = false) String mobile,
                                @RequestParam(value = "aka", required = false) String aka
                                  ) throws ParseException {
        return userService.updateUserMe(img,trueName,age,sex,beginDanceDate,mobile,aka);
    }

    @RequestMapping(value = "/notice/me/get")
    public Object getNoticeMe() {
        return RedisSsoProxy.getRedisTemplate().opsForHash().entries(BcwCommonService.getNoticeKeyMe());
    }

    @RequestMapping(value = "/me/get")
    public Object getUserMe() {
        return BcwUserHolder.getDanceUser();
    }

    @RequestMapping(value = "/me/refresh")
    public Object refreshUserMe() {
        return userService.refreshUserMe();
    }

    @RequestMapping(value = "/me/notice/remove")
    public void removeNoticeMe(@RequestParam(value = "noticeType", required = true) String noticeType,
                                 @RequestParam(value = "type", required = true) String type,
                                 @RequestParam(value = "typeId", required = true) Integer typeId) {
        BcwCommonService.removeNoticeMeByTypeId(noticeType,type,typeId);
    }

    @RequestMapping(value = "/all/search")
    public Object searchUserAll(@RequestParam(value = "name", required = false) String name) throws Exception {
        SearchUserAll sub=new SearchUserAll();
        sub.setName(name);
        KeepJoyPagination kjp=new KeepJoyPagination(sub);
        return kjp.doPagination();
    }
}



