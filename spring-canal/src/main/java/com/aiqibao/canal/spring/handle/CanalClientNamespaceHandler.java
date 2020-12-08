package com.aiqibao.canal.spring.handle;

import com.aiqibao.canal.spring.parser.CanalConfigBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author fqh
 * @version 1.0  2016/9/25
 */
public class CanalClientNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("canal-config", new CanalConfigBeanDefinitionParser());
    }
}
