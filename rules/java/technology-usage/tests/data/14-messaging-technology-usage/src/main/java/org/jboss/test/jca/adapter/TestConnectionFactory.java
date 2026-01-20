package org.jboss.test.jca.adapter;

import javax.resource.cci.ConnectionFactory;
import javax.resource.cci.Connection;
import javax.resource.ResourceException;

public interface TestConnectionFactory extends ConnectionFactory {
    TestConnection getConnection() throws ResourceException;
    TestConnection getConnection(int failure) throws ResourceException;
    TestConnection getConnection(String failure) throws ResourceException;
    void setFailure(String failure);
}

