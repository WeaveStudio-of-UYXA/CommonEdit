package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class BooleanArgumentParser<TSender> extends ArgumentParser<TSender, Boolean> {
    @Override
    public @NotNull Boolean parse(Feeder<String> argFeeder, TSender sender) throws IllegalArgumentException {
        argFeeder.checkHasMore(1);
        String s = argFeeder.read();
        if (CommandUtils.equals("true", s)) return true;
        else if (CommandUtils.equals("false", s)) return false;
        else throw new IllegalArgumentException("is not boolean");
    }

    @Override
    public @NotNull List<String> getPotentialHints(Feeder<String> feeder, TSender sender) {
        feeder.checkHasMore(1);
        return CommandUtils.tryMatch(getCommonHints(sender), feeder.read(), false);
    }

    @Override
    public @NotNull List<String> getCommonHints(Object sender) {
        return Arrays.asList("true", "false");
    }

    @Override
    public @NotNull String getSimpleHint() {
        return "true|false";
    }

}
