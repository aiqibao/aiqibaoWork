package com.aiqibao.canal.client.context;

import com.aiqibao.canal.client.model.CanalModel;

/**
 * @Author:aiqibao
 * @Date:2020/12/7 17:15
 * Best wish!
 */
public class CanalContext {

    private static ThreadLocal<CanalModel> threadLocal = new ThreadLocal<>() ;

    public static CanalModel getModel(){return threadLocal.get();}

    public static void setModel(CanalModel canalModel){threadLocal.set(canalModel);}

    public static void removeModel(){threadLocal.remove();}
}
