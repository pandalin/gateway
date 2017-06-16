package com.jvv.gateway.provider.SynOldSystem;


import com.jvv.common.services.order.validation.Add;
import com.jvv.gateway.biz.synOldSystem.BillSynPhpSystemService;
import com.jvv.gateway.facade.synOldSystem.api.SynPhpSystemApi;
import com.jvv.gateway.facade.synOldSystem.order.SynOldSystemOrder;
import com.jvv.gateway.facade.synOldSystem.result.SynOldSystemResult;
import com.jvv.gateway.provider.AbstractProvider;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * Created by IntelliJ IDEA
 * 〈同步php服务接口〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/4/10
 * @time 11:33
 */
@Service("synPhpSystemApi")
public class SynPhpSystemProvider extends AbstractProvider implements SynPhpSystemApi {
    @Resource
    BillSynPhpSystemService billSynPhpSystemService;
    @Override
    public SynOldSystemResult syn(SynOldSystemOrder synOldSystemOrder) {
        logger.info("===>>收到同步PHP的请求，order={}",synOldSystemOrder);
        SynOldSystemResult result;
        try{
            checkOrder(synOldSystemOrder, Default.class, Add.class);
            result=billSynPhpSystemService.syn(synOldSystemOrder);
        }catch (Exception e){
            result = handleResult(e,new SynOldSystemResult());
        }
        logger.info("<<===同步PHP处理完成，result={}",result);
        return result;
    }
}
