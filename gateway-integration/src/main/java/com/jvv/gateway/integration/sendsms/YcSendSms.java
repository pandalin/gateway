/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.integration.sendsms;

import com.alibaba.fastjson.JSONObject;
import com.jvv.gateway.biz.sendsms.SendSmsChannel;
import com.jvv.common.services.result.SimpleResult;
import com.jvv.common.enums.Status;
import com.jvv.gateway.facade.sendsms.order.SendSmsOrder;
import com.jvv.gateway.facade.sendsms.result.SmsBalanceResult;
import com.jvv.gateway.integration.sendsms.utils.YcMessageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉 <p>
 * 〈功能详细描述〉
 *
 * @author linxm
 * @date
 * @time
 */
@Service("ycSendSms")
public class YcSendSms implements SendSmsChannel {
	
	
	//#云测短信云服务平台配置文件
	@Value("#{jvvContext.yc_sms_url}")
	private String ycUrl;
	@Value("#{jvvContext.yc_sms_apiKey}")
	private String ycApiKey;
	@Value("#{jvvContext.yc_sms_secretKey}")
	private String secretKey;
	
	
	@Override
	public SimpleResult sendSms (SendSmsOrder bill) {
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("op", "Sms.send");
		jsonObj.put("apiKey", ycApiKey);
		jsonObj.put("ts", System.currentTimeMillis());
		jsonObj.put("phone", bill.getPhoneNum ());
		jsonObj.put("templateId", bill.getSmsTempEnum ().getYcTempId ());
		jsonObj.put("content", bill.getMessage ());
		jsonObj.put("taskId", System.currentTimeMillis() + "");//不超过64位长度的唯一字符串，通过和recvRPT获取的结果里的teskid关联，确定发送的信息是否收到。
		jsonObj.put("extNum", "");
		jsonObj.put("sig", YcMessageUtil.getSig(jsonObj, secretKey));
		
		String result = YcMessageUtil.transmessage(ycUrl, jsonObj.toString());
		
		return ycSendMessageType(result);
	}
	
	
	@Override
	public SmsBalanceResult queryBalance () {
		SmsBalanceResult s=	new SmsBalanceResult();
		s.setStatus(Status.SUCCESS);
		s.setMessage("0");
		s.setDescription("通道已经弃用");

		return s;
	}
	
	private SimpleResult ycSendMessageType(String jsonStr) {
		SimpleResult simpleResult=new SimpleResult ();
		
		JSONObject jsonReq = JSONObject.parseObject (jsonStr);
		
		if(jsonReq.get("code")!=null) {
			if (jsonReq.get ("code").toString ().equals ("1000")) {
				simpleResult.setStatus (Status.SUCCESS);
				simpleResult.setCode (jsonReq.get ("code").toString ());
				return simpleResult;
			} else {
				simpleResult.setStatus (Status.FAIL);
				simpleResult.setCode (jsonReq.get ("code").toString ());
			}
		}else {
			simpleResult.setStatus (Status.FAIL);
			simpleResult.setMessage ("发送短信响应状态码为空");
		}
		
		return simpleResult;
	}
	
	
	
	@Override
	public String forChannelApi () {
		return "yc";
	}
}
