package com.hanyun.platform.pay.service.cib.alipay.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.platform.pay.adapt.cib.alipay.CibAlipayAdapterConfig;
import com.hanyun.platform.pay.adapt.cib.alipay.consts.CibAlipayConsts;
import com.hanyun.platform.pay.adapt.cib.alipay.protocol.AlipayBillAllData;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.dao.CibAlipayOrderDao;
import com.hanyun.platform.pay.dao.CibAlipayRefundDao;
import com.hanyun.platform.pay.dao.PayTransactionDao;
import com.hanyun.platform.pay.domain.CibAlipayOrder;
import com.hanyun.platform.pay.domain.CibAlipayRefund;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.SettlementDetailReq;
import com.hanyun.platform.pay.domain.StatementDiff;
import com.hanyun.platform.pay.service.cib.alipay.CibAlipayBillService;
import com.hanyun.platform.pay.service.cib.weixin.StatementDiffService;
import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;

/**
 * @author wangjie@hanyun.com
 * @date 2017年01月05日 下午8:20:21
 */

@Service("cibAlipayBillService")
@Transactional
public class CibAlipayBillServiceImpl implements CibAlipayBillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CibAlipayBillServiceImpl.class);	
	@Resource
	private StatementDiffService statementDiffService;
	@Resource
	private CibAlipayOrderDao cibAlipayOrderDao;
	@Resource
	private CibAlipayRefundDao cibAlipayRefundDao;
    @Resource
    private CibAlipayAdapterConfig cibAlipayAdapterConfig;	
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
        
		String dateBillDirStr = cibAlipayAdapterConfig.getAlipayBillDir()+billDateStr+"/";
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
		        List<AlipayBillAllData> alipayBillAllDataList = readFile(file);// 读取文本内容并将每条记录放入List中
		        handleData(alipayBillAllDataList,statementDiffMap);
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
	private void handleData(List<AlipayBillAllData> alipayBillAllDataList,Map<String,Object> statementDiffMap) {
		if (alipayBillAllDataList != null) {
			for (AlipayBillAllData alipayBillAllData : alipayBillAllDataList) {// 获取list中alipayBillAllData对象
				String  businessType = alipayBillAllData.getBusinessType();//业务类型  交易  、退款
				SettlementDetailReq settlementDetailReq = new SettlementDetailReq();//

				if (CibAlipayConsts.BILL_BUSINESS_TYPE_REFUND.equals(businessType)) {// 业务类型  退款 扣款 
					settlementDetailReq.setWxTransId(alipayBillAllData.getTransactionId());// 支付宝交易流水号
					settlementDetailReq.setTransId(alipayBillAllData.getRefundBatchNumber());// 汉云退款交易流水号
					settlementDetailReq.setAmount(alipayBillAllData.getOrderAmount());//订单金额
					settlementDetailReq.setStatus(CibAlipayConsts.BILL_STATUS_SUCCESS);//订单状态
					settlementDetailReq.setChargeFee(alipayBillAllData.getCharges());//手续费
					settlementDetailReq.setChargeFeeRate(alipayBillAllData.getChargesRate());//费率
					
					PayTransaction tmptrans = payTransactionDao.selectByTransId(alipayBillAllData.getRefundBatchNumber());
					
					if (tmptrans != null) {
						StatementDiff statementDiff = new StatementDiff();
						statementDiff.setTransId(tmptrans.getTransId());// 交易流水号
						statementDiff.setBrandId(tmptrans.getBrandId());// 品牌
						statementDiff.setStoreId(tmptrans.getStoreId());// 门店
						statementDiff.setDiffSrc(PaymentConsts.DIFF_SRC_SYSTEM_REPORT);// 差异来源 2 系统对账
						if (!tmptrans.getAmount().equals(settlementDetailReq.getAmount())) {// 判断支付宝退款金额跟汉云退款金额是否一致
							statementDiff.setDiffType(PaymentConsts.DIFF_TYPE_INCONSISTENT_AMOUNT);// 差异类型 1.金额不一致
							statementDiff.setDiffDesc("退款金额不一致");
							if(!statementDiffMap.containsKey(tmptrans.getTransId())){//对账差异列表中不存在这个transId
							statementDiffService.addStatementDiffSubmit(statementDiff);
							statementDiffMap.put(tmptrans.getTransId(), tmptrans.getTransId());
							}

						}
                       if (!tmptrans.getStatus().equals(settlementDetailReq.getStatus())) {// 判断支付宝退款状态跟汉云退款状态是否一致
							statementDiff.setDiffType(PaymentConsts.DIFF_TYPE_INCONSISTENT_STATE);// 差异类型 2.状态不一致
							statementDiff.setDiffDesc("退款状态不一致");
							if(!statementDiffMap.containsKey(tmptrans.getTransId())){
							statementDiffService.addStatementDiffSubmit(statementDiff);
							statementDiffMap.put(tmptrans.getTransId(), tmptrans.getTransId());
							}	
						}
						
						//更新退款费率
						CibAlipayRefund cibAlipayRefund = new CibAlipayRefund();
						cibAlipayRefund.setOutRefundNo(settlementDetailReq.getTransId());
						cibAlipayRefund.setChargeFee(settlementDetailReq.getChargeFee());//退款手续费
						cibAlipayRefund.setChargeFeeRate(settlementDetailReq.getChargeFeeRate());//退款费率
						cibAlipayRefund.setRefundStatus(CibAlipayConsts.TRADE_STATE_SUCCESS);//退款状态						
						cibAlipayRefundDao.updateByOutRefundNo(cibAlipayRefund);//						
					}
				} else if(CibAlipayConsts.BILL_BUSINESS_TYPE_SUCCESS.equals(businessType)) {//交易
					settlementDetailReq.setWxTransId(alipayBillAllData.getTransactionId());// 支付宝交易流水号
					settlementDetailReq.setTransId(alipayBillAllData.getOutTradeNo());// 汉云交易流水号
					settlementDetailReq.setAmount(Long.valueOf(alipayBillAllData.getOrderAmount()));// 获取支付总金额
					settlementDetailReq.setStatus(CibAlipayConsts.BILL_STATUS_SUCCESS);// 获取交易状态
					settlementDetailReq.setChargeFee(alipayBillAllData.getCharges());// 手续费
					settlementDetailReq.setChargeFeeRate(alipayBillAllData.getChargesRate());// 费率
					
					PayTransaction tmptrans = payTransactionDao.selectByTransId(alipayBillAllData.getOutTradeNo());
					
					if (tmptrans != null) {
						StatementDiff statementDiff = new StatementDiff();
						statementDiff.setTransId(tmptrans.getTransId());// 交易流水号
						statementDiff.setBrandId(tmptrans.getBrandId());// 品牌
						statementDiff.setStoreId(tmptrans.getStoreId());// 门店
						statementDiff.setDiffSrc(PaymentConsts.DIFF_SRC_SYSTEM_REPORT);// 差异来源 2 系统对账
						
						if (!tmptrans.getAmount().equals(settlementDetailReq.getAmount())) {// 判断支付宝扣款金额跟汉云扣款金额是否一致
							 // 如果金额不一致，则需要系统提报至对账差异表中
							statementDiff.setDiffType(PaymentConsts.DIFF_TYPE_INCONSISTENT_AMOUNT);// 差异类型 1.金额不一致
							statementDiff.setDiffDesc("扣款金额不一致");
							 if(!statementDiffMap.containsKey(tmptrans.getTransId())){
								 statementDiffService.addStatementDiffSubmit(statementDiff);
								 statementDiffMap.put(tmptrans.getTransId(), tmptrans.getTransId());	
							 }
						}
						
						if (!tmptrans.getStatus().equals(settlementDetailReq.getStatus())){
							 // 如果状态不一致，则需要系统提报至对账差异表中
							statementDiff.setDiffType(PaymentConsts.DIFF_TYPE_INCONSISTENT_STATE);// 差异类型 2.状态不一致
							statementDiff.setDiffDesc("扣款状态不一致");
						    if(!statementDiffMap.containsKey(tmptrans.getTransId())){
						    	statementDiffService.addStatementDiffSubmit(statementDiff);							
								statementDiffMap.put(tmptrans.getTransId(), tmptrans.getTransId());	
						    }
							
						}
						
						// 更新费率
						CibAlipayOrder cibAlipayOrder = new CibAlipayOrder();
						cibAlipayOrder.setOutTradeNo(settlementDetailReq.getTransId());
						cibAlipayOrder.setChargeFee(settlementDetailReq.getChargeFee());//扣款手续费
						cibAlipayOrder.setChargeFeeRate(settlementDetailReq.getChargeFeeRate());//扣款费率
						cibAlipayOrder.setTradeState(CibAlipayConsts.TRADE_STATE_SUCCESS);
						cibAlipayOrderDao.updateByOutTradeNo(cibAlipayOrder);
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
	private List<AlipayBillAllData> readFile(File file) throws IOException {
		String encoding = "UTF-8";
		List<AlipayBillAllData> alipayBillAllDataList = new ArrayList<AlipayBillAllData>();// 将每行数据整理成对象放到list中
		try {
			List<String> linesList = FileUtils.readLines(file,encoding);
			if(linesList.size()>0){
				for(int i=0; i<linesList.size()-2;i++){
					if(i!=0){
						AlipayBillAllData alipayBillAllData = ResolveString(linesList.get(i));
						alipayBillAllDataList.add(alipayBillAllData);
					}
					if(i == linesList.size()-2){
						break;// 如果遇到最后标题行跳出循环
					}
				}				
			}
		} catch (FileNotFoundException e) {
            LOGGER.error("读取文件失败", e);
		}
		
		return alipayBillAllDataList;
	}

	/**
	 * @Title: ResolveString
	 * @Description: 解析字符串
	 * @param
	 * @return void
	 * @throws
	 */
	private AlipayBillAllData ResolveString(String lineTxt) {
		String billData = lineTxt;
		String  remarks = "";
		String[] strArry = billData.split(",`");
		String mchId = strArry[0].substring(1);//  商户号
		String transactionId = strArry[1];// 支付宝支付订单号
		String outTradeNo = strArry[2];// 商户订单号 
		String businessType = strArry[3];//  业务类型
		String productName = strArry[4];// 商品名称
		String  createTime = strArry[5];//  创建时间
		String  finishTime = strArry[6];// 完成时间
		String  storeId = strArry[7];// 门店编号
		String  storeName = strArry[8];// 门店名称
		String  operator = strArry[9];// 操作员
		String  deviceInfo = strArry[10];//  设备号
		String  otherAccount = strArry[11];//  对方账户
		String  orderAmount = strArry[12];// 订单金额
		String  mchAmount = strArry[13];// 商家实收金额 
		String  alipayRed = strArry[14];// 支付宝红包
		String  jifenbao = strArry[15];// 集分宝
		String  couponFee = strArry[16];// 支付宝优惠 代金券或立减优惠金额
		String  mchCoupon = strArry[17];//  商家优惠
		String  certificates = strArry[18];//券核销金额
		String  ticketName = strArry[19];// 券名称
		String  mchRed = strArry[20];// 商家红包消费金额（元
		String  cardConsumption = strArry[21];// 卡消费金额（元）   	
		String  refundBatchNumber = strArry[22];// 退款批次号			
		String  charges = strArry[23];// 手续费
		String  chargesRate = strArry[24];// 费率
		String  netReceipts = strArry[25];// 实收净额
		String  tradeType = strArry[26];// 交易方式
		if(!billData.endsWith(",`")){//以,`结尾说明备注为空
			remarks = strArry[27];//备注 
		}
		
		AlipayBillAllData alipayBillAllData = new AlipayBillAllData();
		alipayBillAllData.setMchId(mchId);
		alipayBillAllData.setTransactionId(transactionId);
		alipayBillAllData.setOutTradeNo(outTradeNo);
		alipayBillAllData.setBusinessType(businessType);
		alipayBillAllData.setProductName(productName);
		alipayBillAllData.setCreateTime(DateFormatUtil.parseDateTime(createTime));
		alipayBillAllData.setFinishTime(DateFormatUtil.parseDateTime(finishTime));
		alipayBillAllData.setStoreId(storeId);
		alipayBillAllData.setStoreName(storeName);
		alipayBillAllData.setOperator(operator);
		alipayBillAllData.setDeviceInfo(deviceInfo);
		alipayBillAllData.setOtherAccount(otherAccount);
		alipayBillAllData.setOrderAmount((long)Math.round(Float.valueOf(orderAmount)*100));
		alipayBillAllData.setMchAmount((long)Math.round(Float.valueOf(mchAmount)*100));
		alipayBillAllData.setAlipayRed((long)Math.round(Float.valueOf(alipayRed)*100));
		alipayBillAllData.setJifenbao((long)Math.round(Float.valueOf(jifenbao)*100));
		alipayBillAllData.setCouponFee((long)Math.round(Float.valueOf(couponFee)*100));
		alipayBillAllData.setMchCoupon((long)Math.round(Float.valueOf(mchCoupon)*100));
		alipayBillAllData.setCertificates((long)Math.round(Float.valueOf(certificates)*100));
		alipayBillAllData.setTicketName(ticketName);
		alipayBillAllData.setMchRed((long)Math.round(Float.valueOf(mchRed)*100));
		alipayBillAllData.setCardConsumption((long)Math.round(Float.valueOf(cardConsumption)*100));
		alipayBillAllData.setRefundBatchNumber(refundBatchNumber);
		alipayBillAllData.setCharges((long)Math.round(Float.valueOf(charges)*100));
		double rate1 = Double.parseDouble(chargesRate) * PaymentConsts.FEE_RATE_MULTIPLIER;
		long rate2 = Math.round(rate1);
		alipayBillAllData.setChargesRate(rate2);
		alipayBillAllData.setNetReceipts((long)Math.round(Float.valueOf(netReceipts)*100));
		alipayBillAllData.setTradeType(tradeType);
		alipayBillAllData.setRemarks(remarks);
		return alipayBillAllData;
	}

}
