package io.weavestudio.commoneditlib.brigadier;

import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetHintResult<TSender> {
    @NotNull
    protected final Dispatcher<TSender> dispatcher;
    @NotNull
    protected final DataAdaptor arguments;
    @NotNull
    protected final List<String> hints;
    @NotNull
    protected final TSender sender;
    protected final int length;

    public GetHintResult(@NotNull Dispatcher<TSender> dispatcher, @NotNull DataAdaptor arguments, @NotNull List<String> hints, @NotNull TSender sender, int length) {
        this.dispatcher = dispatcher;
        this.arguments = arguments;
        this.hints = hints;
        this.sender = sender;
        this.length = length;
    }

    public @NotNull Dispatcher<TSender> getDispatcher() {
        return dispatcher;
    }

    public @NotNull DataAdaptor getArguments() {
        return arguments;
    }

    public @NotNull List<String> getHints() {
        return hints;
    }

    public @NotNull TSender getSender() {
        return sender;
    }

    public int getLength() {
        return length;
    }
}
