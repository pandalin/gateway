package com.jvv.gateway.facade.synOldSystem.api;

import com.jvv.gateway.facade.synOldSystem.order.SynOldSystemOrder;
import com.jvv.gateway.facade.synOldSystem.result.SynOldSystemResult;

/**
 * Created by IntelliJ IDEA
 * 〈老系统同步服务接口〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/3/27
 * @time 18:27
 */
public interface SynOldSystemApi {
    /**
     * 老系统同步方法
     * @param synOldSystemOrder
     * @return
     */
    SynOldSystemResult syn(SynOldSystemOrder synOldSystemOrder);
}
