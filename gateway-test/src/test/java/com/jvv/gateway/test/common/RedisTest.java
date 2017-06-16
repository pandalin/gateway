/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-19 09:38 创建
 *
 */
package com.jvv.gateway.test.common;

import com.jvv.gateway.test.GatewayTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * @author turalyon@jinvovo.com
 */
public class RedisTest extends GatewayTestBase {
	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void testRedis() {

		//20秒过期
		redisTemplate.expire("testCache", 5, TimeUnit.SECONDS);

		redisTemplate.opsForHash().put("testCache", "key1", "value1");
		Assert.isTrue("value1".equals(redisTemplate.opsForHash().get("testCache", "key1")));

		try {
			Thread.sleep(5 * 1000 + 10);
		} catch (InterruptedException e) {
		}
		Assert.isNull(redisTemplate.opsForHash().get("testCache", "key1"));
	}
}
