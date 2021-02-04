package com.aiqibao.spi;

import org.apache.dubbo.common.extension.SPI;

/**
 * @Author:aiqibao
 * @Date:2021/2/3 9:36
 * Best wish!
 */
@SPI(value = Woman.NAME)
public interface Person {
    String getName() ;
}
