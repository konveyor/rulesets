package oracle.jms;

import java.util.Enumeration;
import javax.jms.QueueBrowser;

public interface AQjmsQueueBrowser extends QueueBrowser {
    Enumeration<javax.jms.Message> elements();
}

