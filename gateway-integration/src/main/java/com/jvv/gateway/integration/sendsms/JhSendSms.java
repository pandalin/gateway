/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.integration.sendsms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jvv.common.enums.Status;
import com.jvv.common.services.result.SimpleResult;
import com.jvv.gateway.biz.sendsms.SendSmsChannel;
import com.jvv.gateway.facade.sendsms.order.SendSmsOrder;
import com.jvv.gateway.facade.sendsms.result.SmsBalanceResult;
import com.jvv.gateway.integration.sendsms.utils.JhSmsUtils;
import com.jvv.gateway.integration.sendsms.utils.YcMessageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉 <p>
 * 〈功能详细描述〉
 *
 * @author linxm
 * @date
 * @time
 */
@Service("jhSendSms")
public class JhSendSms implements SendSmsChannel {
	
	
	//#聚合短信云服务平台配置文件(yc)
	@Value("#{jvvContext.jh_sms_url}")
	private String jhUrl;
	@Value("#{jvvContext.jh_sms_appkey}")
	private String appKey;
	
	
	@Override
	public SimpleResult sendSms (SendSmsOrder bill) {
		String result = null;
		String url = jhUrl;//请求接口地址
		Map params = new HashMap ();//请求参数
		params.put ("mobile", bill.getPhoneNum ());//接收短信的手机号码
		params.put ("tpl_id", bill.getSmsTempEnum ().getJhTempId ());//短信模板ID，请参考个人中心短信模板设置
		params.put ("tpl_value", bill.getSmsParam ()
				.toString ());//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
		params.put ("key", appKey);//应用APPKEY(应用详细页查询)
		params.put ("dtype", "");//返回数据的格式,xml或json，默认json
		try {
			result = JhSmsUtils.net (url, params, "GET");
		} catch (Exception e) {
			throw new RuntimeException ();//todo qyj 异常信息
		}
		
		return jhSendMessageType(result);
	}
	
	@Override
	public SmsBalanceResult queryBalance () {
		SmsBalanceResult s=	new SmsBalanceResult();
		s.setStatus(Status.SUCCESS);
		s.setMessage("0");
		s.setDescription("通道未启用");
		return s;
	}
	
	public SimpleResult jhSendMessageType (String jsonStr) {
		SimpleResult simpleResult = new SimpleResult ();
		JSONObject object = JSON.parseObject (jsonStr);
		if (object.getIntValue ("error_code") == 0) {//成功
			simpleResult.setStatus (Status.SUCCESS);
			simpleResult.setCode (String.valueOf (object.getIntValue ("error_code")));
		} else {
			simpleResult.setStatus (Status.FAIL);
			simpleResult.setCode (String.valueOf (object.getIntValue ("error_code")));
		}
		return simpleResult;
	}
	
	
	@Override
	public String forChannelApi () {
		return "jh";
	}
}
