package com.example.apps;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.LocalBinding;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.ws.annotation.EndpointConfig;
import org.jboss.wsf.common.DOMUtils;
import org.jboss.wsf.common.addressing.Address;
import org.jboss.wsf.common.handler.Handler;
import org.jboss.wsf.spi.annotation.Annotation;
import org.jboss.wsf.spi.binding.Binding;
import org.jboss.wsf.spi.management.recording.Recording;
import org.jboss.wsf.spi.tools.ant.AntHelper;
import org.jboss.wsf.spi.tools.cmd.CmdHelper;
import org.jboss.wsf.spi.util.ServiceLoader;

import javax.jms.QueueConnectionFactory;

@Service
@Management
@LocalBinding(jndiBinding = "")
@Depends("")
@Annotation
@Binding
@EndpointConfig
public class TestClass {
    private QueueConnectionFactory factory;

    public TestClass() {}

    public static void main(String[] args) {
        QueueConnectionFactory factory;
        Recording management = new Recording();
        AntHelper helper = new AntHelper();
        CmdHelper cmdHelper = new CmdHelper();
        ServiceLoader serviceLoader = new ServiceLoader();
        Handler handler = new Handler();
        Address address = new Address();
        DOMUtils domUtils = new DOMUtils();
    }
}
