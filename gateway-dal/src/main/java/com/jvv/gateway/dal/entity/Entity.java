/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-02-28 00:44 创建
 *
 */
package com.jvv.gateway.dal.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @author turalyon@jinvovo.com
 */
public abstract class Entity {
	/**
	 * id
	 */
	protected Long id;

	/**
	 * 数据新增时间
	 */
	protected Date rawAddTime;
	/**
	 * 数据新增时间
	 */
	protected Date rawUpdateTime;

	/**
	 * 返回 id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置 id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 数据新增时间
	 */
	public Date getRawAddTime() {
		return rawAddTime;
	}

	/**
	 * 设置 数据新增时间
	 */
	public void setRawAddTime(Date rawAddTime) {
		this.rawAddTime = rawAddTime;
	}

	/**
	 * 返回 数据新增时间
	 */
	public Date getRawUpdateTime() {
		return rawUpdateTime;
	}

	/**
	 * 设置 数据新增时间
	 */
	public void setRawUpdateTime(Date rawUpdateTime) {
		this.rawUpdateTime = rawUpdateTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
