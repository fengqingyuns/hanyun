/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayConsts;
import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayMethodType;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayMicroPayReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayMicroPayRes;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.CibAlipayOrderDao;
import com.hanyun.platform.pay.domain.CibAlipayOrder;
import com.hanyun.platform.pay.domain.CibMerchantStore;
import com.hanyun.platform.pay.domain.Payment;

/**
 * 兴业支付宝商户扫码适配器
 * @author liyinglong@hanyun.com
 * @date 2017年1月3日 下午7:02:02
 */
@Component
public class CibAlipayMicroPayAdapter extends CibAlipayBaseAdapter<AlipayMicroPayReq, AlipayMicroPayRes> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CibAlipayMicroPayAdapter.class);
            
    @Resource
    private CibAlipayOrderDao cibAlipayOrderDao;
    
    @Override
    protected void doPreProcessRequest(AlipayMicroPayReq req) {
        Payment payment = PayProcessContext.getPayment();
        
        req.setScene(CibAlipayConsts.PAY_SCENE_BAR_CODE);
        
        CibMerchantStore mchStore = getCibMerchantStoreDao().getByStoreId(payment.getStoreId());
        if(mchStore == null){
            throw BizException.build(BizResCode.CIB_MERCHANT_STORE_EMPTY);
        }
        req.setStoreAppid(mchStore.getCibStoreAppid());
    }

    @Override
    protected void doPostProcessResponse(AlipayMicroPayReq req, AlipayMicroPayRes res) {
        CibAlipayOrder aliorder = new CibAlipayOrder();
        aliorder.setOutTradeNo(res.getOutTradeNo());
        aliorder.setTransactionId(res.getTransactionId());
        aliorder.setPassTradeNo(res.getPassTradeNo());
        aliorder.setOpenid(res.getOpenid());
        aliorder.setTimeEnd(res.getTimeEnd());
        aliorder.setBuyerLogonId(res.getBuyerLogonId());
        aliorder.setFundBillList(res.getFundBillList());
        
        cibAlipayOrderDao.updateByOutTradeNo(aliorder);
    }
    
    @Override
    protected void doCheckResultCode(AlipayMicroPayRes result) {
        if (result == null || CibAlipayConsts.RESULT_CODE_FAIL.equals(result.getResultCode())) {
            LOGGER.error("CIB_ALIPAY_ERROR_RESULTCODE, errcode:{}, errmsg:{}", 
                    result.getErrCode(), result.getErrCodeDes());
            throw BizException.build(BizResCode.CIB_ALIPAY_ERROR_RESULTCODE);
        }
    }

    @Override
    protected String getMethodType() {
        return CibAlipayMethodType.MICROPAY;
    }

}
