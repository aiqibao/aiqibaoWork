package com.aiqibao.canal.client.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 10:44
 * Best wish!
 */
@Slf4j
public class CanalThreadUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    /**
     * Method invoked when the given thread terminates due to the
     * given uncaught exception.
     * <p>Any exception thrown by this method will be ignored by the
     * Java Virtual Machine.
     *
     * @param t the thread
     * @param e the exception
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("thread"+t.getName()+" have a exception",e);
    }
}
