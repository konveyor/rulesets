package com.example.apps;

import com.ibm.jms.JmsConnectionFactory;
import com.ibm.jms.JmsMsgProducer;
import com.ibm.mqe.jms.JMSTextMessage;
import com.ibm.mqe.jms.JmsTopic;
import com.ibm.mqe.jms.MQQueue;

public class TestClass {
    public static void main(String[] args) {
        JmsConnectionFactory jmsConnectionFactory = new JmsConnectionFactory();
        JmsMsgProducer jmsMsgProducer = new JmsMsgProducer();
        MQQueue mqQueue = new MQQueue();
        JMSTextMessage textMessage = new JMSTextMessage();
        JmsTopic jmsTopic = new JmsTopic();
    }
}
