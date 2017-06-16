package com.jvv.gateway.biz.synOldSystem;

import com.jvv.common.enums.Status;
import com.jvv.gateway.biz.AbstractBizService;
import com.jvv.gateway.facade.synOldSystem.info.SynOldSystemInfo;
import com.jvv.gateway.facade.synOldSystem.order.SynOldSystemOrder;
import com.jvv.gateway.facade.synOldSystem.result.SynOldSystemResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * 〈同步php IM〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/4/10
 * @time 11:35
 */
@Component
public class BillSynPhpSystemService extends AbstractBizService {
    @Value("#{synOldSystem.phpUrl}")
    private String phpUrl;
    @Resource
    private SynOldSystemChannelFactory synOldSystemChannelFactory;

    public SynOldSystemResult syn(SynOldSystemOrder synOldSystemOrder){
        SynOldSystemResult result=new SynOldSystemResult();


        Map<String,String> params = synOldSystemOrder.getSynParam();
        SynOldSystemChannel synOldSystemChannel=synOldSystemChannelFactory.findPrepaidChannel("synOld");
        //请求php IM
        Map<String,Object> rMap=synOldSystemChannel.action(phpUrl+synOldSystemOrder.getSynSerivceName(),params);
        SynOldSystemInfo synOldSystemInfo=new SynOldSystemInfo();
        synOldSystemInfo.setResult((String)rMap.get("result"));
        if (synOldSystemInfo.getResult()!=null&&"1".equals(synOldSystemInfo.getResult())){
            synOldSystemInfo.setData((String)rMap.get("data"));
        }else{
            synOldSystemInfo.setFailureReason((String)rMap.get("failureReason"));
        }
        result.setInfo(synOldSystemInfo);
        result.setStatus(Status.SUCCESS);
        return result;
    }
}
