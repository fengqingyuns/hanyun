package com.hanyun.platform.pay.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付交易流水
 * 
 * @author dewen.li
 * @email lidewen@hanyun.com
 * @date 2018-07-31 18:26:38
 */
@TableName("pay_transaction_new")
public class PayTransactionNew implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 交易流水号
	 */
	private String transId;
	/**
	 * 支付单编号
	 */
	private String payId;
	/**
	 * 品牌编号
	 */
	private String brandId;
	/**
	 * 门店编号
	 */
	private String storeId;
	/**
	 * 订单编号
	 */
	private String orderId;
	/**
	 * 操作类型:1-扣款;2-退款
	 */
	private Integer operateType;
	/**
	 * 操作金额
	 */
	private Long amount;
	/**
	 * 通道手续费
	 */
	private Long chnFee;
	/**
	 * 商户手续费
	 */
	private Long mchFee;
	/**
	 * 流水状态:0-初始;10-处理中;20-完成;30-取消;50-失败
	 */
	private Integer status;
	/**
	 * 完成时间
	 */
	private Date finishTime;
	/**
	 * 支付方式
	 */
	private String payType;
	/**
	 * 支付方式明细
	 */
	private String payTypeDetail;
	/**
	 * 支付方式类别
	 */
	private String typeCategory;
	/**
	 * 支付通道
	 */
	private String channel;
	/**
	 * 线上结算:0-是;1-否
	 */
	private Integer settleType;
	/**
	 * 通道费率(x一百万)
	 */
	private Integer chnFeeRate;
	/**
	 * 通道手续费封顶，0表示不封顶
	 */
	private Integer chnFeeMax;
	/**
	 * 商户费率(x一百万)
	 */
	private Integer mchFeeRate;
	/**
	 * 商户手续费封顶，0表示不封顶
	 */
	private Integer mchFeeMax;
	/**
	 * 来源类型：WEB、H5、APP
	 */
	private String sourceType;
	/**
	 * 终端设备号
	 */
	private String terminalDeviceNo;
	/**
	 * 终端IP
	 */
	private String terminalIp;
	/**
	 * 操作用户(如收银员编号/姓名)
	 */
	private String operateUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 订单展示编号
	 */
	private String orderDocumentId;
	/**
	 * 退款时记录源支付流水号
	 */
	private String srcPayTransId;
	/**
	 * 支付流水中的退款状态
	 */
	private Integer refundStatus;
	/**
	 * 支付流水中的已退款金额
	 */
	private Long hadRefundAmount;
	/**
	 * 优惠金额，比如抹零抹掉的金额
	 */
	private Long discountAmount;

	/**
	 * 设置：自增主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：自增主键
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：交易流水号
	 */
	public void setTransId(String transId) {
		this.transId = transId;
	}
	/**
	 * 获取：交易流水号
	 */
	public String getTransId() {
		return transId;
	}
	/**
	 * 设置：支付单编号
	 */
	public void setPayId(String payId) {
		this.payId = payId;
	}
	/**
	 * 获取：支付单编号
	 */
	public String getPayId() {
		return payId;
	}
	/**
	 * 设置：品牌编号
	 */
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	/**
	 * 获取：品牌编号
	 */
	public String getBrandId() {
		return brandId;
	}
	/**
	 * 设置：门店编号
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	/**
	 * 获取：门店编号
	 */
	public String getStoreId() {
		return storeId;
	}
	/**
	 * 设置：订单编号
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单编号
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * 设置：操作类型:1-扣款;2-退款
	 */
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
	/**
	 * 获取：操作类型:1-扣款;2-退款
	 */
	public Integer getOperateType() {
		return operateType;
	}
	/**
	 * 设置：操作金额
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	/**
	 * 获取：操作金额
	 */
	public Long getAmount() {
		return amount;
	}
	/**
	 * 设置：通道手续费
	 */
	public void setChnFee(Long chnFee) {
		this.chnFee = chnFee;
	}
	/**
	 * 获取：通道手续费
	 */
	public Long getChnFee() {
		return chnFee;
	}
	/**
	 * 设置：商户手续费
	 */
	public void setMchFee(Long mchFee) {
		this.mchFee = mchFee;
	}
	/**
	 * 获取：商户手续费
	 */
	public Long getMchFee() {
		return mchFee;
	}
	/**
	 * 设置：流水状态:0-初始;10-处理中;20-完成;30-取消;50-失败
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：流水状态:0-初始;10-处理中;20-完成;30-取消;50-失败
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：完成时间
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	/**
	 * 获取：完成时间
	 */
	public Date getFinishTime() {
		return finishTime;
	}
	/**
	 * 设置：支付方式
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
	 * 获取：支付方式
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * 设置：支付方式明细
	 */
	public void setPayTypeDetail(String payTypeDetail) {
		this.payTypeDetail = payTypeDetail;
	}
	/**
	 * 获取：支付方式明细
	 */
	public String getPayTypeDetail() {
		return payTypeDetail;
	}
	/**
	 * 设置：支付方式类别
	 */
	public void setTypeCategory(String typeCategory) {
		this.typeCategory = typeCategory;
	}
	/**
	 * 获取：支付方式类别
	 */
	public String getTypeCategory() {
		return typeCategory;
	}
	/**
	 * 设置：支付通道
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	/**
	 * 获取：支付通道
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * 设置：线上结算:0-是;1-否
	 */
	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}
	/**
	 * 获取：线上结算:0-是;1-否
	 */
	public Integer getSettleType() {
		return settleType;
	}
	/**
	 * 设置：通道费率(x一百万)
	 */
	public void setChnFeeRate(Integer chnFeeRate) {
		this.chnFeeRate = chnFeeRate;
	}
	/**
	 * 获取：通道费率(x一百万)
	 */
	public Integer getChnFeeRate() {
		return chnFeeRate;
	}
	/**
	 * 设置：通道手续费封顶，0表示不封顶
	 */
	public void setChnFeeMax(Integer chnFeeMax) {
		this.chnFeeMax = chnFeeMax;
	}
	/**
	 * 获取：通道手续费封顶，0表示不封顶
	 */
	public Integer getChnFeeMax() {
		return chnFeeMax;
	}
	/**
	 * 设置：商户费率(x一百万)
	 */
	public void setMchFeeRate(Integer mchFeeRate) {
		this.mchFeeRate = mchFeeRate;
	}
	/**
	 * 获取：商户费率(x一百万)
	 */
	public Integer getMchFeeRate() {
		return mchFeeRate;
	}
	/**
	 * 设置：商户手续费封顶，0表示不封顶
	 */
	public void setMchFeeMax(Integer mchFeeMax) {
		this.mchFeeMax = mchFeeMax;
	}
	/**
	 * 获取：商户手续费封顶，0表示不封顶
	 */
	public Integer getMchFeeMax() {
		return mchFeeMax;
	}
	/**
	 * 设置：来源类型：WEB、H5、APP
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	/**
	 * 获取：来源类型：WEB、H5、APP
	 */
	public String getSourceType() {
		return sourceType;
	}
	/**
	 * 设置：终端设备号
	 */
	public void setTerminalDeviceNo(String terminalDeviceNo) {
		this.terminalDeviceNo = terminalDeviceNo;
	}
	/**
	 * 获取：终端设备号
	 */
	public String getTerminalDeviceNo() {
		return terminalDeviceNo;
	}
	/**
	 * 设置：终端IP
	 */
	public void setTerminalIp(String terminalIp) {
		this.terminalIp = terminalIp;
	}
	/**
	 * 获取：终端IP
	 */
	public String getTerminalIp() {
		return terminalIp;
	}
	/**
	 * 设置：操作用户(如收银员编号/姓名)
	 */
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	/**
	 * 获取：操作用户(如收银员编号/姓名)
	 */
	public String getOperateUser() {
		return operateUser;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：订单展示编号
	 */
	public void setOrderDocumentId(String orderDocumentId) {
		this.orderDocumentId = orderDocumentId;
	}
	/**
	 * 获取：订单展示编号
	 */
	public String getOrderDocumentId() {
		return orderDocumentId;
	}
	/**
	 * 设置：退款时记录源支付流水号
	 */
	public void setSrcPayTransId(String srcPayTransId) {
		this.srcPayTransId = srcPayTransId;
	}
	/**
	 * 获取：退款时记录源支付流水号
	 */
	public String getSrcPayTransId() {
		return srcPayTransId;
	}
	/**
	 * 设置：支付流水中的退款状态
	 */
	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}
	/**
	 * 获取：支付流水中的退款状态
	 */
	public Integer getRefundStatus() {
		return refundStatus;
	}
	/**
	 * 设置：支付流水中的已退款金额
	 */
	public void setHadRefundAmount(Long hadRefundAmount) {
		this.hadRefundAmount = hadRefundAmount;
	}
	/**
	 * 获取：支付流水中的已退款金额
	 */
	public Long getHadRefundAmount() {
		return hadRefundAmount;
	}
	/**
	 * 设置：优惠金额，比如抹零抹掉的金额
	 */
	public void setDiscountAmount(Long discountAmount) {
		this.discountAmount = discountAmount;
	}
	/**
	 * 获取：优惠金额，比如抹零抹掉的金额
	 */
	public Long getDiscountAmount() {
		return discountAmount;
	}
	@Override
	public String toString() {
		return "PayTransactionNew [id=" + id + ", transId=" + transId + ", payId=" + payId + ", brandId=" + brandId
				+ ", storeId=" + storeId + ", orderId=" + orderId + ", operateType=" + operateType + ", amount="
				+ amount + ", chnFee=" + chnFee + ", mchFee=" + mchFee + ", status=" + status + ", finishTime="
				+ finishTime + ", payType=" + payType + ", payTypeDetail=" + payTypeDetail + ", typeCategory="
				+ typeCategory + ", channel=" + channel + ", settleType=" + settleType + ", chnFeeRate=" + chnFeeRate
				+ ", chnFeeMax=" + chnFeeMax + ", mchFeeRate=" + mchFeeRate + ", mchFeeMax=" + mchFeeMax
				+ ", sourceType=" + sourceType + ", terminalDeviceNo=" + terminalDeviceNo + ", terminalIp=" + terminalIp
				+ ", operateUser=" + operateUser + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", orderDocumentId=" + orderDocumentId + ", srcPayTransId=" + srcPayTransId + ", refundStatus="
				+ refundStatus + ", hadRefundAmount=" + hadRefundAmount + ", discountAmount=" + discountAmount + "]";
	}
	
}
