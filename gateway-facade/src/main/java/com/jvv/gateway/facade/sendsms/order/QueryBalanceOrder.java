/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.facade.sendsms.order;

import com.jvv.common.services.order.AbstractOrder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉 <p>
 * 〈功能详细描述〉
 *
 * @author linxm
 * @date
 * @time
 */
public class QueryBalanceOrder extends AbstractOrder {
	//短信通道
	@NotBlank
	private String smsChannel;
	
	public String getSmsChannel () {
		return smsChannel;
	}
	
	public void setSmsChannel (String smsChannel) {
		this.smsChannel = smsChannel;
	}
}
