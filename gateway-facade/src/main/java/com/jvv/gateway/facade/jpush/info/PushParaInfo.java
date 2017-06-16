/*
 *
 *  Copyright (c) 2016, 重庆金窝窝网络科技有限公司.  All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  use this file except in compliance with the License. You may obtain a copy of
 *  the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *  License for the specific language governing permissions and limitations under
 *  the License.
 *  @Author:ascendlin@sina.com
 *
 */

package com.jvv.gateway.facade.jpush.info;

import com.jvv.gateway.facade.constants.enums.DeviceType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by gaochao on 2017/6/6 10:46.
 */
public class PushParaInfo implements Serializable{

    private String                  msgText;//消息文字 必填
    private Integer                 msgType;//消息类型，0-图片，1-文字
    private String                  sendUserid;//发送用户id
    private Integer                 batchFlag;//是否群发：0-不是群发 1-群发
    private Map<String,String>      attachedMessage;//附加消息
    private String            jPuhId;//设备ID 必填
    private String  queneId;//id
    private List<PushParaInfo> list; //多人发送装载 必填
    private DeviceType deviceType;//设备类型 必填
//    private List<String> jPushIds;//设备id

//    public List<String> getjPushIds() {
//        return jPushIds;
//    }
//
//    public void setjPushIds(List<String> jPushIds) {
//        this.jPushIds = jPushIds;
//    }

    public String getQueneId() {
        return queneId;
    }

    public void setQueneId(String queneId) {
        this.queneId = queneId;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public List<PushParaInfo> getList() {
        return list;
    }

    public void setList(List<PushParaInfo> list) {
        this.list = list;
    }


    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getSendUserid() {
        return sendUserid;
    }

    public void setSendUserid(String sendUserid) {
        this.sendUserid = sendUserid;
    }

    public Integer getBatchFlag() {
        return batchFlag;
    }

    public void setBatchFlag(Integer batchFlag) {
        this.batchFlag = batchFlag;
    }

    public Map<String, String> getAttachedMessage() {
        return attachedMessage;
    }

    public void setAttachedMessage(Map<String, String> attachedMessage) {
        this.attachedMessage = attachedMessage;
    }

    public String getjPuhId() {
        return jPuhId;
    }

    public void setjPuhId(String jPuhId) {
        this.jPuhId = jPuhId;
    }
}
