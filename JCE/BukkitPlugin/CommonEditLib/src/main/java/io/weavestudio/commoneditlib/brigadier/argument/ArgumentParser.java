package io.weavestudio.commoneditlib.brigadier.argument;


import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    @NotNull
    public List<String> getHints(Feeder<String> feeder) {
        return Collections.singletonList(getCommonHint());
    }

    @NotNull
    public List<String> tryGetHints(Feeder<String> feeder) {
        try {
            return getHints(feeder);
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    // 获取通用提示：例如：player_name
    @NotNull
    public abstract String getCommonHint();
}
