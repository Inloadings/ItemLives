package com.inloadings.itemLives.listener;

import com.inloadings.itemLives.ItemLives;
import com.inloadings.itemLives.utils.LiveItem;
import com.inloadings.itemLives.utils.NameSpacedKeys;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PlayerDeathListener implements Listener {
    Map<UUID, List<ItemStack>> itemsToReturnToPlayerOnRespawn = new HashMap<>();



    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        // Use an iterator to safely remove drop items from collection.

        UUID playerUUID = event.getPlayer().getUniqueId();
        List<ItemStack> returnItems = itemsToReturnToPlayerOnRespawn.getOrDefault(playerUUID, new ArrayList<>());
        Iterator<ItemStack> iterator = event.getDrops().iterator();
        while (iterator.hasNext()) {
            ItemStack itemStack = iterator.next();
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                continue;
            }
            if (!itemStack.getItemMeta().getPersistentDataContainer().has(NameSpacedKeys.maxLives)) {
                continue;
            }
            iterator.remove();
            LiveItem liveItem = new LiveItem(itemStack);
            if (liveItem.getCurrentLives() <= 1) {
                Component itemName;
                if (itemStack.getItemMeta() != null && itemStack.getItemMeta().customName() != null) {
                    itemName = itemStack.getItemMeta().customName();
                } else {
                    itemName = Component.text(itemStack.getType().name());
                }
                Player player = event.getPlayer();
                /*
                components should die in a hole
                feel free to tell me how to correctly send itemname in chat.
                */
                player.sendMessage(itemName);
                player.sendMessage("has broken!");
                continue;
            }
            liveItem.setItemLives(liveItem.getCurrentLives() - 1, liveItem.getMaxLives());
            returnItems.add(itemStack);
        }
        itemsToReturnToPlayerOnRespawn.put(playerUUID, returnItems);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        if (!itemsToReturnToPlayerOnRespawn.containsKey(playerUUID)) {
            return;
        }
        itemsToReturnToPlayerOnRespawn.get(playerUUID).forEach(itemStack -> player.getInventory().addItem(itemStack));
        itemsToReturnToPlayerOnRespawn.remove(playerUUID);
    }

}
