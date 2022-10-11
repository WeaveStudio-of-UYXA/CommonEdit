package io.weavestudio.commoneditlib.brigadier.argument;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ArgumentParseResult<TResult> {

    private final boolean succeeded;
    @Nullable
    private final TResult result;
    private final int consumedArgumentAmount;

    public ArgumentParseResult(boolean succeeded, @Nullable TResult result, int consumedArgumentAmount) {
        this.succeeded = succeeded;
        this.result = result;
        this.consumedArgumentAmount = consumedArgumentAmount;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public @Nullable TResult tryGetResult() {
        return result;
    }

    public @NotNull TResult getResult() {
        return Objects.requireNonNull(result);
    }

    public int getConsumedArgumentAmount() {
        return consumedArgumentAmount;
    }
}
