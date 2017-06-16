package com.jvv.gateway.provider.jPush;

import com.jvv.common.enums.Status;
import com.jvv.gateway.biz.jpush.PushMsgService;
import com.jvv.gateway.facade.jpush.api.PushMsgApi;
import com.jvv.gateway.facade.jpush.info.PushParaInfo;
import com.jvv.gateway.facade.jpush.result.PushMsgResult;
import com.jvv.gateway.provider.AbstractProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 极光推送 on 2017/6/5.
 *
 */
@Service("pushMsgApi")
public class PushMsgProvider extends AbstractProvider implements PushMsgApi {

    @Autowired
    private PushMsgService pushMsgService;

    /**
     * 推送消息
     */
    @Override
    public PushMsgResult jPushMsg(PushParaInfo pushParaInfo){
        logger.info("推送消息开始---",pushParaInfo);
        PushMsgResult result = new PushMsgResult();
        try{
            pushMsgService.jPushMsg(pushParaInfo);
            result.setCode("1");
            result.setStatus(Status.SUCCESS);
            result.setMessage("推送成功");
        }catch (Exception e){
            result = handleResult(e,new PushMsgResult());
        }

        logger.info("推送消息结束---",pushParaInfo);

        return result;
    }
}
