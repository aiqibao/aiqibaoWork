package com.aiqibao.canal.client.handler.impl;

import com.aiqibao.canal.client.factory.IModelFactory;
import com.aiqibao.canal.client.handler.EntryHandler;
import com.aiqibao.canal.client.handler.RowDataHandler;
import com.alibaba.otter.canal.protocol.CanalEntry;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 10:49
 * Best wish!
 */
public class RowDataHandlerImpl implements RowDataHandler<CanalEntry.RowData> {

    private IModelFactory<List<CanalEntry.Column>> modelFactory;

    public RowDataHandlerImpl(IModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    /**
     * process dml sql
     * @param rowData
     * @param entryHandler
     * @param eventType
     * @param <R>
     * @throws Exception
     */
    @Override
    public <R> void handlerRowData(CanalEntry.RowData rowData, EntryHandler<R> entryHandler, CanalEntry.EventType eventType) throws Exception {
        if (entryHandler != null) {
            switch (eventType) {
                case INSERT:
                    R object = modelFactory.newInstance(entryHandler, rowData.getAfterColumnsList());
                    entryHandler.insert(object);
                    break;
                case UPDATE:
                    Set<String> updateColumnSet = rowData.getAfterColumnsList().stream().filter(CanalEntry.Column::getUpdated)
                            .map(CanalEntry.Column::getName).collect(Collectors.toSet());
                    R before = modelFactory.newInstance(entryHandler, rowData.getBeforeColumnsList(),updateColumnSet);
                    R after = modelFactory.newInstance(entryHandler, rowData.getAfterColumnsList());
                    entryHandler.update(before, after);
                    break;
                case DELETE:
                    R o = modelFactory.newInstance(entryHandler, rowData.getBeforeColumnsList());
                    entryHandler.delete(o);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * process ddl sql
     *
     * @param sql
     * @param entryHandler
     * @param eventType
     * @throws Exception
     */
    @Override
    public <R> void handlerDdlData(String sql, EntryHandler<R> entryHandler, CanalEntry.EventType eventType) throws Exception {
        if (entryHandler != null) {
            switch (eventType){
                case CREATE:
                    entryHandler.create(sql);
            }
        }
    }
}
