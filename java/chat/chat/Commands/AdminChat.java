package chat.chat.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("chatmanager.adminchat.send")){
            String message = "";
            String messag = "";



            for (int i = 0; i < args.length; i++) {
                String arg = (args[i] + " ");
                messag = (messag + arg);
            }

            message = ("&6&lAdmin Chat &8>>> &6" + p.getDisplayName() + "&8 -> &7" + messag);
            message = ChatColor.translateAlternateColorCodes('&', message);


            for(Player ps : Bukkit.getOnlinePlayers()){

                if(ps.hasPermission("chatmanager.adminchat.see")){
                    ps.sendMessage(message);
                }

            }
        }else{
            p.sendMessage(ChatColor.RED + "You don't have permissions for this command");
        }
        return false;
    }
}
