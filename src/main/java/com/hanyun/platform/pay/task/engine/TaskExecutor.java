/**
 * 
 */
package com.hanyun.platform.pay.task.engine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hanyun.platform.pay.consts.TaskQueueConsts;

/**
 * 任务执行器
 * @author liyinglong@hanyun.com
 * @date 2016年11月15日 下午4:58:21
 */
public class TaskExecutor {
    private static final ExecutorService executor = 
            Executors.newFixedThreadPool(TaskQueueConsts.TASK_ENGINE_THREAD_POOL_SIZE);
    
    /**
     * 执行任务
     * @param worker
     * @return
     */
    public static boolean execute(TaskWorker worker){
        
        executor.submit(worker);
        
        return true;
    }
}
