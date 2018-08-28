/**
 * 
 */
package com.hanyun.platform.pay.service.cib.alipay.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.platform.pay.adapt.cib.alipay.CibAlipayAdapterConfig;
import com.hanyun.platform.pay.adapt.cib.alipay.CibAlipayDownloadBillAdapter;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayDownloadBillReq;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayDownloadBillRes;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.CibMerchantDao;
import com.hanyun.platform.pay.domain.CibMerchant;
import com.hanyun.platform.pay.service.cib.alipay.CibAlipayDownloadBillService;
import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;

/**
 * 兴业支付宝账单下载服务
 * 
 * @author liyinglong@hanyun.com
 * @date 2017年1月5日 下午1:35:04
 */
@Service("cibAlipayDownloadBillService")
public class CibAlipayDownloadBillServiceImpl implements CibAlipayDownloadBillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CibAlipayDownloadBillServiceImpl.class);
    @Resource
    private CibAlipayDownloadBillAdapter cibAlipayDownloadBillAdapter;
    @Resource
    private CibAlipayAdapterConfig cibAlipayAdapterConfig;
    @Resource
    private CibMerchantDao cibMerchantDao;

    @Override
    public TradeDetailCheckResultVo downloadBill(Date date, String brandId) {
        TradeDetailCheckResultVo result = new TradeDetailCheckResultVo(false);    	
        if (date == null) {
            result.setMessage("date is null");
            return result;
        }
        if(StringUtils.isBlank(brandId)){
            brandId = null;
        }
        String billDateStr = DateFormatUtil.formatDateNoSep(date);
        
        result.setDate(billDateStr);
        result.setBrandId(brandId);
        
        // 文件存放目录
        String dateBillDirStr = cibAlipayAdapterConfig.getAlipayBillDir() + billDateStr + "/";
        File dateBillDir = new File(dateBillDirStr);
        if (!dateBillDir.exists()) {
            dateBillDir.mkdirs();
        }
        // 开始下载
        List<CibMerchant> mchlst = cibMerchantDao.selectAvailMerchant(brandId);
        if (mchlst == null || mchlst.isEmpty()) {
            result.setMessage("mchlst is empty, brandId: " + brandId);
            return result;
        }
        result.setTotalCount(mchlst.size());
        LOGGER.info("cib merchant size: {}", mchlst.size());
        
        for (CibMerchant mch : mchlst) {
            try {
                LOGGER.info("begin download cib alipay bill, brandId: {}", mch.getBrandId());
                String failmsg = doDownloadBrandBill(mch.getBrandId(), billDateStr, dateBillDirStr);
                if(failmsg == null){
                    LOGGER.info("finish download cib alipay bill, brandId: {}", mch.getBrandId());
                    result.setSuccCount(result.getSuccCount() + 1);
                }else{
                    result.getFailBrandIdList().add(mch.getBrandId() + ":" + failmsg);
                }
            } catch (Exception e) {
                LOGGER.error("download cib alipay bill error, brandId: {}", mch.getBrandId());
                LOGGER.error("download cib alipay bill error", e);
            	result.getFailBrandIdList().add(mch.getBrandId());
            }
        }
        if(result.getSuccCount() > 0){
            result.setSuccess(true);
        }
        
        return result;
    }

    /**
     * 从兴业银行处下载品牌商户支付宝对账单
     * 
     * @param brandId
     * @param billDateStr
     * @param dateBillDirStr
     * @throws Exception
     */
    private String doDownloadBrandBill(String brandId, String billDateStr, String dateBillDirStr) throws Exception {
        PayProcessContext.setBrandId(brandId);

        AlipayDownloadBillReq billreq = new AlipayDownloadBillReq();
        billreq.setBillDate(billDateStr);

        AlipayDownloadBillRes billres = cibAlipayDownloadBillAdapter.request(billreq);

        if (billres != null && StringUtils.isNotBlank(billres.getBilldata())) {
            String filename = brandId;
            File billFile = new File(dateBillDirStr, filename);
            if (billFile.exists()) {
                billFile.delete();
            }
            billFile.createNewFile();
            FileUtils.writeStringToFile(billFile, billres.getBilldata());
        }else{
            String resmsg = (billres == null) ? "null" : billres.getReturnMsg();
            LOGGER.error("download cib weixin bill file error, brandId: {}, resmsg: {}", brandId, resmsg);
            return resmsg;
        }
        return null;

    }
}
