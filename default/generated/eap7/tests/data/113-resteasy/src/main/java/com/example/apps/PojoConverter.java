package com.example.apps;

import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.spi.StringConverter;
import javax.ws.rs.ext.Provider;

@Provider
public class PojoConverter implements StringConverter<POJO>
{
    public POJO fromString(String str)
    {
        System.out.println("FROM STRNG: " + str);
        POJO pojo = new POJO();
        pojo.setName(str);
        return pojo;
    }

    public String toString(POJO value)
    {
        return value.getName();
    }
}
