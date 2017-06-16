/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-02-28 22:25 创建
 *
 */
package com.jvv.gateway.facade.constants.enums;

/**
 * 这个枚举类应该放到公共的模块里面，不应该每个系统都定义
 *
 * @author turalyon@jinvovo.com
 */
public enum Status {
	/**
	 * 成功，处理成功
	 */
	SUCCESS("success", "成功"),

	/**
	 * 失败，处理失败
	 */
	FAIL("fail", "失败"),

	/**
	 * 处理中，异步处理中，可以约定回调通知
	 */
	PROCESSING("processing", "处理中"),

	;
	
	/**
	 * 枚举值
	 */
	private final String code;
	
	/**
	 * 枚举描述
	 */
	private final String message;
	
	/**
	 * 构造一个<code>Status</code>枚举对象
	 *
	 * @param code
	 * @param message
	 */
	private Status(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return Returns the code.
	 */
	public String code() {
		return code;
	}
	
	/**
	 * @return Returns the message.
	 */
	public String message() {
		return message;
	}
	
	/**
	 * 通过枚举<code>code</code>获得枚举
	 *
	 * @param code
	 * @return Status.java
	 */
	public static Status getByCode(String code) {
		for (Status _enum : values()) {
			if (_enum.getCode().equalsIgnoreCase(code)) {
				return _enum;
			}
		}
		return null;
	}
	
	/**
	 * 获取全部枚举
	 *
	 * @return List<LocalCacheEnum>
	 */
	public java.util.List<Status> getAllEnum() {
		java.util.List<Status> list = new java.util.ArrayList<Status>();
		for (Status _enum : values()) {
			list.add(_enum);
		}
		return list;
	}
	
	/**
	 * 获取全部枚举值
	 *
	 * @return List<String>
	 */
	public java.util.List<String> getAllEnumCode() {
		java.util.List<String> list = new java.util.ArrayList<String>();
		for (Status _enum : values()) {
			list.add(_enum.code());
		}
		return list;
	}
	
	/**
	 * 判断给定的枚举，是否在列表中
	 *
	 * @param values 列表
	 * @return
	 */
	public boolean isInList(Status... values) {
		for (Status e : values) {
			if (this == e) {
				return true;
			}
		}
		return false;
	}

	public boolean isSuccess(){
		return this == SUCCESS;
	}
	public boolean isFail(){
		return this == FAIL;
	}
	public boolean isProcessing(){
		return this == PROCESSING;
	}
}
