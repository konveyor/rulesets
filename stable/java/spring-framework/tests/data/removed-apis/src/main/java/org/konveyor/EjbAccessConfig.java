package org.konveyor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ejb.access.LocalStatelessSessionProxyFactoryBean;
import org.springframework.ejb.access.SimpleRemoteStatelessSessionProxyFactoryBean;

/**
 * Test class demonstrating usage of removed EJB access in Spring 6.
 * Dedicated EJB access support has been removed.
 */
@Configuration
public class EjbAccessConfig {

    // Local EJB access - removed in Spring 6
    @Bean
    public LocalStatelessSessionProxyFactoryBean localEjbProxy() {
        LocalStatelessSessionProxyFactoryBean proxy = new LocalStatelessSessionProxyFactoryBean();
        proxy.setJndiName("java:comp/env/ejb/MyLocalEjb");
        proxy.setBusinessInterface(MyEjbInterface.class);
        return proxy;
    }

    // Remote EJB access - removed in Spring 6
    @Bean
    public SimpleRemoteStatelessSessionProxyFactoryBean remoteEjbProxy() {
        SimpleRemoteStatelessSessionProxyFactoryBean proxy = new SimpleRemoteStatelessSessionProxyFactoryBean();
        proxy.setJndiName("java:comp/env/ejb/MyRemoteEjb");
        proxy.setBusinessInterface(MyEjbInterface.class);
        return proxy;
    }

    // Dummy interface for compilation
    public interface MyEjbInterface {
        String performOperation();
    }
}
