/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-17 22:09 创建
 *
 */
package com.jvv.gateway.provider.prepaid;

import com.jvv.common.services.order.validation.Add;
import com.jvv.gateway.biz.prepaid.TelephoneBillPrepaidService;
import com.jvv.gateway.facade.prepaid.api.TelephoneBillPrepaidApi;
import com.jvv.gateway.facade.prepaid.order.TelephoneBillPrepaidOrder;
import com.jvv.gateway.facade.prepaid.result.TelephoneBillPrepaidResult;
import com.jvv.gateway.provider.AbstractProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.groups.Default;

/**
 * @author turalyon@jinvovo.com
 */
@Service("telephoneBillPrepaidApi")
public class TelephoneBillPrepaidProvider extends AbstractProvider implements TelephoneBillPrepaidApi {

	@Autowired
	private TelephoneBillPrepaidService telephoneBillPrepaidService;

	@Override
	public TelephoneBillPrepaidResult prepaid(TelephoneBillPrepaidOrder order) {
		logger.info("===>>收到话费充值请求，order={}",order);
		TelephoneBillPrepaidResult result;
		try {
			//check order
			checkOrder(order, Default.class, Add.class);

			result = telephoneBillPrepaidService.prepaid(order);
		} catch (Exception e) {
			result = handleResult(e,new TelephoneBillPrepaidResult());
		}
		logger.info("<<===话费充值处理完成，result={}",result);
		return result;
	}
}
