package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public class DynamicArgumentParser<TSender> extends ArgumentParser<TSender, String> {

    protected final @NotNull Function<TSender, List<String>> getCommonHintsFunction;

    public DynamicArgumentParser(@NotNull Function<TSender, List<String>> getCommonHintsFunction) {
        this.getCommonHintsFunction = getCommonHintsFunction;
    }


    @Override
    public @NotNull String parse(Feeder<String> feeder, TSender sender) throws IllegalArgumentException {
        feeder.checkHasMore(1);
        String arg = feeder.read();
        List<String> validValues = getCommonHintsFunction.apply(sender);
        if (validValues.contains(arg)) return arg;
        else throw new IllegalArgumentException("Not supported: " + arg);
    }

    @Override
    public @NotNull List<String> getPotentialHints(Feeder<String> feeder, TSender sender) throws IllegalArgumentException {
        feeder.checkHasMore(1);
        return CommandUtils.tryMatch(getCommonHints(sender), feeder.read(), false);
    }

    @Override
    public @NotNull List<String> getCommonHints(TSender sender) {
        return getCommonHintsFunction.apply(sender);
    }

    @Override
    public @NotNull String getSimpleHint() {
        return "dynamic";
    }
}
