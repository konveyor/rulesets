package com.example.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JndiLookupService {

    public DataSource getDataSource() throws NamingException {
        // Test case for jndi-to-quarkus-00001: new InitialContext()
        InitialContext ctx = new InitialContext();

        // Test case for jndi-to-quarkus-00002: lookup() method
        DataSource ds = (DataSource) ctx.lookup("java:jboss/datasources/MyDS");

        return ds;
    }

    public Object lookupEJB() throws NamingException {
        Context ctx = new InitialContext();
        // Another lookup test case
        return ctx.lookup("java:app/MyService");
    }
}
