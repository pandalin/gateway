/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-19 09:20 创建
 *
 */
package com.jvv.gateway.common.redis;

import com.google.common.collect.Lists;
import com.jvv.gateway.common.redis.cache.JvvCacheManager;
import com.jvv.gateway.common.redis.cache.JvvCachingConfigurer;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author turalyon@jinvovo.com
 */
@Configuration
public class RedisConfiguration {

	private static final String JEDIS_CACHE_JMX_OBJECTNAME = "org.apache.commons.pool2:type=GenericObjectPool,name=redis.cache";
	private static final String JEDIS_CACHE_JMX_PREFIX = "redis.cache";
	private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RedisProperties redisProperties;

	@Bean
	public CacheManager cacheManager(@Qualifier("redisTemplate") RedisTemplate redisTemplate,
									 RedisProperties redisProperties) {
		JvvCacheManager yedisCacheManager = new JvvCacheManager(redisTemplate, redisProperties.getExpireTime());
		return yedisCacheManager;
	}

	@Bean
	public JvvCachingConfigurer yijiCachingConfigurer(RedisTemplate redisTemplate, RedisProperties yedisProperties) {
		return new JvvCachingConfigurer(redisTemplate, yedisProperties.getExpireTime());
	}

	@Bean
	public RedisTemplate redisTemplate(
			@Qualifier("redisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setDefaultSerializer(new JvvRedisSerializer());
		return redisTemplate;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory(RedisProperties redisProperties) {
		JvvRedisConnectionFactory yedisConnectionFactory = new JvvRedisConnectionFactory();
		yedisConnectionFactory.setHostName(redisProperties.getHost());
		yedisConnectionFactory.setPort(redisProperties.getPort());
		yedisConnectionFactory.setNamespace(redisProperties.getNamespace());
		yedisConnectionFactory.setPoolConfig(jedisPoolConfig(redisProperties));
		yedisConnectionFactory.setTimeout(redisProperties.getTimeOut());
		return yedisConnectionFactory;
	}

	private JedisPoolConfig jedisPoolConfig(RedisProperties yedisProperties) {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(yedisProperties.getPool().getMaxTotal());
		jedisPoolConfig.setMaxIdle(yedisProperties.getPool().getMaxIdle());
		jedisPoolConfig.setMaxWaitMillis(yedisProperties.getPool().getMaxWait());
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setTestWhileIdle(true);
		jedisPoolConfig.setJmxNamePrefix(JEDIS_CACHE_JMX_PREFIX);
		return jedisPoolConfig;
	}

}
