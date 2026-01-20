package oracle.jms;

import javax.jms.*;

public class AQjmsSession implements TopicSession, QueueSession {
    public Topic getTopic(String schema, String topicName) {
        return null;
    }
    
    public Queue getQueue(String schema, String queueName) {
        return null;
    }
    
    public TopicSubscriber createRemoteSubscriber(Topic topic, AQjmsAgent agent, Object selector) {
        return null;
    }
    
    public Destination createDestination(String schema, String destinationName) {
        return null;
    }
    
    // TopicSession methods
    @Override
    public TopicPublisher createPublisher(Topic topic) throws JMSException {
        return null;
    }
    
    @Override
    public TopicSubscriber createSubscriber(Topic topic) throws JMSException {
        return null;
    }
    
    @Override
    public TopicSubscriber createSubscriber(Topic topic, String messageSelector, boolean noLocal) throws JMSException {
        return null;
    }
    
    @Override
    public Topic createTopic(String topicName) throws JMSException {
        return null;
    }
    
    @Override
    public TopicSubscriber createDurableSubscriber(Topic topic, String name) throws JMSException {
        return null;
    }
    
    @Override
    public TopicSubscriber createDurableSubscriber(Topic topic, String name, String messageSelector, boolean noLocal) throws JMSException {
        return null;
    }
    
    @Override
    public void unsubscribe(String name) throws JMSException {
    }
    
    // QueueSession methods
    @Override
    public Queue createQueue(String queueName) throws JMSException {
        return null;
    }
    
    @Override
    public QueueReceiver createReceiver(Queue queue) throws JMSException {
        return null;
    }
    
    @Override
    public QueueReceiver createReceiver(Queue queue, String messageSelector) throws JMSException {
        return null;
    }
    
    @Override
    public QueueSender createSender(Queue queue) throws JMSException {
        return null;
    }
    
    @Override
    public QueueBrowser createBrowser(Queue queue) throws JMSException {
        return null;
    }
    
    @Override
    public QueueBrowser createBrowser(Queue queue, String messageSelector) throws JMSException {
        return null;
    }
    
    // Session methods
    @Override
    public BytesMessage createBytesMessage() throws JMSException {
        return null;
    }
    
    @Override
    public MapMessage createMapMessage() throws JMSException {
        return null;
    }
    
    @Override
    public Message createMessage() throws JMSException {
        return null;
    }
    
    @Override
    public ObjectMessage createObjectMessage() throws JMSException {
        return null;
    }
    
    @Override
    public ObjectMessage createObjectMessage(java.io.Serializable object) throws JMSException {
        return null;
    }
    
    @Override
    public StreamMessage createStreamMessage() throws JMSException {
        return null;
    }
    
    @Override
    public TextMessage createTextMessage() throws JMSException {
        return null;
    }
    
    @Override
    public TextMessage createTextMessage(String text) throws JMSException {
        return null;
    }
    
    @Override
    public boolean getTransacted() throws JMSException {
        return false;
    }
    
    @Override
    public int getAcknowledgeMode() throws JMSException {
        return 0;
    }
    
    @Override
    public void commit() throws JMSException {
    }
    
    @Override
    public void rollback() throws JMSException {
    }
    
    @Override
    public void close() throws JMSException {
    }
    
    @Override
    public void recover() throws JMSException {
    }
    
    @Override
    public MessageListener getMessageListener() throws JMSException {
        return null;
    }
    
    @Override
    public void setMessageListener(MessageListener listener) throws JMSException {
    }
    
    @Override
    public void run() {
    }
    
    @Override
    public MessageProducer createProducer(Destination destination) throws JMSException {
        return null;
    }
    
    @Override
    public MessageConsumer createConsumer(Destination destination) throws JMSException {
        return null;
    }
    
    @Override
    public MessageConsumer createConsumer(Destination destination, String messageSelector) throws JMSException {
        return null;
    }
    
    @Override
    public MessageConsumer createConsumer(Destination destination, String messageSelector, boolean noLocal) throws JMSException {
        return null;
    }
    
    public MessageConsumer createDurableConsumer(Topic topic, String name) throws JMSException {
        return null;
    }
    
    public MessageConsumer createDurableConsumer(Topic topic, String name, String messageSelector, boolean noLocal) throws JMSException {
        return null;
    }
    
    public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName) throws JMSException {
        return null;
    }
    
    public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName, String messageSelector) throws JMSException {
        return null;
    }
    
    public MessageConsumer createSharedDurableConsumer(Topic topic, String name) throws JMSException {
        return null;
    }
    
    public MessageConsumer createSharedDurableConsumer(Topic topic, String name, String messageSelector) throws JMSException {
        return null;
    }
    
    @Override
    public TemporaryQueue createTemporaryQueue() throws JMSException {
        return null;
    }
    
    @Override
    public TemporaryTopic createTemporaryTopic() throws JMSException {
        return null;
    }
}

