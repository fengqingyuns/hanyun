/**
 * 
 */
package com.hanyun.platform.pay.quartz;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hanyun.platform.pay.task.engine.TaskEngine;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 执行任务队列作业处理器
 * @author liyinglong@hanyun.com
 * @date 2017年10月25日 上午11:50:41
 */
@JobHander(value="taskQueueJobHandler")
@Component
public class TaskQueueJobHandler extends IJobHandler {
    @Resource
    private TaskEngine taskEngine;

    /**
     * 调试中心可传参数：无
     */
    @Override
    public ReturnT<String> execute(String... params) throws Exception {
        
        int tasknum = taskEngine.run();
        XxlJobLogger.log("tasknum: {0}", tasknum);
        
        return ReturnT.SUCCESS;
    }

}
