/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.facade.sendsms.api;


import com.jvv.gateway.facade.sendsms.order.SmsRecordOrder;
import com.jvv.gateway.facade.sendsms.result.SmsRecordResult;

/**
 * Created by IntelliJ IDEA
 * 〈短信记录查询Api〉 <p>
 * 〈短信记录查询Api〉
 *
 * @author linxm
 * @date 2017/3/22
 * @time 9:45
 */
public interface SmsRecordApi {
	
	/**
	 * 查询渠道短信发送量
	 * @param smsRecordOrder
	 * @return
	 */
	SmsRecordResult querySMSRecordByChannel (SmsRecordOrder smsRecordOrder);
}
