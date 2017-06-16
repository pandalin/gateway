/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-02-28 22:28 创建
 *
 */
package com.jvv.gateway.facade.prepaid.api;

import com.jvv.gateway.facade.prepaid.order.TelephoneBillPrepaidOrder;
import com.jvv.gateway.facade.prepaid.result.TelephoneBillPrepaidResult;

/**
 * 话费充值 接口
 * @author turalyon@jinvovo.com
 */
public interface TelephoneBillPrepaidApi {

	/**
	 * 话费充值接口
	 *
	 * @param order
	 * @return
	 */
	public TelephoneBillPrepaidResult prepaid(TelephoneBillPrepaidOrder order);


}
