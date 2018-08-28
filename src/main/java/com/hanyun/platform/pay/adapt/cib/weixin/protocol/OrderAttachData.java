/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.protocol;

/**
 * 订单附加数据
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年8月15日 下午9:39:20
 */
public class OrderAttachData {
    // 商户管理中心的门店应用id
    private String storeAppid;
    // 商户管理中心的门店名
    private String storeName;
    // 收银员标识
    private String opUser;
    
    public String getStoreAppid() {
        return storeAppid;
    }
    public void setStoreAppid(String storeAppid) {
        this.storeAppid = storeAppid;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public String getOpUser() {
        return opUser;
    }
    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }
}
