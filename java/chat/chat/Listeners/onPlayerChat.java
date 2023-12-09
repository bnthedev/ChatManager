package chat.chat.Listeners;

import chat.chat.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;

public class onPlayerChat implements Listener {
    private final ConfigManager configManager;
    public onPlayerChat(ConfigManager configManager) {
        this.configManager = configManager;
    }

    private Map<Player, Long> lastMessageTimes = new HashMap<>();
    private Map<Player, String> lastMessages = new HashMap<>();


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        int spamInterval = configManager.getSpamInterval();

        Player player = event.getPlayer();

        String currentMessage = event.getMessage();


        String message = event.getMessage();


        if (isRepeatingMessage(player, currentMessage)) {
            long lastMessageTime = lastMessageTimes.get(player);
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastMessageTime < (spamInterval * 1000)) {
                if (configManager.isAntiSpamEnabled()){
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You cannost send two identical messages in a row!");
                }

            }
        } else {
            lastMessages.put(player, currentMessage);
            lastMessageTimes.put(player, System.currentTimeMillis());
        }
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            String playerName = onlinePlayer.getName();

            if (message.contains(playerName)) {
                if (configManager.isMentionInChatEnabled()) {
                    sendTitle(onlinePlayer, configManager.getMessage("mentionTitle"), "");
                }
            }
        }
    }




    private boolean isRepeatingMessage(Player player, String currentMessage) {
        return lastMessages.containsKey(player) && lastMessages.get(player).equals(currentMessage);
    }




    private void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(title, subtitle, 10, 40, 10);
    }
}
