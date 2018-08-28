/**
 * 
 */
package com.hanyun.platform.pay.quartz;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hanyun.ground.util.date.DateCalcUtil;
import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.platform.pay.service.cib.alipay.CibAlipayDownloadBillService;
import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 兴业银行支付宝交易明细下载的作业处理器
 * @author liyinglong@hanyun.com
 * @date 2017年10月25日 下午3:02:11
 */
@JobHander(value="cibAlipayTradeDetailDownloadJobHandler")
@Component
public class CibAlipayTradeDetailDownloadJobHandler extends IJobHandler {
    @Resource
    private CibAlipayDownloadBillService cibAlipayDownloadBillService;
    
    /**
     * 调试中心可传参数：
     * 1、[账单日期,格式 20171024 :非必填，不填则默认取昨日]
     * 2、[品牌编号:非必填，不填则默认取全部]
     */
    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        Date date = null;
        String brandId = null;
        if(params != null){
            if(params.length >= 1){
                date = DateFormatUtil.parseDateNoSep(params[0]);
            }
            
            if(params.length >= 2){
                brandId = params[1];
                if(brandId != null){
                    brandId = brandId.trim();
                }
            }
        }
        if(date == null){
            date = DateCalcUtil.getPreDay(new Date());
        }
        
        TradeDetailCheckResultVo result = cibAlipayDownloadBillService.downloadBill(date, brandId);
        XxlJobLogger.log(JsonUtil.toJson(result));
        
        if(! result.isSuccess()){
            return ReturnT.FAIL;
        }
        
        return ReturnT.SUCCESS;
    }

}
