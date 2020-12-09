package com.aiqibao.canal.client.handler;

import com.aiqibao.canal.client.context.CanalContext;
import com.aiqibao.canal.client.model.CanalModel;
import com.aiqibao.canal.client.util.HandlerUtil;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 10:46
 * Best wish!
 */
@Slf4j
public abstract class AbstractMessageHandler implements MessageHandler<Message> {
    private Map<String, EntryHandler> tableHandlerMap;



    private RowDataHandler<CanalEntry.RowData> rowDataHandler;


    public  AbstractMessageHandler(List<? extends EntryHandler> entryHandlers, RowDataHandler<CanalEntry.RowData> rowDataHandler) {
        this.tableHandlerMap = HandlerUtil.getTableHandlerMap(entryHandlers);
        this.rowDataHandler = rowDataHandler;
    }

    @Override
    public  void handleMessage(Message message) {
        List<CanalEntry.Entry> entries = message.getEntries();
        for (CanalEntry.Entry entry : entries) {
            if (entry.getEntryType().equals(CanalEntry.EntryType.ROWDATA)) {
                try {
                    EntryHandler<?> entryHandler = HandlerUtil.getEntryHandler(tableHandlerMap, entry.getHeader().getTableName());
                    if(entryHandler!=null){
                        CanalModel model = CanalModel.Builder.builder().id(message.getId()).table(entry.getHeader().getTableName())
                                .executeTime(entry.getHeader().getExecuteTime()).database(entry.getHeader().getSchemaName()).build();
                        CanalContext.setModel(model);
                        CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                        if (CanalEntry.EventType.QUERY == rowChange.getEventType() || rowChange.getIsDdl()){
                            log.debug("SQL [{}] --> {}", entry.getHeader().getTableName(), rowChange.getSql());
                        }
                        List<CanalEntry.RowData> rowDataList = rowChange.getRowDatasList();
                        CanalEntry.EventType eventType = rowChange.getEventType();
                        for (CanalEntry.RowData rowData : rowDataList) {
                            rowDataHandler.handlerRowData(rowData,entryHandler,eventType);
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                }finally {
                    CanalContext.removeModel();
                }

            }
        }
    }
}
