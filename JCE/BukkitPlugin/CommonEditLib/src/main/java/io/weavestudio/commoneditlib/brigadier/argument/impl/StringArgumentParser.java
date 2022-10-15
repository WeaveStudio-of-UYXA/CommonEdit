package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class StringArgumentParser extends ArgumentParser<String> {
    @Override
    public @NotNull String parse(Feeder<String> argFeeder) throws IllegalArgumentException {
        argFeeder.checkHasMore(1);
        return argFeeder.read();
    }

    @Override
    public @NotNull List<String> getHints(Feeder<String> feeder) {
        feeder.checkHasMore(1);
        feeder.skip(1);
        return Collections.emptyList();
    }

    @Override
    public @NotNull String getCommonHint() {
        return "string";
    }
}
