package com.jvv.gateway.integration.synOldSystem;

import com.jvv.gateway.biz.synOldSystem.SynOldSystemChannel;
import com.jvv.gateway.integration.synOldSystem.utils.HttpClient4Utils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * 〈同步老系统渠道〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/3/24
 * @time 17:14
 */
@Service("synOldSystemAndPhp")
public  class SynOldSystem implements SynOldSystemChannel {
    /**
     * 请求老系统实现
     * @param url
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> action(String url, Map<String,String> params) {
        Map<String,Object> result = new HashMap();
        try {
            String response = HttpClient4Utils.post(url,params);
            result.put("result", "1");
            result.put("data", response);
        } catch (Exception e) {
            result.put("result", "0");
            result.put("failureReason", e.getMessage());
            result.put("failureMessage", e.getMessage());
        }
        return result;
    }

    @Override
    public String forChannelApi () {
        return "synOld";
    }
}
