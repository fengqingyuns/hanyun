package com.hanyun.platform.pay.task.handler;

import com.hanyun.platform.pay.consts.TaskQueueConsts;

/**
 * Created by wangzhen on 2016-07-28.
 */
public class HandleResult {
    private int result;
    private String resultInfo;
    private int execTimes = 1;
    // 延期时间，单位：分
    private int delayTime = 0;

    public HandleResult() {
        this.result = TaskQueueConsts.HANDLE_RESULT_SUCC;
    }

    public HandleResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public int getExecTimes() {
        return execTimes;
    }

    public void setExecTimes(int execTimes) {
        this.execTimes = execTimes;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }
}
