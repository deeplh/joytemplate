package com.bcwms.controller.app.user;


import com.bcwms.bean.event.SearchEventApply;
import com.bcwms.bean.event.SearchEventApplyMe;
import com.bcwms.bean.event.SearchEventMe;
import com.bcwms.bean.event.SearchEventUserResult;
import com.bcwms.service.impl.user.UserEventServiceImpl;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app/user/event",method = {RequestMethod.POST,RequestMethod.GET})
public class UserEventController {

    @Autowired
    private UserEventServiceImpl userEventService;

    @RequestMapping(value = "/me/apply")
    public void applyEventMe(Integer eventId) throws Exception {
        userEventService.applyEventMe(eventId);
    }

    /**
     * 审核创建的活动
     * @param id
     * @param status
     * @throws Exception
     */
    @RequestMapping(value = "/create/approve")
    public void approveCreateEvent(Integer id,String status) throws Exception {
        userEventService.approveCreateEvent(id,status);
    }


    /**
     * 修改活动的状态
     * @param id
     * @param status
     * @throws Exception
     */
    @RequestMapping(value = "/approve")
    public void approve(Integer id,String status) throws Exception {
        userEventService.approve(id,status);
    }

    @RequestMapping(value = "/sign")
    public void sign(Integer id) throws Exception {
        userEventService.sign(id);
    }

    @RequestMapping(value = "/select_user")
    public void selectUser(Integer[] userIds,Integer eventId) throws Exception {
        userEventService.selectUser(userIds,eventId);
    }

    @RequestMapping(value = "/user_confirm")
    public void userConfirm(Integer eventId,String userComment,Integer userScore) throws Exception {
        userEventService.userConfirm(eventId,userComment,userScore);
    }

    @RequestMapping(value = "/founder_confirm")
    public void founderConfirm(Integer eventId) throws Exception {
        userEventService.founderConfirm(eventId);
    }


    @RequestMapping(value = "/apply/search")
    public Object searchEventApply(SearchEventApply ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    @RequestMapping(value = "/apply/me/search")
    public Object searchEventApplyMe(SearchEventApplyMe ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    @RequestMapping(value = "/me/search")
    public Object searchEventMe(SearchEventMe ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }


    @RequestMapping(value = "/search_result")
    public Object searchEventUserResult(SearchEventUserResult ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

}
