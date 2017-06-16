package com.jvv.gateway.facade.synOldSystem.api;

import com.jvv.gateway.facade.synOldSystem.order.SynOldSystemOrder;
import com.jvv.gateway.facade.synOldSystem.result.SynOldSystemResult;

/**
 * Created by IntelliJ IDEA
 * 〈同步php im〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/4/11
 * @time 14:09
 */
public interface SynPhpSystemApi {
    /**
     * php im同步方法
     * @param synOldSystemOrder
     * @return
     */
    SynOldSystemResult syn(SynOldSystemOrder synOldSystemOrder);
}
