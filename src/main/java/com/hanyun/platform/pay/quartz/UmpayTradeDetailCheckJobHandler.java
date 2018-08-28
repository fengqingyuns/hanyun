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
import com.hanyun.platform.pay.service.umpay.UmpayBillService;
import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 联动优势pos交易明细对账的作业处理器
 * @author liyinglong@hanyun.com
 * @date 2017年10月25日 下午3:11:52
 */
@JobHander(value="umpayTradeDetailCheckJobHandler")
@Component
public class UmpayTradeDetailCheckJobHandler extends IJobHandler {
    @Resource
    private UmpayBillService umpayBillService;
    
    /**
     * 调试中心可传参数：
     * 1、[账单日期,格式 20171024 :非必填，不填则默认取昨日]
     */
    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        Date date = null;
        if(params != null){
            if(params.length >= 1){
                date = DateFormatUtil.parseDateNoSep(params[0]);
            }
        }
        if(date == null){
            date = DateCalcUtil.getPreDay(new Date());
        }
        
        TradeDetailCheckResultVo result = umpayBillService.umpayBillFile(date);
        XxlJobLogger.log(JsonUtil.toJson(result));
        
        if(! result.isSuccess()){
            return ReturnT.FAIL;
        }
        
        return ReturnT.SUCCESS;
    }

}
