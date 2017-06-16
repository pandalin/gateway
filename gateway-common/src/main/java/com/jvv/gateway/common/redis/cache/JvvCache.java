/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-19 08:44 创建
 *
 */
package com.jvv.gateway.common.redis.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.jcache.JCacheCache;
import org.springframework.data.redis.cache.DefaultRedisCachePrefix;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @author turalyon@jinvovo.com
 */
public class JvvCache implements Cache {

	private String name;
	private long expiration;
	private RedisConnectionFactory connectionFactory;
	private RedisTemplate redisTemplate;
	private RedisSerializer valueRedisSerializer;
	private byte[] prefix;

	public JvvCache(RedisTemplate redisTemplate, String name, long expiration) {
		this.connectionFactory = redisTemplate.getConnectionFactory();
		this.name = name;
		this.expiration = expiration;
		this.redisTemplate = redisTemplate;
		this.valueRedisSerializer = redisTemplate.getValueSerializer();
		this.prefix = new DefaultRedisCachePrefix().prefix(name);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getNativeCache() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public <T> T get (Object o, Callable<T> callable) {
		ValueWrapper valueWrapper = get (o);
		if(valueWrapper!=null){
			return (T)valueWrapper.get ();
		}
		
		return null;
	}
	
	@Override
	public ValueWrapper get(Object key) {
		if (key == null) {
			return null;
		}

		RedisConnection connection = null;
		byte[] bytes = null;
		try {
			connection = connectionFactory.getConnection();
			byte[] newkey = computeKey(key);
			bytes = connection.get(newkey);
			if (bytes == null) {
				return null;
			}
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		if (bytes == null) {
			return null;
		}
		ValueWrapper value = (ValueWrapper) valueRedisSerializer.deserialize(bytes);
		return value;
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		ValueWrapper wrapper = this.get(key);
		return wrapper == null ? null : (T) wrapper.get();
	}

	@Override
	public void put(Object key, Object value) {
		if (key == null || value == null) {
			return;
		}

		RedisConnection connection = null;
		try {
			connection = connectionFactory.getConnection();
			byte[] newkey = computeKey(key);

			connection.set(newkey, valueRedisSerializer.serialize(value));
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void evict(Object key) {
		if (key == null) {
			return;
		}


		RedisConnection connection = null;
		try {
			connection = connectionFactory.getConnection();
			byte[] newkey = computeKey(key);

			connection.del(newkey);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

	}

	private byte[] computeKey(Object key) {
		byte[] keyBytes = this.convertToBytesIfNecessary(this.redisTemplate.getKeySerializer(), key);
		if (this.prefix != null && this.prefix.length != 0) {
			byte[] result = Arrays.copyOf(this.prefix, this.prefix.length + keyBytes.length);
			System.arraycopy(keyBytes, 0, result, this.prefix.length, keyBytes.length);
			return result;
		} else {
			return keyBytes;
		}
	}

	private byte[] convertToBytesIfNecessary(RedisSerializer<Object> serializer, Object value) {
		return value instanceof byte[] ? (byte[]) (value) : (null == serializer ? null : serializer.serialize(value));
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}
}
