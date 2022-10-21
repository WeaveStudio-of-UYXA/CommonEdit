package io.weavestudio.commoneditlib.utils.registry;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class Registry<TKey, TValue> {

    protected Map<TKey, TValue> map = new HashMap<>();

    protected Function<TValue, TKey> keyGetter;

    public Registry(@NotNull Function<TValue, TKey> keyGetter) {
        this.keyGetter = keyGetter;
    }

    public void register(TValue value) {
        TKey key = keyGetter.apply(value);
        map.put(key, value);
    }

    public boolean unregister(TValue value) {
        TKey key = keyGetter.apply(value);
        return map.remove(key, value);
    }

    public @Nullable TValue get(TKey key) {
        return map.get(key);
    }

    public @NotNull TValue getOrThrow(TKey key, Supplier<Exception> exceptionSupplier) throws Exception {
        return Optional.ofNullable(map.get(key)).orElseThrow(exceptionSupplier);
    }

    public @NotNull Optional<TValue> tryGet(TKey key) {
        return Optional.ofNullable(map.get(key));
    }

    public Set<TKey> keySet() {
        return map.keySet();
    }

    public Collection<TValue> values() {
        return map.values();
    }

    public void clear() {
        map.clear();
    }


}
