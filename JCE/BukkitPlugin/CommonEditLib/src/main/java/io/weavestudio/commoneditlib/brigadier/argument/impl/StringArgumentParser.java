package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import io.weavestudio.commoneditlib.utils.Feeder;
import io.weavestudio.commoneditlib.utils.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class StringArgumentParser<TSender> extends SingleArgumentParser<TSender, String> {

    @Override
    public String parse(String arg, DataAdaptor arguments) throws IllegalArgumentException {
        if (StringUtils.isBlank(arg)) throw new IllegalArgumentException("is blank");
        return arg;
    }

    @Override
    public @NotNull List<String> getPotentialHints(Feeder<String> feeder, TSender sender, DataAdaptor arguments) {
        feeder.checkHasMore(1);
        String arg = feeder.read();
        return StringUtils.isBlank(arg) ? Collections.emptyList() : Collections.singletonList(arg);
    }

    @Override
    public @NotNull String getSimpleHint() {
        return "string";
    }
}
