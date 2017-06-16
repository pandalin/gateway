package com.jvv.gateway.facade.synOldSystem.order;

import com.jvv.common.services.order.AbstractOrder;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * 〈同步老系统服务参数实体〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/3/24
 * @time 15:34
 */
public class SynOldSystemOrder extends AbstractOrder {
    //请求同步服务
    @NotBlank
    private String synSerivceName;

    //请求参数
    private Map<String,String> synParam;

    public Map<String, String> getSynParam() {
        return synParam;
    }

    public void setSynParam(Map<String, String> synParam) {
        this.synParam = synParam;
    }

    public String getSynSerivceName() {
        return synSerivceName;
    }

    public void setSynSerivceName(String synSerivceName) {
        this.synSerivceName = synSerivceName;
    }
}
