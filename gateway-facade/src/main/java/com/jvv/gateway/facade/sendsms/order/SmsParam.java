package com.jvv.gateway.facade.sendsms.order;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 聚合短信模板
 * Created by admin on 2016/11/16.
 */
public class SmsParam implements Serializable {


    private Map<String,String>  tplValue;
    
    public Map<String, String> getTplValue () {
        return tplValue;
    }
    
    public void setTplValue (Map<String, String> tplValue) {
        this.tplValue = tplValue;
    }
    
    @Override
    public String toString() {

        String tplValueStr="";

        if(tplValue!=null&&tplValue.size()>0){
            StringBuilder sb = new StringBuilder();

            for (Map.Entry en:tplValue.entrySet()){
                sb.append('&').append('#').append(en.getKey()).append("#=").append(en.getValue());
            }
            if(sb.length () > 0){
                sb.deleteCharAt (0);
            }

            try {
                tplValueStr= URLEncoder.encode(sb.toString(),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        }


 /*      Set<String> keys= tplValue.keySet();

        if(keys.size()>0){

            Integer i=0;
            for (String key:keys){

                tplValueStr=i.equals(0)?(tplValueStr+"#"+key+"#="+tplValue.get(key)):(tplValueStr+"&#"+key+"#="+tplValue.get(key));

                i++;
            }

            try {
                tplValueStr= URLEncoder.encode(tplValueStr,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }*/

        return tplValueStr;
    }

    public SmsParam () {
    }

    public SmsParam (String title, String value) {
        Map mc=new HashMap();
        mc.put(title,value);
        setTplValue(mc);
    }

    public void upTplValue(String title, String value){
        this.tplValue.put(title,value);
    }

}
