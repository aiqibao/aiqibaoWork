package com.aiqibao.canal.client.handler;


import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * @Author:aiqibao
 * @Date:2020/12/7 17:23
 * Best wish!
 */
public interface RowDataHandler<T> {
    /**
     * process dml sql
     * @param t
     * @param entryHandler
     * @param eventType
     * @param <R>
     * @throws Exception
     */
    <R> void handlerRowData(T t, EntryHandler<R> entryHandler, CanalEntry.EventType eventType) throws Exception ;

    /**
     * process ddl sql
     * @param sql
     * @param entryHandler
     * @param eventType
     * @param <R>
     * @throws Exception
     */
    <R> void handlerDdlData(String sql,EntryHandler<R> entryHandler, CanalEntry.EventType eventType) throws Exception ;
}
