package com.hanyun.platform.pay.adapt.yeepay.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hanyun.platform.pay.context.YeepayChildChannelRegGlobal;
import com.yeepay.g3.sdk.yop.client.YopClient;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay.util
 * @Author: dewen.li
 * @Date: 2018-08-09 上午10:07
 */
public class UsualTools {

    /**
     * 数据加密，算法（DES）
     *
     * @param data 原数据
     *            要进行加密的数据
     * @return 加密后的数据
     */
    public static String encryptBasedDes(String data) {
        String encryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(YeepayChildChannelRegGlobal.DES_KEY.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);
            // 加密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);
            // 加密，并把字节数组编码成字符串
            encryptedData = new sun.misc.BASE64Encoder().encode(cipher
                    .doFinal(data.getBytes()));
        } catch (Exception e) {
            // log.error("加密错误，错误信息：", e);
            throw new RuntimeException("加密错误，错误信息：", e);
        }
        return encryptedData;
    }

    /**
     * 数据解密，算法（DES）
     *
     * @param cryptData 解密数据
     *            加密数据
     * @return 解密后的数据
     */
    public static String decryptBasedDes(String cryptData) {
        String decryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(YeepayChildChannelRegGlobal.DES_KEY.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);
            // 解密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key, sr);
            // 把字符串解码为字节数组，并解密
            decryptedData = new String(
                    cipher.doFinal(new sun.misc.BASE64Decoder()
                            .decodeBuffer(cryptData)));
        } catch (Exception e) {
            // log.error("解密错误，错误信息：", e);
            throw new RuntimeException("解密错误，错误信息：", e);
        }
        return decryptedData;
    }

    /**
     *
     * @param input 输入的长整型数
     * @param baseValue 除数基值
     * @return String  返回保留两位小数点的Double数的字符串
     */
    public static String  tranceLongToString(Long input,int baseValue){
    	
    	BigDecimal in = new BigDecimal(input);
		BigDecimal base = new BigDecimal(baseValue);
		BigDecimal res  = new BigDecimal("0");
		
		res = in.divide(base).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		String num = String.valueOf(res);
		
        return num;
		
        //格式化小数
      //  DecimalFormat df = new DecimalFormat("0.00");

        //返回的是String类型
      //  String num = df.format(input/baseValue);
		
    }
    //将获取到的response转换成json格式
    public static Map<String, Object> parseResponse(String yopresponse) {

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap = JSON.parseObject(yopresponse,
                new TypeReference<TreeMap<String, Object>>() {
                });
        System.out.println("将response转化为map格式之后: " + jsonMap);
        return jsonMap;
    }


    public static void main(String[] args){
        String keyWord = null;
        String bodyData = "%7B%22doSignature%22%3A%22true%22%2C%22signatureAlg%22%3A%22SHA1%22%2C%22encryption%22%3A%22vhCt8zQoIRym8OAZm7aDRJwMcgRzCq%2B2ietoYyQ8jJjSU5%2BdQciwaT4XiKi4MR13UxMPfzBnCFBOlCVnxorWP6V%5C%2FPEAQO23DsP8rM2VJ6Aocris40wlFfudvgmtQ%5C%2FujU0pPmIdSKKLeAMYLEg3L8oTB6jSd81Bg2oLmG3JiSuB21yz6eH07cvwBUpBPx24JJQjNYuaUTCF2lRh%2Ba1XfNpsm5CLPMBzmgLKFRyDZ9%2BiuJDPrtC797vCMadp7nRhUkV0lG%2BiPngNxU88TwhQcGREY3wNpIf881neM4n1Ux0SbstqZbBSiWcCdCo1%5C%2FWSUn76Lj2oN9jEZLtU54jFTrRoRUa29GAW4f83MzYcnJlYc9LRPKjdiTeRok2xaygu30SpTqIZ9MBUWxFFw8tZECwmQ%3D%3D%22%2C%22signature%22%3A%22628f1c8f2759dc23008e2ac8cbc2a7d3fef9d96e%22%2C%22doEncryption%22%3A%22true%22%2C%22customerIdentification%22%3A%22BM12345678909497%22%2C%22encryptionAlg%22%3A%22AES%22%7D&customerIdentification=BM12345678909497";
        String secretKey = "wq0j+07EjgspYgvnev7pUg==";
        try {
             keyWord = URLDecoder.decode(bodyData, "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String resObject = YopClient.acceptNotificationAsJson(secretKey,keyWord);
        System.out.println(resObject);
    }
}
