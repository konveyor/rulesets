package oracle.jms;

public class AQjmsTopicConnectionFactory implements AQjmsConnectionFactory {
    public AQjmsConnection createTopicConnection(String username, String password) {
        return new AQjmsConnectionImpl();
    }
}

