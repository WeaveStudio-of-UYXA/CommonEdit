package io.weavestudio.commoneditlib.brigadier.parameter;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.DispatchResult;
import io.weavestudio.commoneditlib.brigadier.Dispatcher;
import io.weavestudio.commoneditlib.brigadier.Executor;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import io.weavestudio.commoneditlib.dataadaptor.DataAdaptorUtils;
import io.weavestudio.commoneditlib.utils.Feeder;
import io.weavestudio.commoneditlib.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class NodeParameter<TSender, TResult> extends Parameter<TSender> {

    @Nullable
    protected String name;
    @Nullable
    protected ArgumentParser<TResult> argumentParser;
    @NotNull
    protected Supplier<TResult> defaultValueSupplier;

    public NodeParameter(@NotNull String name, @NotNull Supplier<TResult> defaultValueSupplier) {
        this.name = name;
        this.defaultValueSupplier = defaultValueSupplier;
        this.argumentParser = null;
        this.executor = null;
    }

    public NodeParameter(@NotNull String name, @NotNull ArgumentParser<TResult> argumentParser) {
        this.name = name;
        this.defaultValueSupplier = CommandUtils.suppliesNull();
        this.argumentParser = argumentParser;
        this.executor = null;
    }

    public NodeParameter(@NotNull Executor<TSender> executor) {
        this.name = null;
        this.defaultValueSupplier = CommandUtils.suppliesNull();
        this.argumentParser = null;
        this.executor = executor;
    }

    public @NotNull String getName() {
        return StringUtils.isBlank(name) ? "" : name;
    }

    @Override
    public List<DispatchResult<TSender>> onDispatch(Feeder<String> argFeeder, DataAdaptor argCollector, TSender sender, Dispatcher<TSender> dispatcher) {
        List<DispatchResult<TSender>> results = new ArrayList<>();

        if (argumentParser != null) { // 参数解析器非空，则必须要解析成功才可继续
            @Nullable TResult result = argumentParser.tryParse(argFeeder).orElseGet(defaultValueSupplier);
            if (result == null) return Collections.emptyList();
            if (!StringUtils.isBlank(name)) {
                argCollector.set(name, DataAdaptorUtils.fromObject(result));
            }
        }

        if (executor != null) {
            DispatchResult<TSender> result = new DispatchResult<>(dispatcher, argCollector, executor, argFeeder.getIndex());
            results.add(result);
        }

        // 解析接下来的参数，接下来的参数可能是带有默认值的
        int startIndex = argFeeder.getIndex();
        for (Parameter<TSender> followingParameter : followingParameters) {
            DataAdaptor subArgCollector = argCollector.clone();

            List<DispatchResult<TSender>> subResults = followingParameter.onDispatch(argFeeder, subArgCollector, sender, dispatcher);
            results.addAll(subResults);

            argFeeder.setIndex(startIndex);
        }

        return results;
    }
    
}
