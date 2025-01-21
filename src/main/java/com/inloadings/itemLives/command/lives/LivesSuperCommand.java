package com.inloadings.itemLives.command.lives;

import com.inloadings.itemLives.command.lives.sub.LivesAddCommand;
import com.inloadings.itemLives.command.lives.sub.LivesSetCommand;
import com.inloadings.itemLives.utils.commandImpl.Super.AbstractSuperCommand;
import com.inloadings.itemLives.utils.commandImpl.Super.ICommandInfo;
import com.inloadings.itemLives.utils.commandImpl.sub.AbstractSubCommand;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
@ICommandInfo(name = "lives", description = "asd", usage = {
        "/§8lives\n§2» §7Shows this.",
        "",
        "/§8lives set <currentLives>/<maxLives> |bypass|\n§2» §7Sets your current and max lives.",
        "",
        "/§8lives add <amount> |bypass|\n§2» §7Adds the specified amount of lives to your current total.",
        "",
        "",
        "§8|bypass|\n§2» §7Addition parameter you can add to live commands to bypass any restrictions"
})
public class LivesSuperCommand extends AbstractSuperCommand {
    public LivesSuperCommand(@NotNull String name) {
        super(name);
    }
    @Override
    protected List<AbstractSubCommand> initializeSubCommands() {
        return List.of(new LivesSetCommand(), new LivesAddCommand());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String string, @NotNull String @NotNull [] args) {
        if (args.length >= 1) executeSubCommand(sender, args);
        else sender.sendMessage(getUsageMessage());
        return true;
    }
}
