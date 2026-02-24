package org.jboss.test.jca.adapter;

import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionFactory;
import javax.resource.ResourceException;

/**
 * Stub class for compilation purposes
 */
public interface TestConnectionFactory extends ConnectionFactory {
    TestConnection getConnection(String failure) throws ResourceException;
    void setFailure(String failure);
}

