package com.bcwms.controller.app.user;


import com.bcwms.bean.crew.*;
import com.bcwms.entity.TblDanceUser;
import com.bcwms.service.impl.user.UserCrewServiceImpl;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app/user/crew",method = {RequestMethod.POST,RequestMethod.GET})
public class UserCrewController {

    @Autowired
    private UserCrewServiceImpl userCrewService;

    /**
     * 我发起关注团的申请
     * @param crewId
     * @throws Exception
     */
    @RequestMapping(value = "/me/apply")
    public void applyCrewMe(Integer crewId) throws Exception {
        userCrewService.applyCrewMe(crewId);
    }

    /**
     * 审批创建团的申请
     * @param id
     * @param status
     * @throws Exception
     */
    @RequestMapping(value = "/create/approve")
    public void approveCreateCrew(Integer id,String status) throws Exception {
        userCrewService.approveCrew(id,status);
    }


    /**
     * 审批关注团的申请
     * @param id
     * @param status
     * @throws Exception
     */
    @RequestMapping(value = "/approve")
    public void approveCrew(Integer id,String status) throws Exception {
        userCrewService.approveCrewApply(id,status);
    }


    @RequestMapping(value = "/me/unbind")
    public TblDanceUser unbindCrewMe(Integer id) throws Exception {
        return userCrewService.unbindCrewMe(id);
    }



    /**
     * 查询所有的关注团的申请
     * @param ss
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/apply/search")
    public Object searchCrewApply(SearchCrewApply ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    /**
     * 查询我发的关注团申请
     * @param ss
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/apply/me/search")
    public Object searchCrewApplyMe(SearchCrewApplyMe ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    /**
     * 查询我发布的crew
     * @param ss
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/me/search")
    public Object searchCrewMe(SearchCrewMe ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }
}
