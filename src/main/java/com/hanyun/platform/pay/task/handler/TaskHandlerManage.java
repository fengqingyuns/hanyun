package com.hanyun.platform.pay.task.handler;

import java.util.HashMap;

import com.google.common.collect.Maps;

/**
 * Created by wangzhen on 2016-07-28.
 */
public class TaskHandlerManage {
    private static final HashMap<Integer, TaskBaseHandler> handlerMap = Maps.newHashMap();

    public static TaskBaseHandler getHandler(Integer type) {
        return handlerMap.get(type);
    }

    public static void register(Integer type, TaskBaseHandler handler) {
        handlerMap.put(type, handler);
    }

}
