package com.jvv.gateway.facade.jpush.api;

import com.jvv.gateway.facade.jpush.info.PushParaInfo;
import com.jvv.gateway.facade.jpush.result.PushMsgResult;

/**
 * Created by gaochao on 2017/6/6.
 */
public interface PushMsgApi {
    PushMsgResult jPushMsg(PushParaInfo pushParaInfo);
}
