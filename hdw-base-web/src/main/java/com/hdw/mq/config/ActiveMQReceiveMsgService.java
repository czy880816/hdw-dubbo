package com.hdw.mq.config;


import com.hdw.common.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.*;
import java.util.Enumeration;

/**
 * @Description ActiveMQ 生产者/消费者模式 消费者服务类 发布/订阅模式  订阅者模式
 * @Author TuMinglong
 * @Date 2018/5/23 16:05
 */
@Component
@Slf4j
public class ActiveMQReceiveMsgService {

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    /**
     * 生产者/消费者模式 消费者服务类
     *
     * @param text
     */
    @JmsListener(destination = QueueConstants.QUEUE_TEST, containerFactory = "queueJmsListenerContainerFactory", concurrency = "5-10")
    public void receiveMsg(String text) throws JMSException {
        destinationInfo(QueueConstants.QUEUE_TEST);
        log.info("==== 生产者/消费者模式 收到的消息：" + text + " ====");
    }


    /**
     * 发布/订阅模式  订阅者模式
     *
     * @param text
     */
    //@JmsListener(destination = "testTopic", containerFactory = "topicJmsListenerContainerFactory", concurrency = "5-10")
    public void receiveTopicMsg(String text) {
        log.info("==== 发布/订阅模式 收到的消息：" + text + " ====");
    }


    @JmsListener(destination = "ActiveMQ.Advisory.Connection", containerFactory = "topicJmsListenerContainerFactory", concurrency = "5-10")
    public void advisoryConnection(Message msg) {
        try {
            if (msg instanceof ActiveMQMessage) {
                ActiveMQMessage aMsg = (ActiveMQMessage) msg;
                if (aMsg.getDataStructure() instanceof ConnectionInfo) {
                    ConnectionInfo connectionInfo = (ConnectionInfo) aMsg.getDataStructure();
                    log.info("连接信息：" + JacksonUtils.toJson(connectionInfo));
                } else if (aMsg.getDataStructure() instanceof RemoveInfo) {
                    RemoveInfo removeInfo = (RemoveInfo) aMsg.getDataStructure();
                    log.info("移除信息：" + JacksonUtils.toJson(removeInfo));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @JmsListener(destination = "ActiveMQ.Advisory.Consumer.Queue.test", containerFactory = "topicJmsListenerContainerFactory", concurrency = "5-10")
    public void advisoryConsumerQueueTest3(Message msg) {
        try {
            if (msg instanceof ActiveMQMessage) {
                ActiveMQMessage aMsg = (ActiveMQMessage) msg;
                if (aMsg.getDataStructure() instanceof ConsumerInfo) {
                    ConsumerInfo consumerInfo = (ConsumerInfo) aMsg.getDataStructure();
                    log.info("sensorDataQueue消费者信息：" + JacksonUtils.toJson(consumerInfo));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @JmsListener(destination = "ActiveMQ.Advisory.Producer.Queue.test", containerFactory = "topicJmsListenerContainerFactory", concurrency = "5-10")
    public void advisoryProducerQueueTest(Message msg) {
        try {
            if (msg instanceof ActiveMQMessage) {
                ActiveMQMessage aMsg = (ActiveMQMessage) msg;
                if (aMsg.getDataStructure() instanceof ProducerInfo) {
                    ProducerInfo producerInfo = (ProducerInfo) aMsg.getDataStructure();
                    log.info("sensorDataQueue生产者信息：" + JacksonUtils.toJson(producerInfo));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void destinationInfo(String acQueueName) {
        Connection connection;
        try {
            connection = cachingConnectionFactory.createConnection();
            connection.start();
            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue replyTo = session.createTemporaryQueue();
            MessageConsumer consumer = session.createConsumer(replyTo);
            MessageProducer producer = session.createProducer(null);

            String queueName = "ActiveMQ.Statistics.Destination." + acQueueName;
            Queue query = session.createQueue(queueName);
            Message msg = session.createMessage();
            msg.setJMSReplyTo(replyTo);
            producer.send(query, msg);

            MapMessage reply = (MapMessage) consumer.receive();
            for (Enumeration e = reply.getMapNames(); e.hasMoreElements(); ) {
                String name = e.nextElement().toString();
                System.err.println(name + "=" + reply.getObject(name));
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
