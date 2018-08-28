package com.hanyun.platform.pay.task.handler;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.hanyun.platform.pay.domain.TaskQueue;
import com.hanyun.platform.pay.service.TaskQueueService;

/**
 * Created by wangzhen on 2016-08-05.
 */
public abstract class TaskBaseHandler {
    @Resource
    private TaskQueueService taskQueueService;

    @PostConstruct
    public void init() {
        TaskHandlerManage.register(getMessageType(), this);
    }

    public abstract Integer getMessageType();

    public abstract HandleResult handle(TaskQueue task);
}
