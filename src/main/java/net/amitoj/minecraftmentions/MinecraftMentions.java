package net.amitoj.minecraftmentions;

import net.amitoj.minecraftmentions.commands.CommandMinecarftMentions;
import net.amitoj.minecraftmentions.listeners.PlayerChatListener;
import net.amitoj.minecraftmentions.listeners.PlayerJoinListener;
import net.amitoj.minecraftmentions.listeners.PlayerResourcePackStatusListener;
import net.amitoj.minecraftmentions.util.Config;
import net.amitoj.minecraftmentions.util.Updater;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftMentions extends JavaPlugin {

    public Config config = new Config(this);
    public Updater updater = new Updater(this, config);

    @Override
    public void onEnable() {

        PlayerJoinListener playerJoinListener = new PlayerJoinListener(this);
        PlayerChatListener playerChatListener = new PlayerChatListener(this);
        PlayerResourcePackStatusListener playerResourcePackStatusListener = new PlayerResourcePackStatusListener();

        getServer().getPluginManager().registerEvents(playerJoinListener, this);
        getServer().getPluginManager().registerEvents(playerChatListener, this);
        getServer().getPluginManager().registerEvents(playerResourcePackStatusListener, this);

        this.getCommand("minecraftmentions").setExecutor(new CommandMinecarftMentions(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
