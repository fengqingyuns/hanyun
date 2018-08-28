package com.hanyun.platform.pay.service;

import com.hanyun.platform.pay.domain.TaskQueue;
import com.hanyun.platform.pay.domain.TaskQueueHis;

import java.util.Date;
import java.util.List;

/**
 * Created by wangzhen on 2016-08-09.
 */
public interface TaskQueueService {
    public int deleteByMessageId(String messageId);

    public int addSelective(TaskQueue record);

    public List<TaskQueue> getUndisposedTaskQueue(String operator, String ip, Integer rows);

    public List<TaskQueue> getDisposedTaskQueue(Date limitTime);

    public TaskQueue getByMessageId(String messageId);

    public int updateByMessageIdSelective(TaskQueue record);

    public int updateDisposedTaskQueue(Date limitDate);

    public int batchUpdateByMessageIdSelective(List<TaskQueue> list);

    public int deleteTaskQueueHisByMessageId(String id);

    public int addTaskQueueHis(TaskQueueHis record);

    public TaskQueueHis getTaskQueueHisByMessageId(String id);

    public List<TaskQueueHis> getTaskQueueHis(TaskQueueHis record);

    public int updateByPrimaryKeySelective(TaskQueueHis record);
    
}
