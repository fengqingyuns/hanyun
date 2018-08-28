package com.hanyun.platform.pay.service.umpay.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanyun.ground.util.date.DateFormatUtil;
import com.hanyun.platform.pay.adapt.cib.weixin.protocol.UmpayBillAllData;
import com.hanyun.platform.pay.adapt.umpay.UmPayConfig;
import com.hanyun.platform.pay.adapt.umpay.consts.UmPayConsts;
import com.hanyun.platform.pay.consts.PaymentConsts;
import com.hanyun.platform.pay.dao.PayTransactionDao;
import com.hanyun.platform.pay.dao.UmpayOrderDao;
import com.hanyun.platform.pay.domain.PayTransaction;
import com.hanyun.platform.pay.domain.StatementDiff;
import com.hanyun.platform.pay.domain.UmpayOrder;
import com.hanyun.platform.pay.logic.trade.TradeServiceAssistLogic;
import com.hanyun.platform.pay.service.cib.weixin.StatementDiffService;
import com.hanyun.platform.pay.service.umpay.UmpayBillService;
import com.hanyun.platform.pay.util.CardTypeUtil;
import com.hanyun.platform.pay.vo.tradecheck.TradeDetailCheckResultVo;

/**
 * @Description:进行联动优势刷卡进行对账 
 * @author wangjie@hanyun.com
 * @date 2016年12月18日 下午4:20:21
 */

@Service("umpayBillService")
@Transactional
public class UmpayBillServiceImpl implements UmpayBillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmpayBillServiceImpl.class);	
	@Resource
	private StatementDiffService statementDiffService;
    @Resource
    private UmPayConfig umPayConfig;
    @Resource
    private  UmpayOrderDao umpayOrderDao;
    @Resource
    private PayTransactionDao payTransactionDao;
    @Resource
    private TradeServiceAssistLogic tradeServiceAssistLogic;

	/**
	 * Title: umpayBillFile 
	 * Description:
	 * @param billDateStr
	 * @return
	 */
	@Override
	public TradeDetailCheckResultVo umpayBillFile(Date date) {
	    TradeDetailCheckResultVo result = new TradeDetailCheckResultVo(false);
        if (date == null) {
            result.setMessage("date is null");
            return result;
        }
        
        String billDateStr = DateFormatUtil.formatDateNoSep(date);
        result.setDate(billDateStr);
        result.setBrandId(null);
        result.setTotalCount(1);
        
		String billDirStr = umPayConfig.getUmpayBillDir();// 获取目录
		File fileDir = new File(billDirStr);
		if (!fileDir.exists() && !fileDir.isDirectory()) {// 判断目录
			LOGGER.info("Not found Directory : {}", fileDir);
			result.setMessage("Not found Directory :" + fileDir);
			return result;
		}

		String zipFileName = billDateStr + ".zip";// 获取CSV的文件名称
		String zipFilePath = umPayConfig.getUmpayBillDir() + zipFileName;
		LOGGER.info("Get zipFilePath:{}",zipFilePath);
		File zipFile = new File(zipFilePath);
		if(!zipFile.exists()){
		    LOGGER.error("对账失败，对账文件不存在！");
		    result.setMessage("Not found file");
            return result;
		}
		
		Map<String, Object> statementDiffMap = new HashMap<String, Object>();// 对账差异Map
		try {
		    List<UmpayBillAllData> umpayBillAllDataList = readZipFile(zipFilePath, billDateStr);// 读取ZIP文件
		    handleData(umpayBillAllDataList, statementDiffMap);
		    
		    result.setSuccCount(result.getSuccCount() + 1);
		} catch (Exception e) {
		    LOGGER.error("对账失败 !", e);
		    result.setMessage("catch exception: " + e.getMessage());
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
	private void handleData(List<UmpayBillAllData> umpayBillAllDataList,Map<String,Object> statementDiffMap) {
		if (umpayBillAllDataList != null) {
			for (UmpayBillAllData umpayBillAllData : umpayBillAllDataList) {// 获取list中umpayBillAllDataList对象
				String tradeType = umpayBillAllData.getTradeType();//交易类型： 1:消费      51:缴费  9:退货
				String settlementDate = umpayBillAllData.getSettlementDate();//结算日期
				String referenceNo = umpayBillAllData.getReferenceNo();//参考号	
				Long payamount = Long.valueOf(umpayBillAllData.getPayAmount());
			    if(UmPayConsts.TRADE_TYPE_CONSUME.equals(tradeType)){//判断是消费类型
			    	if(StringUtils.isNotBlank(settlementDate) &&  StringUtils.isNotBlank(referenceNo) ){
			    		UmpayOrder record = new UmpayOrder();
			    		Date settleDate = DateFormatUtil.parseDateNoSep(settlementDate);
			    		record.setSettleDate(settleDate);
			    		record.setWposRefNo(referenceNo);
				    	UmpayOrder	umpayOrder = umpayOrderDao.selectBySettleDateAndRefNo(record);//通过结算日期和参考号查询到交易流水号	
		    		    if(umpayOrder != null){
				    	PayTransaction tmptrans = payTransactionDao.selectByTransId(umpayOrder.getTransId());
				    	if(tmptrans != null){
				    		StatementDiff statementDiff = new StatementDiff();
				    		statementDiff.setTransId(tmptrans.getTransId());// 交易流水号
							statementDiff.setBrandId(tmptrans.getBrandId());// 品牌
							statementDiff.setStoreId(tmptrans.getStoreId());// 门店
							statementDiff.setDiffSrc(PaymentConsts.DIFF_SRC_SYSTEM_REPORT);// 差异来源 2 系统对账
							if(!tmptrans.getAmount().equals(payamount)){
								statementDiff.setDiffType(PaymentConsts.DIFF_TYPE_INCONSISTENT_AMOUNT);// 差异类型 1.金额不一致
								statementDiff.setDiffDesc("扣款金额不一致");
								if(!statementDiffMap.containsKey(tmptrans.getTransId())){//对账差异列表中不存在这个transId
								statementDiffService.addStatementDiffSubmit(statementDiff);
								statementDiffMap.put(tmptrans.getTransId(), tmptrans.getTransId());
								}	
							}
							
				    	}
				    	
						//更新联动优势订单表
				    	UmpayOrder UmpayOrderPay = new UmpayOrder();
				    	UmpayOrderPay.setTransId(tmptrans.getTransId());
				    	UmpayOrderPay.setOutMchNo(umpayBillAllData.getOutMerId());//外部商户号
				    	UmpayOrderPay.setTerminalNo(umpayBillAllData.getTerminalNumber());//终端号
				    	UmpayOrderPay.setTerminalTradeNo(umpayBillAllData.getTerminalSerialNumber());//终端流水号
				    	UmpayOrderPay.setRefNo(umpayBillAllData.getReferenceNo());//参考号
				    	UmpayOrderPay.setPayTime(DateFormatUtil.parseDateTimeNoSep(umpayBillAllData.getPayDate()));//支付日期时间
				    	UmpayOrderPay.setSettleDate(DateFormatUtil.parseDateNoSep(umpayBillAllData.getSettlementDate()));//结算日期
				    	UmpayOrderPay.setPayAmt(Long.valueOf(umpayBillAllData.getPayAmount()));//支付金额
				    	UmpayOrderPay.setSettleAmt(Long.valueOf(umpayBillAllData.getSettlementAmount()));//结算金额
				    	UmpayOrderPay.setChargeFee(Long.valueOf(umpayBillAllData.getFee()));//手续费
				    	UmpayOrderPay.setTradeType(umpayBillAllData.getTradeType());//交易类型
				    	UmpayOrderPay.setSettleType(umpayBillAllData.getSettlementType());//结算类型
				    	UmpayOrderPay.setBusinessType(umpayBillAllData.getValueAddedServiceType());//增值业务类型
				    	UmpayOrderPay.setBankCardNo(umpayBillAllData.getCardNumber());// 脱敏卡号
				    	UmpayOrderPay.setBankCardType(umpayBillAllData.getCardType());//卡类型
				    	UmpayOrderPay.setBankChannelId(umpayBillAllData.getChannelId());//通道 ID
				    	UmpayOrderPay.setUpdateTime(new Date());
				    	umpayOrderDao.updateByTransIdSelective(UmpayOrderPay);
				    	
				    	if(StringUtils.isBlank(tmptrans.getPayTypeDetail())){
				    	    tradeServiceAssistLogic.updateTransPayTypeDetailInfo(tmptrans, umpayBillAllData.getCardType());
				    	}
		    		    }
		    		   }			    	
			    }else{//否则是退款类型
			    	if(StringUtils.isNotBlank(settlementDate) &&  StringUtils.isNotBlank(referenceNo)){
			    		UmpayOrder record = new UmpayOrder();
			    		record.setSettleDate(DateFormatUtil.parseDateNoSep(settlementDate));
			    		record.setWposRefNo(referenceNo);
				    	UmpayOrder	umpayOrder = umpayOrderDao.selectBySettleDateAndRefNo(record);//通过结算日期和参考号查询到交易流水号					    	if(umpayOrder != null){
				    	PayTransaction tmptrans = payTransactionDao.selectByTransId(umpayOrder.getTransId());
				    	if(tmptrans != null){
				    		StatementDiff statementDiff = new StatementDiff();
				    		statementDiff.setTransId(tmptrans.getTransId());// 交易流水号
							statementDiff.setBrandId(tmptrans.getBrandId());// 品牌
							statementDiff.setStoreId(tmptrans.getStoreId());// 门店
							statementDiff.setDiffSrc(PaymentConsts.DIFF_SRC_SYSTEM_REPORT);// 差异来源 2 系统对账
							if(!tmptrans.getAmount().equals(payamount)){
								statementDiff.setDiffType(PaymentConsts.DIFF_TYPE_INCONSISTENT_AMOUNT);// 差异类型 1.金额不一致
								statementDiff.setDiffDesc("退款金额不一致");
								if(!statementDiffMap.containsKey(tmptrans.getTransId())){//对账差异列表中不存在这个transId
								statementDiffService.addStatementDiffSubmit(statementDiff);
								statementDiffMap.put(tmptrans.getTransId(), tmptrans.getTransId());
								}								
							}
				    	}
				    	
						//更新联动优势订单表
				    	UmpayOrder UmpayOrderRefund = new UmpayOrder();
				    	UmpayOrderRefund.setOutMchNo(umpayBillAllData.getOutMerId());//外部商户号
				    	UmpayOrderRefund.setTransId(tmptrans.getTransId());
				    	UmpayOrderRefund.setTerminalNo(umpayBillAllData.getTerminalNumber());//终端号
				    	UmpayOrderRefund.setTerminalTradeNo(umpayBillAllData.getTerminalSerialNumber());//终端流水号
				    	UmpayOrderRefund.setRefNo(umpayBillAllData.getReferenceNo());//参考号
				    	UmpayOrderRefund.setPayTime(DateFormatUtil.parseDateTimeNoSep(umpayBillAllData.getPayDate()));//支付日期时间
				    	UmpayOrderRefund.setSettleDate(DateFormatUtil.parseDateNoSep(umpayBillAllData.getSettlementDate()));//结算日期
				    	UmpayOrderRefund.setPayAmt(Long.valueOf(umpayBillAllData.getPayAmount()));//支付金额
				    	UmpayOrderRefund.setSettleAmt(Long.valueOf(umpayBillAllData.getSettlementAmount()));//结算金额
				    	UmpayOrderRefund.setChargeFee(Long.valueOf(umpayBillAllData.getFee()));//手续费
				    	UmpayOrderRefund.setTradeType(umpayBillAllData.getTradeType());//交易类型
				    	UmpayOrderRefund.setSettleType(umpayBillAllData.getSettlementType());//结算类型
				    	UmpayOrderRefund.setBusinessType(umpayBillAllData.getValueAddedServiceType());//增值业务类型
				    	UmpayOrderRefund.setBankCardNo(umpayBillAllData.getCardNumber());// 脱敏卡号
				    	UmpayOrderRefund.setBankCardType(umpayBillAllData.getCardType());//卡类型
				    	UmpayOrderRefund.setBankChannelId(umpayBillAllData.getChannelId());//通道 ID
				    	umpayOrderDao.updateByTransIdSelective(UmpayOrderRefund);
				    	if(StringUtils.isBlank(tmptrans.getPayTypeDetail())){
				    	    tradeServiceAssistLogic.updateTransPayTypeDetailInfo(tmptrans, umpayBillAllData.getCardType());
                        }
				    	}
				    }				    	
			    	
			    }
			}			
		}
	
	
	
	/**
	 * @Title: ResolveString
	 * @Description: 解析字符串
	 * @param
	 * @return void
	 * @throwss
	 */
	private UmpayBillAllData ResolveString(String lineTxt) {
		String billData = lineTxt;
		String authorizationCode = "";//信用卡授权码，储蓄卡不存在
		String[] strArry = billData.split("\\|");
		String organizationNumber = strArry[0];// 机构号
		String outMerId = strArry[1];// 外部商户号
		String merName = strArry[2];// 商户名称
		String settlementDate = strArry[3];// 结算日期
		String terminalNumber = strArry[4];// 终端号
		String tradeType = strArry[5];// 交易类型
		String cardNumber = strArry[6];// 脱敏卡号
		String payDate = strArry[7];//支付日期时间 
		String  merOrderId= strArry[8];//商户订单号 
		String terminalSerialNumber = strArry[9];// 终端流水号
		String referenceNo = strArry[10];// 参考号
		String transactionSerialNumber = strArry[11];// 交易流水号
		String payAmount = strArry[12];// 支付金额（元）
		String settlementAmount = strArry[13];// 结算金额（元）
		String fee = strArry[14];// 手续费（元）
		String agencyLevel = strArry[15];// 代理商级别
		String currentAgent = strArry[16];// 当前代理商
		String currentAgentName = strArry[17];// 当前代理商名称
		String higherLevelAgents = strArry[18];//上级代理商
		String higherLevelAgentsName = strArry[19];// 上级代理商名称
		String higherHigherLevelAgents = strArry[20];// 上上级代理商
		String higherHigherLevelName = strArry[21];// 上上级代理商名称
		String settlementType= strArry[22];// 结算类型
		String valueAddedServiceType = strArry[23];// 增值业务类型
		String cardType = strArry[24];// 卡类型
		String channelId = strArry[25];// 通道 ID
		if(!billData.endsWith("|")){//以|结尾说明是是储蓄卡
			authorizationCode = strArry[26];// 信用卡预授权码
		}		
		
		UmpayBillAllData umpayBillAllData = new UmpayBillAllData();
		umpayBillAllData.setOrganizationNumber(organizationNumber);
		umpayBillAllData.setOutMerId(outMerId);
		umpayBillAllData.setMerName(merName);
		umpayBillAllData.setSettlementDate(settlementDate);
		umpayBillAllData.setTerminalNumber(terminalNumber);
		umpayBillAllData.setTradeType(tradeType);
		umpayBillAllData.setCardNumber(cardNumber);
		umpayBillAllData.setPayDate(payDate);
		umpayBillAllData.setMerOrderId(merOrderId);
		umpayBillAllData.setTerminalSerialNumber(terminalSerialNumber);
		umpayBillAllData.setReferenceNo(referenceNo);
		umpayBillAllData.setTransactionSerialNumber(transactionSerialNumber);
		umpayBillAllData.setPayAmount(Math.round(Float.valueOf(payAmount)*100));
		umpayBillAllData.setSettlementAmount(Math.round(Float.valueOf(settlementAmount)*100));
		umpayBillAllData.setFee(Math.abs(Math.round(Float.valueOf(fee)*100)));
		umpayBillAllData.setAgencyLevel(agencyLevel);
		umpayBillAllData.setCurrentAgent(currentAgent);
		umpayBillAllData.setCurrentAgentName(currentAgentName);
		umpayBillAllData.setHigherLevelAgents(higherLevelAgents);
		umpayBillAllData.setHigherLevelAgentsName(higherLevelAgentsName);
		umpayBillAllData.setHigherHigherLevelAgents(higherHigherLevelAgents);
		umpayBillAllData.setHigherHigherLevelName(higherHigherLevelName);
		umpayBillAllData.setSettlementType(settlementType);
		umpayBillAllData.setValueAddedServiceType(valueAddedServiceType);
		umpayBillAllData.setCardType(CardTypeUtil.getCardTypeTransfer(cardType));// 00 借记卡 DEBIT_CARD 01 贷记卡 CREDIT_CARD
		umpayBillAllData.setChannelId(channelId);
		umpayBillAllData.setAuthorizationCode(authorizationCode);
 		return umpayBillAllData;
	}
	
	/**
	 * 
	* @Title: readZipFile 
	* @Description: 读取ZIP中csv文件 
	* @param  
	* @return List<UmpayBillAllData>   
	* @throws
	 */
	private List<UmpayBillAllData> readZipFile(String file, String billDateStr) throws Exception {
		List<UmpayBillAllData> umpayBillAllDataList = new ArrayList<UmpayBillAllData>();// 将每行数据整理成对象放到list中
		ZipFile zf = new ZipFile(file);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry ze;
		StringBuffer sb = new StringBuffer();
		sb.append(billDateStr);
		sb.append("_");
		sb.append(umPayConfig.getInstId());
		sb.append(".csv");
	    String csvFileName =sb.toString();
		while ((ze = zin.getNextEntry()) != null) {
			if (ze.isDirectory()) {
			} else {
				if (csvFileName.equals(ze.getName())) {
					BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze), UmPayConsts.XML_ENCODING));
					String lineTxt = null;
					int count = 0;// 第几行
					while ((lineTxt = br.readLine()) != null) {
						if (count != 0) {// 第一行是 总笔数|总金额 可以忽略
							UmpayBillAllData umpayBillAllData = ResolveString(lineTxt);
							umpayBillAllDataList.add(umpayBillAllData);
						}
						count++;
					}
					br.close();
				}
			}
		}
		zin.closeEntry();
		return umpayBillAllDataList;
	}
	
	
}

