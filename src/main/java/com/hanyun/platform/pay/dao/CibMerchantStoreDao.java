package com.hanyun.platform.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanyun.platform.pay.domain.CibMerchantStore;
import com.hanyun.platform.pay.vo.paychninfo.PayChnInfoListReq;

/**
 * <pre>
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　　┃　　　┃Code is far away from bug with the animal protecting
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * </pre>
 */
@SuppressWarnings("UnnecessaryInterfaceModifier")
@Repository
public interface CibMerchantStoreDao {

    public int insertSelective(CibMerchantStore record);

    public int updateByPrimaryKeySelective(CibMerchantStore record);

    public List<CibMerchantStore> getCIBMerChantStoreByBrandId(PayChnInfoListReq payChnInfoListReq);
    
    public int getCIBMerChantStoreCountByBrandId(PayChnInfoListReq payChnInfoListReq);
    
    public CibMerchantStore getByStoreId(String storeId);

    void updateCIBMerChantStore(CibMerchantStore payChnInfoStoreListRsp);
}