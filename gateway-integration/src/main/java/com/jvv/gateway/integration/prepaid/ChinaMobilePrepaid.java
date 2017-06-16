/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-17 23:00 创建
 *
 */
package com.jvv.gateway.integration.prepaid;

import com.jvv.gateway.biz.prepaid.PrepaidChannel;
import com.jvv.gateway.biz.prepaid.TelephoneBillPrepaid;
import com.jvv.common.services.result.SimpleResult;

/**
 * 发送移动充值
 *
 * @author turalyon@jinvovo.com
 */
public class ChinaMobilePrepaid implements PrepaidChannel {

	@Override
	public SimpleResult prepaid(TelephoneBillPrepaid bill) {
		//发送移动......

		return new SimpleResult();
	}

	@Override
	public String forChannelApi() {
		return "ChinaMobile001";
	}
}
