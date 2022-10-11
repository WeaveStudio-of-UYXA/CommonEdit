package io.weavestudio.commoneditlib.brigadier.argument;


import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

// 注意这是argument，是单数，只解析一个参数
public abstract class ArgumentParser<TResult> {

    @NotNull
    abstract public TResult parse(Feeder<String> feeder) throws IllegalArgumentException;

    @NotNull
    public TResult parse(Feeder<String> feeder, Supplier<Exception> exceptionSupplier) throws Exception {
        try {
            return parse(feeder);
        } catch (IllegalArgumentException e) {
            throw exceptionSupplier.get();
        }
    }

    @NotNull
    public Optional<TResult> tryParse(Feeder<String> feeder) {
        try {
            return Optional.of(parse(feeder));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

}
