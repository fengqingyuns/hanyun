package com.hanyun.platform.pay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.consts.PayModeConsts;
import com.hanyun.platform.pay.dao.PayChannelDao;
import com.hanyun.platform.pay.dao.PayChnModeDao;
import com.hanyun.platform.pay.dao.PayMchModeDao;
import com.hanyun.platform.pay.dao.PayModeDao;
import com.hanyun.platform.pay.domain.PayChannel;
import com.hanyun.platform.pay.domain.PayChnMode;
import com.hanyun.platform.pay.domain.PayMchMode;
import com.hanyun.platform.pay.domain.PayMode;
import com.hanyun.platform.pay.service.PayModeManageService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.vo.paymode.PayChannelDetailRes;
import com.hanyun.platform.pay.vo.paymode.PayChannelReq;
import com.hanyun.platform.pay.vo.paymode.PayChnModeReq;
import com.hanyun.platform.pay.vo.paymode.PayChannelDetailReq;
import com.hanyun.platform.pay.vo.paymode.PayMchModeReq;
import com.hanyun.platform.pay.vo.paymode.PayModeDetailReq;
import com.hanyun.platform.pay.vo.paymode.PayModeDetailRes;
import com.hanyun.platform.pay.vo.paymode.PayModeReq;
import com.hanyun.platform.pay.vo.paymode.PayModeRes;

/**
 * 
 * @author wangximin@hanyun.com
 * 
 * @date 2016年8月5日 下午3:58:06
 *
 */

@Service("PayModeManageServiceImpl")
public class PayModeManageServiceImpl implements PayModeManageService {

	private static Logger LOGGER=LoggerFactory.getLogger(PayModeManageServiceImpl.class) ;

	@Resource
	private PayChannelDao payChannelDao;
	@Resource
	private PayModeDao payModeDao;
	@Resource
	private PayChnModeDao payChnModeDao;
	@Resource
	private PayMchModeDao payMchModeDao;
	
	/**
	 * 功能：支付通道列表展示
	 * 
	 */
	@Override
	public HttpResponse<List<PayChannel>> selectChannelList() {

		HttpResponse<List<PayChannel>> httpResponse = null;
		try {
			List<PayChannel> payChannelList = payChannelDao.selectChannelList();

			httpResponse = BizResUtil.succ(payChannelList);
		} catch (Exception e) {
		    LOGGER.error("支付通道列表展示异常(selectChannelList)",e);
			httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
		}

		return httpResponse;
	}
	/**
	 * 功能：支付方式列表展示
	 * 
	 */
	public HttpResponse<List<PayModeRes>> selectModList(){

		HttpResponse<List<PayModeRes>> httpResponse = null;
		try {
			//1 获取支付方式的列表
			List<PayModeRes> payModList = payModeDao.selectModList();
			//2 获取支付方式对应的支付通道
			List<PayModeRes> channelList = payChannelDao.selectInSrvChannelList();
			//3 对应的进行封装
			Map<String,PayModeRes> map = new HashMap<String,PayModeRes>();
			for (PayModeRes payChannel : channelList) {
                map.put(payChannel.getPayType(), payChannel);
            }
			
			for(PayModeRes payMode : payModList) {
			    PayModeRes payChannel = map.get(payMode.getPayType());
			    if(payChannel != null){
			        payMode.setChannel(payChannel.getChannel());
			        payMode.setChnName(payChannel.getChnName());
			    }
			}
			
			httpResponse = BizResUtil.succ(payModList);
		} catch (Exception e) {
		    LOGGER.error("支付方式列表展示异常(selectModList)",e);
			httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
		}
		return httpResponse;	
	}
	/**
	 * 支付通道详情
	 */
	public HttpResponse<List<PayChannelDetailRes>> selectPayChannelDetailByChannel(PayChannelDetailReq payChannelDetailTReq){

		HttpResponse<List<PayChannelDetailRes>> httpResponse = null;
		try {
			List<PayChannelDetailRes> payChannelDetail = payChnModeDao.selectPayChannelDetailByChannel(payChannelDetailTReq);
			httpResponse = BizResUtil.succ(payChannelDetail);
		} catch (Exception e) {
		    LOGGER.error("支付通道详情异常(selectPayChannelDetailByChannel)",e);
			httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
		}
		return 	httpResponse;	
	}

	/**
	 * 通道状态更改
	 */
	@Transactional
	public HttpResponse<Object> updateChannelStatusByChannel(PayChannelReq payChannelReq){
		//判断状态
		HttpResponse<Object> httpResponse = null;
		try {
		    PayChannel updchannel = new PayChannel();
		    updchannel.setChannel(payChannelReq.getChannel());
			if(PayModeConsts.PAY_AVAILSTATUS_OFF.equals(payChannelReq.getAvailStatus())){
				//启用  不需要更新关联表 
			    updchannel.setAvailStatus(PayModeConsts.PAY_AVAILSTATUS_ON);
				//更新通道表
				payChannelDao.updateBychannel(updchannel);
			}else{
				//禁用  需要更新关联表
			    updchannel.setAvailStatus(PayModeConsts.PAY_AVAILSTATUS_OFF);
				//更改服务状态为常规
			    PayChnMode updchnmode = new PayChnMode();
			    updchnmode.setChannel(payChannelReq.getChannel());
			    updchnmode.setSrvStatus(PayModeConsts.PAY_SERVSTATUS_OFF);
				//更新通道表
				payChannelDao.updateBychannel(updchannel);
				//更新通道和支付方式表
				payChnModeDao.updateBychannel(updchnmode);

			}																																		
			httpResponse = BizResUtil.succ(null);
		} catch (Exception e) {
			LOGGER.error("状态更改异常(updateByChannel)",e);
			httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
		}
		return httpResponse ;
	}
	
	/**
	 * 支付方式详情
	 */
	public HttpResponse<List<PayModeDetailRes>> selectPayModeDetailByMode(PayModeDetailReq payModeDetailReq){

		HttpResponse<List<PayModeDetailRes>> httpResponse = null;
		try {
			List<PayModeDetailRes> ModeDetail = payChnModeDao.selectPayModeDetailByMode(payModeDetailReq);
			httpResponse = BizResUtil.succ(ModeDetail);
		} catch (Exception e) {
		    LOGGER.error("支付方式详情异常",e);
			httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
		}
		return httpResponse;
	}
	/**
	 * 通过支付通道查询支付通道详情
	 */
	public HttpResponse<PayChannel> getPayChannelByChannel(PayChannelDetailReq payChannelDetailTReq){

		HttpResponse<PayChannel> httpResponse = null;
		try {
			PayChannel payChannel = payChannelDao.getPayChannelByChannel(payChannelDetailTReq.getChannel());
			httpResponse = BizResUtil.succ(payChannel);
		} catch (Exception e) {
		    LOGGER.error("通过支付通道查询支付通道详情异常(getPayChannelByChannel)",e);
			httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
		}

		return httpResponse;
	}
	/**
	 * 通过支付方式查询支付详情
	 */
	public HttpResponse<PayMode> getPayModeByPayType(PayModeDetailReq payModeDetailReq){

		HttpResponse<PayMode> httpResponse = null;
		try {
			PayMode payMode = payModeDao.getPayModeByPayType( payModeDetailReq.getPayType());
			httpResponse = BizResUtil.succ(payMode);
		} catch (Exception e) {
		    LOGGER.error("通过支付方式查询支付详情异常(getPayModeByPayType)",e);
			httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
		}
		return httpResponse;
	}
	/**
	 * 支付方式状态更改
	 */
	public HttpResponse<Object>  updateModeStatusByMode(PayModeReq payModeReq){
		HttpResponse<Object> httpResponse = null;

		try {
			//判断状态 ---禁用
			if(PayModeConsts.PAY_AVAILSTATUS_OFF.equals(payModeReq.getAvailStatus())){
				//启用  不需要更新关联表 
			    payModeReq.setAvailStatus(PayModeConsts.PAY_AVAILSTATUS_ON);
				//更新通道表
				payModeDao.updateAvailStatusByMode(payModeReq);
			}else{
				//禁用  同样更新关联表
			    payModeReq.setAvailStatus(PayModeConsts.PAY_AVAILSTATUS_OFF);
				//更改服务状态为常规
			    PayChnMode updchnmode = new PayChnMode();
			    updchnmode.setPayType(payModeReq.getPayType());
			    updchnmode.setSrvStatus(PayModeConsts.PAY_SERVSTATUS_OFF);
				//更新通道表
				payModeDao.updateAvailStatusByMode(payModeReq);
				//更新通道和支付方式表
				payChnModeDao.updateByMode(updchnmode);

			}
		} catch (Exception e) {
			LOGGER.error("支付方式状态更改异常(updateByMode)",e);
			httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
		}
		httpResponse = BizResUtil.succ(null);

		return httpResponse;

	}

	/**
	 * 修改中间表的服务状态
	 */

	public HttpResponse<Object> updateChnModeServStatus(PayChnModeReq payChnModeReq){

		HttpResponse<Object> httpResponse = null;
		try {
			//查询出支付通道的状态
			PayChannel channel = payChannelDao.getPayChannelByChannel(payChnModeReq.getChannel());
			//查询出支付方式的状态
			PayMode payMode = payModeDao.getPayModeByPayType(payChnModeReq.getPayType());
			//判断支付通道和支付方式都是可用的
			if(PayModeConsts.PAY_AVAILSTATUS_ON.equals(channel.getAvailStatus())
			        && PayModeConsts.PAY_AVAILSTATUS_ON.equals(payMode.getAvailStatus())){
				//判断服务状态状态--- 服务中
				if(PayModeConsts.PAY_SERVSTATUS_ON.equals(payChnModeReq.getSrvStatus())){
					//判断是否是启用状态
					if(PayModeConsts.PAY_AVAILSTATUS_ON.equals(payChnModeReq.getAvailStatus())){
						//常规
					    payChnModeReq.setSrvStatus(PayModeConsts.PAY_SERVSTATUS_OFF);	
						//更新中间表
						payChnModeDao.updateOneServStatus(payChnModeReq);
					}
				}else{
					//判断是否是启用状态
					if(PayModeConsts.PAY_AVAILSTATUS_ON.equals(payChnModeReq.getAvailStatus())){
						//实现每次只能使用一个通道   
						payChnModeDao.offlineAllByPayType(payChnModeReq.getPayType());

						//更改为服务中  
						payChnModeReq.setSrvStatus(PayModeConsts.PAY_SERVSTATUS_ON);	

						payChnModeDao.updateOneServStatus(payChnModeReq);
					}
				}

			}
			httpResponse = BizResUtil.succ(null);
		} catch (Exception e) {
			LOGGER.error("服务状态更改异常(updateServStatus)",e);
			httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
		}
		return httpResponse ;
	}
	/**
	 * 更改支付通道费率
	 */

	public HttpResponse<Object> updateChnModeFeeRate(PayChnModeReq payChnModeReq){

		HttpResponse<Object> httpResponse;
			try {
			    payChnModeDao.updateOneFeeRate(payChnModeReq);
            } catch (Exception e) {
                LOGGER.error("更改支付通道费率异常",e);
                httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
            }

			httpResponse = BizResUtil.succ(null);
		return httpResponse ;
	}
	/**
     * 通过品牌id查询商户费率
     */

    public HttpResponse<List<PayMchMode>> getPayMchModeByBrandId(PayMchModeReq payMchModeReq){

       
        HttpResponse<List<PayMchMode>> httpResponse;
        try {
           
            List<PayMchMode> payMchMode = payMchModeDao.getPayMchModeByBrandId(payMchModeReq);
            httpResponse = BizResUtil.succ(payMchMode);
        } catch (Exception e) {
            LOGGER.error("通过品牌id查询商户费率异常(getPayMchModeByBrandId)",e);
            httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
        }
        return httpResponse ;
    }
    
    /**
     * 通过支付方式和品牌id修改商户费率
     */
    public HttpResponse<Object> updatePayMchMode(PayMchModeReq payMchModeReq){
        HttpResponse<Object> httpResponse = null;
      
        //通过支付方式和品牌id查商户支付方式表是否有记录
        int count = payMchModeDao.getCountByBrandIdAndPayType(payMchModeReq);
        try {
            //判断是否存在记录 如果有更新  没有添加
            if(count > 0){
                    payMchModeDao.updatePayMchMode( payMchModeReq);
            }else{
                    payMchModeDao.insertPayMchMode(payMchModeReq);
            }
        } catch (Exception e) {
            LOGGER.error("更新状态异常(updatePayMchMode)",e);
            httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
        }
        return httpResponse;
    }
    /**
     * 修改商户支付方式的使用状态
     */
    public HttpResponse<Object> updatemchavailstatus(PayMchModeReq payMchModeReq){
        HttpResponse<Object> httpResponse = null;
        
        int count = payMchModeDao.getCountByBrandIdAndPayType(payMchModeReq);
        try {
            if(count==0){
                payMchModeDao.insertByAvailstatus(payMchModeReq);
            }else{
                //判断状态 ---禁用
                if(payMchModeReq.getAvailStatus()==PayModeConsts.PAY_AVAILSTATUS_OFF){
                    //启用  不需要更新关联表 
                    payMchModeReq.setAvailStatus(PayModeConsts.PAY_AVAILSTATUS_ON);
                    payMchModeDao.updatemchavailstatus(payMchModeReq);  
                    httpResponse = BizResUtil.succ(null);
                }else{
                    //禁用  同样更新关联表
                    payMchModeReq.setAvailStatus(PayModeConsts.PAY_AVAILSTATUS_OFF);
                    payMchModeDao.updatemchavailstatus(payMchModeReq);
                    httpResponse = BizResUtil.succ(null);
                }
            }
        } catch (Exception e) {
            LOGGER.error("支付方式状态更改异常(updatemchavailstatus)",e);
            httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
        }
        
        return httpResponse;
    }
    
    /**
     * 修改支付方式的默认商户费率
     */
    public HttpResponse<Object> updatePayMode(PayModeReq payModeReq){
        HttpResponse<Object> httpResponse = null;
        try {
                payModeDao.updateByMode(payModeReq);
                httpResponse = BizResUtil.succ(null);
        } catch (Exception e) {
            LOGGER.error("修改支付方式的默认商户费率异常(updatePayMode)",e);
            httpResponse = BizResUtil.fail(BizResCode.SYSTEMERROR);
        }
        
        return httpResponse ;
    }
}