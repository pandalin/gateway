/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-17 22:03 创建
 *
 */
package com.jvv.gateway.biz.prepaid;

import com.jvv.common.enums.Status;
import com.jvv.common.lang.beans.Copier;
import com.jvv.common.services.result.SimpleResult;
import com.jvv.gateway.biz.AbstractBizService;
import com.jvv.gateway.common.exceptions.GatewayExceprion;
import com.jvv.gateway.dal.entity.TelephoneBillPrepaidDO;
import com.jvv.gateway.dal.mapper.TelephoneBillPrepaidMapper;
import com.jvv.gateway.facade.constants.enums.GatewayStatusEnum;
import com.jvv.gateway.facade.prepaid.order.TelephoneBillPrepaidOrder;
import com.jvv.gateway.facade.prepaid.result.TelephoneBillPrepaidResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

/**
 * @author turalyon@jinvovo.com
 */
@Component
public class TelephoneBillPrepaidService extends AbstractBizService {

	@Autowired
	private TelephoneBillPrepaidMapper telephoneBillPrepaidMapper;
	@Autowired
	private PrepaidChannelFactory prepaidChannelFactory;
	/**
	 * 话费充值接口
	 *
	 * @param order
	 * @return
	 */
	public TelephoneBillPrepaidResult prepaid(TelephoneBillPrepaidOrder order) {
		TelephoneBillPrepaidResult result = new TelephoneBillPrepaidResult();

		TelephoneBillPrepaid bill = TelephoneBillPrepaid.convertFrom(order);

		//1、入库
		try {
			TelephoneBillPrepaidDO dbDo = bill.convertToDo();
			Copier.copy(order, dbDo);
			telephoneBillPrepaidMapper.insert(dbDo);
		} catch (DuplicateKeyException e) {
			//利用数据唯一索引来做幂等验证
			TelephoneBillPrepaidDO existsDo = telephoneBillPrepaidMapper.findByOrderNo(order.getOrderNo());
			if (existsDo == null) {
				//这里最好封装错误码,参照OpenAPI实例 com.jvv.openapi.common.exception.OpenApiBizException
				throw new GatewayExceprion("数据库异常");
			}

			bill = TelephoneBillPrepaid.convertFrom(existsDo);
			result.setInfo(bill.convertToInfo());
			result.setStatus(convertStatus(bill.getStatus()));
			result.setCode(bill.getResultCode());
			result.setMessage(bill.getResultMessage());

			return result;
		}

		//调用外部系统进行收集充值业务，如果涉及到多渠道，则需要进行渠道调度

		//假设渠道为："ChinaMobile001" 中国移动充值渠道
		PrepaidChannel channel = prepaidChannelFactory.findPrepaidChannel("ChinaMobile001");

		try {
			SimpleResult prepaidResult = channel.prepaid(bill);
			bill.setStatus(prepaidResult.getStatus() == Status.SUCCESS ?
					GatewayStatusEnum.BANK_SUCCESS :
					prepaidResult.getStatus() == Status.FAIL ?
							GatewayStatusEnum.BANK_FAIL :
							GatewayStatusEnum.BANK_PROCESS);
			bill.setResultCode(prepaidResult.getCode());
			bill.setResultMessage(prepaidResult.getMessage());
		} catch (Exception e) {
			bill.setStatus(GatewayStatusEnum.SEND_FAIL);
			//自己定义编码
			bill.setResultCode("999999");
			//注意长度
			bill.setResultMessage(e.getMessage());
		}finally {
			telephoneBillPrepaidMapper.update(bill.convertToDo());
		}


		result.setInfo(bill.convertToInfo());
		result.setStatus(convertStatus(bill.getStatus()));
		result.setCode(bill.getResultCode());
		result.setMessage(bill.getResultMessage());

		return result;
	}

	private Status convertStatus(GatewayStatusEnum status) {
		switch (status) {
			case BANK_SUCCESS:
				return Status.SUCCESS;
			case BANK_FAIL:
				return Status.FAIL;
			default:
				return Status.PROCESSING;
		}
	}
}
