package com.example.apps;

import org.jboss.logging.LogMessage;

public interface MyLogger {
    @LogMessage
    void foobar(Throwable t);
}
