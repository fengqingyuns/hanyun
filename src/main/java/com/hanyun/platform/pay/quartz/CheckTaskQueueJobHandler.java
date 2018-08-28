/**
 * 
 */
package com.hanyun.platform.pay.quartz;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hanyun.ground.util.date.DateCalcUtil;
import com.hanyun.platform.pay.service.TaskQueueService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 检查任务队列的作业处理器
 * @author liyinglong@hanyun.com
 * @date 2017年10月25日 上午11:58:58
 */
@JobHander(value="checkTaskQueueJobHandler")
@Component
public class CheckTaskQueueJobHandler extends IJobHandler {
    private static final int DEFAULT_LIMIT_MIN = 3;
    @Resource
    private TaskQueueService taskQueueService;

    /**
     * 调试中心可传参数：
     * 1、[超时时限，单位为分钟:非必填]
     */
    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        Integer limitmin = null;
        if(params != null && params.length > 0){
            limitmin = Integer.valueOf(params[0]);
        }
        if(limitmin == null || limitmin < DEFAULT_LIMIT_MIN){
            limitmin = DEFAULT_LIMIT_MIN;
        }
        
        Date limitTime = DateCalcUtil.addMinutes(new Date(), -limitmin);
        int releasenum = taskQueueService.updateDisposedTaskQueue(limitTime);
        XxlJobLogger.log("limitTime: {0}, releasenum: {1}",limitTime, releasenum);
        
        return ReturnT.SUCCESS;
    }

}
