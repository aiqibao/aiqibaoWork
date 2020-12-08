package com.aiqibao.canal.client.handler.impl;

import com.aiqibao.canal.client.handler.AbstractFlatMessageHandler;
import com.aiqibao.canal.client.handler.EntryHandler;
import com.aiqibao.canal.client.handler.RowDataHandler;
import com.alibaba.otter.canal.protocol.FlatMessage;

import java.util.List;
import java.util.Map;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 11:19
 * Best wish!
 */
public class SyncFlatMessageHandlerImpl extends AbstractFlatMessageHandler {
    public SyncFlatMessageHandlerImpl(List<? extends EntryHandler> entryHandlers, RowDataHandler<List<Map<String, String>>> rowDataHandler) {
        super(entryHandlers, rowDataHandler);
    }

    @Override
    public void handleMessage(FlatMessage flatMessage) {
        super.handleMessage(flatMessage);
    }
}
