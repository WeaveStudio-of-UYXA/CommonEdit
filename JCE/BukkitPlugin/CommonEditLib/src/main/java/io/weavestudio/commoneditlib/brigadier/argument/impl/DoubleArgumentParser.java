package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DoubleArgumentParser<TSender> extends ArgumentParser<TSender, Double> {
    @Override
    public @NotNull Double parse(Feeder<String> argFeeder, TSender sender) throws IllegalArgumentException {
        argFeeder.checkHasMore(1);
        String arg = argFeeder.read();
        try {
            return Double.parseDouble(arg);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(arg + " is not a integer");
        }
    }

    @Override
    public @NotNull List<String> getPotentialHints(Feeder<String> feeder, TSender sender) {
        feeder.checkHasMore(1);
        String arg = feeder.read();
        try {
            Double.parseDouble(arg);
            return Collections.singletonList(arg);
        } catch (NumberFormatException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public @NotNull List<String> getCommonHints(Object sender) {
        return Arrays.asList("true", "false");
    }

    @Override
    public @NotNull String getSimpleHint() {
        return "double";
    }
}
