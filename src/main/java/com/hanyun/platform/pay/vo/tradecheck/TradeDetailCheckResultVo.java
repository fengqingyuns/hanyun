/**
 * 
 */
package com.hanyun.platform.pay.vo.tradecheck;

import java.util.ArrayList;
import java.util.List;

/**
 * 交易明细对账结果
 * @author liyinglong@hanyun.com
 * @date 2017年10月25日 下午3:34:27
 */
public class TradeDetailCheckResultVo {
    /** 成功标识 */
    private boolean success = false;
    /** 日期 */
    private String date;
    /** 品牌编号 */
    private String brandId;
    /** 总品牌数 */
    private int totalCount = 0;
    /** 成功品牌数 */
    private int succCount = 0;
    /** 消息 */
    private String message;
    /** 失败品牌编号列表 */
    private List<String> failBrandIdList = new ArrayList<>();
    
    public TradeDetailCheckResultVo(boolean success){
        this.success = success;
    }
    
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getBrandId() {
        return brandId;
    }
    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getSuccCount() {
        return succCount;
    }
    public void setSuccCount(int succCount) {
        this.succCount = succCount;
    }
    public List<String> getFailBrandIdList() {
        return failBrandIdList;
    }
    public void setFailBrandIdList(List<String> failBrandIdList) {
        this.failBrandIdList = failBrandIdList;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
