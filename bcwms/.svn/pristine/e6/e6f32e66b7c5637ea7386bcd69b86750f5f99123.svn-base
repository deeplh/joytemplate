package com.bcwms.controller.app.user;


import com.bcwms.bean.studio.*;
import com.bcwms.entity.TblDanceUser;
import com.bcwms.service.impl.studio.StudioServiceImpl;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app/user/studio",method = {RequestMethod.POST,RequestMethod.GET})
public class UserStudioController {

    @Autowired
    private StudioServiceImpl studioService;

    @RequestMapping(value = "/me/apply")
    public void applyStudioMe(Integer studioId) throws Exception {
        studioService.applyStudioMe(studioId);
    }

    @RequestMapping(value = "/create/approve")
    public void approveCreateStudio(Integer id,String status) throws Exception {
        studioService.approveCreateStudio(id,status);
    }


    @RequestMapping(value = "/approve")
    public void approveStudio(Integer id,String status) throws Exception {
        studioService.approveStudio(id,status);
    }


    @RequestMapping(value = "/me/unbind")
    public TblDanceUser unbindStudioMe(Integer id) throws Exception {
        return studioService.unbindStudioMe(id);
    }


    @RequestMapping(value = "/apply/search")
    public Object searchStudioApply(SearchStudioApply ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    @RequestMapping(value = "/apply/me/search")
    public Object searchStudioApplyMe(SearchStudioApplyMe ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    @RequestMapping(value = "/me/search")
    public Object searchStudioMe(SearchStudioMe ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }
}
