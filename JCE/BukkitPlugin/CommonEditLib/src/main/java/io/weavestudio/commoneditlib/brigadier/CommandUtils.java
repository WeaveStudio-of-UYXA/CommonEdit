package io.weavestudio.commoneditlib.brigadier;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CommandUtils {

    public static <T> Supplier<T> suppliesNull() {
        return () -> null;
    }

    public static void ensureArrayLength(String[] args, int startIndex, int length) throws IllegalArgumentException {
        if (args.length < startIndex + length) throw new IllegalArgumentException("Length not enough");
    }

    public static Player asPlayer(CommandSender sender) {
        if (!(sender instanceof Player)) throw new IllegalArgumentException("命令发送者必须是玩家");
        return (Player) sender;
    }


    public static List<String> tryMatch(List<String> rawList, String inputting, boolean returnRawIfNotFound) {
        List<String> res = rawList.stream().filter(name -> name.startsWith(inputting)).collect(Collectors.toList());
        return (res.size() == 0 && returnRawIfNotFound) ? rawList : res;
    }

    public static List<String> tryMatch(List<String> rawList, String inputting) {
        return tryMatch(rawList, inputting, true);
    }

    public static String tryGet(String[] args, int index) {
        return args.length > index ? args[index] : "";
    }

    public static boolean equals(String s1, String s2) {
        return s1 != null && s1.equals(s2);
    }

    public static Supplier<Exception> suppliesException(String message) {
        return () -> new Exception(message);
    }
}
