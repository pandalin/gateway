/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-19 09:24 创建
 *
 */
package com.jvv.gateway.common.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author turalyon@jinvovo.com
 */
public class JvvRedisSerializer<T> implements RedisSerializer<T> {
	@Override
	public byte[] serialize(T t) throws SerializationException {
		return JSON.toJSONBytes(t, SerializerFeature.WriteClassName);
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (bytes == null) {
			return null;
		}
		return (T) JSON.parse(bytes);
	}
}
