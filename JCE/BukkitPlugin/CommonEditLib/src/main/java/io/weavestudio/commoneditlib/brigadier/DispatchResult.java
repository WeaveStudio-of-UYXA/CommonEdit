package io.weavestudio.commoneditlib.brigadier;

import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import org.jetbrains.annotations.NotNull;

public class DispatchResult<TSender> {
    @NotNull
    protected final Dispatcher<TSender> dispatcher;
    @NotNull
    protected final DataAdaptor arguments;
    @NotNull
    protected final Executor<TSender> executor;
    @NotNull
    protected final TSender sender;
    protected final int length;

    public DispatchResult(@NotNull Dispatcher<TSender> dispatcher, @NotNull DataAdaptor arguments, @NotNull Executor<TSender> executor, @NotNull TSender sender, int length) {
        this.dispatcher = dispatcher;
        this.arguments = arguments;
        this.executor = executor;
        this.sender = sender;
        this.length = length;
    }

    public @NotNull Dispatcher<TSender> getDispatcher() {
        return dispatcher;
    }

    public @NotNull DataAdaptor getArguments() {
        return arguments;
    }

    public @NotNull Executor<TSender> getExecutor() {
        return executor;
    }

    public int getLength() {
        return length;
    }
}
