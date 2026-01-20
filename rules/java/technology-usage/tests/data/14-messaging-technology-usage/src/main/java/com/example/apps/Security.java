package com.jboss.windup.test;

import javax.naming.Context;
import javax.naming.InitialContext;
// Commented out rare APIs that are not commonly available
// import javax.management.j2ee.Management;
// import javax.management.j2ee.ManagementHome;
// import javax.security.jacc.PolicyConfigurationFactory;

class MyJaccProviderService {

    // Commented out due to missing javax.security.jacc API
    /*
    public PolicyConfigurationFactory getPolicyConfigurationFactory() {
        PolicyConfigurationFactory pcf = null;
        try {
            pcf = PolicyConfigurationFactory.getPolicyConfigurationFactory();
        } catch (Exception e) {
            return null;
        }
        return pcf;
    }    
    */
    
    // Commented out due to missing javax.management.j2ee API
    /*
    public static Management getMEJBRemote() {
            Context context = new InitialContext(null);
            ManagementHome home = (ManagementHome) context.lookup("ejb.mgmt.MEJB");
            Management bean = home.create();
            return bean;
    }
    */
}