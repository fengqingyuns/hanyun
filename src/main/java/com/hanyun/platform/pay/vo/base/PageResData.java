/**
 * 
 */
package com.hanyun.platform.pay.vo.base;

/**
 * 分页响应数据结构
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年11月17日 上午11:45:09
 */
public class PageResData {
    private Integer totalCount;
    private Object dataList;

    public PageResData() {
    }

    public PageResData(Integer totalCount, Object dataList) {
        this.totalCount = totalCount;
        this.dataList = dataList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Object getDataList() {
        return dataList;
    }

    public void setDataList(Object dataList) {
        this.dataList = dataList;
    }

}
