/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-02-28 22:24 创建
 *
 */
package com.jvv.gateway.facade.base.result;

import com.jvv.gateway.facade.constants.enums.Status;

/**
 * 应该放到公共的模块里面，不应该每个系统都定义
 * @author turalyon@jinvovo.com
 */
public class SimpleResult extends AbstractResult {

	/**
	 * 返回结果状态
	 */
	private Status status;

	/**
	 * 结果码
	 */
	private String code;

	/**
	 * 详细信息
	 */
	private String message;

	/**
	 * 失败返回
	 * @param message
	 * @return
	 */
	public SimpleResult failure(String message) {
		return failure ("999",message);
	}

	/**
	 * 失败返回
	 * @param message
	 * @return
	 */
	public SimpleResult failure(String code,String message) {
		this.code = code;
		this.message = message;
		this.status = Status.FAIL;
		return this;
	}

	/**
	 * 成功
	 * @param message
	 * @return
	 */
	public SimpleResult success(String code,String message) {
		this.code = code;
		this.message = message;
		this.status = Status.SUCCESS;
		return this;
	}

	/**
	 * 成功
	 * @param message
	 * @return
	 */
	public SimpleResult success(String message) {
		return success ("200",message);
	}


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
