package io.weavestudio.commoneditlib.brigadier;

public interface Executor<TSender> {
    void onExecute(DispatchResult<TSender> result);
}
