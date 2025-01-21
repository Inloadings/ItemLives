package com.inloadings.itemLives.utils.commandImpl;

import com.inloadings.itemLives.utils.commandImpl.Super.AbstractSuperCommand;
import org.bukkit.Bukkit;

public class CommandManager {
    public static void registerCommand(AbstractSuperCommand... superCommands) {
        for (AbstractSuperCommand superCommand : superCommands) {
            Bukkit.getCommandMap().register(superCommand.getName(), superCommand);
        }
    }
}
