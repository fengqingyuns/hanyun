package com.hanyun.platform.pay.task.engine;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hanyun.ground.util.network.LocalHost;
import com.hanyun.platform.pay.consts.TaskQueueConsts;
import com.hanyun.platform.pay.domain.TaskQueue;
import com.hanyun.platform.pay.service.TaskQueueService;

/**
 * Created by wangzhen on 2016-08-01.
 */
@Service
public class TaskEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskEngine.class);
    @Resource
    private TaskQueueService taskQueueService;

    /**
     * 任务执行引擎执行
     */
    public int run() {
        int tasknum = 0;
        try {
            List<TaskQueue> taskList = loadTask();
            if (taskList == null || taskList.isEmpty()) {
                return tasknum;
            }
            tasknum = taskList.size();
            for (TaskQueue task : taskList) {
                TaskWorker worker = new TaskWorker(taskQueueService, task);
                TaskExecutor.execute(worker);
            }
        } catch (Exception e) {
            LOGGER.error("Task engine run error!", e);
        }
        return tasknum;
    }

    /**
     * 加载任务
     * 
     * @return
     */
    private List<TaskQueue> loadTask() {
        List<TaskQueue> taskList = taskQueueService.getUndisposedTaskQueue(UUID.randomUUID().toString(),
                LocalHost.getLocalIP(), TaskQueueConsts.TASK_FETCH_BATCH_SIZE);

        return taskList;
    }
}
