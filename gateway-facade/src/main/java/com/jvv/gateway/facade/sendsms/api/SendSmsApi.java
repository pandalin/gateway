/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.facade.sendsms.api;

import com.jvv.gateway.facade.sendsms.order.QueryBalanceOrder;
import com.jvv.gateway.facade.sendsms.order.SendSmsOrder;
import com.jvv.gateway.facade.sendsms.result.SendSmsResult;
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
public interface SendSmsApi {
	
	SendSmsResult sendSms(SendSmsOrder sendSmsOrder);

	SmsBalanceResult queryBalance(QueryBalanceOrder order);
	
}
