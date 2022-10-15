package io.weavestudio.commoneditlib.brigadier.parameter;

import io.weavestudio.commoneditlib.brigadier.DispatchResult;
import io.weavestudio.commoneditlib.brigadier.Dispatcher;
import io.weavestudio.commoneditlib.brigadier.GetHintResult;
import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import io.weavestudio.commoneditlib.utils.Feeder;

import java.util.ArrayList;
import java.util.List;

public class RootParameter<TSender> extends Parameter<TSender> {
    @Override
    public List<GetHintResult<TSender>> onGetHints(Feeder<String> argFeeder, DataAdaptor argCollector, TSender sender, Dispatcher<TSender> dispatcher) {
        // 解析接下来的参数，接下来的参数可能是带有默认值的
        int startIndex = argFeeder.getIndex();
        List<GetHintResult<TSender>> results = new ArrayList<>();
        for (Parameter<TSender> followingParameter : followingParameters) {
            DataAdaptor subArgCollector = argCollector.clone();

            List<GetHintResult<TSender>> subResults = followingParameter.onGetHints(argFeeder, subArgCollector, sender, dispatcher);
            results.addAll(subResults);

            argFeeder.setIndex(startIndex);
        }

        return results;
    }

    @Override
    public List<DispatchResult<TSender>> onDispatch(Feeder<String> argFeeder, DataAdaptor argCollector, TSender sender, Dispatcher<TSender> dispatcher) {
        // 解析接下来的参数，接下来的参数可能是带有默认值的
        int startIndex = argFeeder.getIndex();
        List<DispatchResult<TSender>> results = new ArrayList<>();
        for (Parameter<TSender> followingParameter : followingParameters) {
            DataAdaptor subArgCollector = argCollector.clone();

            List<DispatchResult<TSender>> subResults = followingParameter.onDispatch(argFeeder, subArgCollector, sender, dispatcher);
            results.addAll(subResults);

            argFeeder.setIndex(startIndex);
        }

        return results;
    }
}
