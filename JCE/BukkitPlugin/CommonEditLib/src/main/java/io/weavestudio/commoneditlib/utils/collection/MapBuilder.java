package io.weavestudio.commoneditlib.utils.collection;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<TKey, TValue> {

    public static <TKey, TValue> MapBuilder<TKey, TValue> start(TKey key, TValue value) {
        return new MapBuilder<TKey, TValue>().put(key, value);
    }

    public static <TKey, TValue> MapBuilder<TKey, TValue> start() {
        return new MapBuilder<>();
    }

    public static <TKey, TValue> Map<TKey, TValue> mapOf(TKey key, TValue value) {
        return new MapBuilder<TKey, TValue>().put(key, value).build();
    }

    protected Map<TKey, TValue> map;

    public MapBuilder(Map<TKey, TValue> map) {
        this.map = new HashMap<>(map);
    }

    public MapBuilder() {
        this.map = new HashMap<>();
    }

    public MapBuilder<TKey, TValue> put(TKey key, TValue value) {
        map.put(key, value);
        return this;
    }

    public MapBuilder<TKey, TValue> add(Map<TKey, TValue> map) {
        this.map.putAll(map);
        return this;
    }

    public Map<TKey, TValue> build(TKey key, TValue value) {
        map.put(key, value);
        return map;
    }

    public Map<TKey, TValue> build() {
        return map;
    }
}
