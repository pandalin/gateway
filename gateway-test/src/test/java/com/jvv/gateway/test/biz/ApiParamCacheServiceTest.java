/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-19 10:12 创建
 *
 */
package com.jvv.gateway.test.biz;

import com.jvv.gateway.biz.ApiParamCacheService;
import com.jvv.gateway.test.GatewayTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author turalyon@jinvovo.com
 */
public class ApiParamCacheServiceTest extends GatewayTestBase {
	@Autowired
	private ApiParamCacheService apiParamCacheService;

	@Test
	public void testCache(){
		String key ="api0001";
		Object values ="api0001's parapms....";
		apiParamCacheService.put(key,values);


		Assert.isTrue(values.equals(apiParamCacheService.get(key)));
		try {
			Thread.sleep(10 * 1000 );
		} catch (InterruptedException e) {
		}
		Assert.isNull(apiParamCacheService.get(key));
	}
}
