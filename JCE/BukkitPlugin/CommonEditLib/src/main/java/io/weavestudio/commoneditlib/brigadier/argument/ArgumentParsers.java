package io.weavestudio.commoneditlib.brigadier.argument;

import io.weavestudio.commoneditlib.brigadier.argument.impl.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ArgumentParsers {

    public static PlayerNameArgumentParser PLAYER_NAME = new PlayerNameArgumentParser();

    public static <TSender> BooleanArgumentParser<TSender> bool() {
        return new BooleanArgumentParser<>();
    }

    public static <TSender> IntegerArgumentParser<TSender> integer() {
        return new IntegerArgumentParser<>();
    }

    public static <TSender> DoubleArgumentParser<TSender> decimal() {
        return new DoubleArgumentParser<>();
    }

    public static <TSender> StringArgumentParser<TSender> string() {
        return new StringArgumentParser<>();
    }

    public static <TSender> VectorArgumentParser<TSender> vector() {
        return new VectorArgumentParser<>();
    }
    public static <TSender> LiteralArgumentParser<TSender> literals(String... literals) {
        return new LiteralArgumentParser<>(literals);
    }

    public static <TSender> LiteralArgumentParser<TSender> literals(List<String> literals) {
        return new LiteralArgumentParser<>(literals);
    }

    public static <TSender> DynamicArgumentParser<TSender> dynamic(Function<TSender, List<String>> getCommonHintsFunction) {
        return new DynamicArgumentParser<>(getCommonHintsFunction);
    }

    public static <TSender, TKey, TResult> MapArgumentParser<TSender, TKey, TResult> map(@NotNull ArgumentParser<TSender, TKey> parser, @NotNull Map<TKey, TResult> map) {
        return MapArgumentParser.of(parser, map);
    }

    public static <TSender, TResult> MapArgumentParser<TSender, String, TResult> map(@NotNull Map<String, TResult> map) {
        return MapArgumentParser.of(map);
    }


}
