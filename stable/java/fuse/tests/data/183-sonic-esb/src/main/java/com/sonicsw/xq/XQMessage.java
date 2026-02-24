package com.sonicsw.xq;

import java.util.List;
import java.util.ArrayList;

public class XQMessage {
    public String getHeaderValue(String name) {
        return null;
    }
    
    public void setHeaderValue(String name, String value) {
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

