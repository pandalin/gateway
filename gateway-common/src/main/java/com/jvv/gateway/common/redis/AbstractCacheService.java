/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-19 10:17 创建
 *
 */
package com.jvv.gateway.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author turalyon@jinvovo.com
 */
public abstract  class AbstractCacheService <T> {

	@Autowired
	protected RedisTemplate redisTemplate;


	@PostConstruct
	public void initCache(){
		//5秒过期（测试）
		redisTemplate.expire(getCahceName(), getExpire(), TimeUnit.SECONDS);
	}

	public void put(String key,Object value){
		redisTemplate.opsForHash().put(getCahceName(), key, value);
	}

	public <T> T get(String key){
		return (T) redisTemplate.opsForHash().get(getCahceName(), key);
	}

	public void delete(String key){
		redisTemplate.opsForHash().delete(getCahceName(), key);
	}

	protected abstract String getCahceName();

	/**
	 * 秒
	 * @return
	 */
	protected abstract long getExpire();

}
