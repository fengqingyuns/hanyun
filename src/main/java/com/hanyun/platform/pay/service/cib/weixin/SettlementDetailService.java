package com.hanyun.platform.pay.service.cib.weixin;

import java.util.List;

import com.hanyun.platform.pay.domain.SettlementDetail;
import com.hanyun.platform.pay.domain.SettlementDetailReq;



public interface SettlementDetailService {	 

	 /**
	  * 
	 * @Title: getSettlementDetailByReq 
	 * @Description: 扣款和退款    通过参数获取支付交易流水信息 
	 * @param  
	 * @return SettlementDetail   
	 * @throws
	  */
	 public SettlementDetail getSettlementDetailByReq(SettlementDetailReq settlementDetailReq);	 
}
