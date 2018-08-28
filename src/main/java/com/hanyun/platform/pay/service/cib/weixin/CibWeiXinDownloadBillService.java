/**
 * 
 */
package com.hanyun.platform.pay.service.cib.weixin;

import java.util.Date;

import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;

/**
 * 兴业微信账章下载服务
 * @author liyinglong@hanyun.com
 * @date 2016年9月11日 下午12:42:58
 */
public interface CibWeiXinDownloadBillService {
    
    /**
     * 下载对账单
     * @param date 账单日期
     * @param brandId 品牌编号
     */
    public TradeDetailCheckResultVo downloadBill(Date date, String brandId);
}
