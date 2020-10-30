package com.aiqibaowork.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author:aiqibao
 * @Date:2020/10/28 15:05
 * Best wish!
 */
public abstract class ServerLog {
    protected final Logger log = LoggerFactory.getLogger(getClass()) ;
    protected Logger getLog(){
        return log ;
    }
}
