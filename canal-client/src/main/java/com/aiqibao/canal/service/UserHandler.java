package com.aiqibao.canal.service;

import com.aiqibao.canal.client.handler.EntryHandler;
import com.aiqibao.canal.client.model.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 11:48
 * Best wish!
 */
@Slf4j
public class UserHandler implements EntryHandler<User> {
    @Override
    public void insert(User user) {
        log.info("insert message  {}", user);
    }

    @Override
    public void update(User before, User after) {
        log.info("update before {} ", before);
        log.info("update after {}", after);
    }

    @Override
    public void delete(User user) {
        log.info("delete  {}", user);
    }
}
