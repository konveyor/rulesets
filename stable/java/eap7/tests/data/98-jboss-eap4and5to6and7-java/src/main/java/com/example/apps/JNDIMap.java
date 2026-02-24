package com.example.apps;

import org.jboss.system.ServiceMBeanSupport;

import javax.naming.NamingException;

public class JNDIMap extends ServiceMBeanSupport implements JNDIMapMBean
{
    private String jndiName;

    public String getJndiName()
    {
        return jndiName;
    }

    public void setJndiName(String jndiName) throws NamingException
    {
    }
}