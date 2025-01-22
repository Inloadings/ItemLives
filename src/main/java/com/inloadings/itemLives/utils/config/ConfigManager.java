package com.inloadings.itemLives.utils.config;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {

    public static void reloadConfig(ConfigurationFile configurationFile, JavaPlugin plugin) {
        configurationFile.load(plugin);
    }

    public static <T> T getValue(ConfigurationFile configurationFile, ConfigPath configPath) {
        Configuration config = configurationFile.getYamlConfiguration();
        return configPath.castToType(config.get(configPath.getPath()));
    }
}
