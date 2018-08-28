/**   
* @Title: SettlementDetailDao.java 
* @Package com.hanyun.platform.settle.dao 
* @author A18ccms A18ccms_gmail_com   
* @date 2016年7月26日 上午9:55:48 
* @version V1.0   
*/
package com.hanyun.platform.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanyun.platform.pay.domain.SettlementDetail;
import com.hanyun.platform.pay.domain.SettlementDetailReq;

/**
 * @author jack
 *
 */
@SuppressWarnings("UnnecessaryInterfaceModifier")
@Repository
public interface SettlementDetailDao {

    public SettlementDetail getSettlementDetailByReq(SettlementDetailReq req);
    
}
