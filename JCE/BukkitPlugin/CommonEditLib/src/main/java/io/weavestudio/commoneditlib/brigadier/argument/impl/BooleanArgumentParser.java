package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class BooleanArgumentParser extends ArgumentParser<Boolean> {
    @Override
    public @NotNull Boolean parse(Feeder<String> argFeeder) throws IllegalArgumentException {
        argFeeder.checkHasMore(1);
        String s = argFeeder.read();
        if (CommandUtils.equals("true", s)) return true;
        else if (CommandUtils.equals("false", s)) return false;
        else throw new IllegalArgumentException("is not boolean");
    }

    @Override
    public @NotNull List<String> getHints(Feeder<String> feeder) {
        feeder.checkHasMore(1);
        return CommandUtils.tryMatch(Arrays.asList("true", "false"), feeder.read());
    }

    @Override
    public @NotNull String getCommonHint() {
        return "true|false";
    }
}
