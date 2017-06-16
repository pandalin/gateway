package com.jvv.gateway.integration.sendsms.utils;

/**
 * Created by admin on 2017/2/7.
 */

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 公共类
 */
public class MwCHttpPost {

    private Logger logger = LoggerFactory.getLogger(MwCHttpPost.class);

    private int sameErrorCount = 0;//相同内容发送失败次数计数
    /**
     * 短信息发送接口（相同内容群发，可自定义流水号）
     * @param strPtMsgId 平台返回的流水号
     * @param strUserId  帐号
     * @param strPwd 密码
     * @param strMobiles 手机号
     * @param strMessage 短信内容
     * @param strSubPort 扩展子号
     * @param strUserMsgId 用户自编流水号
     * @return 0:成功 非0:返回webservice接口返回的错误代码
     */
    public int SendSms(StringBuffer strPtMsgId, String ip,String port,String strUserId, String strPwd, String strMobiles, String strMessage, String strSubPort, String strUserMsgId)
    {
        int returnInt=-200;//定义返回值变量
        try {
            String result = null;//存放解析后的返回值
            MwParams p = new MwParams ();
            p.setUserId(strUserId);//设置账号
            p.setPassword(strPwd);//设置密码
            p.setPszMobis(strMobiles);//设置手机号码
            p.setPszMsg(strMessage);//设置短信内容
            p.setIMobiCount(String.valueOf(strMobiles.split(",").length));//设置手机号码个数
            p.setPszSubPort(strSubPort);//设置扩展子号
            p.setMsgId(strUserMsgId);//设置流水号
            String Message = executePost(p, "http://"+ip+":"+port+"/MWGate/wmgw.asmx/"+
                    "MongateSendSubmit");//调用底层POST方法提交
            //请求返回值不为空，则解析返回值
            if(Message != null&& Message != "")
            {
                Document doc= DocumentHelper.parseText(Message);
                Element el = doc.getRootElement();
                result = el.getText();//解析返回值
            } //处理返回结果
            if(result != null&& !"".equals(result)&&result.length()>10){
                //解析后的返回值不为空且长度大于10，则是提交成功
                returnInt=0;
                strPtMsgId.append(result);
            }else if(result==null||"".equals(result)){//解析后的返回值为空，则提交失败
                strPtMsgId.append(returnInt);
            }else{//解析后的返回值不为空且长度不大于10，则提交失败，返回错误码				                       				returnInt=Integer.parseInt(result);
                strPtMsgId.append(returnInt);
            }
        } catch (Exception e) {
            sameErrorCount=sameErrorCount+1; //发送失败,发送失败次数加1
            returnInt=-200;
            strPtMsgId.append(returnInt);
            e.printStackTrace();//异常处理
        }
        return returnInt;//返回值返回
    }
    
    
    
    /**
     * 查询余额接口
     * @param nBalance 帐号费用
     * @param strUserId 帐号
     * @param strPwd 密码
     * @return 0:成功 非0:返回webservice接口返回的错误代码
     */
    public int QueryBalance(StringBuffer nBalance, String ip,String port,String strUserId, String strPwd)
    {
        Integer result=null;//返回值
        try {
            MwParams p = new MwParams();
            p.setUserId(strUserId);//设置账号
            p.setPassword(strPwd);//设置密码
            String Message = executePost(p, "http://"+ip+":"+port+"/MWGate/wmgw.asmx/"+
                    "MongateQueryBalance");//查询余额接口POST请求
            if(Message != null&& Message != "")
            {//网关返回值不为空，则解析网关返回值
                Document doc=DocumentHelper.parseText(Message);
                Element el = doc.getRootElement();
                result = Integer.parseInt(el.getText());//解析返回值
            }
        }catch(Exception e) {
            //balanceErrorCount=balanceErrorCount+1; //查询余额失败，查询余额失败次数加1
            logger.error("出现了异常，访问地址配置错误！",e);
        }
        if(result==null){//返回值为空，则代表查询余额失败
            nBalance.append("-1");
            result=-1;
        }else if(result>=0){//返回值大于等于0，则代表查询余额成功
            nBalance.append(result);
            result=0;
        }else{//返回值为非0，则代表查询余额失败，返回值错误码
            nBalance.append(result);
        }
        return result.intValue();//返回返回值
    }
    
    
    
    
    /**
     * 使用post请求
     * @param obj  请求参数对象
     * @param httpUrl  请求URL地址
     * @return 请求网关的返回值
     * @throws Exception
     */
    private String executePost(Object obj, String httpUrl) throws Exception {
        String result = "";
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        //设置请求参数
        String fieldName = null;
        String fieldNameUpper = null;
        Method getMethod = null;
        String value = null;
        for (int i = 0; i < fields.length; i++)   {//循环设置请求参数
            fieldName = fields[i].getName();
            fieldNameUpper = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            getMethod = cls.getMethod("get" + fieldNameUpper);//通过反射获取get方法
            value = (String) getMethod.invoke(obj);//通过反射调用get方法
            if(value != null) {//请求参数值不为空，才设置
                params.add(new BasicNameValuePair(fieldName, value));
            }
        }
        HttpPost httppost = new HttpPost(httpUrl);//设置HttpPost
        httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8)); // 设置参数的编码UTF-8
        HttpClient httpclient = new DefaultHttpClient();//创建HttpClient
        HttpEntity entity = httpclient.execute(httppost).getEntity();//Http请求网关
        if(entity != null&& entity.getContentLength() >0) {//返回值不为空，且长度大于0
            result= EntityUtils.toString(entity);//将返回值转换成字符串
        }//处理返回结果
        httpclient.getConnectionManager().shutdown();//关闭连接
        return result;//返回返回值
    }

}

