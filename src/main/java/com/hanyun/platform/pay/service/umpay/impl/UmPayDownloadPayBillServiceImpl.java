/**
 * 
 */
package com.hanyun.platform.pay.service.umpay.impl;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.platform.pay.adapt.umpay.UmPayConfig;
import com.hanyun.platform.pay.adapt.umpay.UmPayDlPayBillAdapter;
import com.hanyun.platform.pay.adapt.umpay.consts.UmPayConsts;
import com.hanyun.platform.pay.adapt.umpay.protocol.UmDataField;
import com.hanyun.platform.pay.adapt.umpay.protocol.UmDlPayBillReq;
import com.hanyun.platform.pay.adapt.umpay.protocol.UmDlPayBillRes;
import com.hanyun.platform.pay.adapt.umpay.util.UmPaySignEncryptUtils;
import com.hanyun.platform.pay.service.umpay.UmPayDownloadPayBillService;
import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;

/**
 * 联动优势对账文件下载服务
 * @author liyinglong@hanyun.com
 * @date 2016年12月19日 下午8:47:53
 */
@Service("umPayDownloadPayBillService")
public class UmPayDownloadPayBillServiceImpl implements UmPayDownloadPayBillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmPayDownloadPayBillServiceImpl.class);
    
    @Resource
    private UmPayDlPayBillAdapter umPayDlPayBillAdapter;
    @Resource
    private UmPayConfig umPayConfig;

    @Override
    public TradeDetailCheckResultVo downloadPayBill(Date date) {
        TradeDetailCheckResultVo result = new TradeDetailCheckResultVo(false);     
        if (date == null) {
            result.setMessage("date is null");
            return result;
        }
        String billDateStr = DateFormatUtil.formatDateNoSep(date);
        result.setDate(billDateStr);
        result.setBrandId(null);
        result.setTotalCount(1);
        
        // 文件存放目录
        String billDirStr = umPayConfig.getUmpayBillDir();
        File billDir = new File(billDirStr);
        if (!billDir.exists()) {
            billDir.mkdirs();
        }
        
        try {
            // 下载
            UmDlPayBillReq req = new UmDlPayBillReq();
            req.setDzFileType(UmDataField.valueOf(UmPayConsts.DZ_FILE_TYPE_INST));
            req.setInstId(UmDataField.valueOf(umPayConfig.getInstId()));
            req.setAttach(UmDataField.valueOf(billDateStr));
            
            UmDlPayBillRes res = umPayDlPayBillAdapter.request(req);
            if(res == null){
                LOGGER.error("UmPayDownloadPayBill error, res empty!");
                result.setMessage("adapter res empty");
                return result;
            }
            // 解密
            String fileEnStr = res.getDzFile().getValue();
            byte[] fileData = UmPaySignEncryptUtils.decryptByPrivateKey(fileEnStr);
            if(fileData == null || fileData.length <= 0){
                LOGGER.error("UmPayDownloadPayBill error, filestr encrypt fail!");
                result.setMessage("filestr encrypt fail");
                return result;
            }
            // 保存文件
            File billFile = new File(billDir, billDateStr + ".zip");
            if (billFile.exists()) {
                billFile.delete();
            }
            billFile.createNewFile();
            FileUtils.writeByteArrayToFile(billFile, fileData);
            
            result.setSuccCount(result.getSuccCount() + 1);
        } catch (Exception e) {
            LOGGER.error("UmPayDownloadPayBill error!", e);
            result.setMessage("catch exception: " + e.getMessage());
        }
        
        if(result.getTotalCount() == result.getSuccCount()){
            result.setSuccess(true);
        }
        
        return result;
    }
    

}
