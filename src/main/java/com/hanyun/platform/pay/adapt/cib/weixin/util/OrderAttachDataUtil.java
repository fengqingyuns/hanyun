/**
 * 
 */
package com.hanyun.platform.pay.adapt.cib.weixin.util;

import org.apache.commons.lang.StringUtils;

import com.hanyun.platform.pay.adapt.cib.weixin.protocol.OrderAttachData;

/**
 * 订单附加数据格式化工具类
 * <br>格式为: `store_appid=xx#store_name =xx#op_user=xx
 * <br>各参数以符号#分隔,参数前增加 ` 符号,为标准键盘 1 左 边键的字符,参数顺序与格式描述一致
 * @author liyinglong@hanyun.com
 * @date 2016年8月15日 下午9:44:24
 */
public abstract class OrderAttachDataUtil {
    
    /**
     * 格式化
     * @param attach
     * @return
     */
    public static String format(OrderAttachData attach){
        StringBuilder sb = new StringBuilder(300);
        sb.append('`');
        sb.append("store_appid=");
        sb.append(attach.getStoreAppid());
        sb.append('#');
        sb.append("store_name=");
        sb.append(attach.getStoreName());
        sb.append('#');
        sb.append("op_user=");
        if(StringUtils.isNotBlank(attach.getOpUser())){
            sb.append(attach.getOpUser());
        }
        
        return sb.toString();
    }
}
