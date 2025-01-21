package com.inloadings.itemLives.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public class NameSpacedKeys {
    public static NamespacedKey currentLives;
    public static NamespacedKey maxLives;
    public static NamespacedKey livesItemID;

    public NameSpacedKeys(Plugin plugin) {
        currentLives = new NamespacedKey(plugin, "currentLives");
        maxLives = new NamespacedKey(plugin, "maxLives");
        livesItemID = new NamespacedKey(plugin, "livesItemID");
    }
}
