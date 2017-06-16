/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-17 22:03 创建
 *
 */
package com.jvv.gateway.biz;

import com.jvv.gateway.dal.mapper.ExtraMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author turalyon@jinvovo.com
 */
public abstract class AbstractBizService {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected ExtraMapper extraMapper;


	protected Date getSysdate(){
		return extraMapper.getSysdate();
	}
}
