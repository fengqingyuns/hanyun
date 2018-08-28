/**
 * 
 */
package com.hanyun.platform.pay.consts;

/**
 * 任务队列相关常量
 * @author liyinglong@hanyun.com
 * @date 2016年11月15日 下午3:27:33
 */
public abstract class TaskQueueConsts {
    /** 线程池大小 */
    public static final int TASK_ENGINE_THREAD_POOL_SIZE = 30;
    /** 单次加载任务数量 */
    public static final int TASK_FETCH_BATCH_SIZE = 50;
    /** 任务单次执行重试次数 */
    public static final int TASK_SINGLE_RETRY_TIMES = 20;
    /** 任务单次执行重试间隔 */
    public static final int TASK_SINGLE_RETRY_INTERVAL = 3000;
    /** 任务最大可执行次数 */
    public static final int TASK_MAX_EXEC_TIMES = 60;
    
    /** 抢占状态－未处理 */
    public static final Integer OPERATE_STATUS_UNDEAL = 0;
    /** 抢占状态－已被抢占 */
    public static final Integer OPERATE_STATUS_INDEAL = 1;
    
    /** 处理结果状态－成功 */
    public static final Integer PROCESS_STATUS_SUCC = 0;
    /** 处理结果状态－失败 */
    public static final Integer PROCESS_STATUS_FAIL = 1;
    
    /** 任务处理器结果类型－成功 */
    public static final Integer HANDLE_RESULT_SUCC = 0;
    /** 任务处理器结果类型－失败，不需要重试 */
    public static final Integer HANDLE_RESULT_FAIL_NO_TRY = 1;
    /** 任务处理器结果类型－失败，需要立即重试 */
    public static final Integer HANDLE_RESULT_FAIL_TRY = 2;
    /** 任务处理器结果类型－失败，需要延时重试 */
    public static final Integer HANDLE_RESULT_FAIL_DELAY_TRY = 3;
}
