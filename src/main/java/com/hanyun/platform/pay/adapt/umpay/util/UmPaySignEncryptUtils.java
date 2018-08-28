/**
 * 
 */
package com.hanyun.platform.pay.adapt.umpay.util;

import java.io.File;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 * 联动优势签名及加解密工具类
 * 
 * @author liyinglong@hanyun.com
 * @date 2016年12月15日 下午7:43:37
 */
public abstract class UmPaySignEncryptUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmPaySignEncryptUtils.class);

    /** 数字签名算法 */
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
    /** 密钥算法 */
    public static final String KEY_ALGORITHM = "RSA";
    /** 加密算法 */
    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    /** 公钥文件路径 */
    public static final String KEY_FILEPATH_PUBLIC =  "umpaykey/uts.posp.public.key.pem";
    /** 私钥文件路径 */
    public static final String KEY_FILEPATH_PRIVATE = "umpaykey/pos.hanyun.private.key.p8";

    // 公钥
    private static final PublicKey PUBLICKEY;
    // 私钥
    private static final PrivateKey PRIVATEKEY;
    static {
        // 初始化密钥
        PUBLICKEY = loadPublicKey();
        PRIVATEKEY = loadPrivateKey();
    }

    /**
     * 签名
     * 
     * @param plainText
     *            原串
     * @return 签名串
     */
    public static String sign(String plainText) {
        String signStr = null;
        try {
            // 实例化
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            // 初始化，传入私钥
            signature.initSign(PRIVATEKEY);
            // 更新
            signature.update(plainText.getBytes());
            // 签名
            byte[] signByte = signature.sign();
            // 16进制编码
            signStr = Hex.encodeHexString(signByte);
        } catch (Exception e) {
            LOGGER.error("umpay sign error! plainText: " + plainText, e);
            signStr = null;
        }

        return signStr;
    }

    /**
     * 验证签名
     * 
     * @param plainText
     *            原串
     * @param verifySign
     *            待验签名串
     * @return 是否验证通过
     */
    public static boolean verify(String plainText, String verifySign) {
        boolean verifyOk = false;
        try {
            // 实例化
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            // 初始化
            signature.initVerify(PUBLICKEY);
            // 更新
            signature.update(plainText.getBytes());
            // 16进制解码
            byte[] verifySignByte = Hex.decodeHex(verifySign.toCharArray());
            // 验签
            verifyOk = signature.verify(verifySignByte);
        } catch (Exception e) {
            LOGGER.error("umpay sign verify error! plainText: " + plainText, e);
            verifyOk = false;
        }

        return verifyOk;
    }
    
    /**
     * 公钥加密
     * 
     * @param plainData
     *            明文数据
     * @return 密文串
     */
    public static String encryptByPublicKey(byte[] plainData){
        return encrypt(plainData, PUBLICKEY);
    }
    
    /**
     * 私钥加密
     * 
     * @param plainData
     *            明文数据
     * @return 密文串
     */
    public static String encryptByPrivateKey(byte[] plainData){
        return encrypt(plainData, PRIVATEKEY);
    }
    
    /**
     * 私钥解密
     * 
     * @param enText
     *            密文串
     * @return 明文数据
     */
    public static byte[] decryptByPrivateKey(String enText) {
        return decrypt(enText, PRIVATEKEY);
    }
    
    /**
     * 公钥解密
     * 
     * @param enText
     *            密文串
     * @return 明文数据
     */
    public static byte[] decryptByPublicKey(String enText) {
        return decrypt(enText, PUBLICKEY);
    }

    /**
     * 加密
     * 
     * @param plainData
     *            明文数据
     * @return 密文串
     */
    private static String encrypt(byte[] plainData, Key key) {
        String enText = null;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 数据块必须比RSA密钥模长(modulus)短至少11个字节
            int blockLen = ((RSAKey) key).getModulus().bitLength() / 8 - 11;

            // 数据块 <= 模长-11
            byte[][] plainBlocks = splitByteArray(plainData, blockLen);

            // 分组加密
            int totalLen = 0;
            byte[][] enBlocks = new byte[plainBlocks.length][];
            for (int i = 0; i < plainBlocks.length; i++) {
                byte[] enBlock = cipher.doFinal(plainBlocks[i]);
                enBlocks[i] = enBlock;
                totalLen += enBlock.length;
            }
            // 合并数据
            byte[] enData = mergeByteArray(enBlocks, totalLen);
            // base64编码
            enText = Base64.encodeBase64String(enData);
        } catch (Exception e) {
            LOGGER.error("umpay encrypt error!", e);
        }
        return enText;
    }

    /**
     * 解密
     * 
     * @param enText 密文串
     * @return 明文数据
     */
    private static byte[] decrypt(String enText, Key key) {
        byte[] plainData = null;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 数据块必须不长于RSA密钥模长(modulus)
            int blockLen = ((RSAKey) key).getModulus().bitLength() / 8;
            // base64 解码
            byte[] enData = Base64.decodeBase64(enText);
            byte[][] enBlocks = splitByteArray(enData, blockLen);

            // 分组加密
            int totalLen = 0;
            byte[][] plainBlocks = new byte[enBlocks.length][];
            for (int i = 0; i < enBlocks.length; i++) {
                byte[] plainBlock = cipher.doFinal(enBlocks[i]);
                plainBlocks[i] = plainBlock;
                totalLen += plainBlock.length;
            }
            // 合并数据
            plainData = mergeByteArray(plainBlocks, totalLen);

        } catch (Exception e) {
            LOGGER.error("umpay decrypt error! enText: " + enText, e);
        }
        return plainData;
    }

    /**
     * 切分字节数组
     * 
     * @param orignData
     *            原字节数组
     * @param blockLen
     *            块长度
     * @return 切分后的数组
     */
    public static byte[][] splitByteArray(byte[] orignData, int blockLen) {
        // 计算块数
        int blockCnt = orignData.length / blockLen;
        int remainderLen = orignData.length % blockLen;
        if (remainderLen != 0) {
            blockCnt += 1;
        }

        byte[][] blockAry = new byte[blockCnt][];
        for (int i = 0; i < blockCnt; i++) {
            int copyLen = blockLen;
            if (i == blockCnt - 1 && remainderLen != 0) {
                copyLen = remainderLen;
            }
            byte[] block = new byte[copyLen];
            System.arraycopy(orignData, i * blockLen, block, 0, copyLen);
            blockAry[i] = block;
        }
        return blockAry;
    }

    /**
     * 合并字节数组
     * 
     * @param blockAry
     *            块数组
     * @param totalLen
     *            数据总长
     * @return 合并后的字节数组
     */
    public static byte[] mergeByteArray(byte[][] blockAry, int totalLen) {
        byte[] mergeData = new byte[totalLen];
        int mergeLen = 0;
        for (byte[] tmp : blockAry) {
            System.arraycopy(tmp, 0, mergeData, mergeLen, tmp.length);
            mergeLen += tmp.length;
        }

        return mergeData;
    }

    /**
     * 从文件加载公钥
     */
    private static synchronized PublicKey loadPublicKey() {
        File file = null;
        String fileLoc = ResourceUtils.CLASSPATH_URL_PREFIX + KEY_FILEPATH_PUBLIC;
        try {
            file = ResourceUtils.getFile(fileLoc);
        } catch (Exception e) {
            LOGGER.error("umpay public key file not found!, fileLoc: " + fileLoc, e);
            return null;
        }

        PublicKey pubKey = null;
        try {
            List<String> lines = FileUtils.readLines(file);
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                // 过滤注释行
                if (line.startsWith("--")) {
                    continue;
                }
                sb.append(line);
            }
            byte[] encodedKey = Base64.decodeBase64(sb.toString());

            X509EncodedKeySpec keyspec = new X509EncodedKeySpec(encodedKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            pubKey = keyFactory.generatePublic(keyspec);
        } catch (Exception e) {
            LOGGER.error("umpay public key parse error!", e);
        }
        return pubKey;
    }

    /**
     * 从文件加载私钥
     */
    private static synchronized PrivateKey loadPrivateKey() {
        File file = null;
        String fileLoc = ResourceUtils.CLASSPATH_URL_PREFIX + KEY_FILEPATH_PRIVATE;
        try {
            file = ResourceUtils.getFile(fileLoc);
        } catch (Exception e) {
            LOGGER.error("umpay private key file not found!, fileLoc: " + fileLoc, e);
            return null;
        }

        PrivateKey priKey = null;
        try {
            byte[] encodedKey = FileUtils.readFileToByteArray(file);

            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(encodedKey);
            KeyFactory keyf = KeyFactory.getInstance(KEY_ALGORITHM);
            priKey = keyf.generatePrivate(priPKCS8);
        } catch (Exception e) {
            LOGGER.error("umpay private key parse error!", e);
        }

        return priKey;
    }
}
