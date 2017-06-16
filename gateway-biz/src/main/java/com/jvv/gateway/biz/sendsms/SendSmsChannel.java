/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.biz.sendsms;

import com.jvv.common.services.result.SimpleResult;
import com.jvv.gateway.biz.prepaid.TelephoneBillPrepaid;
import com.jvv.gateway.facade.sendsms.info.SendSmsInfo;
import com.jvv.gateway.facade.sendsms.order.SendSmsOrder;
import com.jvv.gateway.facade.sendsms.result.SmsBalanceResult;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉 <p>
 * 〈功能详细描述〉
 *
 * @author linxm
 * @date
 * @time
 */
public interface SendSmsChannel {
	/**
	 * 发送短信
	 * @param bill
	 * @return
	 */
	SimpleResult sendSms(SendSmsOrder bill);
	
	/**
	 * 查询余量接口
	 * @return code:短信网关原响应码   message：剩余短信数量    description:具体描述
	 */
	SmsBalanceResult queryBalance ();
	
	String forChannelApi();
	
	
}
