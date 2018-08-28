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
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayRefundReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayRefundRes;
import com.hanyun.platform.pay.consts.BizResCode;

/**
 * 申请退款
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午8:49:50
 */
@Component
public class CibAlipayRefundAdapter extends CibAlipayBaseAdapter<AlipayRefundReq, AlipayRefundRes> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CibAlipayRefundAdapter.class);
    
    @Override
    protected void doPreProcessRequest(AlipayRefundReq req) {
        req.setOpUserId(req.getMchId());
    }

    @Override
    protected void doPostProcessResponse(AlipayRefundReq req, AlipayRefundRes res) {
        
    }
    
    @Override
    public AlipayRefundRes request(AlipayRefundReq req) throws BizException {
        AlipayRefundRes refres = null;
        try{
            refres = super.request(req);
        }catch (BizException bize) {
            if(BizResCode.REFUND_NOT_ALLOW_NO_MONEY.equals(bize.getErrorMsg())){
                throw bize;
            }
            refres = null;
        }catch (Exception e) {
            refres = null;
        }
        return refres;
    }
    
    /**
     * 检查业务结果码
     * 
     * @param result
     */
    protected void doCheckResultCode(AlipayRefundRes result) {
        if (!CibAlipayConsts.RESULT_CODE_SUCCESS.equals(result.getResultCode()) 
                && !CibAlipayConsts.ERRCODE_TRADE_FINISHED.equals(result.getErrCode())) {
            LOGGER.error("CIB_ALIPAY_ERROR_RESULTCODE, errcode:{}, errmsg:{}", 
                    result.getErrCode(), result.getErrCodeDes());
            if(CibAlipayConsts.ERRCODE_REFUND_NOT_ALLOW.equals(result.getErrCode())
                    || CibAlipayConsts.ERRCODE_SELLER_BALANCE_NOT_ENOUGH.equals(result.getErrCode())){
                throw BizException.build(BizResCode.REFUND_NOT_ALLOW_NO_MONEY);
            }else{
                throw BizException.build(BizResCode.CIB_ALIPAY_ERROR_RESULTCODE);
            }
        }
    }

    @Override
    protected String getMethodType() {
        return CibAlipayMethodType.REFUND;
    }

}
