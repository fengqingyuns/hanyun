package com.hanyun.platform.pay.service.cib.weixin;

import java.util.Date;

import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;

/**
 * @author wangjie@hanyun.com
 * @date 2016年8月31日 下午8:20:21
 */
public interface CibWeixinBillService {
    /**
     * 账单对账
     * @param date 账单日期
     * @param brandId 品牌编号
     */
    public TradeDetailCheckResultVo cibBillFile(Date date, String brandId);

}
