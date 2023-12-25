package com.uzeyirapaydin.weatherforecastapi.config.caching;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableCaching
@EnableScheduling
public class CachingConfig {

    public static final String FORECAST = "forecasts48hours";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(FORECAST);
    }

    @CacheEvict(allEntries = true, value = {FORECAST})
    @Scheduled(fixedDelay = 60 * 60 * 1000 )
    public void reportCacheEvict() {
        System.out.println("Flush Cache reportCacheEvict");
    }

}