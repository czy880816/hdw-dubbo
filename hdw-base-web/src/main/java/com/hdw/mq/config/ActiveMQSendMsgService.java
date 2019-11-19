package com.hdw.mq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.io.Serializable;

/**
 * @Description ActiveMQ 生产者服务类
 * @Author TuMinglong
 * @Date 2018/5/23 16:01
 */
@Component
public class ActiveMQSendMsgService {

    @Resource
    private JmsTemplate jmsTemplate;

    /**
     * 生产者/消费者模式
     * 发送消息
     *
     * @param queueName 队列名称
     * @param message   消息
     */
    public void sendMessage(final String queueName, final String message) {
        Destination destination = new ActiveMQQueue(queueName);
        jmsTemplate.convertAndSend(destination, message);
    }

    /**
     * 生产者/消费者模式
     * 发送消息
     *
     * @param queueName 队列名称
     * @param obj       对象
     */
    public void sendObjectMessage(final String queueName, final Serializable obj) {
        Destination destination = new ActiveMQQueue(queueName);
        jmsTemplate.convertAndSend(destination, obj);
    }

    /**
     * 订阅模式
     * 发送消息
     *
     * @param topicName topic名称
     * @param obj
     */
    public void sendObjectMessageOfTopic(final String topicName, final Serializable obj) {
        Destination destination = new ActiveMQTopic(topicName);
        jmsTemplate.convertAndSend(destination, obj);
    }
}
