package com.inloadings.itemLives.utils.commandImpl.sub;

import com.inloadings.itemLives.utils.commandImpl.Super.AbstractSuperCommand;
import org.bukkit.command.CommandSender;

public abstract class AbstractSubCommand {
    ISubCommandInfo subCommandInfo = getClass().getAnnotation(ISubCommandInfo.class);



    public String getRequiredPermission() {
        return subCommandInfo.perm();
    }
    public String[] getSubCommandNames() {
        return subCommandInfo.params();
    }
    public String getUsageMessage() {
        return subCommandInfo.usage();
    }

    public void attemptCommand(CommandSender sender, AbstractSuperCommand command, String[] args) {
        if (sender.hasPermission(getRequiredPermission())) onCommand(sender, command, args);
        else command.getPermissionMessage();

    }
    public abstract void onCommand(CommandSender sender, AbstractSuperCommand command, String[] args);

}
