package com.mercadona.mbordoya.web.main.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CaffeineCacheConfiguration {

  @Bean
  public Caffeine<Object, Object> caffeineConfig() {
    return Caffeine.newBuilder()
        .expireAfterWrite(1, TimeUnit.MINUTES);
  }

  @Bean
  public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
    caffeineCacheManager.setCaffeine(caffeine);

    caffeineCacheManager.registerCustomCache(
        "exampleTypesMap",
        Caffeine.from("expireAfterWrite=24h, maximumSize=1").build()
    );

    caffeineCacheManager.registerCustomCache(
        "exampleTypes",
        Caffeine.from("expireAfterWrite=24h, maximumSize=1").build()
    );

    return caffeineCacheManager;
  }
}
