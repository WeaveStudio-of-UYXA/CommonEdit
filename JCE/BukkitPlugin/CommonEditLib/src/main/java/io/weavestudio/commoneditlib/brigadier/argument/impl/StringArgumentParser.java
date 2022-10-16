package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class StringArgumentParser<TSender> extends ArgumentParser<TSender, String> {
    @Override
    public @NotNull String parse(Feeder<String> argFeeder, TSender sender) throws IllegalArgumentException {
        argFeeder.checkHasMore(1);
        return argFeeder.read();
    }

    @Override
    public @NotNull List<String> getPotentialHints(Feeder<String> feeder, TSender sender) {
        feeder.checkHasMore(1);
        String arg = feeder.read();
        return arg.length() == 0 ? Collections.emptyList() : Collections.singletonList(arg);
    }

    @Override
    public @NotNull String getSimpleHint() {
        return "string";
    }
}
