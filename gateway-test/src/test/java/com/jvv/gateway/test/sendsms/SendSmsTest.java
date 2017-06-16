/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.test.sendsms;

import com.alibaba.fastjson.JSONObject;
import com.jvv.common.enums.Status;
import com.jvv.common.services.result.SimpleResult;
import com.jvv.gateway.facade.constants.enums.SmsTempEnum;
import com.jvv.gateway.facade.sendsms.api.SendSmsApi;
import com.jvv.gateway.facade.sendsms.order.QueryBalanceOrder;
import com.jvv.gateway.facade.sendsms.order.SendSmsOrder;
import com.jvv.gateway.facade.sendsms.order.SmsParam;
import com.jvv.gateway.facade.sendsms.result.SendSmsResult;
import com.jvv.gateway.facade.synOldSystem.api.SynOldSystemApi;
import com.jvv.gateway.test.GatewayTestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉 <p>
 * 〈功能详细描述〉
 *
 * @author linxm
 * @date
 * @time
 */
public class SendSmsTest extends GatewayTestBase {
	@Resource
	SendSmsApi sendSmsApi;

	SendSmsOrder sendSmsOrder;

	QueryBalanceOrder balanceOrder;
	
	@Before
	public  void initParam(){
		sendSmsOrder=new SendSmsOrder ();
		sendSmsOrder.setOrderNo ("JDBCDDDDDDDN");
		//sendSmsOrder.setMessage (SmsTempEnum.CQ_SMS_CONTENT.getName ());
		sendSmsOrder.setPhoneNum ("18223156892");
		sendSmsOrder.setSmsParam (new SmsParam ("code", "1234"));
		sendSmsOrder.setSmsTempEnum (SmsTempEnum.CQ_SMS_CONTENT);
		
		System.out.println ("message="+sendSmsOrder.getMessage ());


		balanceOrder=new QueryBalanceOrder();
		balanceOrder.setSmsChannel("cl");
		
		
		
		System.out.println ("jsonStr="+ JSONObject.toJSONString (sendSmsOrder));
	}
	
	
	@Test
	public void testSendSms(){
		SendSmsResult sendSmsResult= sendSmsApi.sendSms (sendSmsOrder);
		
		System.out.println (sendSmsResult.toString ());
		
		
		Assert.assertTrue (sendSmsResult.getStatus ()== Status.SUCCESS);
		
	}



	@Test
	public void testQueryBalance(){




		SimpleResult simpleResult= sendSmsApi.queryBalance (balanceOrder);

		System.out.println ("剩余短信"+simpleResult.toString ());


		Assert.assertTrue (simpleResult.getStatus ()== Status.SUCCESS);

	}
	
	
}
