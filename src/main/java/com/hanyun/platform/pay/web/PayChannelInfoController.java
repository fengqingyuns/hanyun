package com.hanyun.platform.pay.web;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.domain.CibMerchant;
import com.hanyun.platform.pay.domain.CibMerchantStore;
import com.hanyun.platform.pay.service.PayChnInfoService;
import com.hanyun.platform.pay.vo.base.PageResData;
import com.hanyun.platform.pay.vo.paychninfo.PayChnInfoListReq;

/**
 * 通道信息
 * 
 * @author wangximin@hanyun.com
 * 
 * @date 2016年8月25日 上午10:54:19
 *
 */
@Controller
@RequestMapping(value = "/paychninfo")
public class PayChannelInfoController {
    private static Logger LOGGER = LoggerFactory.getLogger(PayChannelInfoController.class);
    @Resource
    private PayChnInfoService payChnInfoService;

    /**
     * 
     * 功能: 查询兴业银行商户列表 时间: 2016年8月25日11:02:27 作者: wangximin@hanyun.com
     */
    @RequestMapping(value = "/cibmerchantlist", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse<PageResData> getCIBMerChantList(@RequestBody PayChnInfoListReq payChnInfoListReq) {

        HttpResponse<PageResData> httpResponse = payChnInfoService.getCIBMerChantList(payChnInfoListReq);

        return httpResponse;
    }

    /**
     * 
     * 功能: 通过品牌id获取兴业银行商户详情 时间: 2016年8月25日11:02:27 作者: wangximin@hanyun.com
     */
    @RequestMapping(value = "/cibmerchantdetail", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse<CibMerchant> getCIBMerChantByBrandId(@RequestBody PayChnInfoListReq payChnInfoListReq) {
        HttpResponse<CibMerchant> httpResponse = payChnInfoService.getCIBMerChantByBrandId(payChnInfoListReq);

        return httpResponse;
    }

    /**
     * 
     * 功能: 通过品牌id获取兴业银行门店信息 时间: 2016年8月25日11:02:27 作者: wangximin@hanyun.com
     */
    @RequestMapping(value = "/cibmerchantstorebybrandid", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse<PageResData> getCIBMerChantStoreByBrandId(@RequestBody PayChnInfoListReq payChnInfoListReq) {

        HttpResponse<PageResData> httpResponse = payChnInfoService.getCIBMerChantStoreByBrandId(payChnInfoListReq);

        return httpResponse;
    }

    /**
     * 
     * 功能: 增加兴业银行商户信息 时间: 2016年8月25日11:02:27 作者: wangximin@hanyun.com
     */
    @RequestMapping(value = "/insertcibmerchant", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse<Object> insertCIBMerChant(@RequestBody CibMerchant cibMerchant) {

        HttpResponse<Object> httpResponse = payChnInfoService.insertCIBMerChant(cibMerchant);

        return httpResponse;
    }

    /**
     * 
     * 功能: 增加兴业银行商户门店信息 时间: 2016年8月25日11:02:27 作者: wangximin@hanyun.com
     */
    @RequestMapping(value = "/insertCIBMerChantStore", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse<Object> insertCIBMerChantStore(@RequestBody CibMerchantStore cibMerchantStore) {

        HttpResponse<Object> httpResponse = payChnInfoService.insertCIBMerChantStore(cibMerchantStore);

        return httpResponse;
    }

    /**
     * 
     * 功能: 查询出所有已经增加为兴业银行商户的品牌id 时间: 2016年8月25日11:02:27 作者: wangximin@hanyun.com
     */
    @RequestMapping(value = "/getcibmerchant/brandidlist", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse getCIBMerChantBrandIdList() {
        LOGGER.info("查询出已经增加为兴业银行商户的品牌id集合请求到达(getCIBMerChantBrandIdList) 没有参数");
        HttpResponse response = payChnInfoService.getCIBMerChantBrandIdList();
        LOGGER.info("查询出已经增加为兴业银行商户的品牌id集合请求结束(getCIBMerChantBrandIdList)");
        return response;
    }

    /**
     *
     * 功能: 更新兴业银行商户 时间: 2016年10月27日11:19:30 作者: wangximin@hanyun.com
     */
    @RequestMapping(value = "/updatecibmerchant/brandidlist", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse updateCIBMerChantBrandIdList(@RequestBody CibMerchant payChnInfoListRsp) {
        LOGGER.info("更新兴业银行商户请求到达(getCIBMerChantBrandIdList) 参数:" + payChnInfoListRsp.toString());
        HttpResponse response;
        try {

            if (StringUtils.isBlank(payChnInfoListRsp.getBrandId())) {
                LOGGER.error("更新兴业银行商户参数丢失(getCIBMerChantBrandIdList) ");
                return HttpResponse.failure(BizResCode.PARAMERROR);
            }
            response = payChnInfoService.updateCIBMerChantBrandIdList(payChnInfoListRsp);
        } catch (Exception e) {
            LOGGER.error("更新兴业银行商户异常(getCIBMerChantBrandIdList) ", e);
            response = HttpResponse.failure(BizResCode.SYSTEMERROR);
        }
        LOGGER.info("更新兴业银行商户请求结束(getCIBMerChantBrandIdList)");
        return response;
    }

    /**
     *
     * 功能: 更新兴业银行商户门店信息 时间: 2016年10月27日15:32:17 作者: wangximin@hanyun.com
     */
    @RequestMapping(value = "/updatecibmerchantstore", method = { RequestMethod.POST })
    @ResponseBody
    public HttpResponse updateCIBMerChantStore(@RequestBody CibMerchantStore payChnInfoStoreListRsp) {
        LOGGER.info("更新兴业银行商户门店信息请求到达(updateCIBMerChantStore) 参数:" + payChnInfoStoreListRsp.toString());
        HttpResponse response;
        try {

            if (StringUtils.isBlank(payChnInfoStoreListRsp.getStoreId())
                    || StringUtils.isBlank(payChnInfoStoreListRsp.getCibMchId())) {
                LOGGER.error("更新兴业银行商户门店信息参数丢失(updateCIBMerChantStore) :{}");
                return HttpResponse.failure(BizResCode.PARAMERROR);
            }
            response = payChnInfoService.updateCIBMerChantStore(payChnInfoStoreListRsp);
        } catch (Exception e) {
            LOGGER.error("更新兴业银行商户门店信息异常(updateCIBMerChantStore) ", e);
            response = HttpResponse.failure(BizResCode.SYSTEMERROR);
        }
        LOGGER.info("更新兴业银行商户门店信息请求结束(updateCIBMerChantStore)");
        return response;
    }

}
