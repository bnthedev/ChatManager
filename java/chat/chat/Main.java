package chat.chat;

import chat.chat.Commands.AdminChat;
import chat.chat.Commands.ReloadCommand;
import chat.chat.Listeners.onPlayerChat;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {


    private ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);

        getLogger().info("Plugin byl úspěšně načten.");
        getServer().getPluginManager().registerEvents(new onPlayerChat(configManager), this);
        getCommand("ac").setExecutor(new AdminChat());
        getCommand("chatmanager-reload").setExecutor(new ReloadCommand(configManager));


    }

    @Override
    public void onDisable() {
        configManager.saveConfig();
        getLogger().info("Plugin byl vypnut.");
    }



}