package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class BooleanArgumentParser<TSender> extends SingleArgumentParser<TSender, Boolean> {

    @Override
    public Boolean parse(String arg, DataAdaptor arguments) throws IllegalArgumentException {
        if (CommandUtils.equals("true", arg)) return true;
        else if (CommandUtils.equals("false", arg)) return false;
        else throw new IllegalArgumentException("is not boolean");
    }

    @Override
    public @NotNull List<String> getCommonHints(Object sender, DataAdaptor arguments) {
        return Arrays.asList("true", "false");
    }

    @Override
    public @NotNull String getSimpleHint() {
        return "true|false";
    }

}
