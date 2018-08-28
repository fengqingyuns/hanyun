/**
 * 
 */
package com.hanyun.platform.pay.service.cib.weixin.impl;

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
import com.hanyun.platform.pay.adapt.cib.weixin.CibAdapterConfig;
import com.hanyun.platform.pay.adapt.cib.weixin.CibWeiXinDownloadBillAdapter;
import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinConsts;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.DownloadBillReq;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.DownloadBillRes;
import com.hanyun.platform.pay.context.PayProcessContext;
import com.hanyun.platform.pay.dao.CibMerchantDao;
import com.hanyun.platform.pay.domain.CibMerchant;
import com.hanyun.platform.pay.service.cib.weixin.CibWeiXinDownloadBillService;
import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;

/**
 * 兴业微信账章下载服务
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年9月11日 下午12:49:27
 */
@Service("cibWeiXinDownloadBillService")
public class CibWeiXinDownloadBillServiceImpl implements CibWeiXinDownloadBillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CibWeiXinDownloadBillServiceImpl.class);
    @Resource
    private CibWeiXinDownloadBillAdapter cibWeiXinDownloadBillAdapter;
    @Resource
    private CibAdapterConfig cibAdapterConfig;
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
        String dateBillDirStr = cibAdapterConfig.getWeixinBillDir() + billDateStr + "/";
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
                LOGGER.info("begin download cib weixin bill, brandId: {}", mch.getBrandId());
                String failmsg = doDownloadBrandBill(mch.getBrandId(), billDateStr, dateBillDirStr);
                if(failmsg == null){
                    LOGGER.info("finish download cib weixin bill, brandId: {}", mch.getBrandId());
                    result.setSuccCount(result.getSuccCount() + 1);
                }else{
                    result.getFailBrandIdList().add(mch.getBrandId() + ":" + failmsg);
                }
            } catch (Exception e) {
                LOGGER.error("download cib weixin bill error, brandId: {}", mch.getBrandId());
                LOGGER.error("download cib weixin bill error" ,e);
                result.getFailBrandIdList().add(mch.getBrandId());
            }
        }
        if(result.getSuccCount() > 0){
            result.setSuccess(true);
        }
        
        return result;
    }

    /**
     * 从兴业银行处下载品牌商户微信对账单
     * 
     * @param brandId
     * @param billDateStr
     * @param dateBillDirStr
     * @throws Exception
     */
    private String doDownloadBrandBill(String brandId, String billDateStr, String dateBillDirStr) throws Exception {
        PayProcessContext.setBrandId(brandId);

        DownloadBillReq billreq = new DownloadBillReq();
        billreq.setBill_type(CibWeiXinConsts.BILL_TYPE_ALL);
        billreq.setBill_date(billDateStr);

        DownloadBillRes billres = cibWeiXinDownloadBillAdapter.request(billreq);

        if (billres != null && StringUtils.isNotBlank(billres.getBilldata())) {
            String filename = brandId;
            File billFile = new File(dateBillDirStr, filename);
            if (billFile.exists()) {
                billFile.delete();
            }
            billFile.createNewFile();
            FileUtils.writeStringToFile(billFile, billres.getBilldata());
        }else{
            String resmsg = (billres == null) ? "null" : billres.getReturn_msg();
            LOGGER.error("download cib weixin bill file error, brandId: {}, resmsg: {}", brandId, resmsg);
            return resmsg;
        }
        return null;
    }
}
