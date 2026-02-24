package com.example.apps;

import javax.management.ObjectName;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class TestClass {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("Hey");
        Hashtable env = new Hashtable();
        Context context = new InitialContext(env);
        context.lookup("java:comp/env");
        ObjectName objectName = new ObjectName("com.example.apps");
        JMXServiceURL jmxServiceURL = new JMXServiceURL("url");
        JMXConnectorFactory.connect(jmxServiceURL);
    }
}
