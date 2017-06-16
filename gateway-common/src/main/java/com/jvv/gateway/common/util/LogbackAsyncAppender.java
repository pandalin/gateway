/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-02-28 01:04 创建
 *
 */
package com.jvv.gateway.common.util;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author turalyon@jinvovo.com
 */
public class LogbackAsyncAppender extends AsyncAppender

	{
		private static final String LOGGER_NAME = "cn.moonlighting";

		public LogbackAsyncAppender() {
		this.setQueueSize(1024);
		this.setDiscardingThreshold(100);
		this.setIncludeCallerData(true);
	}

	protected boolean isDiscardable(ILoggingEvent event) {
		Level level = event.getLevel();
		return level.toInt() < 20000?true:(level.toInt() > 20000?false:event.getLoggerName() == null || !event.getLoggerName().startsWith("cn.moonlighting"));
	}
}
