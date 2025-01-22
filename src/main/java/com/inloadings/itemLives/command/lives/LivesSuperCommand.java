package com.inloadings.itemLives.command.lives;

import com.inloadings.itemLives.command.lives.sub.LivesAddCommand;
import com.inloadings.itemLives.command.lives.sub.LivesReloadCommand;
import com.inloadings.itemLives.command.lives.sub.LivesSetCommand;
import com.inloadings.itemLives.utils.commandImpl.Super.AbstractSuperCommand;
import com.inloadings.itemLives.utils.commandImpl.Super.ICommandInfo;
import com.inloadings.itemLives.utils.commandImpl.sub.AbstractSubCommand;
import com.inloadings.itemLives.utils.config.ConfigManager;
import com.inloadings.itemLives.utils.config.ConfigPath;
import com.inloadings.itemLives.utils.config.ConfigurationFile;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@ICommandInfo(name = "lives", description = "asd", usage = {})
public class LivesSuperCommand extends AbstractSuperCommand {
    public LivesSuperCommand(@NotNull String name, JavaPlugin plugin) {
        super(name, plugin);
        overrideUsageMessage(ConfigManager.getValue(ConfigurationFile.MESSAGES, ConfigPath.LIVES_USAGE));
    }
    @Override
    protected List<AbstractSubCommand> initializeSubCommands() {
        return List.of(
                new LivesSetCommand(ConfigManager.getValue(ConfigurationFile.MESSAGES, ConfigPath.LIVES_SET_USAGE)),
                new LivesAddCommand(ConfigManager.getValue(ConfigurationFile.MESSAGES, ConfigPath.LIVES_ADD_USAGE)),
                new LivesReloadCommand(getPlugin())
        );
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String string, @NotNull String @NotNull [] args) {
        if (args.length >= 1) executeSubCommand(sender, args);
        else sender.sendMessage(getUsageMessage());
        return true;
    }
}
