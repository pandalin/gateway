/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-19 08:53 创建
 *
 */
package com.jvv.gateway.common.redis.cache;

import com.google.common.collect.Lists;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;

/**
 * @author turalyon@jinvovo.com
 */
public class JvvCacheManager extends AbstractCacheManager {
	private RedisTemplate redisTemplate;
	private long expiration;

	public JvvCacheManager(RedisTemplate redisTemplate, long expiration) {
		this.redisTemplate = redisTemplate;
		this.expiration = expiration;
	}

	@Override
	protected Collection<? extends Cache> loadCaches() {
		return Lists.newArrayList();
	}

	@Override
	protected Cache getMissingCache(String name) {
		return new JvvCache(redisTemplate, name, expiration);
	}
}
