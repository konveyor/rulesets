package com.example.apps;

import com.ibm.websphere.asynchbeans.Work;
import com.ibm.websphere.startupservice.XXStartupService;

public class TestClass extends Work {
    public static void main(String[] args) {
        Work work = new TestClass();
        XXStartupService startupService = new XXStartupService();
    }
}
