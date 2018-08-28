package com.hanyun.platform.pay.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.service.PayTradeYbService;
import com.hanyun.platform.pay.service.YbPayRefundTradeService;
import com.hanyun.platform.pay.util.BizResUtil;
import com.hanyun.platform.pay.vo.trade.TradePreCreateReq;
import com.hanyun.platform.pay.vo.trade.TradePreCreateRes;
import com.hanyun.platform.pay.vo.trade.TradeRefundReq;
import com.hanyun.platform.pay.vo.trade.TradeRefundRes;

/**
 * 
 * @date 2018年8月1日
 * 
 * @apiDefine common 易宝支付
 * @author litao@hanyun.com
 */
@Controller
@RequestMapping(value = "ybpay/inner")
public class PayInnerTradeYbController {
	private static Logger LOGGER = LoggerFactory.getLogger(PayInnerTradeYbController.class);

	@Resource
	private PayTradeYbService payTradeServiceYbImpl;
	@Resource
	private YbPayRefundTradeService ybPayRefundTradeService;

	/**
	 * 
	 * @api {post} /ybpay/inner 支付单
	 * @apiDescription 创建预支付单
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
	 * 
	 
	 *
	 * @apiVersion 1.0.0
	 * @apiErrorExample {json} 错误返回值: { "code": 10003, "msg":
	 *                  "ParametersError [Method]:get_tests参数错误!", "error": {
	 *                  "id": "", "page": "", "perpage": "" }, "status": "fail"
	 *                  }
	 * @apiSuccessExample {json} 正确返回值: { "code": 0, "msg": "OK ", "data": [ {
	 *                    "orderId": "622051004185471233", "payId": "",
	 *                    "payStatus": "", "orderAmount": "", "payAmount": "",
	 *                    "hadPayAmount": ", "hadDiscountAmount": "",
	 *                    "curPayAmount": "", "curDiscountAmount": "",
	 *                    "transId": "", "transStatus": "", "payType": "",
	 *                    "extdata":"" } ], "status": "ok", "count": "" }
	 */
	@PostMapping(value = "/ybprecreate")
	public HttpResponse<TradePreCreateRes> preCreate(@RequestBody TradePreCreateReq req) {
		LOGGER.info(" yb  inner trade precreate req: {}", JsonUtil.toJson(req));

		HttpResponse<TradePreCreateRes> res = null;

		try {
			res = payTradeServiceYbImpl.preCreate(req);

			LOGGER.info("yb inner trade precreate res: {}", JsonUtil.toJson(res));
		} catch (Exception e) {
			LOGGER.error("yb inner preCreate error !", e);
			res = BizResUtil.fail(e, null);
		}

		return res;
	}

	/**
	 * 退款
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
