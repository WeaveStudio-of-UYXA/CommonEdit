package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class SingleArgumentParser<TSender, TResult> extends ArgumentParser<TSender, TResult> {
    @Override
    public @NotNull TResult parse(Feeder<String> feeder, TSender sender, DataAdaptor arguments) throws IllegalArgumentException {
        feeder.checkHasMore(1);
        return parse(feeder.read(), arguments);
    }

    abstract TResult parse(String arg, DataAdaptor arguments) throws IllegalArgumentException;

    @Override
    public @NotNull List<String> getPotentialHints(Feeder<String> feeder, TSender sender, DataAdaptor arguments) throws IllegalArgumentException {
        feeder.checkHasMore(1);
        return CommandUtils.tryMatch(getCommonHints(sender, arguments), feeder.read(), false);
    }
}
