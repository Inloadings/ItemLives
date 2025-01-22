package com.inloadings.itemLives.command.lives.sub;

import com.inloadings.itemLives.utils.commandImpl.Super.AbstractSuperCommand;
import com.inloadings.itemLives.utils.commandImpl.sub.AbstractSubCommand;
import com.inloadings.itemLives.utils.commandImpl.sub.ISubCommandInfo;
import com.inloadings.itemLives.utils.config.ConfigManager;
import com.inloadings.itemLives.utils.config.ConfigurationFile;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

@ISubCommandInfo(usage = "/lives reload <config>", params = {"reload"})
public class LivesReloadCommand extends AbstractSubCommand {
    JavaPlugin plugin;

    public LivesReloadCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onCommand(CommandSender sender, AbstractSuperCommand command, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(getUsageMessage());
            return;
        }
        for (ConfigurationFile file : ConfigurationFile.values()) {
            if (!file.getFileName().equalsIgnoreCase(args[1])) {
                continue;
            }
            ConfigManager.reloadConfig(file, plugin);
            sender.sendMessage("reloaded config " + file.getFileName());
            return;
        }
    }
}
