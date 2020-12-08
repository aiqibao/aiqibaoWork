package com.aiqibao.canal.client.handler;

/**
 * @Author:aiqibao
 * @Date:2020/12/7 16:12
 * Best wish!
 */
public interface MessageHandler<T> {

    void handleMessage(T t) ;
}
