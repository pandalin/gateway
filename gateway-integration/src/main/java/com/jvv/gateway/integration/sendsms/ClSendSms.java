/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.integration.sendsms;

import com.bcloud.msg.http.HttpSender;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.jvv.common.enums.Status;
import com.jvv.common.services.result.SimpleResult;
import com.jvv.gateway.biz.sendsms.SendSmsChannel;
import com.jvv.gateway.common.exceptions.SendSmsExceprion;
import com.jvv.gateway.facade.sendsms.info.SmsBalanceInfo;
import com.jvv.gateway.facade.sendsms.order.SendSmsOrder;
import com.jvv.gateway.facade.sendsms.result.SmsBalanceResult;
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
@Service("clSendSms")
public class ClSendSms implements SendSmsChannel {
	
	
	//#创蓝短信云服务平台配置文件
	@Value("#{jvvContext.cl_sms_url}")
	protected String clSmsUrl;
	@Value("#{jvvContext.cl_sms_balance_url}")
	protected String clSmsBalanceUrl;
	@Value("#{jvvContext.cl_sms_account}")
	protected String clSmsAccount;
	@Value("#{jvvContext.cl_sms_pswd}")
	protected String clSmsPswd;
	
	
	@Override
	public SimpleResult sendSms (SendSmsOrder bill) {
		
		String result = null;
		try {
			Preconditions.checkNotNull (bill.getMessage (), "短信内容不能为空");
			Preconditions.checkNotNull (bill.getPhoneNum (), "手机号不能为空");
			result = HttpSender.batchSend (clSmsUrl, clSmsAccount, clSmsPswd, bill.getPhoneNum (), bill.getMessage (), true, null,
					            null);
		} catch (Exception e) {
			throw new SendSmsExceprion ("创蓝短信发送失败" + result, e);
		}
		
		return clSendMessageType (result);
		
	}
	
	
	@Override
	public SmsBalanceResult queryBalance () {
		
		String result = null;
		try {
			result = HttpSender.batchSend (clSmsBalanceUrl, clSmsAccount, clSmsPswd, null, null, true, null,
			                               null);
		} catch (Exception e) {
			//throw new SendSmsExceprion ("创蓝短信发送失败" + result, e);
		}
		
		return clSendMessageType (result);
	}
	
	public SmsBalanceResult clSendMessageType (String XMLStr) {

		SmsBalanceResult simpleResult = new SmsBalanceResult ();
		if (!Strings.isNullOrEmpty (XMLStr)) {
			String[] respArr = XMLStr.split (",");
			if (respArr[1].startsWith ("0")) {//请求成功
				/*simpleResult.setStatus (Status.SUCCESS);
				simpleResult.setCode (respArr[1]);
				simpleResult.setMessage (respArr[3]);*/

				SmsBalanceInfo balanceInfo = new SmsBalanceInfo(Integer.valueOf(respArr[3]).intValue());
				simpleResult.setInfo(balanceInfo);

				simpleResult.setToSuccess("查询成功");
			} else {
				simpleResult.setStatus (Status.FAIL);
				simpleResult.setCode (respArr[1]);
				simpleResult.setMessage(respArr[1]);
			}
			
		} else {
			simpleResult.setStatus (Status.FAIL);
			simpleResult.setMessage ("CL短信响应报文为空");
		}
		return simpleResult;
	}
	
	
	@Override
	public String forChannelApi () {
		return "cl";
	}
}
