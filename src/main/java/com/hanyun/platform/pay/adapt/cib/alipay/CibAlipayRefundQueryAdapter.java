/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayConsts;
import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayMethodType;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayRefundQueryReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayRefundQueryRes;
import com.hanyun.platform.pay.consts.BizResCode;

/**
 * 退款查询
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午8:50:11
 */
@Component
public class CibAlipayRefundQueryAdapter extends CibAlipayBaseAdapter<AlipayRefundQueryReq, AlipayRefundQueryRes> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CibAlipayRefundQueryAdapter.class);

    @Override
    protected void doPreProcessRequest(AlipayRefundQueryReq req) {
        
    }

    @Override
    protected void doPostProcessResponse(AlipayRefundQueryReq req, AlipayRefundQueryRes res) {
        
    }
    
    @Override
    protected void doCheckResultCode(AlipayRefundQueryRes result) {
        super.doCheckResultCode(result);
        if(CibAlipayConsts.REFUND_STATUS_FAIL.equals(result.getRefundStatus())){
            LOGGER.error("CIB_ALIPAY_FAIL_REFUND_STATUS, refund status: {}", result.getRefundStatus());
            throw BizException.build(BizResCode.CIB_ALIPAY_FAIL_REFUND_STATUS);
        }
    }

    @Override
    protected String getMethodType() {
        return CibAlipayMethodType.REFUNDQUERY;
    }

}
