package io.weavestudio.commoneditlib.brigadier.parameter;

import io.weavestudio.commoneditlib.brigadier.DispatchResult;
import io.weavestudio.commoneditlib.brigadier.Dispatcher;
import io.weavestudio.commoneditlib.brigadier.Executor;
import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public abstract class Parameter<TSender> {

    @Nullable
    protected Executor<TSender> executor;

    protected List<Parameter<TSender>> followingParameters = new LinkedList<>();


    public Parameter<TSender> fork(Parameter<TSender> parameter) {
        followingParameters.add(parameter);
        return this;
    }

    public Parameter<TSender> execute(Executor<TSender> executor) {
        this.executor = executor;
        return this;
    }

    abstract List<DispatchResult<TSender>> onDispatch(Feeder<String> argFeeder, DataAdaptor argCollector, TSender sender, Dispatcher<TSender> dispatcher);
}
