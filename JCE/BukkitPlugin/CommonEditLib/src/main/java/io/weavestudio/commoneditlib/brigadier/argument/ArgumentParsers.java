package io.weavestudio.commoneditlib.brigadier.argument;

import io.weavestudio.commoneditlib.brigadier.argument.impl.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArgumentParsers {

    public static BooleanArgumentParser BOOLEAN = new BooleanArgumentParser();
    public static IntegerArgumentParser INTEGER = new IntegerArgumentParser();
    public static DoubleArgumentParser DOUBLE = new DoubleArgumentParser();
    public static StringArgumentParser STRING = new StringArgumentParser();
    public static VectorArgumentParser VECTOR = new VectorArgumentParser();

    public static <TResult> NamedArgumentParser<TResult> createParserWithName(@NotNull String name, @NotNull ArgumentParser<TResult> valueParser) {
        return new NamedArgumentParser<>(name, valueParser);
    }

    public static LiteralArgumentParser literals(String... literals) {
        return new LiteralArgumentParser(literals);
    }

    public static LiteralArgumentParser literals(List<String> literals) {
        return new LiteralArgumentParser(literals);
    }

}
