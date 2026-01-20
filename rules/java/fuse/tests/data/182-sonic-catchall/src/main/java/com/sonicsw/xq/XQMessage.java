package com.sonicsw.xq;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock class for Sonic ESB XQMessage.
 * Created for testing purposes.
 */
public class XQMessage {
    public XQMessage() {
    }
    
    public String getHeaderValue(String name) {
        return null;
    }
    
    public void setHeaderValue(String name, String value) {
        // Mock implementation
    }
    
    public List<String> getHeaderNames() {
        return new ArrayList<String>();
    }
    
    public int getPartCount() {
        return 0;
    }
    
    public XQPart getPart(int index) {
        return new XQPart();
    }
    
    public String getCorrelationId() {
        return null;
    }
}

