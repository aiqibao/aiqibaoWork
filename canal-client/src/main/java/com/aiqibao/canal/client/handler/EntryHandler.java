package com.aiqibao.canal.client.handler;

/**
 * @Author:aiqibao
 * @Date:2020/12/7 17:21
 * Best wish!
 */
public interface EntryHandler<T> {

    default void insert(T t){} ;

    default void update(T before,T after){} ;

    default void delete(T t){}

    default void create(T t){}
}
