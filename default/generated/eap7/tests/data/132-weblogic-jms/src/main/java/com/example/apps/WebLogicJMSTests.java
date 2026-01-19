package com.example.apps;// Contains code that should match for the following tests:
//
// weblogic-04000-test
// weblogic-05000-test
// weblogic-06000-test
// weblogic-07000-test
// weblogic-08000-test
//

import java.util.Enumeration;
import java.util.Properties;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import oracle.jms.AQjmsAgent;
import oracle.jms.AQjmsConnection;
import oracle.jms.AQjmsConnectionFactory;
import oracle.jms.AQjmsFactory;
import oracle.jms.AQjmsTopicConnectionFactory;
import oracle.jms.AQjmsQueueConnectionFactory;
import oracle.jms.AQjmsConsumer;
import oracle.jms.AQjmsDestination;
import oracle.jms.AQjmsSession;
import oracle.jms.AQjmsQueueBrowser;
import oracle.jms.AQjmsProducer;

import oracle.jms.AQjmsTextMessage;
import oracle.jms.AQjmsStreamMessage;
import oracle.jms.AQjmsObjectMessage;
import oracle.jms.AQjmsMapMessage;
import oracle.jms.AQjmsBytesMessage;
import oracle.jms.AQjmsMessage;

class WebLogicJMSTests
{
    public static void jmsSession()
    {
        AQjmsTopicConnectionFactory topicConnectionFactory = null;
        AQjmsQueueConnectionFactory queueConnectionFactory = null;
        AQjmsConnection topicConnection = null;
        TopicSession topicSession = null;
        TopicSession jmsSession;
        TopicSubscriber subscriber;
        Topic shipped_orders;
        int port = 5521;
        AQjmsAgent remoteAgent;

        /* create connection and session */
        topicConnectionFactory = AQjmsFactory.getTopicConnectionFactory("HOSTNAME", "SESSIONID", port, "oci8");
        topicConnection = topicConnectionFactory.createTopicConnection("jmstopic", "jmstopic");
        jmsSession = topicConnection.createTopicSession(true, Session.CLIENT_ACKNOWLEDGE);

        shipped_orders = ((AQjmsSession) jmsSession).getTopic("E", "TOPIC_NAME");

        remoteAgent = new AQjmsAgent("AGENT_NAME", "WS.TOPIC_NAME", null);
        subscriber = ((AQjmsSession) jmsSession).createRemoteSubscriber(shipped_orders, remoteAgent, null);
    }

    private static Context getContext() throws Exception
    {
        Properties environment = new Properties();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        environment.put(Context.PROVIDER_URL, "t3://localhost:7001");
        Context context = new InitialContext(environment);

        return context;
    }

    public void messageDestination(QueueSession session)
    {
        Destination destination;
//        destination = (AQjmsDestination) session.createDestination("", "");
    }

    public void messageProducer(QueueSession session)
    {
        AQjmsProducer producer;
//        producer = (AQjmsProducer) session.createPublisher(null);
    }

    public void messageConsumer(QueueSession session) throws JMSException {
        AQjmsConsumer producer;
        producer = (AQjmsConsumer) session.createConsumer(null);
    }

    public void messages(QueueSession session)
    {
        AQjmsObjectMessage message = null;
//        AQjmsTextMessage message2 = session.createMessage();
        AQjmsStreamMessage message3 = null;
        AQjmsMapMessage message4 = null;
        AQjmsBytesMessage message5 = null;
        AQjmsMessage message6 = null;
    }

    public void queueBrowser(QueueSession session) throws JMSException {
        AQjmsQueueBrowser browser;
        Queue queue;
        AQjmsObjectMessage message = null;
        Enumeration messages;

        queue = ((AQjmsSession) session).getQueue("E", "QUEUE_NAME");
        browser = (AQjmsQueueBrowser) session.createBrowser(queue, "ID");

//        for (messages = browser.elements(); message.hasMoreElements();)
//        {
//            ObjectMessage obj_message = (ObjectMessage) message.nextElement();
//        }
    }
}