/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.facade.sendsms.info;

import com.jvv.common.services.info.AbstractInfo;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉 <p>
 * 〈功能详细描述〉
 *
 * @author linxm
 * @date
 * @time
 */
public class SendSmsInfo extends AbstractInfo {
	
	protected String logId;
	
	protected String sendContent;
	
	protected String receivePhone;
	//短信通道
	protected String msgChannel;
	//成功与否  2初始   0失败   1成功
	protected Long isSucess;
	
	public String getLogId () {
		return logId;
	}
	
	public void setLogId (String logId) {
		this.logId = logId;
	}
	
	public String getSendContent () {
		return sendContent;
	}
	
	public void setSendContent (String sendContent) {
		this.sendContent = sendContent;
	}
	
	public String getReceivePhone () {
		return receivePhone;
	}
	
	public void setReceivePhone (String receivePhone) {
		this.receivePhone = receivePhone;
	}
	
	public String getMsgChannel () {
		return msgChannel;
	}
	
	public void setMsgChannel (String msgChannel) {
		this.msgChannel = msgChannel;
	}
	
	public Long getIsSucess () {
		return isSucess;
	}
	
	public void setIsSucess (Long isSucess) {
		this.isSucess = isSucess;
	}
}
