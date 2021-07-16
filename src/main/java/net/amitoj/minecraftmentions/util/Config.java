package net.amitoj.minecraftmentions.util;

import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {
    public String configPath;
    public String pluginPath;
    public JSONObject defaultConfig = new JSONObject();

    public boolean enabled;
    public boolean shouldAutoUpdate;
    public String resourcePackUrl;
    public String resourcePackHash;

    public Config(JavaPlugin plugin) {
        this.pluginPath = plugin.getDataFolder().getPath();
        this.configPath = pluginPath + "./config.json";
        setDefaults();
        checkConfigPath();
        checkConfigUpdates();
        getConfig();
    }

    private void getConfig() {
        JSONObject config = new JSONObject();
        FileReader reader = null;
        try {
            reader = new FileReader(configPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        try {
            config = (JSONObject) jsonParser.parse(reader);
            reader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        setEnabled((Boolean) config.get("enabled"));
        setShouldAutoUpdate((Boolean) config.get("should_auto_update"));
        setResourcePackUrl((String) config.get("resource_pack_url"));
        setResourcePackHash((String) config.get("resource_pack_hash"));
    }

    private void checkConfigUpdates() {
        JSONObject config = new JSONObject();
        FileReader reader = null;
        try {
            reader = new FileReader(configPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        try {
            config = (JSONObject) jsonParser.parse(reader);
            reader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        System.out.println(0);
        for (Object o : defaultConfig.keySet()) {
            String key = (String) o;
            if (config.get(key) == null) {
                System.out.println("Adding " + key + " to config");
                config.put(key, defaultConfig.get(key));
            }
        }

        File file = new File(configPath);
        if (file.delete()) {
            if (!Files.exists(Paths.get(configPath))) {
                try {
                    Files.write(Paths.get(configPath), config.toJSONString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setConfig() {
        JSONObject config = new JSONObject();
        config.put("enabled", enabled);
        config.put("should_auto_update",shouldAutoUpdate);
        config.put("resource_pack_url", resourcePackUrl);
        config.put("resource_pack_hash", resourcePackHash);

        File file = new File(configPath);
        if (file.delete()) {
            if (!Files.exists(Paths.get(configPath))) {
                try {
                    Files.write(Paths.get(configPath), config.toJSONString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setDefaults() {
        defaultConfig.put("enabled", true);
        defaultConfig.put("should_auto_update", true);
        defaultConfig.put("resource_pack_url", "https://cdn.amitoj.net/mc.amitoj.net/resourcepacks/mention_sound.zip");
        defaultConfig.put("resource_pack_hash", "0439A6AABDD6D3AC3173C150E464FBE8");
    }

    private void checkConfigPath() {
        if (!Files.exists(Paths.get(pluginPath))) {
            File file = new File(pluginPath);
            if (file.mkdir()) {
                System.out.println("Successfully created plugin folder");
            }
        }
        if (!Files.exists(Paths.get(configPath))) {
            try {
                Files.write(Paths.get(configPath), defaultConfig.toJSONString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        setConfig();
    }

    public void setShouldAutoUpdate(boolean shouldAutoUpdate) {
        this.shouldAutoUpdate = shouldAutoUpdate;
        setConfig();
    }

    public void setResourcePackUrl(String resourcePackUrl) {
        this.resourcePackUrl = resourcePackUrl;
        setConfig();
    }

    public void setResourcePackHash(String resourcePackHash) {
        this.resourcePackHash = resourcePackHash;
        setConfig();
    }
}
