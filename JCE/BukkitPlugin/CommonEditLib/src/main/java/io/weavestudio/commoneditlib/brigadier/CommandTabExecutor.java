package io.weavestudio.commoneditlib.brigadier;

import io.weavestudio.commoneditlib.brigadier.Dispatcher;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CommandTabExecutor extends Dispatcher<CommandSender> implements TabExecutor {

    public CommandTabExecutor(String name) {
        super(name);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!Objects.equals(name, command.getName())) return false;
        try {
            dispatch(sender, new Feeder<>(Arrays.asList(args)));
            return true;
        } catch (Exception e) {
            sender.sendMessage(e.getMessage());
            return true;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return getHints(sender, new Feeder<>(Arrays.asList(args)));
    }
}
