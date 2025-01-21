package com.inloadings.itemLives.command.lives.sub;

import com.inloadings.itemLives.utils.CommandUtil;
import com.inloadings.itemLives.utils.LiveItem;
import com.inloadings.itemLives.utils.commandImpl.Super.AbstractSuperCommand;
import com.inloadings.itemLives.utils.commandImpl.sub.AbstractSubCommand;
import com.inloadings.itemLives.utils.commandImpl.sub.ISubCommandInfo;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;

@ISubCommandInfo(usage = "/lives add", params = {"add"})
public class LivesAddCommand extends AbstractSubCommand {
    @Override
    public void onCommand(CommandSender sender, AbstractSuperCommand command, String[] args) {
        sender.sendMessage("started");
        if (args.length != 2 && args.length != 3) {
            sender.sendMessage(getUsageMessage());
            return;
        }

        CommandUtil.checkPlayer(sender).ifPresent(player -> {
            ItemStack item = player.getInventory().getItemInMainHand();

            if (!StringUtils.isNumeric(args[1])) {
                player.sendMessage("Not a valid number: ", args[1]);
                return;
            }

            if (item.getType() == Material.AIR) {
                player.sendMessage("You must be holding a valid item!");
                return;
            }

            LiveItem liveItem = new LiveItem(item);
            // Integer because liveItem.getMaxLives() can return null and you cant null check a primitive.
            Integer maxLives = liveItem.getMaxLives();
            if (maxLives == null) {
                player.sendMessage("This is not a live item. Use /lives set");
                return;
            }
            int currentLives = liveItem.getCurrentLives() + Integer.parseInt(args[1]);

            if (currentLives > maxLives && (args.length == 2 || !args[2].equalsIgnoreCase("bypass"))) {
                player.sendMessage("That goes above the max lives! Use /lives add '[amount] |Bypass|' to bypass the max live threshold.");
                return;
            }
            liveItem.setItemLives(currentLives, maxLives);
        });
    }
}
