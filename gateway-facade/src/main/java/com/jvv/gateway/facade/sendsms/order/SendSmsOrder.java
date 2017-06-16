/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.facade.sendsms.order;

import com.google.common.base.Strings;
import com.jvv.common.services.order.AbstractOrder;
import com.jvv.gateway.facade.constants.enums.SmsTempEnum;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉 <p>
 * 〈功能详细描述〉
 *
 * @author linxm
 * @date
 * @time
 */
public class SendSmsOrder extends AbstractOrder {
	
	@NotBlank
	protected String orderNo;
	
	private String message;
	
	
	@Length(min = 11,max = 11)
	private String phoneNum;
	
	private SmsTempEnum smsTempEnum;
	
	protected SmsParam smsParam;
	
	public String getOrderNo () {
		return orderNo;
	}
	
	public void setOrderNo (String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getMessage () {
		if(Strings.isNullOrEmpty (message)) {
			message = smsTempEnum.getName ();
		}
			if(this.smsParam!=null&&this.smsParam.getTplValue ()!=null){
			for (Map.Entry<String, String> entry : this.smsParam.getTplValue ().entrySet ()) {
				String rep1 = "#".concat (entry.getKey ()).concat ("#");
				message=message.replace (rep1, entry.getValue ());
			}}

		return message;
	}
	
	public void setMessage (String message) {
		this.message = message;
	}
	
	public String getPhoneNum () {
		return phoneNum;
	}
	
	public void setPhoneNum (String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public SmsTempEnum getSmsTempEnum () {
		return smsTempEnum;
	}
	
	public void setSmsTempEnum (SmsTempEnum smsTempEnum) {
		this.smsTempEnum = smsTempEnum;
	}
	
	public SmsParam getSmsParam () {
		return smsParam;
	}
	
	public void setSmsParam (SmsParam smsParam) {
		this.smsParam = smsParam;
	}
}
