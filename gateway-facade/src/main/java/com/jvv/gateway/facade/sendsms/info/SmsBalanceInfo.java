package com.jvv.gateway.facade.sendsms.info;

import com.jvv.common.services.info.AbstractInfo;

/**
 * Created by IntelliJ IDEA
 * <p>〈类详细描述〉 </p>
 * 〈功能详细描述〉
 *
 * @author linxm
 * @version 1.0
 * @date 2017/4/5
 * @time 15:30
 */
public class SmsBalanceInfo extends AbstractInfo {

    private int balance;

    public SmsBalanceInfo(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
