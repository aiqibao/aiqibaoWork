package com.aiqibao.canal.client.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 11:53
 * Best wish!
 */
@Data
@ConfigurationProperties(prefix = CanalSimpleProperties.CANAL_PREFIX)
public class CanalSimpleProperties extends CanalProperties {

    private String userName;

    private String password;
}
