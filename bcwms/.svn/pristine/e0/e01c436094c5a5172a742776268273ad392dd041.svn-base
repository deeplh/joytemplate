package com.bcwms.controller.admin;


import com.alibaba.fastjson.JSONObject;
import com.bcwms.bean.studio.SearchStudioStore;
import com.bcwms.bean.studio.SearchStudioStoreLesson;
import com.bcwms.bean.studio.SearchStudioTeacher;
import com.bcwms.entity.TblDanceStudioStoreLessonDetail;
import com.bcwms.service.impl.studio.StudioStoreLessonServiceImpl;
import com.bcwms.service.impl.studio.StudioStoreServiceImpl;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/admin/studio",method = {RequestMethod.POST,RequestMethod.GET})
public class StudioAdminController {


    @RequestMapping(value = "/search_teacher")
    public Object searchStudioStoreTeacher(SearchStudioTeacher s) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(s);
        return kjp.doPagination();
    }


}



