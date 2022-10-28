package io.weavestudio.commoneditlib.brigadier.argument.impl;

import io.weavestudio.commoneditlib.brigadier.CommandUtils;
import io.weavestudio.commoneditlib.brigadier.argument.ArgumentParser;
import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import io.weavestudio.commoneditlib.utils.Feeder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerNameArgumentParser extends ArgumentParser<CommandSender, String> {
    @Override
    public @NotNull String parse(Feeder<String> feeder, CommandSender sender, DataAdaptor arguments) throws IllegalArgumentException {
        feeder.checkHasMore(1);
        return feeder.read();
    }

    @Override
    public @NotNull List<String> getPotentialHints(Feeder<String> feeder, CommandSender sender, DataAdaptor arguments) throws IllegalArgumentException {
        feeder.checkHasMore(1);
        return CommandUtils.tryMatch(getCommonHints(sender, arguments), feeder.read(), false);
    }

    @Override
    public @NotNull List<String> getCommonHints(CommandSender sender, DataAdaptor arguments) {
        return sender.getServer().getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
    }

    @Override
    public @NotNull String getSimpleHint() {
        return "player_name";
    }
}
