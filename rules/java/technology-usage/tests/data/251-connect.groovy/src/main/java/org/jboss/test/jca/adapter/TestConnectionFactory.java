package org.jboss.test.jca.adapter;

import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionFactory;

public interface TestConnectionFactory extends ConnectionFactory {
    TestConnection getConnection() throws ResourceException;
    TestConnection getConnection(String failure) throws ResourceException;
    void setFailure(String failure);
}

