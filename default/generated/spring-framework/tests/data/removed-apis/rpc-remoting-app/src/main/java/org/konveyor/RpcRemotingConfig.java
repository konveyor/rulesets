package org.konveyor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.caucho.HessianServiceExporter;

/**
 * Test class demonstrating usage of removed RPC-style remoting in Spring 6.
 * All of these remoting mechanisms have been removed.
 */
@Configuration
public class RpcRemotingConfig {

    // HTTP Invoker - removed in Spring 6
    @Bean
    public HttpInvokerProxyFactoryBean accountService() {
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setServiceUrl("http://localhost:8080/AccountService");
        proxy.setServiceInterface(AccountService.class);
        return proxy;
    }

    @Bean
    public HttpInvokerServiceExporter accountServiceExporter(AccountService accountService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(accountService);
        exporter.setServiceInterface(AccountService.class);
        return exporter;
    }

    // RMI Remoting - removed in Spring 6
    @Bean
    public RmiProxyFactoryBean rmiAccountService() {
        RmiProxyFactoryBean proxy = new RmiProxyFactoryBean();
        proxy.setServiceUrl("rmi://localhost:1099/AccountService");
        proxy.setServiceInterface(AccountService.class);
        return proxy;
    }

    @Bean
    public RmiServiceExporter rmiServiceExporter(AccountService accountService) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("AccountService");
        exporter.setService(accountService);
        exporter.setServiceInterface(AccountService.class);
        exporter.setRegistryPort(1099);
        return exporter;
    }

    // Hessian - removed in Spring 6
    @Bean
    public HessianProxyFactoryBean hessianAccountService() {
        HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
        proxy.setServiceUrl("http://localhost:8080/remoting/AccountService");
        proxy.setServiceInterface(AccountService.class);
        return proxy;
    }

    @Bean
    public HessianServiceExporter hessianServiceExporter(AccountService accountService) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(accountService);
        exporter.setServiceInterface(AccountService.class);
        return exporter;
    }

    // Dummy interface for compilation
    public interface AccountService {
        String getAccount(String id);
    }
}
