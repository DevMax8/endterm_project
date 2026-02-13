package devmax.controller;

import devmax.cache.SimpleCache;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private final SimpleCache cache;

    public CacheController(SimpleCache cache) {
        this.cache = cache;
    }

    // ✅ GET Cache Info
    @GetMapping
    public Map<String, Object> getCacheStatus() {
        return Map.of(
                "message", "Cache is working",
                "cacheSize", cache.size()
        );
    }

    // ✅ DELETE Cache Clear
    @DeleteMapping
    public Map<String, Object> clearCache() {
        cache.clear();
        return Map.of(
                "message", "Cache cleared successfully"
        );
    }
}
