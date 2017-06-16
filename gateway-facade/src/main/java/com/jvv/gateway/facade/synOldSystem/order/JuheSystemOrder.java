package com.jvv.gateway.facade.synOldSystem.order;

import com.jvv.common.services.order.AbstractOrder;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * 〈聚合服务实体参数〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/4/27
 * @time 9:56
 */
public class JuheSystemOrder extends AbstractOrder {
    //请求同步服务
    @NotBlank
    private String synSerivceName;
    //请求参数
    private Map<String,String> param;

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public String getSynSerivceName() {
        return synSerivceName;
    }

    public void setSynSerivceName(String synSerivceName) {
        this.synSerivceName = synSerivceName;
    }
}
