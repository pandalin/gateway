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

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author turalyon@jinvovo.com
 */
public class JvvCachingConfigurer implements CachingConfigurer {
	private RedisTemplate redisTemplate;
	private long expiration;

	public JvvCachingConfigurer(RedisTemplate redisTemplate, long expiration) {
		this.redisTemplate = redisTemplate;
		this.expiration = expiration;
	}

	@Override
	public CacheManager cacheManager() {
		return new JvvCacheManager(redisTemplate, expiration);
	}

	@Override
	public CacheResolver cacheResolver() {
		return null;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return null;
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return new JvvCacheErrorHandler();
	}
}
