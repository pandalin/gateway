/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.provider.sendSms;

import com.jvv.common.services.order.validation.Query;
import com.jvv.gateway.biz.smsrecord.SmsRecordService;
import com.jvv.gateway.facade.sendsms.api.SmsRecordApi;
import com.jvv.gateway.facade.sendsms.order.SmsRecordOrder;
import com.jvv.gateway.facade.sendsms.result.SmsRecordResult;
import com.jvv.gateway.provider.AbstractProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.groups.Default;

/**
 * Created by IntelliJ IDEA
 * 〈短信发送量provider〉 <p>
 * 〈短信发送量provider〉
 *
 * @author linxm
 * @date 2017/3/22
 * @time 9:47
 */
@Service("smsRecordApi")
public class SmsRecordProvider extends AbstractProvider implements SmsRecordApi {
	
	@Autowired
	private SmsRecordService smsRecordService;
	
	@Override
	public SmsRecordResult querySMSRecordByChannel (SmsRecordOrder smsRecordOrder) {
		SmsRecordResult result;
		try {
			checkOrder (smsRecordOrder, Default.class, Query.class);
			result = smsRecordService.querySmsRecordByChannel (smsRecordOrder);
		} catch (Exception e) {
			result = handleResult (e,new SmsRecordResult ());
		}
		return result;
	}
}
