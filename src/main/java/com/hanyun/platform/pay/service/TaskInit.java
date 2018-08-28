package com.hanyun.platform.pay.service;

import com.hanyun.platform.pay.adapt.yeepay.YeepaySecretKeyConfig;
import com.hanyun.platform.pay.context.YeepayChildChannelRegGlobal;
import com.hanyun.platform.pay.dao.YeepaySecretKeyConfigDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.service
 * @Author: dewen.li
 * @Date: 2018-08-08 下午2:17
 */
@Service
public class TaskInit {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskInit.class);

    @Autowired
    private YeepaySecretKeyConfigDao yeepaySecretKeyConfigDao;


    @PostConstruct
    public void init(){
        List<YeepaySecretKeyConfig> list =  yeepaySecretKeyConfigDao.getYeepaySecretKeyConfig();
        LOGGER.info("获取list========》》》》》》》"+list);
        if(null==list){
            //初始化测试账号数据
            Map<String,String> vls = new HashMap<>();
            vls.put("appNo","BM12345678909497");
            vls.put("appKey","wq0j+07EjgspYgvnev7pUg==");
            YeepayChildChannelRegGlobal.setChildChannel(vls, "AH8C6246571A7D4670A1BE1289C6FDE850");
        }else {
            LOGGER.info("共有" + list.size() + "个门店客户数据被初始化！");
            for (YeepaySecretKeyConfig yeepaySecretKeyConfig : list) {
                String storeId = yeepaySecretKeyConfig.getStoreId();
                String app_no = yeepaySecretKeyConfig.getPayNo();
                String app_key = yeepaySecretKeyConfig.getPayKey();
                if (null == YeepayChildChannelRegGlobal.getChildChannel(storeId)) {
                    Map<String, String> values = new HashMap<String, String>();
                    values.put("appNo", app_no);
                    values.put("appKey", app_key);
                    YeepayChildChannelRegGlobal.setChildChannel(values, storeId);
                }
            }
        }
    }

    @Scheduled(cron = "0 0/15 * * * ?")
    public void initChannel(){
        List<YeepaySecretKeyConfig> list =  yeepaySecretKeyConfigDao.getYeepaySecretKeyConfig();
        LOGGER.info("共有"+list.size()+"个门店客户数据被初始化！");
        for(YeepaySecretKeyConfig yeepaySecretKeyConfig:list){
            LOGGER.info("init key serkey------------------>"+yeepaySecretKeyConfig.getPayKey()+"="+yeepaySecretKeyConfig.getPayNo());
            String storeId = yeepaySecretKeyConfig.getStoreId();
            String app_no = yeepaySecretKeyConfig.getPayNo();
            String app_key = yeepaySecretKeyConfig.getPayKey();
            if(null == YeepayChildChannelRegGlobal.getChildChannel(storeId)){
                Map<String,String> values = new HashMap<String,String>();
                values.put("appNo",app_no);
                values.put("appKey",app_key);
                YeepayChildChannelRegGlobal.setChildChannel(values,storeId);
            }
        }
    }
}
