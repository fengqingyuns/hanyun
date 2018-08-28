package com.hanyun.platform.pay.logic.paytype.yeepay;

import com.hanyun.platform.pay.adapt.yeepay.YeepayQueryAdapter;
import com.hanyun.platform.pay.adapt.yeepay.model.YeePayQueryReq;
import com.hanyun.platform.pay.adapt.yeepay.model.YeeQueryOrderRes;
import com.hanyun.platform.pay.adapt.yeepay.util.CommonUtil;
import com.hanyun.platform.pay.adapt.yeepay.util.UsualTools;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.context.YeepayChildChannelRegGlobal;
import com.hanyun.platform.pay.vo.trade.TradePayReq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.logic.paytype.yeepay
 * @Author: dewen.li
 * @Date: 2018-08-14 上午9:30
 */

@Component
public class YeePayQueryHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(YeePayQueryHandler.class);
    @Autowired
    private YeepayQueryAdapter yeepayQueryAdapter;

    /***
     *
     * @param request  支付单查询入参
     * @param requestNo  支付请求ID
     * @return
     * @throws IOException
     */
    public YeeQueryOrderRes doFindByRequestNo(TradePayReq request, String requestNo) throws IOException {
        YeePayQueryReq yeePayQueryReq = new YeePayQueryReq();
        yeePayQueryReq.setBrandId(request.getBrandId());
        yeePayQueryReq.setPayTool(request.getPayType());
        yeePayQueryReq.setStoreId(request.getStoreId());
        yeePayQueryReq.setRequestNo(requestNo);
        yeePayQueryReq.setRequestNo(PayProcessContext.getTransationNew().getTransId());
        yeePayQueryReq.setOrderAmount(UsualTools.tranceLongToString(request.getOrderAmount(),YeepayChildChannelRegGlobal.TRANCE_AMOUNT));
        yeePayQueryReq.setFundAmount(UsualTools.tranceLongToString(request.getOrderAmount(),YeepayChildChannelRegGlobal.TRANCE_AMOUNT));
        yeePayQueryReq.setProductCatalog(YeepayChildChannelRegGlobal.PAY_MCC);
        yeePayQueryReq.setMcc(YeepayChildChannelRegGlobal.PAY_MCC);
        yeePayQueryReq.setMerchantOrderDate(CommonUtil.formatDateTime(request.getOrderTime()));
        LOGGER.info("--------------------->yeePayQueryReq:"+yeePayQueryReq.getOrderAmount());
        return yeepayQueryAdapter.findPaymentByRequestNo(yeePayQueryReq);
    }

}
