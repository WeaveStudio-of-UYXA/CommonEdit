package io.weavestudio.commoneditlib.brigadier;

import io.weavestudio.commoneditlib.brigadier.parameter.RootParameter;
import io.weavestudio.commoneditlib.dataadaptor.impl.MapDataAdaptor;
import io.weavestudio.commoneditlib.utils.Feeder;

import java.util.Comparator;

public class Dispatcher<TSender> extends RootParameter<TSender> {

    protected String name;

    public Dispatcher(String name) {
        this.name = name;
    }

    public void dispatch(TSender sender, Feeder<String> argFeeder) throws Exception {
        DispatchResult<TSender> result = onDispatch(argFeeder, MapDataAdaptor.create(), sender, this)
                .stream()
                .max(Comparator.comparingInt(DispatchResult::getLength))
                .orElseThrow(CommandUtils.suppliesException("没有匹配的指令"));

        argFeeder.setIndex(result.getLength());
        if (argFeeder.hasNext()) throw new Exception("多余或错误的参数：" + argFeeder.read());

        result.getExecutor().onExecute(result);
    }
}
