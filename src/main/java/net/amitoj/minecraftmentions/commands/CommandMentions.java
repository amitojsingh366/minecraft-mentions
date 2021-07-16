package net.amitoj.minecraftmentions.commands;

import net.amitoj.minecraftmentions.MinecraftMentions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMentions implements CommandExecutor {
    private MinecraftMentions _plugin;

    public CommandMentions(MinecraftMentions plugin) {
        this._plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0]) {
            case "on":
            case "enable":
                _plugin.database.updatePlayerEnabled((Player) sender, true);
                sender.sendMessage("Enabled mentions");
                return true;
            case "off":
            case "disable":
                _plugin.database.updatePlayerEnabled((Player) sender, false);
                sender.sendMessage("Disabled mentions");
                return true;
            default:
                return false;
        }
    }

}
