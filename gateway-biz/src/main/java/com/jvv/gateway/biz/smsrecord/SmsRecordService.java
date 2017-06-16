/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.biz.smsrecord;

import com.jvv.gateway.biz.AbstractBizService;
import com.jvv.gateway.dal.mapper.UserMessageRecordLogMapper;
import com.jvv.gateway.facade.sendsms.info.SmsRecordInfo;
import com.jvv.gateway.facade.sendsms.order.SmsRecordOrder;
import com.jvv.gateway.facade.sendsms.result.SmsRecordResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉 <p>
 * 〈功能详细描述〉
 *
 * @author linxm
 * @date 2017/3/22
 * @time 10:27
 */
@Service("smsRecordService")
public class SmsRecordService extends AbstractBizService{
	
	@Autowired
	private UserMessageRecordLogMapper userMessageRecordLogMapper;
	
	/**
	 * 统计渠道短信发送量
	 * @param order
	 * @return
	 */
	public SmsRecordResult querySmsRecordByChannel(SmsRecordOrder order) {
		
		SmsRecordResult result = new SmsRecordResult();
		
		//渠道时间段内成功总量
		int sucCount = userMessageRecordLogMapper.querySmsRecordByChannel(order.getBeginDate (), order.getEndDate (), order.getChannelType (),1);
		//渠道成功总量
		int oldSucCount = userMessageRecordLogMapper.querySmsRecordByChannel(null, null, order.getChannelType (),1);
		//渠道时间段内失败总量
		int failCount = userMessageRecordLogMapper.querySmsRecordByChannel(order.getBeginDate (), order.getEndDate (), order.getChannelType (),0);
		//渠道失败总量
		int oldFailCount = userMessageRecordLogMapper.querySmsRecordByChannel(null, null, order.getChannelType (),0);
		
		SmsRecordInfo smsRecordInfo = new SmsRecordInfo ();
		BeanUtils.copyProperties (order,smsRecordInfo);
		
		smsRecordInfo.setSucCount (sucCount);
		smsRecordInfo.setFailCount (failCount);
		smsRecordInfo.setTotalCount (sucCount + failCount);
		
		smsRecordInfo.setOldSucCount (oldSucCount);
		smsRecordInfo.setOldFailCount (oldFailCount);
		smsRecordInfo.setOldTotalCount (oldSucCount + oldFailCount);
		
		result.setInfo (smsRecordInfo);
		result.setToSuccess ();
		
		return result;
		
	}
}
