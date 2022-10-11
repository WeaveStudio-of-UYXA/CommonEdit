package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

public class BooleanArgumentParser extends ArgumentParser<Boolean> {
    @Override
    public @NotNull Boolean parse(Feeder<String> argFeeder) throws IllegalArgumentException {
        argFeeder.checkHasMore(1);
        String s = argFeeder.read();
        if (CommandUtils.equals("true", s)) return true;
        else if (CommandUtils.equals("false", s)) return false;
        else throw new IllegalArgumentException("is not boolean");
    }
}
