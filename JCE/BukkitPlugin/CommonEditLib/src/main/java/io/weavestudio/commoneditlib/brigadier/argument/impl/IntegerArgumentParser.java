package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class IntegerArgumentParser extends ArgumentParser<Integer> {

    @Override
    public @NotNull Integer parse(Feeder<String> argFeeder) throws IllegalArgumentException {
        argFeeder.checkHasMore(1);
        String arg = argFeeder.read();
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(arg + " is not a integer");
        }
    }

    @Override
    public @NotNull List<String> getHints(Feeder<String> feeder) {
        feeder.checkHasMore(1);
        feeder.skip(1);
        return Collections.emptyList();
    }

    @Override
    public @NotNull String getCommonHint() {
        return "int";
    }
}
