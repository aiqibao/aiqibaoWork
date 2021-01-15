package com.aiqibao.canal.client.xml.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:aiqibao
 * @Date:2020/12/10 10:50
 * Best wish!
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Field {
    private String name ;
    private String type ;
    private String alias ;
    private Boolean isPrimaryKey ;
}
