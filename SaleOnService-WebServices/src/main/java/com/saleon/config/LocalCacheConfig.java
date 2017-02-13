package com.saleon.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableCaching
@Profile({"default","local"})
public class LocalCacheConfig {

    @Bean
    public CacheManager concurrentMapCacheManager() {
        return new ConcurrentMapCacheManager("defaultCache");
    }
}