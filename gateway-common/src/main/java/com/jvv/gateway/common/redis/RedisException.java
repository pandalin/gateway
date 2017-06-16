/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-19 09:12 创建
 *
 */
package com.jvv.gateway.common.redis;

/**
 * @author turalyon@jinvovo.com
 */
public class RedisException extends RuntimeException {
	public RedisException() {
		super();
	}

	public RedisException(String message) {
		super(message);
	}

	public RedisException(String message, Throwable cause) {
		super(message, cause);
	}

	public RedisException(Throwable cause) {
		super(cause);
	}

	protected RedisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
