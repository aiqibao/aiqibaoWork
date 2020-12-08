package com.aiqibao.canal.client.factory;


import com.aiqibao.canal.client.handler.EntryHandler;

import java.util.Set;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 10:38
 * Best wish!
 */
public interface IModelFactory<T> {
    <R> R newInstance(EntryHandler entryHandler, T t) throws Exception;


    default <R> R newInstance(EntryHandler entryHandler, T t, Set<String> updateColumn) throws Exception {
        return null;
    }
}
