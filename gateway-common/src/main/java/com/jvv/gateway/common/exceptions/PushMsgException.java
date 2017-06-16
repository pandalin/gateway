package com.jvv.gateway.common.exceptions;

import com.jvv.common.enums.Status;
import com.jvv.common.lang.exception.BizException;

/**
 * Created by 高超 on 2017/6/6.
 * 推送异常
 */
public class PushMsgException extends BizException {

    public PushMsgException() {
    }

    public PushMsgException(String msg) {
        super(msg);
    }

    public PushMsgException(Status status, Throwable cause) {
        super(status, cause);
    }
}
