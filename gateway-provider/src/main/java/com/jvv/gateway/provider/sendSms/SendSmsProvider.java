/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.provider.sendSms;

import com.jvv.common.services.order.validation.Add;
import com.jvv.common.services.result.SimpleResult;
import com.jvv.gateway.biz.sendsms.BillSendSmsService;
import com.jvv.gateway.facade.sendsms.api.SendSmsApi;
import com.jvv.gateway.facade.sendsms.order.QueryBalanceOrder;
import com.jvv.gateway.facade.sendsms.order.SendSmsOrder;
import com.jvv.gateway.facade.sendsms.result.SendSmsResult;
import com.jvv.gateway.facade.sendsms.result.SmsBalanceResult;
import com.jvv.gateway.provider.AbstractProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉 <p>
 * 〈功能详细描述〉
 *
 * @author linxm
 * @date
 * @time
 */
@Service("sendSmsApi")
public class SendSmsProvider  extends AbstractProvider implements SendSmsApi {
	
	@Resource
	BillSendSmsService sendSmsService;
	
	
	@Override
	public SendSmsResult sendSms (SendSmsOrder sendSmsOrder) {
		logger.info("===>>收到发送短信的请求，order={}",sendSmsOrder);

		SendSmsResult result;
		try {
			//check order
			checkOrder(sendSmsOrder, Default.class, Add.class);
			
			result = sendSmsService.sendSms (sendSmsOrder);
		} catch (Exception e) {
			result = handleResult(e,new SendSmsResult ());
		}
		logger.info("<<===话费充值处理完成，result={}",result);
		return result;
	}
	
	@Override
	public SmsBalanceResult queryBalance(QueryBalanceOrder order) {
		logger.info("===>>收到发送短信的请求，order={}",order);

		SmsBalanceResult result;
		try {
			//check order
			checkOrder(order, Default.class, Add.class);
			
			result = sendSmsService.queryBalance(order);
		} catch (Exception e) {
			result = handleResult(e,new SmsBalanceResult ());
		}
		logger.info("<<===话费充值处理完成，result={}",result);
		return result;
	}
	
}
