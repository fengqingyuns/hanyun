package com.hanyun.platform.pay.service.cib.alipay;

import java.util.Date;

import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;

/**
 * 兴业支付宝账单对账服务
 * @author wangjie@hanyun.com
 * @date 2017年01月05日 下午8:20:21
 */
public interface CibAlipayBillService {
    /**
     * 兴业支付宝账单对账
     * @param date 账单日期
     * @param brandId 品牌编号
     */
    public TradeDetailCheckResultVo cibBillFile(Date date, String brandId);

}
