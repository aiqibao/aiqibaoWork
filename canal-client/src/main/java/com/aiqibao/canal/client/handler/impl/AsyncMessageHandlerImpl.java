package com.aiqibao.canal.client.handler.impl;

import com.aiqibao.canal.client.handler.AbstractMessageHandler;
import com.aiqibao.canal.client.handler.EntryHandler;
import com.aiqibao.canal.client.handler.RowDataHandler;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 11:15
 * Best wish!
 */
@Slf4j
public class AsyncMessageHandlerImpl extends AbstractMessageHandler {
    private ExecutorService executor;


    public AsyncMessageHandlerImpl(List<? extends EntryHandler> entryHandlers, RowDataHandler<CanalEntry.RowData> rowDataHandler, ExecutorService executor) {
        super(entryHandlers, rowDataHandler);
        this.executor = executor;
    }

    @Override
    public void handleMessage(Message message) {
        log.info("AsyncMessageHandlerImpl process");
        executor.execute(() -> super.handleMessage(message));
    }
}
