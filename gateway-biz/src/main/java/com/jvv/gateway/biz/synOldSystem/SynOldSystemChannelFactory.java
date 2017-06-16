package com.jvv.gateway.biz.synOldSystem;

import com.google.common.collect.Maps;
import com.jvv.gateway.common.exceptions.GatewayExceprion;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/3/24
 * @time 17:22
 */
@Component
public class SynOldSystemChannelFactory implements ApplicationContextAware,InitializingBean {
    private Map<String,SynOldSystemChannel> channelMap = Maps.newConcurrentMap();


    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext =  applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String,SynOldSystemChannel> beans = applicationContext.getBeansOfType(SynOldSystemChannel.class);
        for(Map.Entry<String,SynOldSystemChannel> bean: beans.entrySet()){
            SynOldSystemChannel channel = bean.getValue();
            channelMap.put(channel.forChannelApi(),channel);
        }
    }

    /**
     * 根据渠道编码获取渠道信息
     *
     * @param channelApi
     * @return
     */
    public SynOldSystemChannel findPrepaidChannel(String channelApi){
        SynOldSystemChannel channel = channelMap.get(channelApi);
        if(channel==null){
            throw new GatewayExceprion("不支持的渠道："+channelApi);
        }

        return channel;
    }
}
