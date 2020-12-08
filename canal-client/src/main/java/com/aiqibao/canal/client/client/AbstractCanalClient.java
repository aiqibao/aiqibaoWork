package com.aiqibao.canal.client.client;

import com.aiqibao.canal.client.handler.MessageHandler;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;
import lombok.extern.slf4j.Slf4j;
import org.jooq.tools.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Author:aiqibao
 * @Date:2020/12/7 15:57
 * Best wish!
 */
@Slf4j
public abstract class AbstractCanalClient implements CanalClient {

    protected volatile boolean flag ;

    private Thread workThread ;

    private CanalConnector canalConnector ;

    protected String filter = StringUtils.EMPTY ;

    protected Integer batchSize = 1 ;

    protected Long timeout = 1L ;

    public CanalConnector getCanalConnector() {
        return canalConnector;
    }

    public void setCanalConnector(CanalConnector canalConnector) {
        this.canalConnector = canalConnector;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    protected TimeUnit unit = TimeUnit.SECONDS ;

    private MessageHandler messageHandler ;

    @Override
    public void start() {
        log.info("start canal client");
        workThread = new Thread(this::process) ;
        workThread.setName("canal-client-thread");
        flag = true ;
        workThread.start();
    }

    @Override
    public void stop() {
        log.info("stop canal client");
        flag = false ;
        if (null != workThread){
            workThread.interrupt();
        }
    }

    @Override
    public void process() {
        while (flag){
            try{
                canalConnector.connect();
                canalConnector.subscribe(filter);
                while (flag){
                    Message message = canalConnector.getWithoutAck(batchSize,timeout,unit) ;
                    log.info("获取消息{}",message) ;
                    long batchId = message.getId() ;
                    if (message.getId() != -1 && message.getEntries().size() != 0){
                        messageHandler.handleMessage(message);
                    }
                    canalConnector.ack(batchId);
                }
            }catch (Exception e){
                log.error("canal client 异常",e);
            }finally {
                canalConnector.disconnect();
            }
        }
    }
}
