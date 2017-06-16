package com.jvv.gateway.facade.synOldSystem.api;

import com.jvv.gateway.facade.synOldSystem.order.JuheSystemOrder;
import com.jvv.gateway.facade.synOldSystem.result.JuheSystemResult;

/**
 * Created by IntelliJ IDEA
 * 〈聚合服务接口〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/4/27
 * @time 10:06
 */
public interface JuheSystemApi {
    /**
     * 通过appkey调用聚合接口
     * @param juheSystemOrder
     * @return
     */
    JuheSystemResult getByAppkey(JuheSystemOrder juheSystemOrder);
}
