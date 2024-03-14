package demo_cache_caffeine.api;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class CacheStatisticsController {

    private final CacheManager cacheManager;

    @GetMapping("/cache-info")
    public List<cacheInfo> getCacheInfo() {
        return cacheManager.getCacheNames()
                .stream()
                .map(this::getCacheInfo)
                .toList();
    }

    private cacheInfo getCacheInfo(String cacheName) {
        Cache<Object, Object> nativeCache =
                (Cache) cacheManager.getCache(cacheName).getNativeCache();
        Set<Object> keys = nativeCache.asMap().keySet();
        CacheStats stats = nativeCache.stats();
        return new cacheInfo(
                cacheName, keys.size(), keys, stats.toString());
    }

    private record cacheInfo(
            String name, int size, Set<Object> keys, String stats) {
    }
}