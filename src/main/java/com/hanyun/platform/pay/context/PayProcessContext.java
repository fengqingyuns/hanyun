/**
 * 
 */
package com.hanyun.platform.pay.context;

import java.util.HashMap;
import java.util.Map;

import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.PayTransactionNew;
import com.hanyun.platform.pay.domain.Payment;
import com.hanyun.platform.pay.domain.PaymentNew;

/**
 *  支付流程上下文
 * @author liyinglong@hanyun.com
 * @date 2016年9月2日 下午5:31:06
 */
public class PayProcessContext {
    /** 品牌编号 */
    private static final String KEY_BRANDID = "BRANDID";
    /** 门店编号 */
    private static final String KEY_STOREID = "STOREID";
    /** 易宝web回调地址*/
    private static final String KEY_WEBNOTIFY_URL = "YBWEB_NOTIFY_URL";
    /** 易宝支付单 */
    private static final String KEY_PAYMENT_NEW = "PAYMENT_NEW";
    /** 易宝交易流水*/
    private static final String KEY_TRANSACTION_NEW = "TRANSACTION_NEW";
    /** 支付单 */
    private static final String KEY_PAYMENT = "PAYMENT";
    /** 交易流水 */
    private static final String KEY_TRANSATION = "TRANSATION";
    /** 退款交易流水 */
    private static final String KEY_REFUNDTRANSATION = "REFUNDTRANSATION";
    /** 易宝退款交易流水 */
    private static final String KEY_REFUNDTRANSACTION_New = "REFUNDTRANSACTION_NEW";
    /** 扩展数据 */
    private static final String EXT_DATA = "EXTDATA";
    
    private static final ThreadLocal<Map<String, Object>> CXT_DATA = new ThreadLocal<>();
    
    /**
     * 初始化
     */
    public static void init(){
        Map<String, Object> dataMap = CXT_DATA.get();
        if(dataMap != null){
            dataMap.clear();
        }else{
            dataMap = new HashMap<>();
            CXT_DATA.set(dataMap);
        }
    }
    
    /**
     * 清空数据
     */
    public static void clear(){
        Map<String, Object> dataMap = CXT_DATA.get();
        if(dataMap != null){
            dataMap.clear();
        }
    }
    
    /**
     * 设置值
     * @param key
     * @param value
     */
    public static void set(String key, Object value){
        Map<String, Object> dataMap = CXT_DATA.get();
        if(dataMap == null){
            init();
            dataMap = CXT_DATA.get();
        }
        dataMap.put(key, value);
    }
    
    /**
     * 获取值
     * @param key
     * @return
     */
    public static Object get(String key){
        Map<String, Object> dataMap = CXT_DATA.get();
        if(dataMap == null){
            return null;
        }
        return dataMap.get(key);
    }
    
    public static void setBrandId(String brandId){
        set(KEY_BRANDID, brandId);
    }
    
    public static String getBrandId(){
        return (String) get(KEY_BRANDID);
    }
    
    public static void setStoreId(String storeId){
        set(KEY_STOREID, storeId);
    }
    
    public static String getStoreId(){
        return (String) get(KEY_STOREID);
    }
    
    public static void setPayment(Payment payment){
        set(KEY_PAYMENT, payment);
    }
    
    public static void setPaymentNew(PaymentNew paymentNew){
    	set(KEY_PAYMENT_NEW,paymentNew);
    }
    public static Payment getPayment(){
        return (Payment) get(KEY_PAYMENT);
    }
    
    public static void setYbNotifyUrl(String YbWebNotifyUrl){
    	set(KEY_WEBNOTIFY_URL,YbWebNotifyUrl);
    }
    
    public static String getYbNotifyUrl(){
    	return (String) get(KEY_WEBNOTIFY_URL);
    }
    
    public static PaymentNew getPaymentNew(){
    	return (PaymentNew) get(KEY_PAYMENT_NEW);
    }
    
    public static void setTransation(PayTransaction trans){
        set(KEY_TRANSATION, trans);
    }
    
    public static void setTransationNew(PayTransactionNew transNew){
        set(KEY_TRANSACTION_NEW, transNew);
    }
    
    public static PayTransaction getTransation(){
        return (PayTransaction) get(KEY_TRANSATION);
    }
    
    public static PayTransactionNew getTransationNew(){
        return (PayTransactionNew) get(KEY_TRANSACTION_NEW);
    }
    
    public static void setRefundTransation(PayTransaction reftrans){
        set(KEY_REFUNDTRANSATION, reftrans);
    }
    
    public static void setRefundTransationNew(PayTransactionNew reftransNew){
        set(KEY_REFUNDTRANSACTION_New, reftransNew);
    }
    
    public static PayTransaction getRefundTransation(){
        return (PayTransaction) get(KEY_REFUNDTRANSATION);
    }
    
    public static PayTransactionNew getRefundTransationNew(){
        return (PayTransactionNew) get(KEY_REFUNDTRANSACTION_New);
    }
    
    public static void setExtData(Object extData){
        set(EXT_DATA, extData);
    }
    
    public static Object getExtData(){
        return get(EXT_DATA);
    }
}
