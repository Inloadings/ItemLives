package com.inloadings.itemLives.utils.commandImpl;

import com.inloadings.itemLives.utils.commandImpl.Super.AbstractSuperCommand;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private static final List<AbstractSuperCommand> commands = new ArrayList<>();
    public static void registerCommand(AbstractSuperCommand... superCommands) {
        for (AbstractSuperCommand superCommand : superCommands) {
            Bukkit.getCommandMap().register(superCommand.getName(), superCommand);
            commands.add(superCommand);
        }
    }
}
