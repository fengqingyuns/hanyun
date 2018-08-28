/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hanyun.ground.util.date.DateCalcUtil;
import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.ground.util.exception.BizException;
import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayConsts;
import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayMethodType;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayNativeReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayNativeRes;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.CibAlipayOrderDao;
import com.hanyun.platform.pay.domain.CibAlipayOrder;
import com.hanyun.platform.pay.domain.CibMerchantStore;
import com.hanyun.platform.pay.domain.Payment;

/**
 * 用户扫码适配器
 * 
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午8:09:33
 */
@Component
public class CibAlipayNativeAdapter extends CibAlipayBaseAdapter<AlipayNativeReq, AlipayNativeRes> {
    @Resource
    private CibAlipayOrderDao cibAlipayOrderDao;

    @Override
    protected void doPreProcessRequest(AlipayNativeReq req) {
        Payment payment = PayProcessContext.getPayment();
        // 组装接口通用参数
        req.setNotifyUrl(getCibAlipayAdapterConfig().getNotifyAlipayUrl());
        Date now = new Date();
        req.setTimeStart(DateFormatUtil.formatDateTimeNoSep(now));
        req.setTimeExpire(DateFormatUtil.formatDateTimeNoSep(
                DateCalcUtil.addHours(now, CibAlipayConsts.ORDER_EXPIRE_HOURS)));

        CibMerchantStore mchStore = getCibMerchantStoreDao().getByStoreId(payment.getStoreId());
        if(mchStore == null){
            throw BizException.build(BizResCode.CIB_MERCHANT_STORE_EMPTY);
        }
        req.setStoreAppid(mchStore.getCibStoreAppid());
    }

    @Override
    protected void doPostProcessResponse(AlipayNativeReq req, AlipayNativeRes res) {
        Payment payment = PayProcessContext.getPayment();

        CibAlipayOrder aliorder = cibAlipayOrderDao.selectByOutTradeNo(req.getOutTradeNo());
        if (aliorder == null) {
            aliorder = new CibAlipayOrder();
            aliorder.setBrandId(payment.getBrandId());
            aliorder.setStoreId(payment.getStoreId());
            aliorder.setOutTradeNo(req.getOutTradeNo());
            aliorder.setDeviceInfo(req.getDeviceInfo());
            aliorder.setVersion(req.getVersion());
            aliorder.setBody(req.getBody());
            aliorder.setAttach(req.getAttach());
            aliorder.setFeeType(req.getFeeType());
            aliorder.setTotalFee(req.getTotalFee());
            aliorder.setTimeStart(req.getTimeStart());
            aliorder.setTimeExpire(req.getTimeExpire());
            aliorder.setTradeType(CibAlipayConsts.TRADE_TYPE_NATIVE);

            aliorder.setCodeUrl(res.getCodeUrl());

            cibAlipayOrderDao.insert(aliorder);
        } else {
            aliorder.setTotalFee(req.getTotalFee());
            aliorder.setTimeStart(req.getTimeStart());
            aliorder.setTimeExpire(req.getTimeExpire());
            aliorder.setDeviceInfo(req.getDeviceInfo());
            
            aliorder.setCodeUrl(res.getCodeUrl());

            cibAlipayOrderDao.updateByOutTradeNo(aliorder);
        }

    }

    @Override
    protected String getMethodType() {
        return CibAlipayMethodType.NATIVE;
    }

}
