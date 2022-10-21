package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LiteralArgumentParser<TSender> extends ArgumentParser<TSender, String> {

    private final List<String> literals;

    public LiteralArgumentParser(List<String> literals) {
        this.literals = literals;
    }

    public LiteralArgumentParser(String... literals) {
        this.literals = Arrays.asList(literals);
    }

    @Override
    public @NotNull String parse(Feeder<String> argFeeder, TSender sender) throws IllegalArgumentException {
        argFeeder.checkHasMore(1);
        String arg = argFeeder.read();
        if (!literals.contains(arg)) throw new IllegalArgumentException("is on in optional literals");
        return arg;
    }

    @Override
    public @NotNull List<String> getPotentialHints(Feeder<String> feeder, TSender sender) {
        feeder.checkHasMore(1);
        return CommandUtils.tryMatch(literals, feeder.read(), false);
    }

    @Override
    public @NotNull List<String> getCommonHints(Object sender) {
        return literals;
    }

    @Override
    public @NotNull String getSimpleHint() {
        return String.join("|", literals);
    }
}
