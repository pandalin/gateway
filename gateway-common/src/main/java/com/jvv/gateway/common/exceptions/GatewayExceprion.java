/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-17 22:51 创建
 *
 */
package com.jvv.gateway.common.exceptions;

import com.jvv.common.lang.exception.BizException;

/**
 * @author turalyon@jinvovo.com
 */
public class GatewayExceprion extends BizException {
	public GatewayExceprion(String message) {
		super(message);
	}

	public GatewayExceprion(String message, Throwable cause) {
		super(message, cause);
	}
}
