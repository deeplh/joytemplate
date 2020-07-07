package com.bcwms.controller.admin;


import com.alibaba.fastjson.JSONObject;
import com.bcwms.bean.studio.SearchStudioStore;
import com.bcwms.bean.studio.SearchStudioStoreLesson;
import com.bcwms.entity.TblDanceStudioStoreLessonDetail;
import com.bcwms.service.impl.studio.StudioStoreLessonServiceImpl;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/admin/studio/store",method = {RequestMethod.POST,RequestMethod.GET})
public class StudioStoreAdminController {


    private final StudioStoreLessonServiceImpl studioStoreLessonService;

    @Autowired
    public StudioStoreAdminController(StudioStoreLessonServiceImpl studioStoreLessonService) {
        this.studioStoreLessonService = studioStoreLessonService;
    }


    @RequestMapping(value = "/search")
    public Object searchStudioStore(SearchStudioStore s) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(s);
        return kjp.doPagination();
    }


    @RequestMapping(value = "/create_lesson")
    public void createStudioStoreLesson(String obj) {
        studioStoreLessonService.createStudioStoreLesson(JSONObject.parseObject(obj,TblDanceStudioStoreLessonDetail.class));

    }

    @RequestMapping(value = "/search_lesson")
    public Object searchStudioStoreLesson(SearchStudioStoreLesson s) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(s);
        return kjp.doPagination();
    }

    @RequestMapping(value = "/remove_lesson")
    public void removeStudioStoreLesson(Integer id) {
         studioStoreLessonService.removeStudioStoreLesson(id);
    }

    @RequestMapping(value = "/settle_lesson")
    public void settleStudioStoreLesson(Integer studioStoreId) throws KeepJoyServiceException {
        studioStoreLessonService.settleStudioStoreLesson(studioStoreId);
    }

}



