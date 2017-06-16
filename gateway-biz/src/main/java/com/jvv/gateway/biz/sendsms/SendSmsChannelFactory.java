/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.biz.sendsms;

import com.google.common.collect.Maps;
import com.jvv.gateway.biz.prepaid.PrepaidChannel;
import com.jvv.gateway.common.exceptions.GatewayExceprion;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

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
public class SendSmsChannelFactory implements ApplicationContextAware,InitializingBean {
	
	
	private Map<String,SendSmsChannel> channelMap = Maps.newConcurrentMap();
	
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext =  applicationContext;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String,SendSmsChannel> beans = applicationContext.getBeansOfType(SendSmsChannel.class);
		for(Map.Entry<String,SendSmsChannel> bean: beans.entrySet()){
			SendSmsChannel channel = bean.getValue();
			channelMap.put(channel.forChannelApi(),channel);
		}
	}
	
	/**
	 * 根据渠道编码获取渠道信息
	 *
	 * @param channelApi
	 * @return
	 */
	public SendSmsChannel findPrepaidChannel(String channelApi){
		SendSmsChannel channel = channelMap.get(channelApi);
		if(channel==null){
			throw new GatewayExceprion ("不支持的渠道："+channelApi);
		}
		
		return channel;
	}
	
}
