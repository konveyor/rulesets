package org.konveyor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class EhCache2Application {
    public static void main(String[] args) {
        SpringApplication.run(EhCache2Application.class, args);
    }

    @Bean
    public EhCacheCacheManager cacheManager() {
        return new EhCacheCacheManager();
    }
}