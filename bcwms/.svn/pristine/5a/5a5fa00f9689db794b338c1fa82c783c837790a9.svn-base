package com.keepjoy.weixin.rpc.smallplatform;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.keepjoy.weixin.bean.smallplatform.SessionKeyBean;
import com.keepjoy.weixin.rpc.SendTemplate;

public class SendGetSessionKeyByCode extends SendTemplate {

	public SendGetSessionKeyByCode(String appId,String secret,String code) {
		super("https://api.weixin.qq.com/sns/jscode2session");
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("appid",appId);
		param.put("secret",secret);
		param.put("js_code",code);
		param.put("grant_type","authorization_code");
		super.param=param;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <T> T getResultBean(String result) {
		// TODO Auto-generated method stub

		return (T) JSONObject.parseObject(result, SessionKeyBean.class);
	}

}
