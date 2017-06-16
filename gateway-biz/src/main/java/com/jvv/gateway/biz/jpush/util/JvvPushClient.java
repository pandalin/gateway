/*
 *
 *  Copyright (c) 2016, 重庆金窝窝网络科技有限公司.  All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License. You may obtain a copy of
 *  the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 *  @Author:ascendlin@sina.com
 *
 */

package com.jvv.gateway.biz.jpush.util;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JvvPushClient {
	@Value("#{client['jpush.masterSecret']}")
	private String masterSecret;

	@Value("#{client['jpush.appKey']}")
	private String appKey;

	@Value("#{client['jpush.apnsProduction']}")
	private String apnsProduction;

	private JPushClient client;

	//**************************IOS独立企业证书******************************
	@Value("#{client['jpush.masterSecret.ios']}")
	private String masterSecretIOS;

	@Value("#{client['jpush.appKey.ios']}")
	private String appKeyIOS;

	@Value("#{client['jpush.apnsProduction.ios']}")
	private String apnsProductionIOS;

	@Value("#{client['ios.product']}")
	private String iosProduct;

	private JPushClient iosPushClient;

	private Long timeToLive = 86400L;

	@PostConstruct
	public void init() {
		ClientConfig config = ClientConfig.getInstance();
		config.setApnsProduction(getApnsProcut());
		config.setTimeToLive(timeToLive);
		client = new JPushClient(masterSecret, appKey, null, config);

		ClientConfig iosConfig = ClientConfig.getInstance();
		iosConfig.setApnsProduction(getApnsProcutIOS());
		iosConfig.setTimeToLive(timeToLive);
		iosPushClient = new JPushClient(masterSecretIOS, appKeyIOS, null, iosConfig);

	}

	public boolean getApnsProcut() {
		return BooleanUtils.toBoolean(apnsProduction);
	}

	public boolean getApnsProcutIOS() {
		return BooleanUtils.toBoolean(apnsProductionIOS);
	}

	public boolean isIosProduct() {
		return BooleanUtils.toBoolean(iosProduct);
	}

	public JPushClient getClient() {
		return client;
	}

	public JPushClient getIOSClient() {
		return iosPushClient;
	}

}
