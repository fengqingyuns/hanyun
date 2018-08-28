/**
 * 
 */
package com.hanyun.platform.service.test;

import com.hanyun.ground.util.network.LocalHost;
import com.hanyun.platform.pay.domain.TaskQueue;
import com.hanyun.platform.pay.service.TaskQueueService;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-config.xml"})
public class TaskQueueServiceTest {
    @Resource
    private TaskQueueService taskQueueService;

    @Test
    public void testAddCallBackQueueSelective(){
        for (int i = 0; i < 15; i++) {
            TaskQueue taskQueue = new TaskQueue();
            taskQueue.setMessageId(UUID.randomUUID().toString());
            taskQueue.setMessageType(0);
            taskQueue.setMessage("{\"name\":\"zhangsan\",\"type\":111}");
            taskQueue.setPayId(UUID.randomUUID().toString());
            taskQueue.setTransId(UUID.randomUUID().toString());
            taskQueue.setOperateTime(new Date());
            taskQueue.setExpectRunTime(new Date());
            taskQueueService.addSelective(taskQueue);
        }
    }

    @Test
    public void testGetUndisposedCallBackQueue(){
        List<TaskQueue> queues = taskQueueService.getUndisposedTaskQueue(UUID.randomUUID().toString(), LocalHost.getLocalIP(),10);
    }

    @Test
    public void testBatchUpdateByIdSelective(){
        List<TaskQueue> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TaskQueue call = new TaskQueue();
            call.setId((long)(5000+i));
            call.setMessage("dddddddddddd");
            list.add(call);
        }
        taskQueueService.batchUpdateByMessageIdSelective(list);
    }

}
