package com.hanyun.platform.pay.test;

import com.hanyun.platform.pay.context.YeepayChildChannelRegGlobal;
import com.yeepay.g3.sdk.yop.client.YopClient;
import com.yeepay.g3.sdk.yop.client.YopRequest;
import com.yeepay.g3.sdk.yop.client.YopResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.test
 * @Author: dewen.li
 * @Date: 2018-08-09 下午4:09
 */
public class TestYiBao {
    public static void main(String[] args) throws IOException {
//        YopRequest yopRequest = new YopRequest("BS12345678902726","TyU6c417xSFzunsHd7XH6A==","https://open.yeepay.com/yop-center");
//        yopRequest.setEncrypt(true);
//        yopRequest.setSignRet(true);
//        yopRequest.setSignAlg("sha-256");
//        yopRequest.addParam("merchantNo", "BM12345678901247");
//        yopRequest.addParam("orderAmount", "0.01");
//        yopRequest.addParam("fundAmount", "0.01");
//        yopRequest.addParam("payTool", "");
//        YopResponse response = YopClient.post("/rest/v1.0/payplus/order/consume", yopRequest);
//        System.out.println(response.getResult());

//        String path = "/Users/ldwtxwhspring/data/tmp/totp.txt";
//        File file  = new File(path);
//        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
//        try {
//            while (it.hasNext()) {
//                String line = it.nextLine();
//                System.out.println(line.toUpperCase());
//            }
//        } finally {
//            LineIterator.closeQuietly(it);
//        }

        String ramount = new BigDecimal(12000000000000000l/YeepayChildChannelRegGlobal.TRANCE_AMOUNT).setScale(2, BigDecimal.ROUND_HALF_UP).toString();

        System.out.println(ramount);

    }
}
