package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumArgumentParser<TSender, TEnum extends Enum<TEnum>> extends SingleArgumentParser<TSender, TEnum>{

    protected final TEnum[] values;
    protected final Function<TEnum, String> literalGetter;
    protected final String simpleHint;

    public EnumArgumentParser(TEnum[] values, Function<TEnum, String> literalGetter, String simpleHint) {
        this.values = values;
        this.literalGetter = literalGetter;
        this.simpleHint = simpleHint;
    }


    @Override
    public @NotNull String getSimpleHint() {
        return simpleHint;
    }

    @Override
    TEnum parse(String arg) throws IllegalArgumentException {
        return Arrays.stream(values).filter(v -> CommandUtils.equals(literalGetter.apply(v), arg)).findAny().orElseThrow(() -> new IllegalArgumentException("is not valid value"));
    }

    @Override
    public @NotNull List<String> getCommonHints(TSender sender) {
        return Arrays.stream(values).map(literalGetter).collect(Collectors.toList());
    }
}
