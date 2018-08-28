package com.hanyun.platform.pay.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.service.PayTradeMchInfoService;
import com.hanyun.platform.pay.service.PayTradeQueryService;
import com.hanyun.platform.pay.service.PayTradeYbService;
import com.hanyun.platform.pay.service.YbPayRefundTradeService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.vo.trade.TradePayReq;
import com.hanyun.platform.pay.vo.trade.TradePayRes;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradePreCreateRes;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;
import com.hanyun.platform.pay.vo.trade.TradeRefundRes;

/**
 * 
 * @date 2018年8月2日
 * 
 * @apiDefine common 支付相关
 * @author litao@hanyun.com
 */
@RestController
@RequestMapping("/Ybtrade")
public class PayTradeYbController {
	private static Logger LOGGER = LoggerFactory.getLogger(PayTradeYbController.class);

	@Resource
	private PayTradeMchInfoService payTradeMchInfoService;
	@Resource
	private PayTradeYbService payTradeYbService;
	@Resource
	private YbPayRefundTradeService ybPayRefundTradeService;

	/**
	 * 
	 * @api {post} /ybpay/inner 支付单
	 * @apiDescription 创建支付单
	 * @apiGroup common
	 *
	 * @apiParam {String} brandId 品牌Id
	 * @apiParam {String} storeId 门店Id
	 * @apiParam {String} orderDocumentId 订单显示编号
	 * @apiParam {String} orderTime 订单时间
	 * @apiParam {String} sourceType 来源类型
	 * @apiParam {String} terminalDeviceNo 终端设备号
	 * @apiParam {String} terminalIp 终端IP地址
	 * @apiParam {String} operateUser 操作用户信息
	 * @apiParam {Long} orderAmount 订单金额
	 * @apiParam {Long} payAmount 订单需付金额
	 * @apiParam {String} payType 支付方式
	 * @apiParam {Long} curPayAmount 本次支付金额
	 * @apiParam {Long} curDiscountAmount 本次优惠金额
	 * @apiParam {String} orderDesc 订单描述，最大32个字符，格式：门店名-门店类型或商品类型
	 * @apiParam {String} wxAppid 微信支付-商户微信公众账号ID
	 * @apiParam {String} wxOpenid 微信支付-用户在商户appid下的唯一标识
	 * @apiVersion 1.1.0
	 * @apiErrorExample {json} 错误返回值: {
	 * 						"SYSTEMERROR":"系统错误"
	 *                  }
	 * @apiSuccessExample {json} 正确返回值: {
	 * 			"data": [
	 * 						 {
	 *                    		"orderId": "AI1BC5C85C1BCB4F3D9343EA8A239DFC00",
	 *                    		"payId": "100201807261642550117884411",
	 *                    		"payStatus": "20",
	 *                    		"orderAmount": "100",
	 *                    		"payAmount": "100",
	 *                    		"hadPayAmount": "100",
	 *                    		"hadDiscountAmount": "0",
	 *                    		"curPayAmount": "100",
	 *                    		"curDiscountAmount": "0",
	 *                    		"transId": "110201807261642550171548503",
	 *                    		"transStatus": "20",
	 *                    		"payType": "CASH",
	 *                    		"extdata":""
	 *                     }
	 *                    ]
	 *                     				}
	 */
	@PostMapping(value = "/pay")
	public HttpResponse<TradePayRes> pay(@RequestBody TradePayReq req) {
		LOGGER.info("Yb pay req: {}", JsonUtil.toJson(req));

		HttpResponse<TradePayRes> res = null;
		try {
			res = payTradeYbService.pay(req);
			LOGGER.info("Yb pay res: {}", JsonUtil.toJson(res));
		} catch (Exception e) {
			LOGGER.error("Yb pay error !", e);
			res = BizResUtil.fail(e, null);
		}

		return res;
	}

	@PostMapping(value = "/prepay")
	public HttpResponse<TradePreCreateRes> prePay(@RequestBody TradePreCreateReq req) {
		LOGGER.info("Yb prePay req: {}", JsonUtil.toJson(req));

		HttpResponse<TradePreCreateRes> res = null;
		
		try {
			res = payTradeYbService.preCreate(req);
			LOGGER.info("Yb prePay res: {}", JsonUtil.toJson(res));
		} catch (Exception e) {
			LOGGER.error("Yb prePay error !", e);
			res = BizResUtil.fail(e, null);
		}

		return res;
	}

	/**
	 * 
	 * @api {post} /ybpay/inner 退款单
	 * @apiDescription 创建退款单
	 * @apiGroup common
	 *
	 * @apiParam {String} brandId 品牌Id
	 * @apiParam {String} storeId 门店Id
	 * @apiParam {String} orderId 订单Id
	 * @apiParam {String} orderDocumentId 订单显示编号
	 * @apiParam {String} orderTime 订单时间
	 * @apiParam {String} sourceType 来源类型
	 * @apiParam {String} terminalDeviceNo 终端设备号
	 * @apiParam {String} terminalIp 终端IP地址
	 * @apiParam {String} operateUser 操作用户信息
	 * @apiParam {Integer} refundMode 退款模式
	 * @apiParam {Long} refundAmount 退款金额
	 * @apiParam {Long} orderAmount 订单金额
	 * @apiParam {Long} payAmount 订单需付金额
	 * @apiParam {String} payType 支付方式
	 * @apiVersion 1.2.0
	 * @apiErrorExample {json} 错误返回值: {
	 * 						"SYSTEMERROR":"系统错误"
	 *                  }
	 * @apiSuccessExample {json} 正确返回值: {
	 * 			"data": [
	 * 						 {
	 *                    		"orderId": "AI1BC5C85C1BCB4F3D9343EA8A239DFC00",
	 *                    		"payId": "100201807261642550117884411",
	 *                    		"payStatus": "20",
	 *                    		"orderAmount": "100",
	 *                    		"payAmount": "100",
	 *                    		"hadPayAmount": "100",
	 *                    		"hadDiscountAmount": "0",
	 *                    		"curPayAmount": "100",
	 *                    		"curDiscountAmount": "0",
	 *                    		"transId": "110201807261642550171548503",
	 *                    		"transStatus": "20",
	 *                    		"payType": "CASH",
	 *                    		"extdata":""
	 *                     }
	 *                    ]
	 *                     				}
	 */
	@RequestMapping(value = "/refund", method = { RequestMethod.POST })
	@ResponseBody
	public HttpResponse<TradeRefundRes> refund(@RequestBody TradeRefundReq req) {
		LOGGER.info(" Yb inner trade refund req: {}", JsonUtil.toJson(req));

		HttpResponse<TradeRefundRes> res = null;
		try {
			res = ybPayRefundTradeService.refund(req);
			LOGGER.info("Yb inner trade refund res: {}", JsonUtil.toJson(res));
		} catch (Exception e) {
			LOGGER.error("Yb inner refund error !", e);
			res = BizResUtil.fail(e, null);
		}

		return res;
	}
}
