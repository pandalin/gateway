package com.jvv.gateway.biz.synOldSystem;


import com.jvv.common.enums.Status;
import com.jvv.gateway.biz.AbstractBizService;
import com.jvv.gateway.facade.synOldSystem.info.SynOldSystemInfo;
import com.jvv.gateway.facade.synOldSystem.order.SynOldSystemOrder;
import com.jvv.gateway.facade.synOldSystem.result.SynOldSystemResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA
 * 〈同步到老系统业务〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/3/24
 * @time
 */
@Component
public class BillSynOldSystemSerivce extends AbstractBizService {
    @Value("#{synOldSystem.url}")
    private String url;
    @Resource
    private SynOldSystemChannelFactory synOldSystemChannelFactory;
    /**
     *同步老系统业务方法
     * @param synOldSystemOrder
     * @return
     */
    public SynOldSystemResult syn(SynOldSystemOrder synOldSystemOrder){
        SynOldSystemResult result=new SynOldSystemResult();


        Map<String,String> params = synOldSystemOrder.getSynParam();
        SynOldSystemChannel synOldSystemChannel=synOldSystemChannelFactory.findPrepaidChannel("synOld");
        Map<String,Object> rMap=synOldSystemChannel.action(url+"/"+synOldSystemOrder.getSynSerivceName(),params);
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
