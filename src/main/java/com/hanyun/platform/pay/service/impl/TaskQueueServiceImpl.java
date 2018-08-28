package com.hanyun.platform.pay.service.impl;

import com.hanyun.platform.pay.dao.TaskQueueDao;
import com.hanyun.platform.pay.dao.TaskQueueHisDao;
import com.hanyun.platform.pay.domain.TaskQueue;
import com.hanyun.platform.pay.domain.TaskQueueHis;
import com.hanyun.platform.pay.service.TaskQueueService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangzhen on 2016-08-09.
 */
@Service
public class TaskQueueServiceImpl implements TaskQueueService{
    @Resource
    private TaskQueueDao taskQueueDao;
    @Resource
    private TaskQueueHisDao taskQueueHisDao;

    @Override
    public List<TaskQueue> getDisposedTaskQueue(Date limitTime) {
        return taskQueueDao.selectDisposedTaskQueue(limitTime);
    }

    @Override
    public int deleteByMessageId(String messageId) {
        return taskQueueDao.deleteByMessageId(messageId);
    }

    @Override
    public int addSelective(TaskQueue record) {
        return taskQueueDao.insertSelective(record);
    }

    @Override
    public int updateDisposedTaskQueue(Date limitDate) {
        return taskQueueDao.updateDisposedTaskQueue(limitDate);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<TaskQueue> getUndisposedTaskQueue(String operator, String ip, Integer rows) {
        List<TaskQueue> taskQueues = taskQueueDao.selectUndisposedTaskQueue(rows);
        if(CollectionUtils.isNotEmpty(taskQueues)) {
            for (TaskQueue taskQueue : taskQueues) {
                taskQueue.setOperator(operator);
                taskQueue.setOperatorIp(ip);
                taskQueue.setOperateTime(new Date());
                taskQueue.setLastProcessTime(new Date());
                taskQueue.setStatus(1);
            }
            taskQueueDao.batchUpdateByMessageIdSelective(taskQueues);
            return taskQueues;
        }
        return new ArrayList<>();
    }

    @Override
    public TaskQueue getByMessageId(String messageId) {
        return taskQueueDao.selectByMessageId(messageId);
    }

    @Override
    public int updateByMessageIdSelective(TaskQueue record) {
        return taskQueueDao.updateByMessageIdSelective(record);
    }

    @Override
    public int batchUpdateByMessageIdSelective(List<TaskQueue> list) {
        return taskQueueDao.batchUpdateByMessageIdSelective(list);
    }

    @Override
    public int deleteTaskQueueHisByMessageId(String id) {
        return taskQueueHisDao.deleteByMessageId(id);
    }

    @Override
    public int addTaskQueueHis(TaskQueueHis record) {
        return taskQueueHisDao.insertSelective(record);
    }

    @Override
    public TaskQueueHis getTaskQueueHisByMessageId(String id) {
        return taskQueueHisDao.selectByMessageId(id);
    }

    @Override
    public List<TaskQueueHis> getTaskQueueHis(TaskQueueHis record) {
        return taskQueueHisDao.selectSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(TaskQueueHis record) {
        return taskQueueHisDao.updateByPrimaryKeySelective(record);
    }

}
