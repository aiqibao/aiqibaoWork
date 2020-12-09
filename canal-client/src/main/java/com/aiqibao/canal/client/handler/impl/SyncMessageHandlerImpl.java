package com.aiqibao.canal.client.handler.impl;

import com.aiqibao.canal.client.handler.AbstractMessageHandler;
import com.aiqibao.canal.client.handler.EntryHandler;
import com.aiqibao.canal.client.handler.RowDataHandler;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 11:14
 * Best wish!
 */
@Slf4j
public class SyncMessageHandlerImpl extends AbstractMessageHandler {
    public SyncMessageHandlerImpl(List<? extends EntryHandler> entryHandlers, RowDataHandler<CanalEntry.RowData> rowDataHandler) {
        super(entryHandlers, rowDataHandler);
    }

    @Override
    public void handleMessage(Message message) {
        log.info("SyncMessageHandlerImpl process");
        super.handleMessage(message);
    }
}
