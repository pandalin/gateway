/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.facade.sendsms.order;


import com.jvv.common.services.order.AbstractOrder;
import com.jvv.common.services.order.validation.Query;

import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA
 * 〈短信记录查询〉 <p>
 * 〈短信记录查询〉
 *
 * @author linxm
 * @date 2017/3/22
 * @time 9:23
 */
public class SmsRecordOrder extends AbstractOrder {
	
	@NotNull(groups = {Query.class}, message = "开始日期")
	private String beginDate;
	
	@NotNull(groups = {Query.class}, message = "结束日期")
	private String endDate;
	
	@NotNull(groups = {Query.class}, message = "渠道类型")
	private String channelType;
	
	public String getBeginDate () {
		return beginDate;
	}
	
	public void setBeginDate (String beginDate) {
		this.beginDate = beginDate;
	}
	
	public String getEndDate () {
		return endDate;
	}
	
	public void setEndDate (String endDate) {
		this.endDate = endDate;
	}
	
	public String getChannelType () {
		return channelType;
	}
	
	public void setChannelType (String channelType) {
		this.channelType = channelType;
	}
}
