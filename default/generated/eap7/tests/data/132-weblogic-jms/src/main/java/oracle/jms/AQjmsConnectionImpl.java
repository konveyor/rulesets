package oracle.jms;

import javax.jms.TopicSession;

public class AQjmsConnectionImpl implements AQjmsConnection {
    @Override
    public TopicSession createTopicSession(boolean transacted, int acknowledgeMode) {
        return new AQjmsSession();
    }
}

