import io.weavestudio.commoneditlib.brigadier.Dispatcher;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.brigadier.parameter.NodeParameter;
import io.weavestudio.commoneditlib.brigadier.parameter.Parameter;
import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import io.weavestudio.commoneditlib.dataadaptor.impl.MapDataAdaptor;
import io.weavestudio.commoneditlib.utils.Feeder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static io.weavestudio.commoneditlib.brigadier.argument.ArgumentParsers.*;

public class CommandTest {
    public static void main(String[] __) throws Exception {
        Dispatcher<DataAdaptor> dispatcher = new Dispatcher<>("cet");
        dispatcher
                .fork(node("", literals("exit"))
                        .execute(r -> System.exit(0))
                        .fork(node("code", INTEGER)
                                .execute(r -> System.exit(r.getArguments().get("code").asInt()))
                        )
                ).fork(node("int", INTEGER)
                        .execute(r -> System.out.println("int = " + r.getArguments().get("int").asInt()))
                        .fork(new NodeParameter<DataAdaptor, String>("name", STRING)
                                .execute(r -> System.out.println("Hello " + r.getArguments().get("int").asInt() + " of " + r.getArguments().get("name").asString() + "!"))
                                .fork(new NodeParameter<DataAdaptor, Boolean>("gender", BOOLEAN)
                                        .execute(r -> System.out.println("Hello " +
                                                r.getArguments().get("int").asInt() + " of " +
                                                (r.getArguments().get("gender").asBoolean() ? "Mr." : "Mrs.") +
                                                r.getArguments().get("name").asString() +
                                                "!")
                                        )
                                )
                        )
                ).fork(node("double", DOUBLE)
                        .execute(r -> System.out.println("double = " + r.getArguments().get("double").asDouble()))
                        .fork(new NodeParameter<DataAdaptor, String>("name", STRING)
                                .execute(r -> System.out.println("It is " + r.getArguments().get("double").asInt() + " kg of " + r.getArguments().get("name").asString() + "!"))
                        )
                ).fork(node("boolean", BOOLEAN)
                        .execute(r -> System.out.println("boolean = " + r.getArguments().get("boolean").asBoolean()))
                ).fork(node("string", STRING)
                        .execute(r -> System.out.println("string = " + r.getArguments().get("string").asString()))
                )
        ;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("/")) {
                String[] args = line.substring(1).split(" ");
                try {
                    dispatcher.dispatch(MapDataAdaptor.create(), new Feeder<>(Arrays.asList(args)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (line.startsWith("?")) {
                String[] args = line.substring(1).split(" ");
                List<String> hints = dispatcher.getHints(MapDataAdaptor.create(), new Feeder<>(Arrays.asList(args)));
                hints.forEach(System.out::println);
            }
        }
    }

    static <TResult> NodeParameter<DataAdaptor, TResult> node(String name, ArgumentParser<TResult> argumentParser) {
        return new NodeParameter<>(name, argumentParser);
    }
}
