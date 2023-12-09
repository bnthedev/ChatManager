package chat.chat.Commands;

import chat.chat.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final ConfigManager configManager;

    public ReloadCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("chatmanager.reload")) {
            configManager.reloadConfig();
            sender.sendMessage("Config has been reloaded.");
            return true;
        } else {
            return false;
        }
    }
}