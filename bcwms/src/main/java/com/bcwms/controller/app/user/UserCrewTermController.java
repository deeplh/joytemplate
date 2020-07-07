package com.bcwms.controller.app.user;


import com.alibaba.fastjson.JSONObject;
import com.bcwms.BcwConstant;
import com.bcwms.bean.crew.SearchCrewTermUser;
import com.bcwms.bean.crew.SearchCrewTermUserScoreExaming;
import com.bcwms.bean.user.UserCrewTermDTO;
import com.bcwms.entity.TblDanceCrewTermUserDetail;
import com.bcwms.entity.TblDanceCrewTermUserDetailScore;
import com.bcwms.service.impl.crew.CrewTermLessonServiceImpl;
import com.bcwms.service.impl.crew.CrewTermUserServiceImpl;
import com.keepjoy.core.module.pagination.KeepJoyPagination;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/app/user/crew", method = {RequestMethod.POST, RequestMethod.GET})
public class UserCrewTermController {

    @Autowired
    private CrewTermUserServiceImpl crewTermUserService;

    @Autowired
    private CrewTermLessonServiceImpl crewTermLessonService;


    /**
     * 查询本次的所有参与考团的人员(包括老师和学生)
     *
     * @param ss
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/term/user/search")
    public Object searchCrewTermUser(SearchCrewTermUser ss) throws Exception {
        KeepJoyPagination kjp = new KeepJoyPagination(ss);
        return kjp.doPagination();
    }

    /**
     * 修改参考人员类型
     *
     * @param id
     * @param crewUserType
     * @throws Exception
     */
    @RequestMapping(value = "/term/user/type/update")
    public void updateCrewUserType(Integer id, String crewUserType) throws Exception {
        crewTermUserService.updateCrewUserType(id, crewUserType);
    }

    /**
     * 老师打分
     *
     * @param id
     * @param score
     */
    @RequestMapping(value = "/term/score")
    public TblDanceCrewTermUserDetailScore score(Integer id, String score) {
        return crewTermUserService.score(id, JSONObject.parseObject(score, TblDanceCrewTermUserDetailScore.class));
    }


    /**
     * 考团过程中老师查看自己的打分情况
     *
     * @return
     */
    @RequestMapping(value = "/term/score/examing/search")
    public Object searchCrewTermUserScoreExaming(SearchCrewTermUserScoreExaming ss) throws Exception {
        KeepJoyPagination kjp = new KeepJoyPagination(ss);
        return kjp.doPagination();
    }


    /*
     * 团课签到-老师
     * */
    @RequestMapping(value = "/term/lesson/sign-teacher")
    public String signTeacher(@RequestParam Integer id) throws Exception {

        return crewTermLessonService.doSignTeacher(id);
    }

    /*
     * 团课签到-学生
     * */
    @RequestMapping(value = "/term/lesson/sign-student")
    public String signStudent(@RequestParam Integer id) throws Exception {

        return crewTermLessonService.doSignStudent(id);
    }

    /*
     * 查询团员
     * */
    @RequestMapping(value = "term/member/query")
    public List<UserCrewTermDTO> queryMembers(@RequestParam Integer crewId, @RequestParam(required = false) Integer crewTermId, @RequestParam(required = false) String status) {
        List<TblDanceCrewTermUserDetail> list = crewTermUserService.queryByCrewIdAndTermId(crewId, crewTermId, status);
        return list.stream().map(detail -> {
            UserCrewTermDTO dto = new UserCrewTermDTO();
            BeanUtils.copyProperties(detail, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    /*
     * 申请加入
     * */
    @RequestMapping(value = "/term/member/applyJoin")
    public String applyJoin(UserCrewTermDTO userCrewTermDTO) {
        TblDanceCrewTermUserDetail detail = new TblDanceCrewTermUserDetail();
        BeanUtils.copyProperties(userCrewTermDTO, detail);
        detail.setStatus(BcwConstant.STATUS_0_ING);
        try {
            crewTermUserService.create(detail);
            return "申请成功。";
        } catch (Exception e) {
            return "申请失败。";
        }
    }

    /*
     * 审批申请
     * */
    @RequestMapping(value = "/term/member/approve")
    public String approve(@RequestParam Integer id, @RequestParam String status) {
        if (!BcwConstant.STATUS_1_NO.equals(status) && !BcwConstant.STATUS_2_YES.equals(status)) {
            return "审核状态异常。";
        }
        try {
            crewTermUserService.updateCrewUserStatus(id, status);
            return "审核完成。";
        } catch (Exception e) {
            return "审核异常。";
        }
    }
}
