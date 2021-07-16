package net.amitoj.minecraftmentions.commands;

import net.amitoj.minecraftmentions.MinecraftMentions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandMinecarftMentions implements CommandExecutor {

    private MinecraftMentions _plugin;

    public CommandMinecarftMentions(MinecraftMentions plugin) {
        this._plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0]) {
            case "on":
            case "enable":
                _plugin.config.setEnabled(true);
                sender.sendMessage("Disabled Minecraft Mentions");
                return true;
            case "off":
            case "disable":
                _plugin.config.setEnabled(false);
                sender.sendMessage("Enabled Minecraft Mentions");
                return true;
            case "update":
                _plugin.updater.checkForUpdates();
                _plugin.updater.tryUpdating();
                sender.sendMessage("Checking for updates...");
                return true;
            case "delay":
                try {
                    Long newDelay = Long.parseLong(args[1]);
                    _plugin.config.setResourcePackPromptDelay(newDelay);
                    sender.sendMessage("Set prompt delay to "+ newDelay);
                } catch (NumberFormatException e) {
                    sender.sendMessage("Error setting delay");
                    e.printStackTrace();
                }
                return true;
            default:
                return false;
        }
    }
}
