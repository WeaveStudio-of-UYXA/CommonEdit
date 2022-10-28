package io.weavestudio.commoneditlib.brigadier.argument;


import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

// 注意这是argument，是单数，只解析一个参数
public abstract class ArgumentParser<TSender, TResult> {

    // 此处的arguments只是之前解析出的参数，仅供查询，请勿修改！
    @NotNull
    abstract public TResult parse(Feeder<String> feeder, TSender sender, DataAdaptor arguments) throws IllegalArgumentException;

    public Optional<TResult> tryParse(Feeder<String> argFeeder, TSender sender, DataAdaptor arguments) {
        try {
            return Optional.of(parse(argFeeder, sender, arguments));
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    // 获得满足正在输入输入的提示：例如：期待["value"]，正在输入"val"，则返回["value"]
    public @NotNull List<String> getPotentialHints(Feeder<String> feeder, TSender sender, DataAdaptor arguments) throws IllegalArgumentException {
        return Collections.emptyList();
    }

    // 获取与正在输入参无关的提示
    public @NotNull List<String> getCommonHints(TSender sender, DataAdaptor arguments) {
        return Collections.emptyList();
    }

    // 获取通用提示：例如：player_name
    public abstract @NotNull String getSimpleHint();

    public @NotNull List<String> tryGetPotentialHints(Feeder<String> argFeeder, TSender sender, DataAdaptor arguments) {
        try {
            return getPotentialHints(argFeeder, sender, arguments);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
