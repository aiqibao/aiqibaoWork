package com.aiqibao.canal.client.factory;

import com.aiqibao.canal.client.enums.TableNameEnum;
import com.aiqibao.canal.client.handler.EntryHandler;
import com.aiqibao.canal.client.util.GenericUtil;
import com.aiqibao.canal.client.util.HandlerUtil;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 10:39
 * Best wish!
 */
public abstract class AbstractModelFactory<T> implements IModelFactory<T> {
    @Override
    public <R> R newInstance(EntryHandler entryHandler, T t) throws Exception {
        String canalTableName = HandlerUtil.getCanalTableName(entryHandler);
        if (TableNameEnum.ALL.name().toLowerCase().equals(canalTableName)) {
            return (R) t;
        }
        Class<R> tableClass = GenericUtil.getTableClass(entryHandler);
        if (tableClass != null) {
            return newInstance(tableClass, t);
        }
        return null;
    }


    abstract <R> R newInstance(Class<R> c, T t) throws Exception;
}
