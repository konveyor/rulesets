package oracle.jms;

import javax.jms.TopicSession;

public interface AQjmsConnection {
    TopicSession createTopicSession(boolean transacted, int acknowledgeMode);
}

