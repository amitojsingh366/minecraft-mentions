package net.amitoj.minecraftmentions.listeners;

import net.amitoj.minecraftmentions.MinecraftMentions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private MinecraftMentions _plugin;

    public PlayerJoinListener(MinecraftMentions plugin) {
        this._plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (_plugin.config.enabled && _plugin.database.getPlayerEnabled(event.getPlayer())) {
            setPlayerResourcePack(event.getPlayer());
        }
    }

    private void setPlayerResourcePack(Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, () ->
                player.setResourcePack(_plugin.config.resourcePackUrl,
                        _plugin.config.resourcePackHash, true), _plugin.config.resourcePackPromptDelay);
    }
}
