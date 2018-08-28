/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hanyun.ground.util.date.DateCalcUtil;
import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.ground.util.exception.BizException;
import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinConsts;
import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinMethodType;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.OrderAttachData;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.UnifiedorderReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.UnifiedorderRes;
import com.hanyun.platform.pay.adapt.cib.weixin.util.OrderAttachDataUtil;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.CibWeixinOrderDao;
import com.hanyun.platform.pay.domain.CibMerchantStore;
import com.hanyun.platform.pay.domain.CibWeixinOrder;
import com.hanyun.platform.pay.domain.Payment;

/**
 * 统一支付接口
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月30日 下午5:10:09
 */
@Component
public class CibWeiXinUnifiedorderAdapter extends CibWeiXinBaseAdapter<UnifiedorderReq, UnifiedorderRes> {

    @Resource
    private CibWeixinOrderDao cibWeixinOrderDao;

    @Override
    protected void doPreProcessRequest(UnifiedorderReq req) {
        Payment payment = PayProcessContext.getPayment();
        // 组装接口通用参数
        req.setNotify_url(getCibAdapterConfig().getNotifyWeixinUrl());
        Date now = new Date();
        req.setTime_start(DateFormatUtil.formatDateTimeNoSep(now));
        req.setTime_expire(DateFormatUtil.formatDateTimeNoSep(
                DateCalcUtil.addHours(now, CibWeiXinConsts.ORDER_EXPIRE_HOURS)));
        // 附加数据
        CibMerchantStore mchStore = getCibMerchantStoreDao().getByStoreId(payment.getStoreId());
        if(mchStore == null){
            throw BizException.build(BizResCode.CIB_MERCHANT_STORE_EMPTY);
        }
        OrderAttachData attachData = new OrderAttachData();
        attachData.setStoreAppid(mchStore.getCibStoreAppid());
        attachData.setStoreName(mchStore.getCibStoreName());
        req.setAttach(OrderAttachDataUtil.format(attachData));

    }

    @Override
    protected void doPostProcessResponse(UnifiedorderReq req, UnifiedorderRes res) {
        Payment payment = PayProcessContext.getPayment();

        CibWeixinOrder wxorder = cibWeixinOrderDao.selectByOutTradeNo(req.getOut_trade_no());
        if (wxorder == null) {
            wxorder = new CibWeixinOrder();
            wxorder.setBrandId(payment.getBrandId());
            wxorder.setStoreId(payment.getStoreId());
            wxorder.setOutTradeNo(req.getOut_trade_no());
            wxorder.setVersion(req.getVersion());
            wxorder.setDeviceInfo(req.getDevice_info());
            wxorder.setBody(req.getBody());
            wxorder.setAttach(req.getAttach());
            wxorder.setFeeType(req.getFee_type());
            wxorder.setTotalFee(req.getTotal_fee());
            wxorder.setSpbillCreateIp(req.getSpbill_create_ip());
            wxorder.setTimeStart(req.getTime_start());
            wxorder.setTimeExpire(req.getTime_expire());
            wxorder.setTradeType(req.getTrade_type());
            wxorder.setOpenid(req.getOpenid());
            wxorder.setProductId(req.getProduct_id());
            wxorder.setLimitPay(req.getLimit_pay());

            doFillCibWeixinOrderWithRes(wxorder, res);

            cibWeixinOrderDao.insert(wxorder);
        } else {
            wxorder.setTradeType(req.getTrade_type());
            wxorder.setDeviceInfo(req.getDevice_info());
            wxorder.setTotalFee(req.getTotal_fee());
            wxorder.setSpbillCreateIp(req.getSpbill_create_ip());
            wxorder.setTimeStart(req.getTime_start());
            wxorder.setTimeExpire(req.getTime_expire());
            wxorder.setOpenid(req.getOpenid());
            wxorder.setProductId(req.getProduct_id());
            wxorder.setLimitPay(req.getLimit_pay());
            
            doFillCibWeixinOrderWithRes(wxorder, res);

            cibWeixinOrderDao.updateByOutTradeNo(wxorder);
        }

    }

    /**
     * 根据响应结果填充
     * 
     * @param wxorder
     * @param res
     */
    private void doFillCibWeixinOrderWithRes(CibWeixinOrder wxorder, UnifiedorderRes res) {
        wxorder.setPrepayId(res.getPrepay_id());
        wxorder.setCodeUrl(res.getCode_url());
        wxorder.setJsapiAppid(res.getJsapi_appid());
        wxorder.setJsapiNoncestr(res.getJsapi_noncestr());
        wxorder.setJsapiPackage(res.getJsapi_package());
        wxorder.setJsapiPaysign(res.getJsapi_paysign());
        wxorder.setJsapiSigntype(res.getJsapi_signtype());
        wxorder.setJsapiTimestamp(res.getJsapi_timestamp());
    }

    @Override
    protected CibWeiXinMethodType getMethodType() {
        return CibWeiXinMethodType.unifiedorder;
    }

}
