package com.hanyun.platform.pay.service.umpay;

import java.util.Date;

import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;

/**
 * @author wangjie@hanyun.com
 * @date 2016年12月18日 下午16:20:21
 */
public interface UmpayBillService {
    /**
     * 账单对账
     * @param date 账单日期
     */
    public TradeDetailCheckResultVo umpayBillFile(Date date);

}
