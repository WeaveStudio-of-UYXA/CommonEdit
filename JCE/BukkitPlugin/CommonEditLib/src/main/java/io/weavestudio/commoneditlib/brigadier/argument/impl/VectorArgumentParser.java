package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParsers;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class VectorArgumentParser extends ArgumentParser<Vector> {
    @NotNull
    @Override
    public Vector parse(Feeder<String> argFeeder) throws IllegalArgumentException {
        argFeeder.checkHasMore(3);
        double[] values = new double[3];
        for (int i = 0; i < 3; i++) {
            Double value = ArgumentParsers.DOUBLE.parse(argFeeder);
            values[i] = value;
        }
        return new Vector(values[0], values[1], values[2]);
    }

    @Override
    public @NotNull List<String> getHints(Feeder<String> feeder) {
        if (feeder.hasMore(3)) {
            feeder.skip(3);
            return Collections.singletonList("z");
        } else if (feeder.hasMore(2)) {
            feeder.skip(2);
            return Collections.singletonList("y z");
        } else {
            feeder.checkHasMore(1);
            feeder.skip(1);
            return Collections.singletonList("x y z");
        }
    }

    @Override
    public @NotNull String getCommonHint() {
        return "x y z";
    }
}
