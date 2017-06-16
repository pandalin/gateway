/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-02-28 23:15 创建
 *
 */
package com.jvv.gateway.provider;

import com.jvv.common.enums.Status;
import com.jvv.common.services.order.AbstractOrder;
import com.jvv.common.services.result.SimpleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author turalyon@jinvovo.com
 */
public abstract class AbstractProvider {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected void checkOrder(AbstractOrder order, Class<?>... groups) {
		//do something
		order.checkWithGroup (groups);
	}

	protected <R extends SimpleResult> R handleResult(Exception e, R result) {
		if(e instanceof RuntimeException){
			// do something
			result.setStatus(Status.FAIL);
			result.setCode("99x999999");
			result.setMessage("系统异常:"+e.toString());

		}else{
			result.setStatus(Status.FAIL);
			result.setCode("99x999999");
			result.setMessage("系统异常"+e.toString());
		}

		return result;
	}

}
