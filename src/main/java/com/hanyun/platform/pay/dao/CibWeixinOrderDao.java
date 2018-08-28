package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.CibWeixinOrder;
import java.util.List;

import org.springframework.stereotype.Repository;

@SuppressWarnings("UnnecessaryInterfaceModifier")
@Repository
public interface CibWeixinOrderDao {

    public int insert(CibWeixinOrder record);
    
    /**
     * 
    * @Title: selectByOutTradeNo 
    * @Description: 通过汉云交易单号 
    * @param  
    * @return CibWeixinOrder   
    * @throws
     */
    public CibWeixinOrder selectByOutTradeNo(String outTradeNo);
    
    /**
     * 
    * @Title: updateByOutTradeNo 
    * @Description: 通过退款订单号进行更新数据 
    * @param  
    * @return int   
    * @throws
     */
    public int updateByOutTradeNo(CibWeixinOrder record);
}