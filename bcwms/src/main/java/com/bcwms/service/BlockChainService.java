//package com.bcwms.service;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.bcwms.bean.chain.ChainDataResponse;
//import com.bcwms.boogie.client.BoogieClient;
//import com.bcwms.boogie.msg.CommonDataResponse;
//import com.bcwms.boogie.vo.CommonGetReq;
//import com.bcwms.boogie.vo.CommonRequestVO;
//import com.bcwms.boogie.vo.ExamVO;
//import com.bcwms.entity.TblDanceCommonAttr;
//import com.bcwms.googie.tools.JacksonUtils;
//import com.bcwms.properties.BcwProperties;
//import com.bcwms.service.BcwCommonService;
//import com.bcwms.util.GenericUtil;
//import com.keepjoy.core.dao.KeepJoyDaoImpl;
//import com.keepjoy.core.exception.KeepJoyServiceException;
//import com.keepjoy.core.util.MyLogUtil;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.lang.reflect.Method;
//
//@Service
//@Transactional(rollbackFor=Exception.class)
//public class BlockChainService {
//
//    @Autowired
//    private KeepJoyDaoImpl dao;
//
//
//
//    private CommonDataResponse doBlockChainCommon(TblDanceCommonAttr fromObj, CommonRequestVO vo,
//                              String boogieClientDeclaredMethod) throws Exception {
//        BoogieClient c = new BoogieClient(BcwProperties.getBlockchainUrl());
//        Method method = c.getClass().getDeclaredMethod(boogieClientDeclaredMethod,vo.getClass());
//
//        Object result = method.invoke(c,vo);
//        String resStr= JacksonUtils.toJson(result);
//
//        MyLogUtil.printlnInfo("["+boogieClientDeclaredMethod+"]上链返回:" + resStr);
//
//        CommonDataResponse<JSONObject> res=JSONObject.parseObject(resStr,CommonDataResponse.class);
//
//        return res;
//    }
//
//    private void doBlockChainPrivate(TblDanceCommonAttr fromObj, CommonRequestVO vo,
//                               String boogieClientDeclaredMethod) throws Exception {
//
//        CommonDataResponse<JSONObject> res=doBlockChainCommon(fromObj,vo,boogieClientDeclaredMethod);
//
//        if(res.getStatus() == 0){
//            if(null!=res.getData()){
//                fromObj.setContractAddress(res.getData().getString("contractAddress"));
//            }
//            fromObj.setSettleDateTime(BcwCommonService.getNow());
//            dao.update(fromObj);
//        }else{
//            MyLogUtil.printlnException("["+boogieClientDeclaredMethod+"]上链失败");
//        }
//    }
//
//    @Async("asyncBlockChainServiceExecutor")
//    public void doBlockChain(TblDanceCommonAttr fromObj, CommonRequestVO vo,
//                             String boogieClientDeclaredMethod) throws Exception {
//        doBlockChainPrivate(fromObj,vo,boogieClientDeclaredMethod);
//    }
//
//
//    @Async("asyncBlockChainServiceExecutor")
//    public void doBlockChainWithGet(TblDanceCommonAttr fromObj, CommonRequestVO vo,
//                               String boogieClientDeclaredMethod, CommonGetReq cgr,String getMethod)throws Exception {
//
//        CommonDataResponse<JSONObject> res=doBlockChainCommon(fromObj,vo,boogieClientDeclaredMethod);
//
//        if(res.getStatus() == 0){
//            if(StringUtils.isNotEmpty(getMethod) && cgr!=null){
//                doBlockChainPrivate(fromObj,cgr,getMethod);
//            }
//        }else{
//            MyLogUtil.printlnException("["+getMethod+"]上链失败");
//        }
//    }
//
//}
