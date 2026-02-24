package org.jboss.test.jca.adapter;

import javax.resource.ResourceException;
import javax.resource.cci.Connection;

public interface TestConnection extends Connection {
    void setFailInPrepare(boolean fail, int xaErrorCode) throws ResourceException;
    void setFailInCommit(boolean fail, int xaErrorCode) throws ResourceException;
    void simulateConnectionError() throws ResourceException;
}

