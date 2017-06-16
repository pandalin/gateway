package com.jvv.gateway.biz.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.common.collect.Lists;
import com.jvv.gateway.biz.AbstractBizService;
import com.jvv.gateway.biz.jpush.util.JvvPushClient;
import com.jvv.gateway.common.exceptions.PushMsgException;
import com.jvv.gateway.facade.constants.enums.DeviceType;
import com.jvv.gateway.facade.jpush.info.PushParaInfo;
import com.jvv.gateway.facade.jpush.result.PushMsgResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by gaochao on 2017/6/6.
 */
@Component
public class PushMsgService extends AbstractBizService {

    @Autowired
    private JvvPushClient jvvPushClient;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Value("#{client['jpush.errTime']}")
    private Integer errTime;//错误次数限制

    @Value("#{client['jpush.sound']}")
    private String sound;//ios声音文件



    /**
     * 推送消息
     * @param pushParaInfo
     * @return
     */
    public void jPushMsg(PushParaInfo pushParaInfo) {
        PushMsgResult result = new PushMsgResult();
        String content = pushParaInfo.getMsgText();
        List<PushParaInfo> pushIds = pushParaInfo.getList();
        Integer flag = pushParaInfo.getBatchFlag();

        if(null == pushParaInfo)
            throw new PushMsgException("推送对象为空!");
        if(StringUtils.isEmpty(content))
            throw new PushMsgException("推送内容为空!");
        if(pushIds.size()==0)
            throw new PushMsgException("推送设备ID不能为空");

//        switch (flag){
//            case 1:
//                //群发
//                break;
//            default:
                //(点对点或1对多)
                pushGigMsg(pushParaInfo);
//                break;
//
//        }
    }


    /**
     * 点对多推送(点对点推送)
     */
    private void pushGigMsg(PushParaInfo pushParaInfo){
        List<String> androidList = new ArrayList<String>();
        List<String> iosList = new ArrayList<String>();
        List<PushParaInfo> pushIds = pushParaInfo.getList();
        //ios、android分组存放
        for (PushParaInfo s:pushIds){
            DeviceType type = s.getDeviceType();
            switch (type) {
                case Android:
                    androidList.add(s.getjPuhId());
                    break;
                case IOS:
                    iosList.add(s.getjPuhId());
                    break;
                default:
                    break;
            }
        }

        long startTime = System.currentTimeMillis();
        if(null != androidList && androidList.size() > 0){
            push(pushParaInfo.getMsgText(),pushParaInfo.getAttachedMessage(),androidList.toArray(new String[androidList.size()]),DeviceType.Android,null);
        }
        if(null != iosList && iosList.size() > 0){
            push(pushParaInfo.getMsgText(),pushParaInfo.getAttachedMessage(),iosList.toArray(new String[iosList.size()]),DeviceType.IOS,null);
        }
        long end = System.currentTimeMillis();
        logger.debug("推送时间:"+(end-startTime));
    }


    /**
     * 最终推送
     * @param content 内容
     * @param extras 附加信息
     * @param jpushIds 设备id
     * @param deviceType 设备类型
     * @param queueId 内容
     */
    private void push(final String content, final Map<String, String> extras, final String[] jpushIds, final DeviceType deviceType,final String queueId) {

        //进入发送队列
        taskExecutor.execute(new Runnable() {

            @Override
            public void run() {

                try {

                    PushResult pushResult = null;

                    switch (deviceType) {
                        case Android:
                            pushResult = jvvPushClient.getClient().sendAndroidNotificationWithRegistrationID("金窝窝", content, extras, jpushIds);
                            System.out.println(pushResult.getResponseCode()+"+++++++++++");
                            break;
                        case IOS:
//                            pushResult = jvvPushClient.getClient().sendIosNotificationWithRegistrationID(content, extras, jpushIds);

                            //ios设置声音
                            IosNotification iosNotification = IosNotification.newBuilder().setSound(sound).addExtras(extras).setAlert(content).build();
                            Notification notification = Notification.newBuilder().addPlatformNotification(iosNotification).build();

                            PushPayload iosPayload = PushPayload.newBuilder()
                                    .setPlatform(Platform.ios())
                                    .setAudience(Audience.registrationId(jpushIds))
                                    .setNotification(notification)
                                    .setOptions(null)
                                    .build();

                            if (jvvPushClient.isIosProduct()) {
                                pushResult = jvvPushClient.getClient().sendPush(iosPayload);
                            } else {
                                pushResult = jvvPushClient.getIOSClient().sendPush(iosPayload);
                            }
                            break;

                        case WinPhone:
                            pushResult = new PushResult();
                            logger.error("userId:{}手机不支持推送winPhone", jpushIds[0]);
                            break;
                    }

                    logger.info(ToStringBuilder.reflectionToString(pushResult));

                    if (StringUtils.isNotEmpty(queueId)) {
                        //删除发送成功的数据
                        if (pushResult.isResultOK()) {
                            System.out.println("发送成功！");
////                            deleteSuccessQueue(queueId);
                        } else {
//                            System.out.println(pushResult.getResponseCode()+"----------------");
                            logger.error("发送失败HTTP返回码:"+pushResult.getResponseCode());

//                            repeatPush(content, extras, jpushIds, deviceType, queueId);
                        }
                    }


                } catch (APIConnectionException e) {

                    logger.error("jpush连接错误", e.getMessage());
                    e.printStackTrace();

                } catch (APIRequestException e) {

                    logger.error("jpush请求错误", e.getMessage());
                    e.printStackTrace();

                }
            }
        });
    }

    /**
     * 重发
     * @param content
     * @param extras
     * @param
     * @param deviceType
     * @param queueId
     */
    public void repeatPush(String content, Map<String, String> extras, String[] jpushIds, DeviceType deviceType, String queueId) {
//        JwwPushMsgQuenue oldQueue = jwwPushmsgQuenueMapper.selectJwwPushMsgQuenueById(queueId);
//        if (oldQueue == null || oldQueue.getError_time() > errTime) {
//            return;
//        }

        push(content, extras, jpushIds, deviceType, queueId);


//        oldQueue.setError_time(oldQueue.getError_time() + 1);
//        jwwPushmsgQuenueMapper.updateJwwPushMsgQuenue(oldQueue);

    }

}
