/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-17 22:46 创建
 *
 */
package com.jvv.gateway.biz.prepaid;

import com.jvv.common.lang.beans.Copier;
import com.jvv.gateway.dal.entity.TelephoneBillPrepaidDO;
import com.jvv.gateway.facade.constants.enums.GatewayStatusEnum;
import com.jvv.gateway.facade.prepaid.info.TelephoneBillPrepaidInfo;
import com.jvv.gateway.facade.prepaid.order.TelephoneBillPrepaidOrder;

/**
 * @author turalyon@jinvovo.com
 */
public class TelephoneBillPrepaid extends TelephoneBillPrepaidDO {

	public static TelephoneBillPrepaid convertFrom(TelephoneBillPrepaidOrder order){
		TelephoneBillPrepaid obj = new TelephoneBillPrepaid();
		Copier.copy(order, obj);
		obj.setStatus(GatewayStatusEnum.INITIAL);
		return obj;
	}

	public TelephoneBillPrepaidDO convertToDo() {
		TelephoneBillPrepaidDO db = new TelephoneBillPrepaidDO();
		Copier.copy(this,db);
		return db;
	}

	public static TelephoneBillPrepaid convertFrom(TelephoneBillPrepaidDO dbDo) {
		if(dbDo==null){
			return null;
		}
		TelephoneBillPrepaid obj = new TelephoneBillPrepaid();
		Copier.copy(dbDo,obj);
		obj.setStatus(GatewayStatusEnum.INITIAL);
		return obj;
	}

	public TelephoneBillPrepaidInfo convertToInfo() {
		TelephoneBillPrepaidInfo info = new TelephoneBillPrepaidInfo();
		Copier.copy(this,info);
		return info;
	}
}
