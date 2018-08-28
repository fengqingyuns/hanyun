package com.hanyun.platform.pay.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付单
 * 
 * @author dewen.li
 * @email lidewen@hanyun.com
 * @date 2018-07-31 18:26:38
 */

public class PaymentNew implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	
	private Long id;
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
	 * 业务线
	 */
	private String businessType;
	/**
	 * 订单编号
	 */
	private String orderId;
	/**
	 * 订单时间
	 */
	private Date orderTime;
	/**
	 * 订单金额
	 */
	private Long orderAmount;
	/**
	 * 支付金额
	 */
	private Long payAmount;
	/**
	 * 退款金额
	 */
	private Long refundAmount;
	/**
	 * 支付状态
	 */
	private Integer payStatus;
	/**
	 * 支付完成时间
	 */
	private Date payTime;
	/**
	 * 支付方式
	 */
	private String payType;
	/**
	 * 支付方式类别
	 */
	private String typeCategory;
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
	 * 已付金额
	 */
	private Long hadPayAmount;
	/**
	 * 已完成支付次数
	 */
	private Integer hadPayCount;
	/**
	 * 退款时间
	 */
	private Date refundTime;
	/**
	 * 已优惠金额，比如抹零抹掉的金额
	 */
	private Long hadDiscountAmount;

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
	 * 设置：业务线
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	/**
	 * 获取：业务线
	 */
	public String getBusinessType() {
		return businessType;
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
	 * 设置：订单时间
	 */
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	/**
	 * 获取：订单时间
	 */
	public Date getOrderTime() {
		return orderTime;
	}
	/**
	 * 设置：订单金额
	 */
	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}
	/**
	 * 获取：订单金额
	 */
	public Long getOrderAmount() {
		return orderAmount;
	}
	/**
	 * 设置：支付金额
	 */
	public void setPayAmount(Long payAmount) {
		this.payAmount = payAmount;
	}
	/**
	 * 获取：支付金额
	 */
	public Long getPayAmount() {
		return payAmount;
	}
	/**
	 * 设置：退款金额
	 */
	public void setRefundAmount(Long refundAmount) {
		this.refundAmount = refundAmount;
	}
	/**
	 * 获取：退款金额
	 */
	public Long getRefundAmount() {
		return refundAmount;
	}
	/**
	 * 设置：支付状态
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * 获取：支付状态
	 */
	public Integer getPayStatus() {
		return payStatus;
	}
	/**
	 * 设置：支付完成时间
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	/**
	 * 获取：支付完成时间
	 */
	public Date getPayTime() {
		return payTime;
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
	 * 设置：已付金额
	 */
	public void setHadPayAmount(Long hadPayAmount) {
		this.hadPayAmount = hadPayAmount;
	}
	/**
	 * 获取：已付金额
	 */
	public Long getHadPayAmount() {
		return hadPayAmount;
	}
	/**
	 * 设置：已完成支付次数
	 */
	public void setHadPayCount(Integer hadPayCount) {
		this.hadPayCount = hadPayCount;
	}
	/**
	 * 获取：已完成支付次数
	 */
	public Integer getHadPayCount() {
		return hadPayCount;
	}
	/**
	 * 设置：退款时间
	 */
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	/**
	 * 获取：退款时间
	 */
	public Date getRefundTime() {
		return refundTime;
	}
	/**
	 * 设置：已优惠金额，比如抹零抹掉的金额
	 */
	public void setHadDiscountAmount(Long hadDiscountAmount) {
		this.hadDiscountAmount = hadDiscountAmount;
	}
	/**
	 * 获取：已优惠金额，比如抹零抹掉的金额
	 */
	public Long getHadDiscountAmount() {
		return hadDiscountAmount;
	}
}
