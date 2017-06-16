/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-19 10:08 创建
 *
 */
package com.jvv.gateway.biz;

import com.jvv.gateway.common.redis.AbstractCacheService;
import org.springframework.stereotype.Component;

/**
 * @author turalyon@jinvovo.com
 */
@Component
public class ApiParamCacheService extends AbstractCacheService {




	public static final String CHACE_NAME = "API_PARAM";

	@Override
	protected String getCahceName() {
		return CHACE_NAME;
	}

	@Override
	protected long getExpire() {
		//5 秒
		return 5;
	}
}
