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

import com.jvv.gateway.facade.constants.enums.GatewayStatusEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author turalyon@moonlighting.cn
 */
@MappedTypes({ GatewayStatusEnum.class })
@MappedJdbcTypes({ JdbcType.VARCHAR  })
public class GatewayStatusEnumTypeHandler extends BaseTypeHandler<GatewayStatusEnum> {

	public void setNonNullParameter(PreparedStatement preparedStatement, int i, GatewayStatusEnum anEnum,
									JdbcType jdbcType) throws SQLException {
		preparedStatement.setString(i, anEnum.getCode());
	}

	public GatewayStatusEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
		String result = resultSet.getString(s);
		return GatewayStatusEnum.getByCode(result);
	}

	public GatewayStatusEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
		String result = resultSet.getString(i);
		return GatewayStatusEnum.getByCode(result);
	}

	public GatewayStatusEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		String result = callableStatement.getString(i);
		return GatewayStatusEnum.getByCode(result);
	}
}
