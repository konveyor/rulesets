package com.example.apps;

import javax.ejb.EntityBean;
import javax.servlet.ServletException;
import javax.enterprise.deploy.shared.ModuleType;

import com.sun.security.ntlm.Client;
import org.apache.catalina.Valve;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.jboss.as.clustering.singleton.SingletonService;
import org.jboss.ejb3.annotation.Clustered;
import org.hornetq.api.core.client.ClientRequestor;

import java.io.IOException;

@Clustered
public class TestClass implements EntityBean, Valve {

    public static void main(String[] args) throws Exception {
        ModuleType moduleType = ModuleType.EJB;
        SingletonService singletonService = new SingletonService();
        ClientRequestor clientRequestor = new ClientRequestor(null, "");
    }

    @Override
    public Valve getNext() {
        return null;
    }

    @Override
    public void setNext(Valve valve) {

    }

    @Override
    public void backgroundProcess() {

    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {

    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }
}
