/**
 * 
 */
package com.hanyun.platform.pay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.dao.PayMchModeDao;
import com.hanyun.platform.pay.dao.PayModeDao;
import com.hanyun.platform.pay.domain.MchActualPayModeInfo;
import com.hanyun.platform.pay.domain.PayMchMode;
import com.hanyun.platform.pay.service.PayTradeMchInfoService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.vo.paymode.PayMchModeReq;
import com.hanyun.platform.pay.vo.paymode.PayModeRes;
import com.hanyun.platform.pay.vo.trade.TradeMchPayModeReq;
import com.hanyun.platform.pay.vo.trade.TradeMchPayModeRes;

/**
 * 支付面向外部的服务
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年7月18日 下午8:17:24
 */
@Service
public class PayTradeMchInfoServiceImpl implements PayTradeMchInfoService {
    private static Logger LOGGER = LoggerFactory.getLogger(PayTradeMchInfoServiceImpl.class);

    @Resource
    private PayMchModeDao payMchModeDao;
    @Resource
    private PayModeDao payModeDao;

    @Override
    public HttpResponse<TradeMchPayModeRes> getTradeMchPayMode(TradeMchPayModeReq req) throws Exception {
        LOGGER.info("getTradeMchPayMode, param: {}", JsonUtil.toJson(req));
        if (req == null || StringUtils.isBlank(req.getBrandId())) {
            return BizResUtil.fail(BizResCode.PARAMERROR);
        }
        TradeMchPayModeRes data = new TradeMchPayModeRes();
        List<MchActualPayModeInfo> payModeLst = payMchModeDao.selectMchAllOnlinePayType(req.getBrandId());
        if (payModeLst != null && !payModeLst.isEmpty()) {
            for (MchActualPayModeInfo tmpinfo : payModeLst) {
                data.addPayType(tmpinfo.getTypeCategory(), tmpinfo.getPayType());
            }
        }

        LOGGER.info("getTradeMchPayMode success");
        return BizResUtil.succ(data);
    }

    @Override
    public HttpResponse<Map<String, PayMchMode>> getTradeMchFeeRate(String brandId) throws Exception {
        LOGGER.info("getTradeMchFeeRate, param: {}", brandId);
        if (StringUtils.isBlank(brandId)) {
            return BizResUtil.fail(BizResCode.PARAMERROR);
        }
        Map<String, PayMchMode> datamap = new HashMap<>();
        // 查询默认支付方式费率
        List<PayModeRes> modelst = payModeDao.selectModList();
        // 查询商户支付方式费率，并转为map结构
        PayMchModeReq payMchModeReq = new PayMchModeReq();
        payMchModeReq.setBrandId(brandId);
        List<PayMchMode> mchmodelst = payMchModeDao.getPayMchModeByBrandId(payMchModeReq);
        Map<String, PayMchMode> mchmodemap = new HashMap<>();
        for (PayMchMode mchmodetmp : mchmodelst) {
            mchmodemap.put(mchmodetmp.getPayType(), mchmodetmp);
        }
        // 遍历处理支付方式
        for (PayModeRes modetmp : modelst) {
            PayMchMode mchmode = mchmodemap.get(modetmp.getPayType());

            Integer mchFeeMax = (mchmode == null || mchmode.getMchFeeMax() == null) ? modetmp.getMchFeeMaxDef()
                    : mchmode.getMchFeeMax();
            Integer mchFeeRate = (mchmode == null || mchmode.getMchFeeRate() == null) ? modetmp.getMchFeeRateDef()
                    : mchmode.getMchFeeRate();

            PayMchMode mchmoderst = new PayMchMode();
            mchmoderst.setMchFeeRate(mchFeeRate);
            mchmoderst.setMchFeeMax(mchFeeMax);
            datamap.put(modetmp.getPayType(), mchmoderst);
            // 添加大分类，默认取最后一个分类下的支付方式
            datamap.put(modetmp.getTypeCategory(), mchmoderst);
        }

        LOGGER.info("getTradeMchFeeRate success");
        return BizResUtil.succ(datamap);
    }
}
