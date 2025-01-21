package com.inloadings.itemLives.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class LiveItem {
    ItemStack itemStack;
    PersistentDataContainer dataContainer;
    ItemMeta itemMeta;

    public LiveItem(ItemStack itemStack) {
        this.itemStack = itemStack;
        itemMeta = itemStack.getItemMeta();
        dataContainer = itemMeta.getPersistentDataContainer();
    }

    public void setItemLives(int currentLives, int maxLives) {
        dataContainer.set(NameSpacedKeys.currentLives, PersistentDataType.INTEGER, currentLives);
        dataContainer.set(NameSpacedKeys.maxLives, PersistentDataType.INTEGER, maxLives);
        updateLore();
    }

    public Integer getCurrentLives() {
        return dataContainer.get(NameSpacedKeys.currentLives, PersistentDataType.INTEGER);
    }
    public Integer getMaxLives() {
        return dataContainer.get(NameSpacedKeys.maxLives, PersistentDataType.INTEGER);
    }
    public void updateLore() {
        if (itemMeta == null) return;

        List<Component> lore = itemMeta.lore();
        Integer currentLives = getCurrentLives();
        Integer maxLives = getMaxLives();

        if (lore == null) {
            lore = new ArrayList<>();
        }

        List<String> dynamicLoreLines = new ArrayList<>();
        dynamicLoreLines.add("§7Bound by threads of fate and soul,");
        dynamicLoreLines.add("§6this blade §7will find its master no matter the peril—");
        dynamicLoreLines.add("§7until its spirit fades away.§8.");
        dynamicLoreLines.add("§7Lives: §c" + currentLives + " §7/ §a" + maxLives);

        boolean hasDynamicLore = lore.stream().anyMatch(line -> line.equals(Component.text(dynamicLoreLines.getFirst())));


        //chatgpt code and is absolutely horrible im sorry for my sins.
        if (!hasDynamicLore) {
            for (String line : dynamicLoreLines) lore.add(Component.text(line));
        } else {
            int startIndex = -1;
            for (int i = 0; i < lore.size(); i++) {
                if (lore.get(i).toString().contains(dynamicLoreLines.getFirst())) {
                    startIndex = i;
                    break;
                }
            }

            if (startIndex != -1) {
                for (int i = 0; i < dynamicLoreLines.size(); i++) {
                    if (startIndex + i < lore.size()) {
                        lore.set(startIndex + i, Component.text(dynamicLoreLines.get(i)));
                    } else {
                        lore.add(Component.text(dynamicLoreLines.get(i)));
                    }
                }
            }
        }

        itemMeta.lore(lore);
        itemStack.setItemMeta(itemMeta);
    }



}
