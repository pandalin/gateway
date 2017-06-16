package com.jvv.gateway.biz.synOldSystem;

import com.jvv.common.enums.Status;
import com.jvv.gateway.biz.AbstractBizService;
import com.jvv.gateway.facade.synOldSystem.info.JuheSystemInfo;
import com.jvv.gateway.facade.synOldSystem.order.JuheSystemOrder;
import com.jvv.gateway.facade.synOldSystem.result.JuheSystemResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * 〈调用聚合实名认证相关第三方接口〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/4/27
 * @time 9:30
 */
@Component
public class BillJuheSystemService  extends AbstractBizService {
    @Value("#{synOldSystem.juheUrl}")
    private String url;
    @Value("#{synOldSystem.juheAppkey}")
    private String juheAppkey;
    @Resource
    private SynOldSystemChannelFactory synOldSystemChannelFactory;

    /**
     * 通过appkey调用聚合接口
     * @param juheSystemOrder
     * @return
     */
    public JuheSystemResult getByAppkey(JuheSystemOrder juheSystemOrder){
        JuheSystemResult result=new JuheSystemResult();


        Map<String,String> params = juheSystemOrder.getParam();
        params.put("key",juheAppkey);
        SynOldSystemChannel synOldSystemChannel=synOldSystemChannelFactory.findPrepaidChannel("synOld");
        Map<String,Object> rMap=synOldSystemChannel.action(url+"/"+juheSystemOrder.getSynSerivceName(),params);
        JuheSystemInfo juheSystemInfo=new JuheSystemInfo();
        juheSystemInfo.setResult((String)rMap.get("result"));
        if (juheSystemInfo.getResult()!=null&&"1".equals(juheSystemInfo.getResult())){
            juheSystemInfo.setData((String)rMap.get("data"));
        }else{
            juheSystemInfo.setFailureReason((String)rMap.get("failureReason"));
        }
        result.setInfo(juheSystemInfo);
        result.setStatus(Status.SUCCESS);
        return result;
    }
}
