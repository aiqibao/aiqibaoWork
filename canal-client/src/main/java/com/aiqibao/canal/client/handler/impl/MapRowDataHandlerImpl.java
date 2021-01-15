package com.aiqibao.canal.client.handler.impl;

import com.aiqibao.canal.client.factory.IModelFactory;
import com.aiqibao.canal.client.handler.EntryHandler;
import com.aiqibao.canal.client.handler.RowDataHandler;
import com.alibaba.otter.canal.protocol.CanalEntry;

import java.util.List;
import java.util.Map;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 11:17
 * Best wish!
 */
public class MapRowDataHandlerImpl implements RowDataHandler<List<Map<String, String>>> {
    private IModelFactory<Map<String,String>> modelFactory;


    public MapRowDataHandlerImpl(IModelFactory<Map<String, String>> modelFactory) {
        this.modelFactory = modelFactory;
    }

    /**
     * process dml sql
     * @param list
     * @param entryHandler
     * @param eventType
     * @param <R>
     * @throws Exception
     */
    @Override
    public <R> void handlerRowData(List<Map<String, String>> list, EntryHandler<R> entryHandler, CanalEntry.EventType eventType) throws Exception{
        if (entryHandler != null) {
            switch (eventType) {
                case INSERT:
                    R entry  = modelFactory.newInstance(entryHandler, list.get(0));
                    entryHandler.insert(entry);
                    break;
                case UPDATE:
                    R before = modelFactory.newInstance(entryHandler, list.get(1));
                    R after = modelFactory.newInstance(entryHandler, list.get(0));
                    entryHandler.update(before, after);
                    break;
                case DELETE:
                    R o = modelFactory.newInstance(entryHandler, list.get(0));
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

    }
}
