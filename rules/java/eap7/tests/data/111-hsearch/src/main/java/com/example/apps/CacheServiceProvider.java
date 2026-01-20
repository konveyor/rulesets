package com.example.apps;

import java.util.Properties;

import org.hibernate.search.spi.BuildContext;
import org.hibernate.search.spi.ServiceProvider;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class CacheServiceProvider implements ServiceProvider<Cache> {
    private EmbeddedCacheManager manager;
    

    public void start(Properties properties) {
        //read configuration
        manager = new DefaultCacheManager();
    }

    @Override
    public void start(Properties properties, BuildContext buildContext) {

    }

    public Cache getService() {
        return manager.getCache("default");
    }

    @Override
    public void stop() {

    }

}
