package com.jvv.gateway.provider.SynOldSystem;

import com.jvv.common.services.order.validation.Add;
import com.jvv.gateway.biz.synOldSystem.BillJuheSystemService;
import com.jvv.gateway.facade.synOldSystem.api.JuheSystemApi;
import com.jvv.gateway.facade.synOldSystem.order.JuheSystemOrder;
import com.jvv.gateway.facade.synOldSystem.result.JuheSystemResult;
import com.jvv.gateway.provider.AbstractProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/4/27
 * @time 10:08
 */
@Service("juheSystemApi")
public class JuheSystemProvider extends AbstractProvider implements JuheSystemApi{
    @Resource
    BillJuheSystemService billJuheSystemService;
    @Override
    public JuheSystemResult getByAppkey(JuheSystemOrder juheSystemOrder) {
        logger.info("===>>收到通过appkey调用聚合的请求，order={}",juheSystemOrder);
        JuheSystemResult result;
        try {
            checkOrder(juheSystemOrder, Default.class, Add.class);
            result =billJuheSystemService.getByAppkey(juheSystemOrder);
        }catch (Exception e){
            result = handleResult(e,new JuheSystemResult());
        }
        logger.info("<<===通过appkey调用聚合处理完成，result={}",result);
        return result;
    }
}
