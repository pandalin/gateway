package com.jvv.gateway.facade.constants.enums;

/**
 * 短信模板不同平台对应
 * 1577是金窝窝，   1535对应的是CNI
 * Created by admin on 2016/11/15.
 */
public enum SmsTempEnum {
    JM("恭喜您，成为金窝窝平台活跃用户，获得长青创客‘微客’资格！您可参与金窝窝.长青创客计划，获得丰厚利润回报。服务热线：4000233969",5,"1535","27202","CNI"),
    UPVIP("恭喜您，平台消费满160PV，成为VIP！您可参与金窝窝.长青创客计划。服务热线：4000233969",5,"1577","27191","JWW"),
    SMS_GETRANDCODE("您的手机验证码为:#code#,请及时输入!",5,"1577","23468","JWW"),
    SMS_GETRANDCODE_LOGIN("您的手机验证码为:#code#,请及时输入!",5,"1577","23468","JWW"),
    SMS_GETRANDCODE_OAUTH_LOGIN("您的手机验证码为:#code#,请及时输入!",5,"1577","23468","JWW"),
    SMS_GETRANDCODE_SET_LOGIN_PWD("您的手机验证码为:#code#,请及时输入!",5,"1577","23468","JWW"),
    SMS_GETRANDCODE_SET_DEAL_PWD("您的手机验证码为:#code#,请及时输入!",5,"1577","23468","JWW"),
    SMS_GETRANDCODE_CHANGE_LOGIN_PWD("您的手机验证码为:#code#,请及时输入!",5,"1577","23468","JWW"),
    SMS_RESETDEALPWD("您正在重置安全码,手机验证码为：#code#，请及时输入。",5,"1577","23469","JWW"),
    SMS_RESETDEALPWD_CHANGE("您正在重置安全码,手机验证码为：#code#，请及时输入。",5,"1577","23469","JWW"),
    SMS_RESETDEALPWD_BACK("您正在重置安全码,手机验证码为：#code#，请及时输入。",5,"1577","23469","JWW"),
    CQ_SMS_CONTENT("您的验证码是#code#,请及时输入",5,"1577","23470","JWW"),
    SMS_SPREADBYUSERID("给您一个平台，试着经营全世界——欢迎注册金窝窝。请凭本手机号码直接登陆，初始密码：#code#，APP下载地址：t.cn/R2IwvgG",5,"1577","23471","JWW"),
    SMS_SENDPASSWORDMSG("恭喜您已成功注册金窝窝。请凭本手机号码直接登录，初始登录密码：#code#",5,"1577","23473","JWW"),
    SMS_TREE_OLD_USER_SENDPASSWORDMSG("验证码：#code#。成长树你更精彩！",5,"1577","23474","JWW"),
    SMS_TREE_NEW_USER_SENDPASSWORDMSG("验证码：#code#。成长树有你更精彩！所有窝币奖励请前往APP使用，登录名为本手机号码，初始登录密码与本次验证码一致，下载地址： t.cn/R2IwvgG",5,"1577","23480","JWW"),
    SMS_BX_EXPIRE("尊敬的#code#，您投保的#code1#将于#code2#到期，请您及时登录金窝窝APP续保。",5,"1577","23496","JWW"),
    SMS_QUESTION_NEW_USER_SENDPASSWORDMSG("验证码：#code#。给您一个平台，试着经营全世界！所获窝币请前往APP使用，登录名为本手机号码，初始登录密码与本次验证码一致，下载地址： t.cn/R2IwvgG",5,"1577","23628","JWW"),
    SMS_QUESTION_OLD_USER_SENDPASSWORDMSG("验证码：#code#。给您一个平台，试着经营全世界！",5,"1577","23629","JWW"),


    //PHP的短信内容
    PHP_SMS_INSHOP("尊敬的窝家人：恭喜您推荐的商家“{{store_name}}”已成功入驻金窝窝商城，感谢您的支持，祝您生活愉快！",21,"1577","23516","JWW"),
    PHP_SMS_RECHARGE("恭喜，您为手机号{{mobile}}{{cz_type}}充值成功，充值面额：{{mobile_type}}{{spec_str}}。",22,"1577","23517","JWW"),
    PHP_SMS_FAIL_RECHARGE("抱歉，您为手机号{{mobile}}{{cz_type}}充值失败，系统繁忙请您稍后再试［详询客服］。",23,"1577","23518","JWW"),
    PHP_SMS_GET_WALLET("哇塞，人品爆发了哟！{{nikeName}}赠送给你一笔消费金，快来查看吧！",24,"1577","23519","JWW"),
    PHP_SMS_GET_EXCHANGE("哇塞，人品爆发了哟！{{nikeName}}赠送给你一笔窝钱包，快来查看吧！",25,"1577","23520","JWW"),
    PHP_SMS_FAIL_DATA("尊敬的管理员，系统暂时无法获取最新的时时彩开奖数据，请您手动解决！",26,"1577","23521","JWW"),
    PHP_SMS_GET_PRIZE("恭喜{{mobile}}，您已成功夺得第{{pno}}期奖品：{{goods_name}}！请到金窝窝APP填写并确认您的收货地址后，奖品7个工作日内发货。",27,"1577","23522","JWW"),
    PHP_SMS_PRIZE_DEL("亲爱的{{mobile}}，您夺宝中奖的{{goods_name}}已由{{express_name}}发出，快递单号为{{shoping_code}}，可在金窝窝APP跟踪物流信息。",28,"1577","23523","JWW"),
    PHP_SMS_TO_UP("当前#orderType#充值预存款还剩#balance#,清注意充值!",29,"1577","23549","JWW"),
    PHP_SMS_FAIL_GET("亲爱的{{mobile}}，您参与的第{{pno}}期奖品{{goods_name}},由于商品疯抢,人次已满导致参与失败！参与失败的{{goods_num}}人次,已全部以窝币形式充值到您的窝币账户中,可用于下次参与夺宝,可登陆APP窝币账户查询详情。",210,"1577","","JWW"),

    LIVE_ANCHOR_START("你的窝现场预计还有15分钟就要开始了，请准备好相关准备",211,"1577","27201","JWW");

    SmsTempEnum (String name, Integer maxCount, String ycTempId, String jhTempId, String mwTempId) {
        this.name = name;//短信模板内容
        this.maxCount=maxCount;//短信限制发送条数
        this.jhTempId=jhTempId;//短信聚合平台id
        this.ycTempId=ycTempId;//短信云测平台 id
        this.mwTempId=mwTempId;//短信梦网平台 id

    }




    private String name;//短信模板内容
    private int maxCount;//短信限制发送条数
    private String ycTempId;//云测平台tempid
    private String jhTempId;//聚合平台tempid
    private String mwTempId;//梦网平台tempid

    public int getMaxCount() {
        return maxCount;
    }
    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getYcTempId() {
        return ycTempId;
    }

    public void setYcTempId(String ycTempId) {
        this.ycTempId = ycTempId;
    }

    public String getJhTempId() {
        return jhTempId;
    }

    public void setJhTempId(String jhTempId) {
        this.jhTempId = jhTempId;
    }

    public String getMwTempId() {
        return mwTempId;
    }

    public void setMwTempId(String mwTempId) {
        this.mwTempId = mwTempId;
    }
}
