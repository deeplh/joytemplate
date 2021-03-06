//package com.bcwms.boogie.client;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import com.bcwms.boogie.exception.HttpCommunicationException;
//import com.bcwms.boogie.msg.CommonDataResponse;
//import com.bcwms.boogie.service.RestTemplateService;
//import com.bcwms.boogie.vo.AssessmentVO;
//import com.bcwms.boogie.vo.CommonGetReq;
//import com.bcwms.boogie.vo.CommonSetStatusReq;
//import com.bcwms.boogie.vo.CrewCourseVO;
//import com.bcwms.boogie.vo.CrewStudyVO;
//import com.bcwms.boogie.vo.CrewVO;
//import com.bcwms.boogie.vo.EnlisterVO;
//import com.bcwms.boogie.vo.ExamVO;
//import com.bcwms.boogie.vo.MemberVO;
//import com.bcwms.boogie.vo.PerformanceTradePickDancerReq;
//import com.bcwms.boogie.vo.PerformanceTradeVO;
//import com.bcwms.boogie.vo.StudioCourseVO;
//import com.bcwms.googie.tools.JacksonUtils;
//
//import lombok.Data;
//
//@Data
//public class BoogieClient {
//
//    private RestTemplate client;
//    private String url;
//
//    public BoogieClient(String url) {
//        super();
//        this.client = RestTemplateService.getRestTemplate();
//        this.url = url;
//    }
//
//    private String getActiveUrl() {
//        String[] urls = StringUtils.split(url, ";");
//        int index = (int) (System.currentTimeMillis() % urls.length);
//        return urls[index];
//    }
//
//    public CommonDataResponse<CrewVO> createNewCrew(CrewVO req) throws URISyntaxException {
//        ResponseEntity<String> entity =
//                client.postForEntity(new URI(getActiveUrl() + "api/crew/create"), req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, CrewVO.class);
//
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse<CrewVO> getCrewInfo(CommonGetReq req) {
//        ResponseEntity<String> entity =
//                (ResponseEntity<String>) client.postForEntity(getActiveUrl() + "api/crew/get", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, CrewVO.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse setCrewStatus(CommonSetStatusReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/crew/set/status", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse<CrewCourseVO> createNewCrewCourse(CrewCourseVO req) throws URISyntaxException {
//        ResponseEntity<String> entity =
//                client.postForEntity(new URI(getActiveUrl() + "api/crewCourse/create"), req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, CrewCourseVO.class);
//
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse<CrewCourseVO> getCrewCourseInfo(CommonGetReq req) {
//        ResponseEntity<String> entity =
//                (ResponseEntity<String>) client.postForEntity(getActiveUrl() + "api/crewCourse/get", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, CrewCourseVO.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse setCrewCourseStatus(CommonSetStatusReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/crewCourse/set/status", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse<CrewStudyVO> createNewCrewStudy(CrewStudyVO req) throws URISyntaxException {
//        ResponseEntity<String> entity =
//                client.postForEntity(new URI(getActiveUrl() + "api/crewStudy/create"), req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, CrewStudyVO.class);
//
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse<CrewStudyVO> getCrewStudyInfo(CommonGetReq req) {
//        ResponseEntity<String> entity =
//                (ResponseEntity<String>) client.postForEntity(getActiveUrl() + "api/crewStudy/get", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, CrewStudyVO.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse setCrewStudyStatus(CommonSetStatusReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/crewStudy/set/status", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse<ExamVO> createNewExam(ExamVO req) throws URISyntaxException {
//        ResponseEntity<String> entity =
//                client.postForEntity(new URI(getActiveUrl() + "api/exam/create"), req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, ExamVO.class);
//
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse<ExamVO> getExamInfo(CommonGetReq req) {
//        ResponseEntity<String> entity =
//                (ResponseEntity<String>) client.postForEntity(getActiveUrl() + "api/exam/get", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, ExamVO.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse setExamStatus(CommonSetStatusReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/exam/set/status", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse<MemberVO> createNewMember(MemberVO req) throws URISyntaxException {
//        ResponseEntity<String> entity =
//                client.postForEntity(new URI(getActiveUrl() + "api/member/create"), req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, MemberVO.class);
//
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse<MemberVO> getMemberInfo(CommonGetReq req) {
//        ResponseEntity<String> entity =
//                (ResponseEntity<String>) client.postForEntity(getActiveUrl() + "api/member/get", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, MemberVO.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse setMemberStatus(CommonSetStatusReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/member/set/status", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    // 新增商演合同
//    public CommonDataResponse<PerformanceTradeVO> createNewPerformanceTrade(PerformanceTradeVO req)
//            throws URISyntaxException {
//        ResponseEntity<String> entity =
//                client.postForEntity(new URI(getActiveUrl() + "api/performanceTrade/create"), req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, PerformanceTradeVO.class);
//
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse<PerformanceTradeVO> getPerformanceTrade(CommonGetReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/performanceTrade/get", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, PerformanceTradeVO.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    // 商演活动报名
//    public CommonDataResponse<EnlisterVO> enlist(EnlisterVO req) throws URISyntaxException {
//        ResponseEntity<String> entity =
//                client.postForEntity(new URI(getActiveUrl() + "api/performanceTrade/enlist"), req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, EnlisterVO.class);
//
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    // 商演报名者的链上地址
//    public CommonDataResponse<List<String>> getEnlisters(CommonGetReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/performanceTrade/get/enlisters", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    // 商演活动组织方选择舞者
//    public CommonDataResponse pickDancers(PerformanceTradePickDancerReq req) throws URISyntaxException {
//        ResponseEntity<String> entity =
//                client.postForEntity(new URI(getActiveUrl() + "api/performanceTrade/pick/dancers"), req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    // 查询入围舞者的UserID
//    public CommonDataResponse<List<String>> getPickedDancers(CommonGetReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/performanceTrade/get/pickedDancers", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            System.out.println(entity.getBody());
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    // 商演活动组织方评价
//    public CommonDataResponse assess(AssessmentVO req) throws URISyntaxException {
//        ResponseEntity<String> entity =
//                client.postForEntity(new URI(getActiveUrl() + "api/performanceTrade/assess"), req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    // 查询某合约内的所有评价的信息
//    public CommonDataResponse<List<String>> getAssessments(CommonGetReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/performanceTrade/get/assessment", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    // 设置商演合同状态 0-正常 1-废弃
//    public CommonDataResponse setPerformanceTradeStatus(CommonSetStatusReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/performanceTrade/set/status", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    // 设置评价状态 0-正常 1-废弃
//    public CommonDataResponse setAssessmentStatus(CommonSetStatusReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/assessment/set/status", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    // 设置报名者状态 0-正常 1-废弃
//    public CommonDataResponse setEnlisterStatus(CommonSetStatusReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/enlister/set/status", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    // 某条商演评价的详情，输入是链上的地址
//    public CommonDataResponse<AssessmentVO> getAssessment(CommonGetReq req) {
//        ResponseEntity<String> entity =
//                (ResponseEntity<String>) client.postForEntity(getActiveUrl() + "api/assessment/get", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, AssessmentVO.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    // 某条报名信息的详情，输入是链上的地址
//    public CommonDataResponse<EnlisterVO> getEnlister(CommonGetReq req) {
//        ResponseEntity<String> entity =
//                (ResponseEntity<String>) client.postForEntity(getActiveUrl() + "api/enlister/get", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, EnlisterVO.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse<StudioCourseVO> createNewStudioCourse(StudioCourseVO req) throws URISyntaxException {
//        ResponseEntity<String> entity =
//                client.postForEntity(new URI(getActiveUrl() + "api/studioCourse/create"), req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, StudioCourseVO.class);
//
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse<StudioCourseVO> getStudioCourseInfo(CommonGetReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/studioCourse/get", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class, StudioCourseVO.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//    public CommonDataResponse setStudioCourseStatus(CommonSetStatusReq req) {
//        ResponseEntity<String> entity = (ResponseEntity<String>) client
//                .postForEntity(getActiveUrl() + "api/studioCourse/set/status", req, String.class);
//        if (entity.getStatusCode().equals(HttpStatus.OK)) {
//            return JacksonUtils.fromJson(entity.getBody(), CommonDataResponse.class);
//        } else {
//            throw new HttpCommunicationException(entity.getStatusCode().getReasonPhrase());
//        }
//    }
//
//}
