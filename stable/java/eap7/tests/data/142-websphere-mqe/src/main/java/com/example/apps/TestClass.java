package com.example.apps;

import com.ibm.mqe.adapters.MQeDiskFieldsAdapter;
import com.ibm.mqe.jms.MQeJMSAuthenticator;
import com.ibm.mqe.jms.MQeJMSMsgFieldNames;

public class TestClass extends MQeDiskFieldsAdapter {
    public static void main(String[] args) {
        MQeJMSAuthenticator authenticator = new MQeJMSAuthenticator();
        MQeJMSMsgFieldNames msgFieldNames = new MQeJMSMsgFieldNames();
    }
}
