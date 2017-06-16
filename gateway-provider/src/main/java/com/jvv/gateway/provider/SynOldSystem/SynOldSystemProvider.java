package com.jvv.gateway.provider.SynOldSystem;

import com.jvv.common.services.order.validation.Add;
import com.jvv.gateway.biz.synOldSystem.BillSynOldSystemSerivce;
import com.jvv.gateway.facade.synOldSystem.api.SynOldSystemApi;
import com.jvv.gateway.facade.synOldSystem.order.SynOldSystemOrder;
import com.jvv.gateway.facade.synOldSystem.result.SynOldSystemResult;
import com.jvv.gateway.provider.AbstractProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * Created by IntelliJ IDEA
 * 〈同步老系统路由服务〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/3/27
 * @time 18:26
 */
@Service("synOldSystemApi")
public class SynOldSystemProvider extends AbstractProvider implements SynOldSystemApi {
    @Resource
    BillSynOldSystemSerivce billSynOldSystemSerivce;
    @Override
    public SynOldSystemResult syn(SynOldSystemOrder synOldSystemOrder) {
        logger.info("===>>收到同步老系统的请求，order={}",synOldSystemOrder);
        SynOldSystemResult result;
        try{
            checkOrder(synOldSystemOrder, Default.class, Add.class);
            result=billSynOldSystemSerivce.syn(synOldSystemOrder);
        }catch (Exception e){
            result = handleResult(e,new SynOldSystemResult());
        }
        logger.info("<<===同步老系统处理完成，result={}",result);
        return result;
    }
}
