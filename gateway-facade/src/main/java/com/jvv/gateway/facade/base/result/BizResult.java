/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-02-28 22:28 创建
 *
 */
package com.jvv.gateway.facade.base.result;

import com.jvv.gateway.facade.base.info.AbstractInfo;

/**
 * 应该放到公共的模块里面，不应该每个系统都定义
 *
 * @author turalyon@jinvovo.com
 */
public class BizResult<T extends AbstractInfo> extends SimpleResult {

	private  T info;


	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}
}
