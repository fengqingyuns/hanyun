package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.TaskQueue;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

@SuppressWarnings("UnnecessaryInterfaceModifier")
@Repository
public interface TaskQueueDao {

    public int deleteByMessageId(String messageId);

    public int insertSelective(TaskQueue record);

    public TaskQueue selectByMessageId(String messageId);

    public List<TaskQueue> selectUndisposedTaskQueue(Integer rows);

    public List<TaskQueue> selectDisposedTaskQueue(Date limitTime);

    public int updateByMessageIdSelective(TaskQueue record);

    public int updateDisposedTaskQueue(Date limitDate);

    public int batchUpdateByMessageIdSelective(List<TaskQueue> list);
}