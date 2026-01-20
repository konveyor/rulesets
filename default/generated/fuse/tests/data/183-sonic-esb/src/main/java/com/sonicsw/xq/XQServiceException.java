package com.sonicsw.xq;

public class XQServiceException extends Exception {
    public XQServiceException() {
        super();
    }
    
    public XQServiceException(String message) {
        super(message);
    }
    
    public XQServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public XQServiceException(Throwable cause) {
        super(cause);
    }
}

