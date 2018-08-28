/**
 * 
 */
package com.hanyun.platform.pay.logic.paytype.cash;

import org.springframework.stereotype.Component;

import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.logic.paytype.base.PayTypeDefaultHandler;

/**
 * 现金支付处理类
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年7月22日 下午12:01:45
 */
@Component
public class PtCashHandler extends PayTypeDefaultHandler {
    @Override
    public String getPayType() {
        return PaymentConsts.PAYTYPE_CASH;
    }

}
