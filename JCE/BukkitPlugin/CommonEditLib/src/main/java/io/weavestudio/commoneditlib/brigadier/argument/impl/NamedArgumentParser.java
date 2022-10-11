package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class NamedArgumentParser<TResult> extends ArgumentParser<TResult> {

    protected String name;
    protected List<String> aliases;
    protected ArgumentParser<TResult> valueParser;

    public NamedArgumentParser(@NotNull String name, @NotNull ArgumentParser<TResult> valueParser) {
        this.name = name;
        this.valueParser = valueParser;
    }

    public NamedArgumentParser<TResult> addAliases(String... aliases) {
        this.aliases.addAll(Arrays.asList(aliases));
        return this;
    }

    @Override
    public @NotNull TResult parse(Feeder<String> argFeeder) throws IllegalArgumentException {
        argFeeder.checkHasMore(1);
        String label = argFeeder.read();
        if (!name.equals(label) && !aliases.contains(label)) throw new IllegalArgumentException(label + " does not match name " + name);
        return valueParser.parse(argFeeder);
    }
}
