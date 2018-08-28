/**
 * 
 */
package com.hanyun.platform.pay.logic.paytype.aliusc;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.hanyun.ground.util.date.DateCalcUtil;
import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.platform.pay.adapt.cib.alipay.CibAlipayNativeAdapter;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayNativeReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayNativeRes;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.CibAlipayOrderDao;
import com.hanyun.platform.pay.domain.CibAlipayOrder;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.logic.paytype.base.PtCibAliBaseHandler;
import com.hanyun.platform.pay.vo.trade.TradePreCreateAlipayExtData;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;

/**
 * 兴业银行支付宝用户扫码处理
 * 
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午8:57:29
 */
@Component
public class PtCibAliuscHandler extends PtCibAliBaseHandler {
    @Resource
    private CibAlipayOrderDao cibAlipayOrderDao;
    @Resource
    private CibAlipayNativeAdapter cibAlipayNativeAdapter;

    @Override
    public String getPayType() {
        return PaymentConsts.PAYTYPE_ALIPAY_USC;
    }

    @Override
    public Object doPreCreate(TradePreCreateReq req) {
        Payment payment = PayProcessContext.getPayment();
        PayTransaction trans = PayProcessContext.getTransation();
        TradePreCreateAlipayExtData extData = new TradePreCreateAlipayExtData();
        // 检测已存在的
        CibAlipayOrder aliorder = cibAlipayOrderDao.selectByOutTradeNo(trans.getTransId());
        if (aliorder != null && StringUtils.isNotBlank(aliorder.getCodeUrl())
                && StringUtils.isNotBlank(aliorder.getTimeExpire())) {
            Date expire = DateFormatUtil.parseDateTimeNoSep(aliorder.getTimeExpire());
            // 过期前2分钟之前，不需要请求接口
            Date preExpire = DateCalcUtil.addMinutes(expire, -2);
            if (preExpire.after(new Date())) {
                extData.setCodeUrl(aliorder.getCodeUrl());
                return extData;
            }
        }
        // 组装业务参数
        AlipayNativeReq mthreq = new AlipayNativeReq();
        mthreq.setOutTradeNo(trans.getTransId());
        mthreq.setBody(req.getOrderDesc());
        mthreq.setTotalFee(req.getCurPayAmount());
        mthreq.setDeviceInfo(req.getTerminalDeviceNo());

        AlipayNativeRes mthres = cibAlipayNativeAdapter.request(mthreq);
        extData.setCodeUrl(mthres.getCodeUrl());

        return extData;
    }
}
