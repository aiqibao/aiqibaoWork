package com.aiqibao.canal.client.model;

import lombok.Data;

/**
 * @Author:aiqibao
 * @Date:2020/12/7 15:17
 * Best wish!
 */
@Data
public class CanalModel {
    /**
     * 消息id
     */
    private long id;

    /**
     * 库名
     */
    private String database;

    /**
     * 表名
     */
    private String table;

    /**
     * binlog executeTime
     */
    private Long executeTime;

    /**
     * dml build timeStamp
     */
    private Long createTime;

    public static final class Builder {
        private long id;
        private String database;
        private String table;
        private Long executeTime;
        private Long createTime;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder database(String database) {
            this.database = database;
            return this;
        }

        public Builder table(String table) {
            this.table = table;
            return this;
        }

        public Builder executeTime(Long executeTime) {
            this.executeTime = executeTime;
            return this;
        }

        public Builder createTime(Long createTime) {
            this.createTime = createTime;
            return this;
        }

        public CanalModel build() {
            CanalModel canalModel = new CanalModel();
            canalModel.setId(id);
            canalModel.setDatabase(database);
            canalModel.setTable(table);
            canalModel.setExecuteTime(executeTime);
            canalModel.setCreateTime(createTime);
            return canalModel;
        }
    }
}
