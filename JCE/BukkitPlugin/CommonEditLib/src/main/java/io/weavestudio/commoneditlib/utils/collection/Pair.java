package io.weavestudio.commoneditlib.utils.collection;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Pair<TFirst, TSecond> {

    public static <TFirst, TSecond> Pair<TFirst, TSecond> fromEntry(Map.Entry<TFirst, TSecond> entry) {
        return new Pair<>(entry.getKey(), entry.getValue());
    }

    public static <TFirst, TSecond> Map<TFirst, TSecond> toMap(Collection<Pair<TFirst, TSecond>> pairs) {
        Map<TFirst, TSecond> map = new HashMap<>();
        for (Pair<TFirst, TSecond> pair : pairs) {
            map.put(pair.getFirst(), pair.getSecond());
        }
        return map;
    }

    protected TFirst first;
    protected TSecond second;

    public Pair(TFirst first, TSecond second) {
        this.first = first;
        this.second = second;
    }

    public TFirst getFirst() {
        return first;
    }

    public TSecond getSecond() {
        return second;
    }
}
