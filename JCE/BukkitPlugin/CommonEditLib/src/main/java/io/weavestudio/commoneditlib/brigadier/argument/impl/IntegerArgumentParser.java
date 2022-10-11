package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

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
}
