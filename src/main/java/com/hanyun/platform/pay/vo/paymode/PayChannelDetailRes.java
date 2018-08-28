/**
 * 
 */
package com.hanyun.platform.pay.vo.paymode;

import com.hanyun.platform.pay.domain.PayChnMode;

/**
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年12月8日 上午10:56:34
 */
public class PayChannelDetailRes extends PayChnMode {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
