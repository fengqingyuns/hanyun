package com.hanyun.platform.pay.service;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.domain.CibMerchant;
import com.hanyun.platform.pay.domain.CibMerchantStore;
import com.hanyun.platform.pay.vo.base.PageResData;
import com.hanyun.platform.pay.vo.paychninfo.PayChnInfoListReq;

public interface PayChnInfoService {
    
    public HttpResponse<PageResData> getCIBMerChantList( PayChnInfoListReq payChnInfoListReq);
    
    public HttpResponse<CibMerchant> getCIBMerChantByBrandId( PayChnInfoListReq payChnInfoListReq);

    public HttpResponse<Object> insertCIBMerChant(CibMerchant cibMerchant);

    public HttpResponse<Object> insertCIBMerChantStore(CibMerchantStore cibMerchantStore);

    public HttpResponse<PageResData> getCIBMerChantStoreByBrandId(PayChnInfoListReq payChnInfoListReq);

    public HttpResponse getCIBMerChantBrandIdList();

    public HttpResponse updateCIBMerChantBrandIdList(CibMerchant payChnInfoListRsp) throws Exception;

    public HttpResponse updateCIBMerChantStore(CibMerchantStore payChnInfoStoreListRsp) throws Exception;
}
