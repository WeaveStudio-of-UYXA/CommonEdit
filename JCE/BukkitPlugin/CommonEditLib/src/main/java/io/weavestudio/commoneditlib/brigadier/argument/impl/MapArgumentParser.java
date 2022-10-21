package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapArgumentParser<TSender, TKey, TResult> extends ArgumentParser<TSender, TResult> {

    public static <TSender, TKey, TResult> MapArgumentParser<TSender, TKey, TResult> of(@NotNull ArgumentParser<TSender, TKey> parser, @NotNull Map<TKey, TResult> map) {
        return new MapArgumentParser<>(parser, map);
    }

    public static <TSender, TResult> MapArgumentParser<TSender, String, TResult> of(@NotNull Map<String, TResult> map) {
        return new MapArgumentParser<>(new DynamicArgumentParser<>((ignored) -> new ArrayList<>(map.keySet())), map);
    }

    protected final @NotNull ArgumentParser<TSender, TKey> parser;
    protected final @NotNull Map<TKey, TResult> map;

    public MapArgumentParser(@NotNull ArgumentParser<TSender, TKey> parser, @NotNull Map<TKey, TResult> map) {
        this.parser = parser;
        this.map = map;
    }

    @Override
    public @NotNull TResult parse(Feeder<String> feeder, TSender sender) throws IllegalArgumentException {
        TKey key = parser.parse(feeder, sender);
        TResult result = map.get(key);
        if (result == null) throw new IllegalArgumentException("Map result of key is null: " + key);
        return result;
    }

    @Override
    public @NotNull List<String> getPotentialHints(Feeder<String> feeder, TSender sender) throws IllegalArgumentException {
        return parser.getPotentialHints(feeder, sender);
    }

    @Override
    public @NotNull List<String> getCommonHints(TSender sender) {
        return parser.getCommonHints(sender);
    }

    @Override
    public @NotNull String getSimpleHint() {
        return parser.getSimpleHint();
    }

}
