package org.jboss.test.jca.adapter;

import javax.resource.cci.Connection;

public interface TestConnection extends Connection {
    void setFailInPrepare(boolean fail, int xaException);
    void setFailInCommit(boolean fail, int xaException);
    void simulateConnectionError();
}

