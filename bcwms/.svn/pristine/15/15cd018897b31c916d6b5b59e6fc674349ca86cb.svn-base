package com.bcwms.controller.admin;


import com.alibaba.fastjson.JSONArray;
import com.bcwms.bean.crew.*;
import com.bcwms.entity.TblDanceCrewTermLessonDetail;
import com.bcwms.entity.TblDanceCrewTermUserDetail;
import com.bcwms.service.impl.crew.CrewTermLessonServiceImpl;
import com.bcwms.service.impl.crew.CrewTermServiceImpl;
import com.bcwms.service.impl.crew.CrewTermUserServiceImpl;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/admin/crew",method = {RequestMethod.POST,RequestMethod.GET})
public class CrewAdminController {

    @Autowired
    private CrewTermUserServiceImpl crewTermUserService;

    @Autowired
    private CrewTermServiceImpl crewTermService;

    @Autowired
    private CrewTermLessonServiceImpl crewTermLessonService;

    /**
     * 查询所有的团
     * @param ss
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search")
    public Object searchCrew(SearchCrewAll ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }


    /**
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/term/search")
    public Object searchCrewTerm(SearchCrewTerm ss) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    /**
     * 根据团期id查询对应的所有考团的名单信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/term/user/search")
    public Object searchCrewTermUser(SearchCrewTermUser ss) throws Exception {
        KeepJoyPagination kjp = new KeepJoyPagination(ss);
        kjp.setPagination(false);
        return kjp.doPagination();
    }


    /**
     * 查询所有考团的老师打分明细
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/term/user/score/search")
    public Object searchCrewTermUserScore(SearchCrewTermUserScore ss) throws Exception {
        KeepJoyPagination kjp = new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    /**
     * 团期结算
     * @param crewTermId
     * @param crewTermUserDetails
     */
    @RequestMapping(value = "/term/exam/settle")
    public void settleCrewTermExam(@RequestParam(value = "crewTermId", required = true)Integer crewTermId,
                                   @RequestParam(value = "crewTermUserDetails", required = true)String crewTermUserDetails) throws URISyntaxException {
        crewTermService.settleCrewTermExam(crewTermId,JSONArray.parseArray(crewTermUserDetails,TblDanceCrewTermUserDetail.class));
    }

    @RequestMapping(value = "/term/lesson/search")
    public Object searchCrewTermLesson(@RequestBody @Valid SearchCrewTermLesson searchCrewTermLesson) throws Exception {
        KeepJoyPagination kjp=new KeepJoyPagination(searchCrewTermLesson);
        return kjp.doPagination();
    }

    /*
    * 创建团课
    * */
    @RequestMapping(value = "/term/lesson/create")
    public String create(@RequestBody @Valid TblDanceCrewTermLessonDetail obj) throws Exception{
        crewTermLessonService.create(obj);
        return "创建成功！";
    }
    /*
    * GET团课详情
    * */
    @RequestMapping(value = "/term/lesson/get")
    public TblDanceCrewTermLessonDetail get(@RequestParam(required = true) Integer id) throws Exception{

        return crewTermLessonService.get(id);
    }

    /*
    * 团课签到-老师
    * *//*
    @RequestMapping(value = "/term/lesson/sign-teacher")
    public String signTeacher(@RequestParam Integer id) throws Exception{

        return crewTermLessonService.doSignTeacher(id);
    }

    *//*
     * 团课签到-学生
     * *//*
    @RequestMapping(value = "/term/lesson/sign-student")
    public String signStudent(@RequestParam Integer id) throws Exception{

        return crewTermLessonService.doSignStudent(id);
    }*/
}
