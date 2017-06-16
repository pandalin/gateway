/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-17 22:41 创建
 *
 */
package com.jvv.gateway.facade.constants.enums;

/**
 * @author turalyon@jinvovo.com
 */
public enum GatewayStatusEnum {
	/**
	 * 描述
	 */
	INITIAL("IT", "初始"),
	ASYNC_SEND("AS", "异步处理"),
	SEND_FAIL("SF", "发送失败"),
	BANK_PROCESS("BP", "已报银行"),
	BANK_SUCCESS("BS", "银行成功"),
	BANK_FAIL("BF", "银行失败"),
	BANK_ERROR("BE", "银行异常"),;
	
	/**
	 * 枚举值
	 */
	private final String code;
	
	/**
	 * 枚举描述
	 */
	private final String message;
	
	/**
	 * 构造一个<code>GatewayStatusEnum</code>枚举对象
	 *
	 * @param code
	 * @param message
	 */
	private GatewayStatusEnum(String code, String message) {
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
	 * @return GatewayStatusEnum.java
	 */
	public static GatewayStatusEnum getByCode(String code) {
		for (GatewayStatusEnum _enum : values()) {
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
	public static java.util.List<GatewayStatusEnum> getAllEnum() {
		java.util.List<GatewayStatusEnum> list = new java.util.ArrayList<GatewayStatusEnum>();
		for (GatewayStatusEnum _enum : values()) {
			list.add(_enum);
		}
		return list;
	}
	
	/**
	 * 获取全部枚举值
	 *
	 * @return List<String>
	 */
	public static java.util.List<String> getAllEnumCode() {
		java.util.List<String> list = new java.util.ArrayList<String>();
		for (GatewayStatusEnum _enum : values()) {
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
	public boolean isInList(GatewayStatusEnum... values) {
		for (GatewayStatusEnum e : values) {
			if (this == e) {
				return true;
			}
		}
		return false;
	}
}
