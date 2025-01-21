package com.inloadings.itemLives.command.lives.sub;

import com.inloadings.itemLives.utils.CommandUtil;
import com.inloadings.itemLives.utils.LiveItem;
import com.inloadings.itemLives.utils.commandImpl.Super.AbstractSuperCommand;
import com.inloadings.itemLives.utils.commandImpl.sub.AbstractSubCommand;
import com.inloadings.itemLives.utils.commandImpl.sub.ISubCommandInfo;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import java.util.regex.Matcher;


@ISubCommandInfo(usage = "/lives set [CurrentLives]/[MaxLives]", params = {"set"}, perm = "itemlives.lives.set")
public class LivesSetCommand extends AbstractSubCommand {
    @Override
    public void onCommand(CommandSender sender, AbstractSuperCommand command, String[] args) {
        sender.sendMessage("started");
        CommandUtil.checkPlayer(sender).ifPresent(player -> {

            if (args.length != 2 && args.length != 3) {
                player.sendMessage(getUsageMessage());
                return;
            }

            String[] livesArray = CommandUtil.getLivesFromFractionMatcher(args[1]);
            if (!(livesArray.length == 2)) {
                player.sendMessage(getUsageMessage());
                return;
            }
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getType() == Material.AIR) {
                player.sendMessage("You are not holding a valid item!");
                return;
            }
            LiveItem liveItem = new LiveItem(item);
            int currentLives = Integer.parseInt(livesArray[0]);
            int maxLives = Integer.parseInt(livesArray[1]);
            if (currentLives > maxLives && (args.length == 2 || !args[2].equalsIgnoreCase("bypass"))) {
                player.sendMessage("That goes above the max lives! Use /lives add '[amount] |Bypass|' to bypass the max live threshold.");
                return;
            }
            liveItem.setItemLives(currentLives, maxLives);
            player.sendMessage("Set item's current lives to: ", Integer.toString(currentLives),
                    "\n Set item's max lives to: ", Integer.toString(maxLives));
        });
    }
}
