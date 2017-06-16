/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.biz.sendsms;

import com.jvv.common.enums.Status;
import com.jvv.common.services.result.SimpleResult;
import com.jvv.gateway.biz.AbstractBizService;
import com.jvv.gateway.common.exceptions.SendSmsExceprion;
import com.jvv.gateway.dal.entity.UserMessageRecordLogDO;
import com.jvv.gateway.dal.mapper.UserMessageRecordLogMapper;
import com.jvv.gateway.facade.constants.enums.SendSmsStatusEnum;
import com.jvv.gateway.facade.sendsms.order.QueryBalanceOrder;
import com.jvv.gateway.facade.sendsms.order.SendSmsOrder;
import com.jvv.gateway.facade.sendsms.result.SendSmsResult;
import com.jvv.gateway.facade.sendsms.result.SmsBalanceResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉 <p>
 * 〈功能详细描述〉
 *
 * @author linxm
 * @date
 * @time
 */
@Component
public class BillSendSmsService extends AbstractBizService {
	
	@Value("#{jvvContext.sms_center_name}")
	private String smsCenterName;
	
	@Resource
	private UserMessageRecordLogMapper userMessageRecordLogMapper;
	@Resource
	private SendSmsChannelFactory sendSmsChannelFactory;
	
	
	public SendSmsResult sendSms (SendSmsOrder sendSmsOrder) {
		
		SendSmsResult result = new SendSmsResult ();
		
		BillUserMessageRecordLog bill = BillUserMessageRecordLog.convertFrom (sendSmsOrder);
		//1、入库
		/*try {
			bill.setMsgChannel (smsCenterName);
			userMessageRecordLogMapper.insert (bill);
		} catch (DuplicateKeyException e) {
			//利用数据唯一索引来做幂等验证
			UserMessageRecordLogDO userMessageRecordLogDO = new UserMessageRecordLogDO ();
			userMessageRecordLogDO.setLogId (sendSmsOrder.getOrderNo ());
			List<UserMessageRecordLogDO> existsDos = userMessageRecordLogMapper.query (userMessageRecordLogDO, 0, 1);
			if (existsDos != null && existsDos.size () > 0) {
				//这里最好封装错误码,参照OpenAPI实例 com.jvv.openapi.common.exception.OpenApiBizException
				throw new SendSmsExceprion ("数据库异常");
			}
			bill = BillUserMessageRecordLog.convertFrom (existsDos.get (0));
			result.setInfo (bill.convertToInfo ());
			result.setStatus (convertStatus (bill.getIsSucess ().intValue ()));
			result.setCode (bill.getResultCode ());
			result.setMessage (bill.getResultMessage ());
			return result;
		}*/
		
		//调用外部系统进行收集充值业务，如果涉及到多渠道，则需要进行渠道调度
		
		//假设渠道为："ChinaMobile001" 中国移动充值渠道
		SendSmsChannel channel = sendSmsChannelFactory.findPrepaidChannel (smsCenterName);
		SimpleResult simpleResult = null;
		try {
			simpleResult = channel.sendSms (sendSmsOrder);
			bill.setIsSucess ((simpleResult.getStatus () == Status.SUCCESS ? SendSmsStatusEnum.SEND_SUCCESS
					: simpleResult.getStatus () == Status.FAIL ? SendSmsStatusEnum.SEND_FAIL
							: SendSmsStatusEnum.INITIAL).getLongValue ());
			bill.setResultCode (simpleResult.getCode ());
			bill.setResultMessage (simpleResult.getMessage ());
		} catch (Exception e) {
			bill.setIsSucess ((SendSmsStatusEnum.SEND_FAIL).getLongValue ());
			bill.setResultCode ("-1");
			bill.setResultMessage ("短信发送异常");
		} finally {
			userMessageRecordLogMapper.update (bill.convertToDo ());
		}
		
		result.setStatus (convertStatus (bill.getIsSucess ().intValue ()));
		result.setInfo (bill.convertToInfo ());
		result.setCode (bill.getResultCode ());
		result.setMessage (bill.getResultMessage ());
		
		return result;
	}
	
	
	public SmsBalanceResult queryBalance (QueryBalanceOrder order) {
		SendSmsChannel channel = sendSmsChannelFactory.findPrepaidChannel (order.getSmsChannel ());
		return  channel.queryBalance ();
	}
	
	
	private Status convertStatus (int status) {
		switch (status) {
			default:
			case 2:
				return Status.PROCESSING;
			case 0:
				return Status.FAIL;
			case 1:
				return Status.SUCCESS;
		}
	}
	
}
