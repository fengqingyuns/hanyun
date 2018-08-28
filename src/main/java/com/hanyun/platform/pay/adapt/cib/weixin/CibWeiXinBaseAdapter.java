/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.ground.util.http.HttpClient;
import com.hanyun.ground.util.http.HttpClientPost;
import com.hanyun.ground.util.http.HttpClientResponse;
import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.ground.util.xml.XmlUtils;
import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinConsts;
import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinMethodType;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.BaseRequest;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.BaseResponse;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.CibMerchantDao;
import com.hanyun.platform.pay.dao.CibMerchantStoreDao;
import com.hanyun.platform.pay.domain.CibMerchant;
import com.hanyun.platform.pay.util.RandomStrUtils;
import com.hanyun.platform.pay.util.SignatureUtils;

/**
 * 兴业微信接口适配器基础类
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月29日 下午5:09:19
 */
public abstract class CibWeiXinBaseAdapter<Q extends BaseRequest, S extends BaseResponse> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CibWeiXinBaseAdapter.class);
    /** 连接超时时间，毫秒 */
    protected static final int HTTP_CONN_TIME_OUT = 10000;
    /** 读取超时时间，毫秒 */
    protected static final int HTTP_SOCKET_TIME_OUT = 10000;

    @Resource
    private CibAdapterConfig cibAdapterConfig;
    @Resource
    private CibMerchantDao cibMerchantDao;
    @Resource
    private CibMerchantStoreDao cibMerchantStoreDao;

    /**
     * 接口请求
     * 
     * @param req
     * @return
     * @throws BizException
     */
    public S request(Q req) throws BizException {
        String brandId = PayProcessContext.getBrandId();
        S result = null;
        try {
            CibMerchant cibmch = doGetCibMerchant(brandId);
            doBuildRequest(req, cibmch);
            LOGGER.info("method:{}, reqjson:{}", getMethodType(), JsonUtil.toJson(req));

            doPreProcessRequest(req);

            doSignRequest(req, cibmch.getCibSecKey());

            String reqxmlstr = doFormatReqestBody(req);
            LOGGER.info("method:{}, reqxml:{}", getMethodType(), reqxmlstr);

            HttpClientResponse hcres = doRequest(reqxmlstr);
            doCheckHttpStatus(hcres);

            String resxmlstr = hcres.getBody();
            LOGGER.info("method:{}, resxml:{}", getMethodType(), resxmlstr);

            result = doParseResponse(resxmlstr);
            doCheckReturnCode(result);
            doCheckSign(result, cibmch.getCibSecKey());
            doCheckResultCode(result);

            doPostProcessResponse(req, result);

            LOGGER.info("method:{}, resjson:{}", getMethodType(), JsonUtil.toJson(result));
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            LOGGER.error("CIBWEIXIN_ERROR", e);
            throw BizException.build(BizResCode.CIBWEIXIN_ERROR);
        }

        return result;
    }

    /**
     * 请求信息前置处理，子类实现
     * 
     * @param req
     */
    protected abstract void doPreProcessRequest(Q req);

    /**
     * 响应结果后置处理，子类实现
     * 
     * @param res
     */
    protected abstract void doPostProcessResponse(Q req, S res);

    /**
     * 获取方法类型
     * 
     * @return
     */
    protected abstract CibWeiXinMethodType getMethodType();

    /**
     * 获取兴业商户信息
     * 
     * @param brandId
     * @return
     */
    protected CibMerchant doGetCibMerchant(String brandId) {
        CibMerchant cibmch = null;
        try {
            cibmch = cibMerchantDao.getByBrandId(brandId);
        } catch (Exception e) {
            LOGGER.error("CIBWEIXIN_ERROR_MERCHANT, brandId:" + brandId, e);
            throw BizException.build(BizResCode.CIBWEIXIN_ERROR_MERCHANT);
        }
        if(cibmch == null){
            throw BizException.build(BizResCode.CIB_MERCHANT_EMPTY);
        }

        return cibmch;
    }

    /**
     * 构建填充请求信息
     * 
     * @param req
     * @param cibmch
     */
    protected void doBuildRequest(Q req, CibMerchant cibmch) {
        req.setVersion(cibAdapterConfig.getVersion());
        req.setAppid(cibmch.getCibAppId());
        req.setMch_id(cibmch.getCibMchId());
        req.setNonce_str(RandomStrUtils.genNonceStr());
    }

    /**
     * 签名请求信息
     * 
     * @param req
     * @param secKey
     */
    protected void doSignRequest(Q req, String secKey) {
        String sign = SignatureUtils.sign(req, secKey);
        req.setSign(sign);
    }

    /**
     * 格式化请求信息体
     * 
     * @param req
     * @param cibmch
     * @return
     */
    protected String doFormatReqestBody(Q req) {
        String reqxmlstr = null;
        try {
            reqxmlstr = XmlUtils.marshal(req);
        } catch (Exception e) {
            LOGGER.error("CIBWEIXIN_ERROR_PARAM", e);
            throw BizException.build(BizResCode.CIBWEIXIN_ERROR_PARAM);
        }
        return reqxmlstr;
    }

    /**
     * 执行请求
     * 
     * @param reqxmlstr
     * @return
     */
    protected HttpClientResponse doRequest(String reqxmlstr) {
        HttpClientResponse hcres = null;
        try {
            String url = cibAdapterConfig.getMethodUrl(getMethodType());
            LOGGER.info("request url:{}", url);

            HttpClientPost hcpost = HttpClient.post(url);
            hcpost.timeout(HTTP_CONN_TIME_OUT, HTTP_SOCKET_TIME_OUT);
            hcpost.xml(reqxmlstr);
            hcres = hcpost.action();
        } catch (Exception e) {
            LOGGER.error("CIBWEIXIN_ERROR_REQ", e);
            throw BizException.build(BizResCode.CIBWEIXIN_ERROR_REQ);
        }
        return hcres;
    }

    /**
     * 检查请求状态
     * 
     * @param hcres
     */
    protected void doCheckHttpStatus(HttpClientResponse hcres) {
        if (hcres == null || hcres.status() != HttpStatus.SC_OK) {
            LOGGER.error("CIBWEIXIN_ERROR_STATUS");
            throw BizException.build(BizResCode.CIBWEIXIN_ERROR_STATUS);
        }
    }

    /**
     * 解析响应结果
     * 
     * @param resxmlstr
     * @return
     */
    protected S doParseResponse(String resxmlstr) {
        S result = null;
        try {
            result = XmlUtils.unmarshal(getResClazz(), resxmlstr);
        } catch (Exception e) {
            LOGGER.error("CIBWEIXIN_ERROR_PARSE", e);
            throw BizException.build(BizResCode.CIBWEIXIN_ERROR_PARSE);
        }
        return result;
    }

    /**
     * 检查返回码
     * 
     * @param result
     */
    protected void doCheckReturnCode(S result) {
        if (result == null) {
            LOGGER.error("CIBWEIXIN_ERROR_RESULEMPTY");
            throw BizException.build(BizResCode.CIBWEIXIN_ERROR_RESULEMPTY);
        }
        
        if (!CibWeiXinConsts.RETURN_CODE_SUCCESS.equals(result.getReturn_code())) {
            LOGGER.error("CIBWEIXIN_ERROR_RETURNCODE, errmsg:{}", result.getReturn_msg());
            if(CibWeiXinConsts.RETURN_MSG_REFUND_NO_MONEY.equals(result.getReturn_msg())){
                throw BizException.build(BizResCode.REFUND_NOT_ALLOW_NO_MONEY);
            }else{
                throw BizException.build(BizResCode.CIBWEIXIN_ERROR_RETURNCODE);
            }
        }
    }

    /**
     * 检查业务结果码
     * 
     * @param result
     */
    protected void doCheckResultCode(S result) {
        if (result == null || !CibWeiXinConsts.RESULT_CODE_SUCCESS.equals(result.getResult_code())) {
            LOGGER.error("CIBWEIXIN_ERROR_RESULTCODE, errcode:{}, errmsg:{}", 
                    result.getErr_code(), result.getErr_code_des());
            throw BizException.build(BizResCode.CIBWEIXIN_ERROR_RESULTCODE);
        }
    }

    /**
     * 检查签名
     * 
     * @param result
     * @param secKey
     */
    protected void doCheckSign(S result, String secKey) {
        if (!SignatureUtils.check(result, secKey, result.getSign())) {
            LOGGER.warn("CIBWEIXIN_ERROR_SIGN");
            // 暂不严格校验返回值
            // LOGGER.error("CIBWEIXIN_ERROR_SIGN");
            // throw BizException.build(BizResCode.CIBWEIXIN_ERROR_SIGN);
        }
    }

    /**
     * 获取响应结果类class
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    protected Class<S> getResClazz() {
        Class<S> clazz = (Class<S>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return clazz;
    }

    public CibAdapterConfig getCibAdapterConfig() {
        return cibAdapterConfig;
    }

    public CibMerchantDao getCibMerchantDao() {
        return cibMerchantDao;
    }

    public CibMerchantStoreDao getCibMerchantStoreDao() {
        return cibMerchantStoreDao;
    }
}
