package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.CibWeixinRefund;
import java.util.List;

import org.springframework.stereotype.Repository;

@SuppressWarnings("UnnecessaryInterfaceModifier")
@Repository
public interface CibWeixinRefundDao {

    public int insert(CibWeixinRefund record);

    /**
     * 
    * @Title: selectByOutRefundNo 
    * @Description: 通过汉云退款单号进行查询 
    * @param  
    * @return CibWeixinRefund   
    * @throws
     */
    public CibWeixinRefund selectByOutRefundNo(String outRefundNo);
    
    /**
     * 
    * @Title: updateByOutRefundNo 
    * @Description: 通过退款单号更新收费，费率等信息 
    * @param  
    * @return int   
    * @throws
     */
    public int updateByOutRefundNo(CibWeixinRefund record);
}