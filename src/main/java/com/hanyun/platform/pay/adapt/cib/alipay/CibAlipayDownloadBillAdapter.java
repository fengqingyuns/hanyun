/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.alipay;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayConsts;
import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayMethodType;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayDownloadBillReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayDownloadBillRes;
import com.hanyun.platform.pay.consts.BizResCode;

/**
 * 下载账单
 * @author liyinglong@hanyun.com
 * @date 2017年1月4日 下午8:44:40
 */
@Component
public class CibAlipayDownloadBillAdapter extends CibAlipayBaseAdapter<AlipayDownloadBillReq, AlipayDownloadBillRes> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CibAlipayDownloadBillAdapter.class);
            
    @Override
    protected void doPreProcessRequest(AlipayDownloadBillReq req) {
        
    }

    @Override
    protected void doPostProcessResponse(AlipayDownloadBillReq req, AlipayDownloadBillRes res) {
        
    }
    
    @Override
    protected AlipayDownloadBillRes doParseResponse(String resxmlstr) {
        AlipayDownloadBillRes res = null;
        String tempStr = StringUtils.trim(resxmlstr);
        if(tempStr.startsWith("<xml>")){
            res = super.doParseResponse(resxmlstr);
        }else{
            res = new AlipayDownloadBillRes();
            res.setBilldata(resxmlstr);
        }
        return res;
    }

    @Override
    protected void doCheckReturnCode(AlipayDownloadBillRes result) {
        if (result == null) {
            LOGGER.error("CIB_ALIPAY_ERROR_RESULEMPTY");
            throw BizException.build(BizResCode.CIB_ALIPAY_ERROR_RESULEMPTY);
        }
        
        // 明细数据为空，则存在返回状态，需要检查状态
        if (StringUtils.isNotBlank(result.getReturnCode())) {
            // 如果没有交易记录，则出现账单不存在情况
            if (CibAlipayConsts.RETURN_CODE_SUCCESS.equals(result.getReturnCode())
                    || CibAlipayConsts.RETURN_MSG_NOBILLEXIST.equals(result.getReturnMsg())) {
                LOGGER.info("CIB_ALIPAY RETURN MSG:{}", result.getReturnMsg());
            }else{
                LOGGER.error("CIB_ALIPAY_ERROR_RETURNCODE, errmsg:{}", result.getReturnMsg());
                throw BizException.build(BizResCode.CIB_ALIPAY_ERROR_RETURNCODE);
            }
        }
    }
    
    @Override
    protected void doCheckResultCode(AlipayDownloadBillRes result) {
        // 无业务结果码，不需校验
    }

    @Override
    protected void doCheckSign(AlipayDownloadBillRes result, String secKey) {
        // 没有签名校验
    }

    @Override
    protected String getMethodType() {
        return CibAlipayMethodType.BILL;
    }

}
