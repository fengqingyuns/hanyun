/**
 * 
 */
package com.hanyun.platform.pay.vo.paymode;

import com.hanyun.platform.pay.domain.PayChnMode;

/**
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年12月8日 上午10:57:51
 */
public class PayModeDetailRes extends PayChnMode {
    private String chnName;

    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
    }
}
