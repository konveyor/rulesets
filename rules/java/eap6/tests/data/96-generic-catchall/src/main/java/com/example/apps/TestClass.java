package com.example.apps;

import EDU.oswego.cs.dl.util.concurrent.BoundedLinkedQueue;
import amx.sample.Clazz;
import com.iona.corba.LegacyClass;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;
import org.mule.component.BindingInvocationHandler;
import mx4j.server.ChainedMBeanServer;
import org.osoa.sca.annotations.Authentication;
import com.tibco.AClazz;
import com.crystaldecisions.SampleClass;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.io.PrintWriter;
import java.sql.DriverManager;

import org.jboss.logging.Logger;

@Authentication
public class TestClass {
    public static void main(String[] args) {
        BoundedLinkedQueue queue = new BoundedLinkedQueue();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        BindingInvocationHandler bindingInvocationHandler = new BindingInvocationHandler(null);
        ChainedMBeanServer mBeanServer = new ChainedMBeanServer();
        Clazz clazz = new Clazz();
        AClazz aclazz = new AClazz();
        SampleClass sampleClass = new SampleClass();
        LegacyClass legacyClass = new LegacyClass();
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        PrintWriter logWriter = DriverManager.getLogWriter();
        Logger log = Logger.getLogger(TestClass.class);
    }
}
