package oracle.jms;

public class AQjmsFactory {
    public static AQjmsTopicConnectionFactory getTopicConnectionFactory(String hostname, String sessionId, int port, String protocol) {
        return new AQjmsTopicConnectionFactory();
    }
}

