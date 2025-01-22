package com.inloadings.itemLives;

import com.inloadings.itemLives.command.lives.LivesSuperCommand;
import com.inloadings.itemLives.listener.PlayerDeathListener;
import com.inloadings.itemLives.utils.NameSpacedKeys;
import com.inloadings.itemLives.utils.commandImpl.CommandManager;
import com.inloadings.itemLives.utils.config.ConfigManager;
import com.inloadings.itemLives.utils.config.ConfigPath;
import com.inloadings.itemLives.utils.config.ConfigurationFile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemLives extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigurationFile.MESSAGES.load(this);
        new NameSpacedKeys(this);
        CommandManager.registerCommand(new LivesSuperCommand("lives", this));
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
        String message = ConfigManager.getValue(ConfigurationFile.MESSAGES, ConfigPath.LIVES_ADD_USAGE);
        Bukkit.getServer().sendMessage(Component.text(message));

// Retrieve a String value

        // config type of deaths
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
