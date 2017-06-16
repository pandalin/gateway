/*
 * www.jinvovo.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */
package com.jvv.gateway.integration.sendsms;

import com.jvv.gateway.biz.sendsms.SendSmsChannel;
import com.jvv.common.services.result.SimpleResult;
import com.jvv.common.enums.Status;
import com.jvv.gateway.facade.sendsms.info.SmsBalanceInfo;
import com.jvv.gateway.facade.sendsms.order.SendSmsOrder;
import com.jvv.gateway.facade.sendsms.result.SmsBalanceResult;
import com.jvv.gateway.integration.sendsms.utils.MwCHttpPost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA
 * 〈类详细描述〉 <p>
 * 〈功能详细描述〉
 *
 * @author linxm
 * @date
 * @time
 */

@Service("mwSendSms")
public class MwSendSms implements SendSmsChannel{
	
	
	//#梦网账户1短信平台配置（金窝窝）
	@Value("#{jvvContext.mw_sms_ip}")
	private String mwIp;
	@Value("#{jvvContext.mw_sms_port}")
	private String mwPort;
	@Value("#{jvvContext.mw_sms_userid}")
	private String mwUserId;
	@Value("#{jvvContext.mw_sms_pwd}")
	private String mwPwd;
	
	@Resource
	ClSendSms clSendSms;
	
	@Override
	public SimpleResult sendSms (SendSmsOrder bill) {
		int result = -1;
		try {
            /*String phone = "138XXXXXXXX,159XXXXXXXX";//手机号码，用英文逗号,分隔，最大1000个号码。
            String strMessage = "测试短信";//短信内容*/
			String strSubPort = "*";//扩展子号 （不带请填星号*，长度不大于6位）;
			String strUserMsgId = "0";//用户自定义流水号，不带请输入0（流水号范围-（2^63）……2^63-1）
			MwCHttpPost sms = new MwCHttpPost();//短信请求业务类
			StringBuffer strPtMsgId = new StringBuffer("");//如果成功，存流水号。失败，存错误码。
			
			if ("CNI".equals(bill.getSmsTempEnum ().getMwTempId())) {
				return clSendSms.sendSms (bill);
			} else {
				result = sms.SendSms(strPtMsgId, mwIp, mwPort, mwUserId, mwPwd, bill.getPhoneNum (), bill.getMessage (), strSubPort, strUserMsgId);//短信息发送接口（相同内容群发，可自定义流水号）POST请求。
			}
			
			if(result!=0){
				//logger.error("梦网短信发送失败,错误码："+strPtMsgId.toString());
			}
			
		} catch (Exception e) {
			//logger.error("梦网短信发送失败" + e.getMessage());
			result = -300;
		}
		return mwSendMessageType(result);
	}
	
	
	
	/**
	 * 查询余额接口
	 */
	public SmsBalanceResult queryBalance (){
		SmsBalanceResult simpleResult=new SmsBalanceResult ();
		try{
			MwCHttpPost sms=new MwCHttpPost();
			StringBuffer nBalance=new StringBuffer("");//查询成功，存放余额。查询失败，存放错误码
			int result=sms.QueryBalance(nBalance,mwIp,mwPort, mwUserId, mwPwd);//调用查余额方法
			if(result==0){//返回值为0，则代表查询成功
//				simpleResult.setStatus (Status.SUCCESS);
//				simpleResult.setCode (nBalance.toString());
//				simpleResult.setMessage (nBalance.toString());

				SmsBalanceInfo balanceInfo = new SmsBalanceInfo(Integer.valueOf(nBalance.toString()));
				simpleResult.setInfo(balanceInfo);

				simpleResult.setToSuccess("查询成功");

			}else{//返回值非0，则代表查询失败
				simpleResult.setStatus (Status.FAIL);
				simpleResult.setCode (nBalance.toString());
				simpleResult.setMessage (nBalance.toString());
			}
		}catch (Exception e) {
			simpleResult.setStatus (Status.FAIL);
			simpleResult.setDescription ("请求短信网络服务异常"+e.getMessage ());
		}
		return simpleResult;
	}
	
	
	
	
	public SimpleResult mwSendMessageType(int resultInt) {
		SimpleResult simpleResult=new SimpleResult ();
		
		if (0==resultInt) {//返回值为0，代表成功
			simpleResult.setStatus (Status.SUCCESS);
			simpleResult.setCode (String.valueOf (resultInt));
		} else {//返回值为非0，代表失败
			simpleResult.setStatus (Status.FAIL);
			simpleResult.setCode (String.valueOf (resultInt));
		}
		return simpleResult;
	}
	
	
	
	@Override
	public String forChannelApi () {
		return "mw";
	}
}
