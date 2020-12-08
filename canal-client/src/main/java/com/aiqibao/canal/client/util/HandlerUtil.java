package com.aiqibao.canal.client.util;

import com.aiqibao.canal.client.annotation.CanalTable;
import com.aiqibao.canal.client.enums.TableNameEnum;
import com.aiqibao.canal.client.handler.EntryHandler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:aiqibao
 * @Date:2020/12/7 15:49
 * Best wish!
 */
public class HandlerUtil {
    public static EntryHandler getEntryHandler(List<? extends EntryHandler> entryHandlers, String tableName) {
        EntryHandler globalHandler = null;
        for (EntryHandler handler : entryHandlers) {
            String canalTableName = getCanalTableName(handler);
            if (TableNameEnum.ALL.name().toLowerCase().equals(canalTableName)) {
                globalHandler = handler;
                continue;
            }
            if (tableName.equals(canalTableName)) {
                return handler;
            }
            String name = GenericUtil.getTableGenericProperties(handler);
            if (name != null) {
                if (name.equals(tableName)) {
                    return handler;
                }
            }
        }
        return globalHandler;
    }


    public static Map<String, EntryHandler> getTableHandlerMap(List<? extends EntryHandler> entryHandlers) {
        Map<String, EntryHandler> map = new ConcurrentHashMap<>();
        if (entryHandlers != null && entryHandlers.size() > 0) {
            for (EntryHandler handler : entryHandlers) {
                String canalTableName = getCanalTableName(handler);
                if (canalTableName != null) {
                    map.putIfAbsent(canalTableName.toLowerCase(), handler);
                } else {
                    String name = GenericUtil.getTableGenericProperties(handler);
                    if (name != null) {
                        map.putIfAbsent(name.toLowerCase(), handler);
                    }
                }
            }
        }
        return map;
    }


    public static EntryHandler getEntryHandler(Map<String, EntryHandler> map, String tableName) {
        EntryHandler entryHandler = map.get(tableName);
        if (entryHandler == null) {
            return map.get(TableNameEnum.ALL.name().toLowerCase());
        }
        return entryHandler;
    }


    public static String getCanalTableName(EntryHandler entryHandler) {
        CanalTable canalTable = entryHandler.getClass().getAnnotation(CanalTable.class);
        if (canalTable != null) {
            return canalTable.value();
        }
        return null;
    }

}
