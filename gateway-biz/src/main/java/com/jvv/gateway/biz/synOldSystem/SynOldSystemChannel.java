package com.jvv.gateway.biz.synOldSystem;

import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * 〈http请求〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/3/24
 * @time 16:46
 */
public interface SynOldSystemChannel {
    /**
     * 同步老系统方法
     * @param url
     * @param params
     * @return
     */
    Map<String,Object> action(String url, Map<String,String> params);

    String forChannelApi();
}
