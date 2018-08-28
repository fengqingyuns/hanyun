package com.hanyun.platform.pay.service;

import java.util.List;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.domain.PayChannel;
import com.hanyun.platform.pay.domain.PayMchMode;
import com.hanyun.platform.pay.domain.PayMode;
import com.hanyun.platform.pay.vo.paymode.PayChannelDetailRes;
import com.hanyun.platform.pay.vo.paymode.PayChannelReq;
import com.hanyun.platform.pay.vo.paymode.PayChnModeReq;
import com.hanyun.platform.pay.vo.paymode.PayChannelDetailReq;
import com.hanyun.platform.pay.vo.paymode.PayMchModeReq;
import com.hanyun.platform.pay.vo.paymode.PayModeDetailReq;
import com.hanyun.platform.pay.vo.paymode.PayModeDetailRes;
import com.hanyun.platform.pay.vo.paymode.PayModeReq;
import com.hanyun.platform.pay.vo.paymode.PayModeRes;

public interface PayModeManageService {
	
	 public HttpResponse<List<PayChannel>> selectChannelList();
	 
	 public HttpResponse<List<PayChannelDetailRes>> selectPayChannelDetailByChannel(PayChannelDetailReq payChannelDetailTReq);
	 
	 public HttpResponse<List<PayModeDetailRes>> selectPayModeDetailByMode(PayModeDetailReq payModeDetailReq);
	 
	 public HttpResponse<Object>  updateChannelStatusByChannel(PayChannelReq payChannelReq);

	 public HttpResponse<Object> updateModeStatusByMode(PayModeReq payModeReq);

	 public HttpResponse<Object> updateChnModeServStatus(PayChnModeReq payChnModeReq);

	 public HttpResponse<Object> updateChnModeFeeRate(PayChnModeReq payChnModeReq);
	 
	 public HttpResponse<List<PayModeRes>> selectModList(); 
	 
	 public HttpResponse<PayChannel> getPayChannelByChannel(PayChannelDetailReq payChannelDetailTReq);
	 
	 public HttpResponse<PayMode> getPayModeByPayType(PayModeDetailReq payModeDetailReq);
	 
	 public HttpResponse<List<PayMchMode>> getPayMchModeByBrandId(PayMchModeReq payMchModeReq);
	 
	 public HttpResponse<Object> updatePayMchMode(PayMchModeReq payMchModeReq);

	 public HttpResponse<Object> updatemchavailstatus(PayMchModeReq payMchModeReq);

	 public HttpResponse<Object> updatePayMode(PayModeReq payModeReq);
	 
	 
}
