package net.amitoj.minecraftmentions.listeners;

import net.amitoj.minecraftmentions.MinecraftMentions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerChatListener implements Listener {

    private MinecraftMentions _plugin;

    public PlayerChatListener(MinecraftMentions plugin) {
        this._plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (_plugin.config.enabled) {
            Pattern pattern = Pattern.compile("@(\\S*)", Pattern.CASE_INSENSITIVE);

            Matcher matcher = pattern.matcher(event.getMessage());
            while (matcher.find()) {
                String username = matcher.group(1);
                Player player = Bukkit.getPlayer(username);

                if (player != null && !event.getPlayer().getName().equals(username)) {
                    player.playSound(player.getLocation(), "custom.mention", 1, 1);
                }
            }
        }
    }

}
