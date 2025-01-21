package com.inloadings.itemLives.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandUtil {
    public static Optional<Player> checkPlayer(CommandSender sender) {
        if (sender instanceof Player player) {
            return Optional.of(player);
        } else {
            if (sender != null) {
                sender.sendMessage("You cannot execute this command as console!");
            }
            return Optional.empty();
        }
    }

    public static String[] getLivesFromFractionMatcher(String string) {
        return string.split("/");
    }
}
