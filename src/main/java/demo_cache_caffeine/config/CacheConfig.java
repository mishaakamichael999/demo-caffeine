package demo_cache_caffeine.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.Scheduler;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@Log4j2
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.registerCustomCache("item",
                buildCache(100,200, 20));
        cacheManager.registerCustomCache("shop",
                buildCache(50,100, 20));
        return cacheManager;
    }

    private Cache<Object, Object> buildCache(
            int initialCapacity, int maximumSize, int durationInMinutes) {
        return Caffeine.newBuilder()
                .initialCapacity(initialCapacity)
                .maximumSize(maximumSize)
                .expireAfterAccess(durationInMinutes, TimeUnit.SECONDS)
                .evictionListener((Object key, Object value,
                                   RemovalCause cause) -> log.info(
                        String.format("Key %s was evicted (%s)%n", key, cause)))
                .removalListener((Object key, Object value,
                                  RemovalCause cause) -> log.info(
                        String.format("Key %s was removed (%s)%n", key, cause)))
                .scheduler(Scheduler.systemScheduler())
                .build();
    }
}
