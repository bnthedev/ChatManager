package chat.chat;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private final JavaPlugin plugin;
    public FileConfiguration config;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }
    private void loadConfig() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }
    public void saveConfig() {
        try {
            config.save(new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getMessage(String key) {
        return ChatColor.translateAlternateColorCodes('&', config.getString(key, ""));
    }
    public void reloadConfig() {
        loadConfig();
    }
    public boolean isAntiSpamEnabled() {
        return config.getBoolean("antiSpam", false);
    }
    public boolean isMentionInChatEnabled() {
        return config.getBoolean("mentionInChat", false);
    }
    public int getSpamInterval() {
        return config.getInt("spamInterval");
    }
}
