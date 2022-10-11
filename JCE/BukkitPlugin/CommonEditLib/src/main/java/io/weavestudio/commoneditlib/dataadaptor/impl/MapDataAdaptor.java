package io.weavestudio.commoneditlib.dataadaptor.impl;

import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

public class MapDataAdaptor implements DataAdaptor {

    public static MapDataAdaptor create() {
        return new MapDataAdaptor();
    }

    public static MapDataAdaptor of(Map<String, DataAdaptor> map) {
        return new MapDataAdaptor(map);
    }

    protected Map<String, DataAdaptor> map;

    public MapDataAdaptor(Map<String, DataAdaptor> map) {
        this.map = new HashMap<>(map);
    }

    public MapDataAdaptor() {
        this.map = new HashMap<>();
    }


    @Override
    public Object asPrimitive() {
        Map<String, Object> primitiveMap = new HashMap<>(map.size());
        for (Map.Entry<String, DataAdaptor> e : map.entrySet()) {
            primitiveMap.put(e.getKey(), e.getValue().asPrimitive());
        }
        return primitiveMap;
    }

    @Override
    public DataAdaptor clone() {
        return new MapDataAdaptor(new HashMap<>(map));
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @Override
    public byte asByte() {
        return (byte) map.size();
    }

    @Override
    public short asShort() {
        return (short) map.size();
    }

    @Override
    public int asInt() {
        return map.size();
    }

    @Override
    public long asLong() {
        return map.size();
    }

    @Override
    public float asFloat() {
        return map.size();
    }

    @Override
    public double asDouble() {
        return map.size();
    }

    @Override
    public String asString() {
        return map.toString();
    }

    @Override
    public DataAdaptor set(String name, DataAdaptor value) {
        return map.put(name, value);
    }

    @Override
    public DataAdaptor get(String name) {
        return Optional.ofNullable(map.get(name)).orElse(NullDataAdaptor.NULL);
    }

    @Override
    public DataAdaptor get(String name, @NotNull DataAdaptor defaultValue) {
        return Optional.ofNullable(map.get(name)).orElse(defaultValue);
    }

    @Override
    public DataAdaptor set(int index, DataAdaptor value) {
        String key = String.valueOf(index);
        return map.put(key, value);
    }

    @Override
    public DataAdaptor get(int index) {
        return Optional.ofNullable(map.get(String.valueOf(index))).orElse(NullDataAdaptor.NULL);
    }

    @Override
    public List<String> keys() {
        return new ArrayList<>(map.keySet());
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Stream<DataAdaptor> stream() {
        return map.values().stream();
    }
}
