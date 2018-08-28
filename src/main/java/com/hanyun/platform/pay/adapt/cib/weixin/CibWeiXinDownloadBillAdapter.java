/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinConsts;
import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinMethodType;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.DownloadBillReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.DownloadBillRes;
import com.hanyun.platform.pay.consts.BizResCode;

/**
 * 下载账单
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月30日 下午5:40:49
 */
@Component
public class CibWeiXinDownloadBillAdapter extends CibWeiXinBaseAdapter<DownloadBillReq, DownloadBillRes> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CibWeiXinDownloadBillAdapter.class);
            
    @Override
    protected void doPreProcessRequest(DownloadBillReq req) {

    }

    @Override
    protected void doPostProcessResponse(DownloadBillReq req, DownloadBillRes res) {

    }

    @Override
    protected DownloadBillRes doParseResponse(String resxmlstr) {
        DownloadBillRes res = null;
        String tempStr = StringUtils.trim(resxmlstr);
        if(tempStr.startsWith("<xml>")){
            res = super.doParseResponse(resxmlstr);
        }else{
            res = new DownloadBillRes();
            res.setBilldata(resxmlstr);
        }
        return res;
    }

    @Override
    protected void doCheckReturnCode(DownloadBillRes result) {
        if (result == null) {
            LOGGER.error("CIBWEIXIN_ERROR_RESULEMPTY");
            throw BizException.build(BizResCode.CIBWEIXIN_ERROR_RESULEMPTY);
        }
        
        // 明细数据为空，则存在返回状态，需要检查状态
        if (StringUtils.isNotBlank(result.getReturn_code())) {
            // 如果没有交易记录，则出现账单不存在情况
            if (CibWeiXinConsts.RETURN_CODE_SUCCESS.equals(result.getReturn_code())
                    || CibWeiXinConsts.RETURN_MSG_NOBILLEXIST.equals(result.getReturn_msg())) {
                LOGGER.info("CIBWEIXIN RETURN MSG:{}", result.getReturn_msg());
            }else{
                LOGGER.error("CIBWEIXIN_ERROR_RETURNCODE, errmsg:{}", result.getReturn_msg());
                throw BizException.build(BizResCode.CIBWEIXIN_ERROR_RETURNCODE);
            }
        }
    }
    
    @Override
    protected void doCheckResultCode(DownloadBillRes result) {
        // 无业务结果码，不需校验
    }

    @Override
    protected void doCheckSign(DownloadBillRes result, String secKey) {
        // 没有签名校验
    }

    @Override
    protected CibWeiXinMethodType getMethodType() {
        return CibWeiXinMethodType.downloadbill;
    }

}
