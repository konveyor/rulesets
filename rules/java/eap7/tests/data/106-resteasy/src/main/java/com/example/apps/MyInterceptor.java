package com.example.apps;

import org.jboss.resteasy.plugins.cache.server.SimpleServerCache;

public class MyInterceptor {
    public static void main(String[] args) {
        SimpleServerCache cache = new SimpleServerCache();
    }
}