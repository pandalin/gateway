/*
 * www.moonlighting.cn Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@moonlighting.cn 2017-01-16 22:46 创建
 *
 */
package com.jvv.gateway.dal.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Maps;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author turalyon@moonlighting.cn
 */
@MappedTypes({ HashMap.class })
@MappedJdbcTypes({ JdbcType.VARCHAR })
public class HashMapTypeHandler extends BaseTypeHandler<HashMap> {

	public void setNonNullParameter(PreparedStatement preparedStatement, int i, HashMap aMap,
									JdbcType jdbcType) throws SQLException {
		preparedStatement.setString(i,
				aMap == null || aMap.size() == 0 ? null : JSON.toJSONString(aMap, SerializerFeature.WriteClassName));
	}

	public HashMap getNullableResult(ResultSet resultSet, String s) throws SQLException {
		String result = resultSet.getString(s);
		return result == null || result.trim().length() == 0 ?
				Maps.newHashMap() :
				JSON.parseObject(result, HashMap.class);
	}

	public HashMap getNullableResult(ResultSet resultSet, int i) throws SQLException {
		String result = resultSet.getString(i);
		return result == null || result.trim().length() == 0 ?
				Maps.newHashMap() :
				JSON.parseObject(result, HashMap.class);
	}

	public HashMap getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		String result = callableStatement.getString(i);
		return result == null || result.trim().length() == 0 ?
				Maps.newHashMap() :
				JSON.parseObject(result, HashMap.class);
	}
}
