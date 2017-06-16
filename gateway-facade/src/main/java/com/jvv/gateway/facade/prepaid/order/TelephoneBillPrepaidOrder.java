/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-02-28 22:29 创建
 *
 */
package com.jvv.gateway.facade.prepaid.order;

import com.jvv.common.services.order.AbstractOrder;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author turalyon@jinvovo.com
 */
public class TelephoneBillPrepaidOrder extends AbstractOrder {
	/**
	 * 用户ID
	 */
	@NotBlank
	protected String orderNo;
	/**
	 * 电话号码
	 */
	@NotBlank
	protected String phoneNo;

	/**
	 * 最好定义个Money对象来标识金额
	 * 充值金额
	 */
	@Min(1)
	@Max(1000)
	protected double amount;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
