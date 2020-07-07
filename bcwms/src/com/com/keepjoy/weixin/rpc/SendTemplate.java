package com.keepjoy.weixin.rpc;

import java.io.IOException;

import com.alibaba.fastjson.JSONArray;
import com.keepjoy.core.exception.KeepJoyServiceException;
import com.keepjoy.core.module.net.KeepJoyHttp;
import com.keepjoy.core.util.MyLogUtil;
import com.keepjoy.weixin.bean.CommonResponseBean;
import org.apache.commons.lang.StringUtils;

public abstract class SendTemplate {
	private KeepJoyHttp http;
	private String url;
	protected String charset;
	
	protected Object param;

	
	public SendTemplate(String url) {
		this.url = url;
		this.http=new KeepJoyHttp();
	}


	public <T> T send() throws IOException{
		String result=null;

			if(StringUtils.isEmpty(charset))charset="utf-8";
			try {
				result=http.sendPost(url, param,charset);
			} catch (Exception e) {
				MyLogUtil.printlnException("send http error");
				throw new KeepJoyServiceException("发送http请求时出现未知异常");
			}

		
		if(StringUtils.isEmpty(result)){
			throw new KeepJoyServiceException("调用微信接口["+url+"]出现异常,返回为空");
		}else{
			try {
				CommonResponseBean eb=JSONArray.parseObject(result, CommonResponseBean.class);
				if(CommonResponseBean.SUCCESS!=eb.getErrcode()){
					KeepJoyServiceException jta=new KeepJoyServiceException(eb.getErrmsg(),eb.getErrcode());
					MyLogUtil.printlnException(jta,eb.getErrcode()+":"+eb.getErrmsg());
					throw jta;
				}
			} catch (KeepJoyServiceException e) {
				throw e;
			}  catch (Exception e) {//说明强转出错,没有返回错误json,返回的是正常json
			} 
			return getResultBean(result);
		}
	}
	
	
	protected abstract <T> T getResultBean(String result);
}
