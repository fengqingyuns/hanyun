package com.hanyun.platform.pay.vo.paychninfo;

import com.hanyun.platform.pay.vo.base.PageRequest;

/**
 * 兴业银行商户请求
 * 
 * @author wangximin@hanyun.com
 * 
 * @date 2016年8月25日 下午5:52:21
 *
 */
public class PayChnInfoListReq extends PageRequest{
    
    private String brandId;
    
    private String cibMchId;
    
    public String getCibMchId() {
        return cibMchId;
    }

    public void setCibMchId(String cibMchId) {
        this.cibMchId = cibMchId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    @Override
    public String toString() {
        return "PayChnInfoListReq [brandId=" + brandId + ", cibMchId=" + cibMchId + "]";
    }
    
    
}
