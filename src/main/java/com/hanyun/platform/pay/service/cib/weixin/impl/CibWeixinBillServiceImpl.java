package com.hanyun.platform.pay.service.cib.weixin.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.platform.pay.adapt.cib.weixin.CibAdapterConfig;
import com.hanyun.platform.pay.adapt.cib.weixin.consts.CibWeiXinConsts;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.BillAllData;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.dao.CibWeixinOrderDao;
import com.hanyun.platform.pay.dao.CibWeixinRefundDao;
import com.hanyun.platform.pay.dao.PayTransactionDao;
import com.hanyun.platform.pay.domain.CibWeixinOrder;
import com.hanyun.platform.pay.domain.CibWeixinRefund;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.SettlementDetailReq;
import com.hanyun.platform.pay.domain.StatementDiff;
import com.hanyun.platform.pay.service.cib.weixin.CibWeixinBillService;
import com.hanyun.platform.pay.service.cib.weixin.StatementDiffService;
import com.hanyun.platform.pay.util.StateTransitionUtil;
import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;

/**
 * @author wangjie@hanyun.com
 * @date 2016年8月31日 下午8:20:21
 */

@Service("cibWeixinBillService")
@Transactional
public class CibWeixinBillServiceImpl implements CibWeixinBillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CibWeixinBillServiceImpl.class);	
	@Resource
	private StatementDiffService statementDiffService;
	@Resource
	private CibWeixinOrderDao cibWeixinOrderDao;
	@Resource
	private CibWeixinRefundDao cibWeixinRefundDao;
    @Resource
    private CibAdapterConfig cibAdapterConfig;	
    @Resource
    private PayTransactionDao payTransactionDao;

	public TradeDetailCheckResultVo cibBillFile(Date date, String brandId) {
	    TradeDetailCheckResultVo result = new TradeDetailCheckResultVo(false);
        if (date == null) {
            result.setMessage("date is null");
            return result;
        }
        if(StringUtils.isBlank(brandId)){
            brandId = null;
        }
        String billDateStr = DateFormatUtil.formatDateNoSep(date);  
        result.setDate(billDateStr);
        result.setBrandId(brandId);  
        
		String dateBillDirStr = cibAdapterConfig.getWeixinBillDir()+billDateStr+"/";
		File fileDir = new File(dateBillDirStr);
		if(!fileDir.exists() && !fileDir.isDirectory()){//判断目录
			LOGGER.error("Not found Directory : {}",fileDir);
			result.setMessage("Not found Directory :" + fileDir);
			return result;
		}
		
		File[] fileList = fileDir.listFiles();//获取目录下的所有文件
		if(fileList == null || fileList.length <= 0){
		    LOGGER.error("对账失败，对账文件不存在！");
		    result.setMessage("Not found file");
		    return result;
		}
		
		if(StringUtils.isBlank(brandId)){
            result.setTotalCount(fileList.length);
        }else{
            result.setTotalCount(1);
        }
		
		Map<String,Object> statementDiffMap = new HashMap<String,Object>();//对账差异Map
		for(File file : fileList){
		    String curBrandId = file.getName();
            if(StringUtils.isNotBlank(brandId) && !curBrandId.equals(brandId)){
                continue;
            }
            
		    try {
		        List<BillAllData> billAllDataList = readFile(file);// 读取文本内容并将每条记录放入List中
		        handleData(billAllDataList,statementDiffMap);
		        result.setSuccCount(result.getSuccCount() + 1);
		    } catch (Exception e) {
		        LOGGER.error("对账失败 !, brandId: {}", curBrandId);
                LOGGER.error("对账失败 !", e);
                result.getFailBrandIdList().add(curBrandId);
		    }
		}
		
		if(result.getTotalCount() == result.getSuccCount()){
            result.setSuccess(true);
        }
        
        return result;
	}

	/**
	 * @Title: handleData 
	 * @Description: 处理数据 
	 * @param 
	 * @return void 
	 * @throws
	 */
	private void handleData(List<BillAllData> billAllDataList,Map<String,Object> statementDiffMap) {
		if (billAllDataList != null) {
			for (BillAllData billAllData : billAllDataList) {// 获取list中billAllData对象
				String  tradeState = billAllData.getTradeState();//交易类型   REFUND：退款    SUCCESS：扣款成功
				SettlementDetailReq settlementDetailReq = new SettlementDetailReq();//

				if (CibWeiXinConsts.BILL_TYPE_REFUND.equals(tradeState)) {// 交易类型   REFUND：退款    SUCCESS：扣款  REVOKED：撤销
					settlementDetailReq.setWxTransId(billAllData.getTransactionId());// 微信交易流水号
					settlementDetailReq.setTransId(billAllData.getOutRefundNo());// 汉云退款交易流水号
					settlementDetailReq
							.setStatus(StateTransitionUtil.getWxStatusTransfer(billAllData.getRefundStatus()));// 如果是退款，获取退款的状态
					settlementDetailReq.setAmount(Long.valueOf(billAllData.getRefundFee()));// 获取退费金额
					settlementDetailReq.setChargeFee(billAllData.getCharges());// 手续费
					settlementDetailReq.setChargeFeeRate(billAllData.getChargesRate());// 费率
					
					PayTransaction tmptrans = payTransactionDao.selectByTransId(billAllData.getOutRefundNo());
					
					if (tmptrans != null) {
						StatementDiff statementDiff = new StatementDiff();
						statementDiff.setTransId(tmptrans.getTransId());// 交易流水号
						statementDiff.setBrandId(tmptrans.getBrandId());// 品牌
						statementDiff.setStoreId(tmptrans.getStoreId());// 门店
						statementDiff.setDiffSrc(PaymentConsts.DIFF_SRC_SYSTEM_REPORT);// 差异来源 2 系统对账
						if (!tmptrans.getAmount().equals(settlementDetailReq.getAmount())) {// 判断微信退款金额跟汉云退款金额是否一致
							statementDiff.setDiffType(PaymentConsts.DIFF_TYPE_INCONSISTENT_AMOUNT);// 差异类型 1.金额不一致
							statementDiff.setDiffDesc("退款金额不一致");
							if(!statementDiffMap.containsKey(tmptrans.getTransId())){//对账差异列表中不存在这个transId
							statementDiffService.addStatementDiffSubmit(statementDiff);
							statementDiffMap.put(tmptrans.getTransId(), tmptrans.getTransId());
							}

						}
                       if (!tmptrans.getStatus().equals(settlementDetailReq.getStatus())) {// 判断微信退款状态跟汉云退款状态是否一致
							statementDiff.setDiffType(PaymentConsts.DIFF_TYPE_INCONSISTENT_STATE);// 差异类型 2.状态不一致
							statementDiff.setDiffDesc("退款状态不一致");
							if(!statementDiffMap.containsKey(tmptrans.getTransId())){
							statementDiffService.addStatementDiffSubmit(statementDiff);
							statementDiffMap.put(tmptrans.getTransId(), tmptrans.getTransId());
							}	
						}
						
						//更新退款费率
						CibWeixinRefund cibWeixinRefund = new CibWeixinRefund();
						cibWeixinRefund.setOutRefundNo(settlementDetailReq.getTransId());
						cibWeixinRefund.setChargeFee(settlementDetailReq.getChargeFee());//退款手续费
						cibWeixinRefund.setChargeFeeRate(settlementDetailReq.getChargeFeeRate());//退款费率
						cibWeixinRefundDao.updateByOutRefundNo(cibWeixinRefund);//						
					}
				} else if(CibWeiXinConsts.BILL_TYPE_SUCCESS.equals(tradeState)) {//扣款
					settlementDetailReq.setWxTransId(billAllData.getTransactionId());// 微信交易流水号
					settlementDetailReq.setTransId(billAllData.getOutTradeNo());// 汉云交易流水号
					settlementDetailReq.setAmount(Long.valueOf(billAllData.getTotalFee()));// 获取支付总金额
					settlementDetailReq.setStatus(StateTransitionUtil.getWxStatusTransfer(billAllData.getTradeState()));// 获取交易状态
					settlementDetailReq.setChargeFee(billAllData.getCharges());// 手续费
					settlementDetailReq.setChargeFeeRate(billAllData.getChargesRate());// 费率
					
					PayTransaction tmptrans = payTransactionDao.selectByTransId(billAllData.getOutTradeNo());
					
					if (tmptrans != null) {
						StatementDiff statementDiff = new StatementDiff();
						statementDiff.setTransId(tmptrans.getTransId());// 交易流水号
						statementDiff.setBrandId(tmptrans.getBrandId());// 品牌
						statementDiff.setStoreId(tmptrans.getStoreId());// 门店
						statementDiff.setDiffSrc(PaymentConsts.DIFF_SRC_SYSTEM_REPORT);// 差异来源 2 系统对账
						
						if (!tmptrans.getStatus().equals(settlementDetailReq.getStatus())){
							 // 如果状态不一致，则需要系统提报至对账差异表中
							statementDiff.setDiffType(PaymentConsts.DIFF_TYPE_INCONSISTENT_STATE);// 差异类型 2.状态不一致
							statementDiff.setDiffDesc("扣款状态不一致");
						    if(!statementDiffMap.containsKey(tmptrans.getTransId())){
						    	statementDiffService.addStatementDiffSubmit(statementDiff);							
								statementDiffMap.put(tmptrans.getTransId(), tmptrans.getTransId());	
						    }
							
						}
						
						if (!tmptrans.getAmount().equals(settlementDetailReq.getAmount())) {// 判断微信扣款金额跟汉云扣款金额是否一致
							 // 如果金额不一致，则需要系统提报至对账差异表中
							statementDiff.setDiffType(PaymentConsts.DIFF_TYPE_INCONSISTENT_AMOUNT);// 差异类型 1.金额不一致
							statementDiff.setDiffDesc("扣款金额不一致");
							 if(!statementDiffMap.containsKey(tmptrans.getTransId())){
								 statementDiffService.addStatementDiffSubmit(statementDiff);
								 statementDiffMap.put(tmptrans.getTransId(), tmptrans.getTransId());	
							 }
						}
						// 更新费率
						CibWeixinOrder cibWeixinOrder = new CibWeixinOrder();
						cibWeixinOrder.setOutTradeNo(settlementDetailReq.getTransId());
						cibWeixinOrder.setChargeFee(settlementDetailReq.getChargeFee());//扣款手续费
						cibWeixinOrder.setChargeFeeRate(settlementDetailReq.getChargeFeeRate());//扣款费率
						cibWeixinOrderDao.updateByOutTradeNo(cibWeixinOrder);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @Title: readFile 
	 * @Description: 读文件 
	 * @param 
	 * @return void 
	 * @throws
	 */
	private List<BillAllData> readFile(File file) throws IOException {
		String encoding = "UTF-8";
		List<BillAllData> billAllDataList = new ArrayList<BillAllData>();// 将每行数据整理成对象放到list中
		try {
			List<String> linesList = FileUtils.readLines(file,encoding);
			if(linesList.size()>0){
				for(int i=0; i<linesList.size()-2;i++){
					if(i!=0){
						BillAllData billAllData = ResolveString(linesList.get(i));
						billAllDataList.add(billAllData);
					}
					if(i == linesList.size()-2){
						break;// 如果遇到最后标题行跳出循环
					}
				}				
			}
		} catch (FileNotFoundException e) {
            LOGGER.error("读取文件失败", e);
		}

		return billAllDataList;

	}

	/**
	 * @Title: ResolveString
	 * @Description: 解析字符串
	 * @param
	 * @return void
	 * @throwss
	 */
	private BillAllData ResolveString(String lineTxt) {
		String billData = lineTxt;
		String[] strArry = billData.split(",`");
		String tredeTime = strArry[0].substring(1);// 交易时间
		String appId = strArry[1];// 公众账号ID
		String mchId = strArry[2];// 商户号
		String deviceInfo = strArry[3];// 设备号
		String transactionId = strArry[4];// 微信订单号
		String outTradeNo = strArry[5];// 商户订单号
		String openId = strArry[6];// 用户标识
		String tradeType = strArry[7];// 交易类型
		String tradeStatus = strArry[8];// 交易状态
		String bankType = strArry[9];// 付款银行
		String feeType = strArry[10];// 货币种类
		String totalFee = strArry[11];// 总金额
		String redPacket = strArry[12];// 代金券或立减券优惠金额
		String refundId = strArry[13];// 微信退款单号
		String outRefundNo = strArry[14];// 商户退款单号
		String refundFee = strArry[15];// 退款金额
		String redPacketRefund = strArry[16];// 代金券或立减优惠退款金额
		String refundType = strArry[17];// 退款类型
		String refundStatus = strArry[18];// 退款状态
		String goodsName = strArry[19];// 商品名称
		String detail = strArry[20];// 商户数据包
		String fee = strArry[21];// 手续费
		String rate = strArry[22];// 费率
		BillAllData billAllData = new BillAllData();
		billAllData.setTransactionId(transactionId);// 微信订单号
		billAllData.setOutTradeNo(outTradeNo);// 商户订单号
		billAllData.setTradeType(tradeType);// 交易类型
		billAllData.setTradeState(tradeStatus);// 交易状态
		billAllData.setTotalFee(Integer.parseInt(totalFee));//总金额(分)
		billAllData.setRefundId(refundId);// 微信退款单号
		billAllData.setOutRefundNo(outRefundNo);// 商户退款单号
		billAllData.setRefundFee(Integer.parseInt(refundFee));//退费金额（分）
		billAllData.setRefundType(refundType);// 退款类型
		billAllData.setRefundStatus(refundStatus);// 退款状态
		billAllData.setCharges(Long.parseLong(fee));//手续费 按分计算
		double rate1 = Double.parseDouble(rate) * PaymentConsts.FEE_RATE_MULTIPLIER;
		long rate2 = Math.round(rate1);
		billAllData.setChargesRate(rate2);// 费率
		return billAllData;
	}

}
