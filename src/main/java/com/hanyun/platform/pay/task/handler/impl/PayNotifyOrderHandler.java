/**
 * 
 */
package com.hanyun.platform.pay.task.handler.impl;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hanyun.ground.util.http.HttpClient;
import com.hanyun.ground.util.http.HttpClientPost;
import com.hanyun.ground.util.http.HttpClientResponse;
import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.ground.util.protocol.MessageId;
import com.hanyun.ground.util.protocol.http.HttpResponse;
import com.hanyun.platform.pay.consts.TaskMessageType;
import com.hanyun.platform.pay.consts.TaskQueueConsts;
import com.hanyun.platform.pay.domain.TaskQueue;
import com.hanyun.platform.pay.task.handler.HandleResult;
import com.hanyun.platform.pay.task.handler.TaskBaseHandler;

/**
 * 支付完成后通知订单
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年11月15日 下午5:54:17
 */
@Component
public class PayNotifyOrderHandler extends TaskBaseHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayNotifyOrderHandler.class);

    @Value("${order.url.base}")
    private String orderUrlBase;
    @Value("${order.url.notify}")
    private String orderUrlNotify;

    @Override
    public Integer getMessageType() {
        return TaskMessageType.PAY_NOTIFY_ORDER;
    }

    @Override
    public HandleResult handle(TaskQueue task) {
        HandleResult rst = new HandleResult(TaskQueueConsts.HANDLE_RESULT_FAIL_TRY);
        try {
            String url = orderUrlBase + orderUrlNotify;
            HttpClientPost hcp = HttpClient.post(url);
            hcp.json(task.getMessage());
            HttpClientResponse res = hcp.action();
            if (res == null) {
                return rst;
            }
            String body = res.getBody();
            if (res.status() == HttpStatus.SC_OK) {
                HttpResponse hres = JsonUtil.fromJson(body, HttpResponse.class);
                if(hres != null && MessageId.SUCCESS_CODE.equals(hres.getCode())){
                    rst.setResult(TaskQueueConsts.HANDLE_RESULT_SUCC);
                }
            }
            rst.setResultInfo(body);

        } catch (Exception e) {
            rst.setResult(TaskQueueConsts.HANDLE_RESULT_FAIL_TRY);
            LOGGER.error("PayNotifyOrderHandler handle error!", e);
        }

        return rst;
    }

}
