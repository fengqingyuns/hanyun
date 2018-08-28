/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinConsts;
import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinMethodType;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.MicroPayReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.MicroPayRes;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.dao.CibWeixinOrderDao;
import com.hanyun.platform.pay.domain.CibWeixinOrder;

/**
 * 刷卡支付
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月30日 下午5:33:26
 */
@Component
public class CibWeiXinMicroPayAdapter extends CibWeiXinBaseAdapter<MicroPayReq, MicroPayRes> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CibWeiXinMicroPayAdapter.class);
            
    @Resource
    private CibWeixinOrderDao cibWeixinOrderDao;
    
    @Override
    protected void doPreProcessRequest(MicroPayReq req) {
        
    }

    @Override
    protected void doPostProcessResponse(MicroPayReq req, MicroPayRes res) {
        CibWeixinOrder wxorder = cibWeixinOrderDao.selectByOutTradeNo(req.getOut_trade_no());
        
        wxorder.setVersion(res.getVersion());
        wxorder.setDeviceInfo(res.getDevice_info());
        wxorder.setTransactionId(res.getTransaction_id());
        wxorder.setPassTradeNo(res.getPass_trade_no());
        wxorder.setTradeType(res.getTrade_type());
        wxorder.setOpenid(res.getOpenid());
        wxorder.setIsSubscribe(res.getIs_subscribe());
        wxorder.setBankType(res.getBank_type());
        wxorder.setTimeEnd(res.getTime_end());
        
        cibWeixinOrderDao.updateByOutTradeNo(wxorder);
    }
    
    @Override
    protected void doCheckResultCode(MicroPayRes result) {
        if (result == null || 
                (!CibWeiXinConsts.RESULT_CODE_SUCCESS.equals(result.getResult_code())
                && !CibWeiXinConsts.MICROPAY_ERRORCODE_NEEDQUERY_SET.contains(result.getErr_code()))) {
            LOGGER.error("CIBWEIXIN_ERROR_RESULTCODE, errcode:{}, errmsg:{}", 
                    result.getErr_code(), result.getErr_code_des());
            throw BizException.build(BizResCode.CIBWEIXIN_ERROR_RESULTCODE);
        }
    }

    @Override
    protected CibWeiXinMethodType getMethodType() {
        return CibWeiXinMethodType.micropay;
    }

}
