package com.aiqibao.canal.client.handler.impl;

import com.aiqibao.canal.client.handler.AbstractFlatMessageHandler;
import com.aiqibao.canal.client.handler.EntryHandler;
import com.aiqibao.canal.client.handler.RowDataHandler;
import com.alibaba.otter.canal.protocol.FlatMessage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 11:18
 * Best wish!
 */
public class AsyncFlatMessageHandlerImpl extends AbstractFlatMessageHandler {
    private ExecutorService executor;


    public AsyncFlatMessageHandlerImpl(List<? extends EntryHandler> entryHandlers, RowDataHandler<List<Map<String, String>>> rowDataHandler, ExecutorService executor) {
        super(entryHandlers, rowDataHandler);
        this.executor = executor;
    }

    @Override
    public void handleMessage(FlatMessage flatMessage) {
        executor.execute(() -> super.handleMessage(flatMessage));
    }
}
