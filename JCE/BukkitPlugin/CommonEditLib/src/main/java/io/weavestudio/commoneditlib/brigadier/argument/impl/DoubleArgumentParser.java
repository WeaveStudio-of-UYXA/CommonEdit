package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class DoubleArgumentParser<TSender> extends SingleArgumentParser<TSender, Double> {

    @Override
    public Double parse(String arg, DataAdaptor arguments) throws IllegalArgumentException {
        try {
            return Double.parseDouble(arg);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(arg + " is not a double");
        }
    }

    @Override
    public @NotNull List<String> getPotentialHints(Feeder<String> feeder, TSender sender, DataAdaptor arguments) {
        feeder.checkHasMore(1);
        String arg = feeder.read();
        try {
            Double.parseDouble(arg);
            return Collections.singletonList(arg);
        } catch (NumberFormatException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public @NotNull String getSimpleHint() {
        return "double";
    }
}
