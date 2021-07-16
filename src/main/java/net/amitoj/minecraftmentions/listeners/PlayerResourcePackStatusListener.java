package net.amitoj.minecraftmentions.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class PlayerResourcePackStatusListener implements Listener {

    @EventHandler
    public void onResourcepackStatusEvent(PlayerResourcePackStatusEvent event) {
        if (event.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED) {
            event.getPlayer().kickPlayer("You need to accept the resource pack to join this server");
        }

        if (event.getStatus() == PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD) {
            event.getPlayer().kickPlayer("The server resource pack failed to download, please try again later");
        }
    }
}
