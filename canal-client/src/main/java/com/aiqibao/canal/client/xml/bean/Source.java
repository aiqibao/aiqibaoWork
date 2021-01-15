package com.aiqibao.canal.client.xml.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author:aiqibao
 * @Date:2020/12/10 10:48
 * Best wish!
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Source {
    private String name ;
    private String table ;
    private String target ;
    private String db ;
    private Boolean allowDetele ;
    private List<Field> fields ;
}
