/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-19 09:11 创建
 *
 */
package com.jvv.gateway.common.redis;

/**
 * @author turalyon@jinvovo.com
 */
public class JvvRedisUnSupportCommandException extends RuntimeException {
	public JvvRedisUnSupportCommandException() {
		super();
	}

	public JvvRedisUnSupportCommandException(String message) {
		super(message);
	}

	public JvvRedisUnSupportCommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public JvvRedisUnSupportCommandException(Throwable cause) {
		super(cause);
	}

	protected JvvRedisUnSupportCommandException(String message, Throwable cause, boolean enableSuppression,
												boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
