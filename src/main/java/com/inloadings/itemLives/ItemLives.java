package com.inloadings.itemLives;

import com.inloadings.itemLives.command.lives.LivesSuperCommand;
import com.inloadings.itemLives.listener.PlayerDeathListener;
import com.inloadings.itemLives.utils.NameSpacedKeys;
import com.inloadings.itemLives.utils.commandImpl.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemLives extends JavaPlugin {

    @Override
    public void onEnable() {
        NameSpacedKeys nameSpacedKeys = new NameSpacedKeys(this);
        CommandManager.registerCommand(new LivesSuperCommand("lives"));
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);

        // config type of deaths
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
