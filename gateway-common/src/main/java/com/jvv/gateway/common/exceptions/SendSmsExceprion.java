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

/**
 * @author turalyon@jinvovo.com
 */
public class SendSmsExceprion extends GatewayExceprion {
	public SendSmsExceprion (String message) {
		super(message);
	}

	public SendSmsExceprion (String message, Throwable cause) {
		super(message, cause);
	}
}
