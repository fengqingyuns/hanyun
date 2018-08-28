package com.hanyun.platform.pay.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.dao.CibMerchantDao;
import com.hanyun.platform.pay.dao.CibMerchantStoreDao;
import com.hanyun.platform.pay.domain.CibMerchant;
import com.hanyun.platform.pay.domain.CibMerchantStore;
import com.hanyun.platform.pay.service.PayChnInfoService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.vo.base.PageResData;
import com.hanyun.platform.pay.vo.paychninfo.PayChnInfoListReq;

@Service
public class PayChnInfoServiceImpl implements PayChnInfoService {
    
    private static Logger LOGGER=LoggerFactory.getLogger(PayChnInfoServiceImpl.class) ;
    @Resource
    private CibMerchantDao cibMerchantDao;
    @Resource
    private CibMerchantStoreDao cibMerchantStoreDao;
    
    
    /**
     * 获得兴业银行商户列表
     */
    public HttpResponse<PageResData> getCIBMerChantList(PayChnInfoListReq payChnInfoListReq){
        
        HttpResponse<PageResData> httpResponse = null;
        try {
            List<CibMerchant> cibMerChantList = cibMerchantDao.getCIBMerChantList(payChnInfoListReq);
            int totalCount = cibMerchantDao.getCIBMerChantListCount(payChnInfoListReq);
            
            httpResponse = BizResUtil.succPageList(totalCount, cibMerChantList);

        } catch (Exception e) {
            LOGGER.error("getCIBMerChantList error!" , e);
            httpResponse=BizResUtil.fail(BizResCode.SYSTEMERROR);
        }
        return httpResponse;
    }
    
    
    /**
     * 通过品牌id获取兴业银行商户详情 
     */
    public HttpResponse<CibMerchant> getCIBMerChantByBrandId(PayChnInfoListReq payChnInfoListReq){
        
        HttpResponse<CibMerchant> httpResponse = null;
        try {
            CibMerchant cibMerChant = cibMerchantDao.getByBrandId(payChnInfoListReq.getBrandId());
            httpResponse=BizResUtil.succ(cibMerChant);
        } catch (Exception e) {
            LOGGER.error("getCIBMerChantByBrandId error!" , e);
            httpResponse=BizResUtil.fail(BizResCode.SYSTEMERROR);
        }
      
        
        return httpResponse;
    }
    
    
    /**
     *    通过品牌id获取兴业银行门店信息 
     */
    public HttpResponse<PageResData> getCIBMerChantStoreByBrandId(PayChnInfoListReq payChnInfoListReq){
        HttpResponse<PageResData> httpResponse =null;
        try {
            List<CibMerchantStore> payChnInfoListRspList = cibMerchantStoreDao.getCIBMerChantStoreByBrandId(payChnInfoListReq);
            int totalCount = cibMerchantStoreDao.getCIBMerChantStoreCountByBrandId(payChnInfoListReq);
            httpResponse = BizResUtil.succPageList(totalCount, payChnInfoListRspList);
        } catch (Exception e) {
            LOGGER.error("getCIBMerChantStoreByBrandId error!" , e);
            httpResponse=BizResUtil.fail(BizResCode.SYSTEMERROR);
        }
        return httpResponse; 
    }
    
    
    
    
    
    
    /**
     * 添加兴业银行商户
     */
    public HttpResponse<Object> insertCIBMerChant(CibMerchant cibMerchant){
        HttpResponse<Object> httpResponse = null;
        
             try {
                cibMerchantDao.insertSelective(cibMerchant);
                httpResponse=BizResUtil.succ(null);
            } catch (Exception e) {
                LOGGER.error("添加兴业银行商户失败", e);;
                httpResponse=BizResUtil.fail(BizResCode.SYSTEMERROR);
            }
             
        return httpResponse;
    }
    /**
     * 添加兴业银行商户
     */
    public HttpResponse<Object> insertCIBMerChantStore(CibMerchantStore cibMerchantStore){
        HttpResponse<Object> httpResponse = null;
        
        try {
            cibMerchantStoreDao.insertSelective(cibMerchantStore);
            httpResponse=BizResUtil.succ(null);
        } catch (Exception e) {
            LOGGER.error("添加兴业银行商户门店失败",e);
            httpResponse=BizResUtil.fail(BizResCode.SYSTEMERROR);
        }
        
        return httpResponse;
    }

    /**
     * 查询出已经增加为兴业银行商户的品牌id集合
     */
    public HttpResponse getCIBMerChantBrandIdList() {
       
        HttpResponse<Object> httpResponse;
        StringBuffer sb = new StringBuffer();
        try {
            List<CibMerchant> cibMerchantList = cibMerchantDao.getCIBMerChantAll();
            if(cibMerchantList!=null){
                for (CibMerchant cibMerchant : cibMerchantList) {
                   sb.append(","+cibMerchant.getBrandId());
                }
            }
            httpResponse=BizResUtil.succ(sb);
        } catch (Exception e) {
            httpResponse=BizResUtil.fail(BizResCode.SYSTEMERROR);
            LOGGER.error("查询出已经增加为兴业银行商户的品牌id集合异常",e);
        }
        return httpResponse;
    }

    /**
     * 更新兴业银行商户
     * @return HttpResponse
     * @param payChnInfoListRsp
     */
    @Override
    public HttpResponse updateCIBMerChantBrandIdList(CibMerchant payChnInfoListRsp) throws Exception{

        CibMerchant cibMerchant = cibMerchantDao.getByBrandId(payChnInfoListRsp.getBrandId());
        if(cibMerchant!=null){
            cibMerchantDao.updateCibMchIdAndCibSeckey(payChnInfoListRsp);
        }else{
            LOGGER.info("---------------未查询到对应商户");
            return HttpResponse.failure(BizResCode.CIB_MERCHANT_EMPTY);
        }
        return HttpResponse.success();
    }

    @Override
    public HttpResponse updateCIBMerChantStore(CibMerchantStore payChnInfoStoreListRsp) throws Exception{
        CibMerchantStore cibMerchantStore = cibMerchantStoreDao.getByStoreId(payChnInfoStoreListRsp.getStoreId());
        if(cibMerchantStore!=null){
            cibMerchantStoreDao.updateCIBMerChantStore(payChnInfoStoreListRsp);
        }else{
            LOGGER.info("---------------未查询到对应商户");
            return HttpResponse.failure(BizResCode.CIB_MERCHANT_STORE_EMPTY);
        }
        return HttpResponse.success();



    }
}
