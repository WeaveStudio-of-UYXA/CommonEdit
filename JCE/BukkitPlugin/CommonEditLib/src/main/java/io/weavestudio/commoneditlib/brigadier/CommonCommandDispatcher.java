package io.weavestudio.commoneditlib.brigadier;

import io.weavestudio.commoneditlib.utils.Feeder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class CommonCommandDispatcher extends Dispatcher<CommandSender> implements CommandExecutor {
    public CommonCommandDispatcher(String name) {
        super(name);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!name.equals(command.getName())) return false;
        try {
            dispatch(sender, new Feeder<>(Arrays.asList(args)));
        } catch (Exception e) {
            sender.sendMessage(e.getMessage());
        }
        return true;
    }
}
