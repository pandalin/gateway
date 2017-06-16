/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-19 08:54 创建
 *
 */
package com.jvv.gateway.common.redis.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;

/**
 * @author turalyon@jinvovo.com
 */
public class JvvCacheErrorHandler extends SimpleCacheErrorHandler {
	Logger logger = LoggerFactory.getLogger(JvvCacheErrorHandler.class);
	@Override
	public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
		logger.warn("get cache error,key={}", key, exception);
	}

	@Override
	public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
		logger.warn("put cache error,key={}", key, exception);
	}

	@Override
	public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
		logger.warn("evict cache error,key={}", key, exception);
	}

	@Override
	public void handleCacheClearError(RuntimeException exception, Cache cache) {
		logger.warn("clear cache error", exception);
	}
}
