/**
 * 
 */
package com.hanyun.platform.pay.logic.paytype.mall;

import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.logic.paytype.base.PayTypeDefaultHandler;

/**
 * 商场收银处理类
 * @author liyinglong@hanyun.com
 * @date 2017年9月18日 下午3:02:11
 */
public class PtShoppingMallHandler extends PayTypeDefaultHandler {
    @Override
    public String getPayType() {
        return PaymentConsts.PAYTYPE_SHOPPINGMALL;
    }
}
