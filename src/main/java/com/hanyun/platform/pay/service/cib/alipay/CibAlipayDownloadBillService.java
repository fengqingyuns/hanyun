/**
 * 
 */
package com.hanyun.platform.pay.service.cib.alipay;

import java.util.Date;

import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;

/**
 * 兴业支付宝账单下载服务
 * @author liyinglong@hanyun.com
 * @date 2017年1月5日 下午1:34:51
 */
public interface CibAlipayDownloadBillService {
    /**
     * 下载对账单
     * @param date 账单日期
     * @param brandId 品牌编号
     */
    public TradeDetailCheckResultVo downloadBill(Date date, String brandId);
}
