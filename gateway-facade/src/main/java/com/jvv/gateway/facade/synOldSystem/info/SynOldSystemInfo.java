package com.jvv.gateway.facade.synOldSystem.info;

import com.jvv.common.services.info.AbstractInfo;

/**
 * Created by IntelliJ IDEA
 * 〈同步返回数据〉<p>
 * 〈功能详细描述〉
 *
 * @author liaotianyu
 * @date 2017/3/24
 * @time 15:51
 */
public class SynOldSystemInfo extends AbstractInfo {
    //返回成功失败代码0失败1成功
    private String result;
    //返回错误信息
    private String failureReason;
    //返回数据
    private String data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
