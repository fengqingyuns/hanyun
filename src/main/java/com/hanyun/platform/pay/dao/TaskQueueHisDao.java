package com.hanyun.platform.pay.dao;

import com.hanyun.platform.pay.domain.TaskQueueHis;
import java.util.List;

import org.springframework.stereotype.Repository;

@SuppressWarnings("UnnecessaryInterfaceModifier")
@Repository
public interface TaskQueueHisDao {

    public int deleteByMessageId(String id);

    public int insertSelective(TaskQueueHis record);

    public TaskQueueHis selectByMessageId(String id);

    public List<TaskQueueHis> selectSelective(TaskQueueHis record);

    public int updateByPrimaryKeySelective(TaskQueueHis record);
}