/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * turalyon@jinvovo.com 2017-03-17 23:06 创建
 *
 */
package com.jvv.gateway.biz.prepaid;

import com.google.common.collect.Maps;
import com.jvv.gateway.common.exceptions.GatewayExceprion;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author turalyon@jinvovo.com
 */
@Component
public class PrepaidChannelFactory implements ApplicationContextAware,InitializingBean{

	private Map<String,PrepaidChannel> channelMap = Maps.newConcurrentMap();


	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext =  applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String,PrepaidChannel> beans = applicationContext.getBeansOfType(PrepaidChannel.class);
		for(Map.Entry<String,PrepaidChannel> bean: beans.entrySet()){
			PrepaidChannel channel = bean.getValue();
			channelMap.put(channel.forChannelApi(),channel);
		}
	}

	/**
	 * 根据渠道编码获取渠道信息
	 *
	 * @param channelApi
	 * @return
	 */
	public PrepaidChannel findPrepaidChannel(String channelApi){
		PrepaidChannel channel = channelMap.get(channelApi);
		if(channel==null){
			throw new GatewayExceprion("不支持的渠道："+channelApi);
		}

		return channel;
	}

}
