/**
 * 
 */
package com.hanyun.platform.pay.service.umpay;

import java.util.Date;

import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;

/**
 * 联动优势对账文件下载服务
 * @author liyinglong@hanyun.com
 * @date 2016年12月19日 下午8:46:02
 */
public interface UmPayDownloadPayBillService {
    /**
     * 下载对账文件
     * @param date 账单日期
     */
    public TradeDetailCheckResultVo downloadPayBill(Date date);
}
