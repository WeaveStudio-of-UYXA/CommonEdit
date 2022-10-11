package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParsers;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

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
}
