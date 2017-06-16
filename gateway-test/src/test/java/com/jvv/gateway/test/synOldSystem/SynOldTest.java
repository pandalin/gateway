package com.jvv.gateway.test.synOldSystem;

import com.jvv.gateway.facade.synOldSystem.api.JuheSystemApi;
import com.jvv.gateway.facade.synOldSystem.api.SynOldSystemApi;
import com.jvv.gateway.facade.synOldSystem.api.SynPhpSystemApi;
import com.jvv.gateway.facade.synOldSystem.order.JuheSystemOrder;
import com.jvv.gateway.facade.synOldSystem.order.SynOldSystemOrder;
import com.jvv.gateway.facade.synOldSystem.result.JuheSystemResult;
import com.jvv.gateway.facade.synOldSystem.result.SynOldSystemResult;
import com.jvv.gateway.test.GatewayTestBase;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/4/11
 * @time 16:34
 */
public class SynOldTest extends GatewayTestBase {
    @Resource
    SynPhpSystemApi synPhpSystemApi;
    @Resource
    JuheSystemApi juheSystemApi;
    @Test
    public void testSynSystem(){
        SynOldSystemOrder synOldSystemOrder=new SynOldSystemOrder();
        synOldSystemOrder.setSynSerivceName("/im/GroupChat/groupDissolve");
        Map<String,String> stringStringMap=new HashMap<String,String>();
        stringStringMap.put("GroupId","1111");
        synOldSystemOrder.setSynParam(stringStringMap);
        SynOldSystemResult r=synPhpSystemApi.syn(synOldSystemOrder);
    }
    @Test
    public void testJuheSystem(){
        JuheSystemOrder juheSystemOrder=new JuheSystemOrder();
        juheSystemOrder.setSynSerivceName("mobile/get");
        Map<String,String> stringStringMap=new HashMap<String,String>();
        stringStringMap.put("phone","13436193682");
        juheSystemOrder.setParam(stringStringMap);
        JuheSystemResult result=juheSystemApi.getByAppkey(juheSystemOrder);
        System.out.print("xx");
    }
}
