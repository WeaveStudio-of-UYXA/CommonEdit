package io.weavestudio.commoneditlib.brigadier;

import io.weavestudio.commoneditlib.brigadier.parameter.RootParameter;
import io.weavestudio.commoneditlib.dataadaptor.impl.MapDataAdaptor;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Dispatcher<TSender> extends RootParameter<TSender> {

    protected String name;

    public Dispatcher(String name) {
        this.name = name;
    }

    public List<String> getHints(TSender sender, Feeder<String> argFeeder) {
        List<GetHintResult<TSender>> results = onGetHints(argFeeder, MapDataAdaptor.create(), sender, this);

        int shortLength = Integer.MAX_VALUE;
        List<GetHintResult<TSender>> shortResults = new ArrayList<>();
        for (GetHintResult<TSender> result : results) {
            if (result.getLength() == shortLength) {
                shortResults.add(result);
            } else if (result.getLength() < shortLength) {
                shortResults.clear();
                shortResults.add(result);
                shortLength = result.getLength();
            }
        }

        argFeeder.setIndex(shortLength);
        if (argFeeder.hasNext()) return Collections.singletonList("!多余或错误的参数：" + argFeeder.read());

        return shortResults.stream().flatMap(r -> r.getHints().stream()).collect(Collectors.toList());
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
