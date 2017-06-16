/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.test.jpus;

import com.jvv.gateway.facade.constants.enums.DeviceType;
import com.jvv.gateway.facade.jpush.api.PushMsgApi;
import com.jvv.gateway.facade.jpush.info.PushParaInfo;
import com.jvv.gateway.test.GatewayTestBase;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class jpuhsmsTest extends GatewayTestBase {
	@Resource
	private PushMsgApi pushMsgApi;






	@Test
	public void testSendSms(){
		PushParaInfo info = new PushParaInfo();
		info.setMsgText("哈哈1");
		PushParaInfo info1 = new PushParaInfo();
		info1.setjPuhId("140fe1da9ea29f25e84");
		info1.setDeviceType(DeviceType.Android);
		info1.setQueneId("112");
		List<PushParaInfo> iss = new ArrayList<PushParaInfo>();
		iss.add(info1);

		info.setList(iss);

		pushMsgApi.jPushMsg (info);



	}




}
