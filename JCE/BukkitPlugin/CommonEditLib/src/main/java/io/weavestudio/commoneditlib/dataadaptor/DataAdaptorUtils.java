package io.weavestudio.commoneditlib.dataadaptor;

import io.weavestudio.commoneditlib.dataadaptor.impl.*;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DataAdaptorUtils {

    public static <T extends DataAdaptable> Function<DataAdaptor, T> createRestorer(Supplier<T> supplier) {
        return data -> {
            T value = supplier.get();
            value.read(data);
            return value;
        };
    }

    public static <TValue extends DataAdaptable> ListDataAdaptor writeList(List<TValue> list) {
        return ListDataAdaptor.of(list.stream().map(DataAdaptable::write).collect(Collectors.toList()));
    }

    public static <TKey, TValue extends DataAdaptable> MapDataAdaptor writeMap(Map<TKey, TValue> map) {
        Map<String, DataAdaptor> ret = new HashMap<>();
        for (Map.Entry<TKey, TValue> e : map.entrySet()) {
            ret.put(String.valueOf(e.getKey()), e.getValue().write());
        }
        return MapDataAdaptor.of(ret);
    }

    public static <TValue extends DataAdaptable> List<TValue> restoreList(DataAdaptor data, Supplier<TValue> valueSupplier) {
        return data.stream().map(createRestorer(valueSupplier)).collect(Collectors.toList());
    }

    public static <TKey, TValue extends DataAdaptable> Map<TKey, TValue> restoreMap(DataAdaptor data, Function<String, TKey> keyMapper, Function<TKey, TValue> valueSupplier) {
        Map<TKey, TValue> ret = new HashMap<>();
        for (String rawKey : data.keys()) {
            TKey key = keyMapper.apply(rawKey);
            TValue value = valueSupplier.apply(key);
            value.read(data.get(rawKey));
            ret.put(key, value);
        }
        return ret;
    }

    public static String toString(DataAdaptor data) {
        return String.valueOf(data.asPrimitive());
    }

    public static DataAdaptor fromObject(Object obj) {
        if (obj == null) return NullDataAdaptor.NULL;
        if (obj instanceof DataAdaptor) return (DataAdaptor) obj;
        if (obj instanceof Number) return NumberDataAdaptor.of((Number) obj);
        if (obj instanceof String) return StringDataAdaptor.of((String) obj);
        if (obj instanceof Boolean) return BooleanDataAdaptor.of((Boolean) obj);
        if (obj instanceof Map) return fromMap((Map<?, ?>) obj);
        if (obj instanceof List) return fromList((List<?>) obj);
        if (obj instanceof ConfigurationSection) return fromMap(((ConfigurationSection) obj).getValues(false));
        return ObjectDataAdaptor.of(obj);
    }

    public static MapDataAdaptor fromMap(Map<?, ?> map) {
        Map<String, DataAdaptor> values = new HashMap<>();
        for (Map.Entry<?, ?> e : map.entrySet()) {
            String key = e.getKey().toString();
            DataAdaptor value = fromObject(e.getValue());
            values.put(key, value);
        }
        return MapDataAdaptor.of(values);
    }

    public static ListDataAdaptor fromList(List<?> list) {
        List<DataAdaptor> values = new ArrayList<>(list.size());
        for (Object obj : list) {
            DataAdaptor value = fromObject(fromObject(obj));
            values.add(value);
        }
        return ListDataAdaptor.of(values);
    }
}
