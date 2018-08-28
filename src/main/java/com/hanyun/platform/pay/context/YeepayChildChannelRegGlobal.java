package com.hanyun.platform.pay.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.context
 * @Author: dewen.li
 * @Date: 2018-08-06 下午4:47
 */
public class YeepayChildChannelRegGlobal {

    //加密通用延值
    public final static String DES_KEY = "12345678";

    //金额换算值
    public final static int TRANCE_AMOUNT = 100;

    /**
     * 行业	码值
     * 综合业务	0002
     * 金融投资大额，基金行业大额	3002
     * 金融投资	3002002
     * 电商	3101
     * 手机预购	3101002
     * 团购	3101003
     * 航空	4511
     * 电信和手机充值	4814
     * 流量充值	4814002
     * 工缴费	4900
     * 保险行业	6300
     * 虚拟产品	7993
     * 海外虚拟产品	7993002
     * 电影票	7993003
     * 网络服务	7993005
     * 彩票行业	7995
     * 行政教育	8299
     * 公益事业	8398
     * 线下服务事业	9499
     * 其他	5969
     * */
    public final static String PAY_MCC = "3101";

    private  static  Map<String,Map<String,String>> childChannel = new HashMap();

    public static Map getChildChannel(String storeId) {
        return childChannel.get(storeId);
    }

    public static void setChildChannel(Map newChildChannel,String storeId) {
       if(null== childChannel.get(storeId)){
           childChannel.put(storeId,newChildChannel);
       }
    }

}
