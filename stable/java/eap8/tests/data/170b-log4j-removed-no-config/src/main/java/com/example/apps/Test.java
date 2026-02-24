package com.example.apps;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Test {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(Test.class);
        logger.info("Hello World");
    }
}