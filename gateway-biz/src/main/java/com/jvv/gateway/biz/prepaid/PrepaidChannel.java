/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-17 23:00 创建
 *
 */
package com.jvv.gateway.biz.prepaid;


import com.jvv.common.services.result.SimpleResult;

/**
 * 充值通道(只是举例)
 *
 * @author turalyon@jinvovo.com
 */
public interface PrepaidChannel {

	SimpleResult prepaid(TelephoneBillPrepaid bill);

	String forChannelApi();
}
