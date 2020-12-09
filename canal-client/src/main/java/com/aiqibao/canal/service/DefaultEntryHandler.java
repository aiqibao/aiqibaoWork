package com.aiqibao.canal.service;

import com.aiqibao.canal.client.annotation.CanalTable;
import com.aiqibao.canal.client.context.CanalContext;
import com.aiqibao.canal.client.handler.EntryHandler;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Param;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 11:42
 * Best wish!
 */

/**
 * 源表至目标表同步的统一操作（字段保持一致，目标表表名为源表的：库名_表名）
 */
@CanalTable(value = "all")
@Component
@Slf4j
public class DefaultEntryHandler implements EntryHandler<Map<String,String>> {
    @Resource
    private DSLContext dsl;


    @Override
    public void insert(Map<String, String> map) {
        log.info("insert {}", map);
        String dataBase = CanalContext.getModel().getDatabase() ;
        String table = CanalContext.getModel().getTable();
        String targetTbale = dataBase + "_" + table ;
        List<Field<Object>> fields = map.keySet().stream().map(DSL::field).collect(Collectors.toList());
        List<Param<String>> values = map.values().stream().map(DSL::value).collect(Collectors.toList());

        int execute = dsl.insertInto(table(targetTbale)).columns(fields).values(values).execute();
        log.info("insert execute result {}", execute);
    }

    @Override
    public void update(Map<String, String> before, Map<String, String> after) {
        log.info("update before {}", before);
        log.info("update after {}", after);
        String table = CanalContext.getModel().getTable();
        Map<Field<Object>, String> map = after.entrySet().stream().filter(entry -> before.get(entry.getKey()) != null)
                .collect(Collectors.toMap(entry -> field(entry.getKey()), Map.Entry::getValue));
        dsl.update(table(table)).set(map).where(field("id").eq(after.get("id"))).execute();
    }

    @Override
    public void delete(Map<String, String> map) {
        log.info("delete {}", map);
        String table = CanalContext.getModel().getTable();
        dsl.delete(table(table)).where(field("id").eq(map.get("id"))).execute();
    }
}
