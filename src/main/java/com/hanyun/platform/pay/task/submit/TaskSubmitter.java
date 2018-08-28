/**
 * 
 */
package com.hanyun.platform.pay.task.submit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hanyun.ground.util.exception.BizException;
import com.hanyun.ground.util.json.JsonUtil;
import com.hanyun.platform.pay.consts.BizResCode;
import com.hanyun.platform.pay.consts.TaskQueueConsts;
import com.hanyun.platform.pay.domain.TaskQueue;
import com.hanyun.platform.pay.service.TaskQueueService;
import com.hanyun.platform.pay.util.BusinessIdUtils;

/**
 * 任务提交器
 * @author liyinglong@hanyun.com
 * @date 2016年11月15日 下午5:39:51
 */
@Service
public class TaskSubmitter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskSubmitter.class);
    @Resource
    private TaskQueueService taskQueueService;
    
    public boolean submit(TaskQueue task){
        try {
            LOGGER.info("task subimt: {}", JsonUtil.toJson(task));
            
            task.setMessageId(BusinessIdUtils.genTaskMessageId());
            task.setStatus(TaskQueueConsts.OPERATE_STATUS_UNDEAL);
            task.setRetryCount(0);
            
            taskQueueService.addSelective(task);
        } catch (Exception e) {
            LOGGER.error("task submit error", e);
            throw BizException.build(BizResCode.SYSTEMERROR);
        }
        return true;
    }
}
