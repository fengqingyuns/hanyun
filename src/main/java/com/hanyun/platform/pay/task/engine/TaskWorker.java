/**
 * 
 */
package com.hanyun.platform.pay.task.engine;

import java.util.Date;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hanyun.ground.util.date.DateCalcUtil;
import com.hanyun.platform.pay.consts.TaskQueueConsts;
import com.hanyun.platform.pay.domain.TaskQueue;
import com.hanyun.platform.pay.domain.TaskQueueHis;
import com.hanyun.platform.pay.service.TaskQueueService;
import com.hanyun.platform.pay.task.handler.HandleResult;
import com.hanyun.platform.pay.task.handler.TaskBaseHandler;
import com.hanyun.platform.pay.task.handler.TaskHandlerManage;

/**
 * 任务执行单元
 * @author liyinglong@hanyun.com
 * @date 2016年11月14日 下午9:28:13
 */
public class TaskWorker implements Callable<Boolean> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskWorker.class);

    private TaskQueueService taskQueueService;
    private TaskQueue task;

    public TaskWorker() {
    }

    public TaskWorker(TaskQueueService taskQueueService, TaskQueue task) {
        this.taskQueueService = taskQueueService;
        this.task = task;
    }

    @Override
    public Boolean call() throws Exception {

        handle(task);

        return true;
    }

    /**
     * 处理任务
     * 
     * @param task
     */
    protected void handle(TaskQueue task) {
        try {
            Date startTime = new Date();
            HandleResult rst = doExecTask(task);
            if (TaskQueueConsts.HANDLE_RESULT_FAIL_TRY.equals(rst.getResult())) {
                rst = retryHandle(task);
            }
            Date endTime = new Date();
            postProcess(task, rst, startTime, endTime);
        } catch (Exception e) {
            LOGGER.error("Handler task error, Task id: " + task.getMessageId(), e);
        }
    }

    /**
     * 重试处理
     * 
     * @param handler
     * @param t
     * @param times
     * @return
     * @throws Exception
     */
    protected HandleResult retryHandle(TaskQueue t) throws Exception {
        HandleResult rst = null;
        for (int i = 2; i <= TaskQueueConsts.TASK_SINGLE_RETRY_TIMES; i++) {
            Thread.sleep(TaskQueueConsts.TASK_SINGLE_RETRY_INTERVAL);
            rst = doExecTask(t);
            rst.setExecTimes(i);
            if (! TaskQueueConsts.HANDLE_RESULT_FAIL_TRY.equals(rst.getResult())) {
                break;
            }
        }
        return rst;
    }

    /**
     * 具体执行任务
     * 
     * @param task
     * @return
     */
    protected HandleResult doExecTask(TaskQueue task) {
        LOGGER.info("Task Exec begin, Task type: {}, id: {}", task.getMessageType(), task.getMessageId());

        HandleResult rst = null;
        try {
            TaskBaseHandler handler = TaskHandlerManage.getHandler(task.getMessageType());
            if (handler == null) {
                LOGGER.error("Handler not found, MessageType: {}", task.getMessageType());
                return new HandleResult(TaskQueueConsts.HANDLE_RESULT_FAIL_NO_TRY);
            }
            rst = handler.handle(task);
            if(rst == null){
                LOGGER.error("Task HandleResult is null, Task type: {}, id: {}", 
                        task.getMessageType(), task.getMessageId());
                return new HandleResult(TaskQueueConsts.HANDLE_RESULT_FAIL_NO_TRY); 
            }
            if(! TaskQueueConsts.HANDLE_RESULT_SUCC.equals(rst.getResult())){
                LOGGER.warn("Task HandleResult is false, Task type: {}, id: {}, result: {}, resultInfo: {}",  
                        task.getMessageType(), task.getMessageId(), rst.getResult(), rst.getResultInfo());
            }

        } catch (Exception e) {
            LOGGER.error("Task Exec error, Task type: " + task.getMessageType() + ", id: " + task.getMessageId(), e);
            return new HandleResult(TaskQueueConsts.HANDLE_RESULT_FAIL_TRY);
        }
        LOGGER.info("Task Exec end, Task type: {}, id: {}", task.getMessageType(), task.getMessageId());

        return rst;
    }

    /**
     * 任务执行后处理
     * 
     * @param taskQueue
     * @param result
     * @param startTime
     * @param endTime
     * @param retryTimes
     */
    public void postProcess(TaskQueue taskQueue, HandleResult result, Date startTime, Date endTime) {
        if(result == null) {
            LOGGER.error("task post process error, result is null: " + taskQueue.getMessageId());
            return;
        }
        
        int totalRetryTimes = taskQueue.getRetryCount() + result.getExecTimes();
        
        try {
            TaskQueueHis taskQueueHis = new TaskQueueHis();
            taskQueueHis.setMessageId(taskQueue.getMessageId());
            taskQueueHis.setMessageType(taskQueue.getMessageType());
            taskQueueHis.setMessage(taskQueue.getMessage());
            taskQueueHis.setPayId(taskQueue.getPayId());
            taskQueueHis.setTransId(taskQueue.getTransId());
            taskQueueHis.setRetryCount(totalRetryTimes);
            taskQueueHis.setLastProcessTime(endTime);
            taskQueueHis.setCreateTime(taskQueue.getCreateTime());
            taskQueueHis.setOperator(taskQueue.getOperator());
            taskQueueHis.setOperatorIp(taskQueue.getOperatorIp());
            taskQueueHis.setOperateTime(taskQueue.getOperateTime());
            taskQueueHis.setProcessStartTime(startTime);
            taskQueueHis.setProcessEndTime(endTime);
            taskQueueHis.setProcessResultInfo(result.getResultInfo());
            taskQueueHis.setProcessStatus(TaskQueueConsts.PROCESS_STATUS_SUCC);
            if (! TaskQueueConsts.HANDLE_RESULT_SUCC.equals(result.getResult())) {
                taskQueueHis.setProcessStatus(TaskQueueConsts.PROCESS_STATUS_FAIL);
            }
            
            taskQueueService.addTaskQueueHis(taskQueueHis);
            
            if (TaskQueueConsts.HANDLE_RESULT_SUCC.equals(result.getResult())
                    || TaskQueueConsts.HANDLE_RESULT_FAIL_NO_TRY.equals(result.getResult())
                    || totalRetryTimes >= TaskQueueConsts.TASK_MAX_EXEC_TIMES) {
                taskQueueService.deleteByMessageId(taskQueue.getMessageId());
            } else {
                Date now = new Date();
                taskQueue.setRetryCount(totalRetryTimes);
                taskQueue.setLastProcessTime(endTime);
                taskQueue.setOperateTime(now);
                if(TaskQueueConsts.HANDLE_RESULT_FAIL_DELAY_TRY.equals(result.getResult())){
                    Date expectRunTime = DateCalcUtil.addMinutes(now, result.getDelayTime());
                    taskQueue.setExpectRunTime(expectRunTime);
                }
                
                taskQueueService.updateByMessageIdSelective(taskQueue);
            }
        } catch (Exception e) {
            LOGGER.error("task post process error, task id: " + taskQueue.getMessageId(), e);
        }
    }
}
