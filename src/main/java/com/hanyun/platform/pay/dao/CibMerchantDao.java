package com.hanyun.platform.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hanyun.platform.pay.domain.CibMerchant;
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
public interface CibMerchantDao {

    public int insertSelective(CibMerchant record);

    public List<CibMerchant> getCIBMerChantList(PayChnInfoListReq payChnInfoListReq);
    
    public int getCIBMerChantListCount(PayChnInfoListReq payChnInfoListReq);
    
    public CibMerchant getByBrandId(String brandId);
     
    public List<CibMerchant> selectAvailMerchant(@Param("brandId") String brandId);

    public List<CibMerchant> getCIBMerChantAll();


    void updateCibMchIdAndCibSeckey(CibMerchant payChnInfoListRsp);
}