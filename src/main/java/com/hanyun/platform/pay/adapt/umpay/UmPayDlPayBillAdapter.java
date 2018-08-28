/**
 * 
 */
package com.hanyun.platform.pay.adapt.umpay;

import javax.annotation.Resource;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.ground.util.http.HttpClient;
import com.hanyun.ground.util.http.HttpClientPost;
import com.hanyun.ground.util.http.HttpClientResponse;
import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.ground.util.xml.XmlUtils;
import com.hanyun.platform.pay.adapt.umpay.consts.UmPayConsts;
import com.hanyun.platform.pay.adapt.umpay.protocol.UmDataField;
import com.hanyun.platform.pay.adapt.umpay.protocol.UmDlPayBillReq;
import com.hanyun.platform.pay.adapt.umpay.protocol.UmDlPayBillRes;
import com.hanyun.platform.pay.adapt.umpay.util.UmPaySignEncryptUtils;
import com.hanyun.platform.pay.consts.BizResCode;

/**
 * 联动优势对账文件下载接口适配器
 * @author liyinglong@hanyun.com
 * @date 2016年12月19日 下午7:50:22
 */
@Component
public class UmPayDlPayBillAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmPayDlPayBillAdapter.class);
    /** 连接超时时间，毫秒 */
    protected static final int HTTP_CONN_TIME_OUT = 10000;
    /** 读取超时时间，毫秒 */
    protected static final int HTTP_SOCKET_TIME_OUT = 20000;
    
    @Resource
    private UmPayConfig umPayConfig;
    
    /**
     * 请求数据
     * @param req
     * @return
     */
    public UmDlPayBillRes request(UmDlPayBillReq req){
        UmDlPayBillRes result = null;
        try {
            doBuildRequest(req);
            LOGGER.info("method:UmPayDlPayBill, reqjson:{}", JsonUtil.toJson(req));

            doSignRequest(req);

            String reqxmlstr = doFormatReqestBody(req);
            LOGGER.info("method:UmPayDlPayBill, reqxml:{}", reqxmlstr);

            HttpClientResponse hcres = doRequest(reqxmlstr);
            doCheckHttpStatus(hcres);

            String resxmlstr = hcres.getBody();
            LOGGER.info("method:UmPayDlPayBill, resxml:{}", resxmlstr);

            result = doParseResponse(resxmlstr);
            doCheckResponseCode(result);

            LOGGER.info("method:UmPayDlPayBill, resjson:{}", JsonUtil.toJson(result));
        } catch (BizException bize) {
            throw bize;
        } catch (Exception e) {
            LOGGER.error("CIBWEIXIN_ERROR", e);
            throw BizException.build(BizResCode.CIBWEIXIN_ERROR);
        }

        return result;
    }
    
    /**
     * 构建填充请求信息
     * 
     * @param req
     * @param cibmch
     */
    protected void doBuildRequest(UmDlPayBillReq req) {
        req.setMessageType(UmDataField.valueOf(UmPayConsts.MESSAGE_TYPE_DL_PAYBILL));
        req.setProcessCode(UmDataField.valueOf(UmPayConsts.PROCESS_CODE_DL_PAYBILL));
    }

    /**
     * 签名请求信息
     * 
     * @param req
     * @param secKey
     */
    protected void doSignRequest(UmDlPayBillReq req) {
        String origin = doGenSignOriginText(req);
        String sign = UmPaySignEncryptUtils.sign(origin);
        req.setMerSign(UmDataField.valueOf(sign));
    }
    
    /**
     * 生成签名原始串
     * @param req
     * @return
     */
    protected String doGenSignOriginText(UmDlPayBillReq req){
        String origin = null;
        if(UmPayConsts.DZ_FILE_TYPE_INST.equals(req.getDzFileType().getValue())){
            origin = req.getInstId().getValue();
        }else{
            origin = req.getMerchantId().getValue();
        }
        origin += req.getAttach().getValue();
        
        return origin;
    }

    /**
     * 格式化请求信息体
     * 
     * @param req
     * @param cibmch
     * @return
     */
    protected String doFormatReqestBody(UmDlPayBillReq req) {
        String reqxmlstr = null;
        try {
            reqxmlstr = XmlUtils.marshal(req, UmPayConsts.XML_ENCODING, false);
        } catch (Exception e) {
            LOGGER.error("UMPAY_ERROR_PARAM", e);
            throw BizException.build(BizResCode.UMPAY_ERROR_PARAM);
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
            String url = umPayConfig.getApiUrl();
            LOGGER.info("request url:{}", url);

            HttpClientPost hcpost = HttpClient.post(url);
            hcpost.timeout(HTTP_CONN_TIME_OUT, HTTP_SOCKET_TIME_OUT);
            hcpost.xml(reqxmlstr);
            hcres = hcpost.action();
        } catch (Exception e) {
            LOGGER.error("UMPAY_ERROR_REQ", e);
            throw BizException.build(BizResCode.UMPAY_ERROR_REQ);
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
            LOGGER.error("UMPAY_ERROR_STATUS");
            throw BizException.build(BizResCode.UMPAY_ERROR_STATUS);
        }
    }

    /**
     * 解析响应结果
     * 
     * @param resxmlstr
     * @return
     */
    protected UmDlPayBillRes doParseResponse(String resxmlstr) {
        UmDlPayBillRes result = null;
        try {
            result = XmlUtils.unmarshal(UmDlPayBillRes.class, resxmlstr);
        } catch (Exception e) {
            LOGGER.error("UMPAY_ERROR_PARSE", e);
            throw BizException.build(BizResCode.UMPAY_ERROR_PARSE);
        }
        return result;
    }

    /**
     * 检查应答码
     * 
     * @param result
     */
    protected void doCheckResponseCode(UmDlPayBillRes result) {
        if (result == null || !UmPayConsts.RESPONSE_CODE_SUCESS.equals(result.getResponseCode().getValue())) {
            LOGGER.error("UMPAY_ERROR_RESPONSECODE, errmsg:{}", result.getResponseCode().getValue());
            throw BizException.build(BizResCode.UMPAY_ERROR_RESPONSECODE);
        }
    }
}
