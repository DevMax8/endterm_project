package devmax.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SimpleCache {

    private final Map<String, Object> storage = new HashMap<>();

    public void put(String key, Object value) {
        storage.put(key, value);
    }

    public Object get(String key) {
        return storage.get(key);
    }

    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }
}
