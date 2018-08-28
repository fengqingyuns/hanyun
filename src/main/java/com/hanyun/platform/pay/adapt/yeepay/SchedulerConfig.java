package com.hanyun.platform.pay.adapt.yeepay;

import com.hanyun.platform.pay.service.TaskInit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @PackageName com.hanyun.platform.pay.adapt.yeepay
 * @Author: dewen.li
 * @Date: 2018-08-08 下午2:10
 */

@Configuration
@EnableScheduling
public class SchedulerConfig {
    @Bean
    public TaskInit initChannel(){
        return new TaskInit();
    }
}
