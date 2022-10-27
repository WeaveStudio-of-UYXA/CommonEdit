package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class IntegerArgumentParser<TSender> extends SingleArgumentParser<TSender, Integer> {

    @Override
    Integer parse(String arg) throws IllegalArgumentException {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(arg + " is not a integer");
        }
    }

    @Override
    public @NotNull List<String> getPotentialHints(Feeder<String> feeder, TSender sender) {
        feeder.checkHasMore(1);
        String arg = feeder.read();
        try {
            Integer.parseInt(arg);
            return Collections.singletonList(arg);
        } catch (NumberFormatException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public @NotNull String getSimpleHint() {
        return "int";
    }
}
