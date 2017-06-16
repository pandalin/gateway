/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.biz.sendsms;

import com.jvv.common.lang.beans.Copier;
import com.jvv.gateway.dal.entity.UserMessageRecordLogDO;
import com.jvv.gateway.facade.constants.enums.SendSmsStatusEnum;
import com.jvv.gateway.facade.sendsms.info.SendSmsInfo;
import com.jvv.gateway.facade.sendsms.order.SendSmsOrder;

import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉 <p>
 * 〈功能详细描述〉
 *
 * @author linxm
 * @date
 * @time
 */
public class BillUserMessageRecordLog extends UserMessageRecordLogDO{
	/**
	 * 初始状态入库
	 * @param order
	 * @return
	 */
	public static BillUserMessageRecordLog convertFrom(SendSmsOrder order){
		BillUserMessageRecordLog obj = new BillUserMessageRecordLog ();
		obj.setLogId (order.getOrderNo ());
		obj.setCreatetime (new Date ());
		obj.setIsSucess (Long.valueOf (SendSmsStatusEnum.INITIAL.getCode ()));
		obj.setSendTime (new Date ());
		obj.setReceivePhone (order.getPhoneNum ());
		obj.setSendContent (order.getMessage ());
		obj.setMsgType (order.getSmsTempEnum ().name ());
		return obj;
	}
	
	public UserMessageRecordLogDO convertToDo() {
		UserMessageRecordLogDO db = new UserMessageRecordLogDO();
		Copier.copy(this, db);
		return db;
	}
	
	public static BillUserMessageRecordLog convertFrom(UserMessageRecordLogDO dbDo) {
		if(dbDo==null){
			return null;
		}
		BillUserMessageRecordLog obj = new BillUserMessageRecordLog ();
		Copier.copy(dbDo,obj);
		return obj;
	}
	
	public SendSmsInfo convertToInfo() {
		SendSmsInfo info = new SendSmsInfo();
		Copier.copy(this,info);
		return info;
	}
	
}
