package com.test.alldemo.service;

/**
 * @author admin
 */
public interface ProducerServer {
    /**
     * 同步发送消息
     * @param msgBody 消息
     * @param topic 队列
     *
     */
    public void sendMsg(String msgBody, String topic);

    /**
     * @param msgBody 消息
     * @param topic 队列
     * 同步延时发送消息
     */
    public void delaySendMsg(String msgBody, String topic);
}
