package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import io.weavestudio.commoneditlib.utils.Feeder;
import io.weavestudio.commoneditlib.utils.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LiteralArgumentParser<TSender> extends SingleArgumentParser<TSender, String> {

    private final List<String> literals;

    public LiteralArgumentParser(List<String> literals) {
        this.literals = literals;
    }

    public LiteralArgumentParser(String... literals) {
        this.literals = Arrays.asList(literals);
    }

    @Override
    public String parse(String arg, DataAdaptor arguments) throws IllegalArgumentException {
        if (!literals.contains(arg)) throw new IllegalArgumentException("is on in optional literals");
        return arg;
    }

    @Override
    public @NotNull List<String> getCommonHints(TSender sender, DataAdaptor arguments) {
        return literals;
    }

    @Override
    public @NotNull String getSimpleHint() {
        return String.join("|", literals);
    }
}
