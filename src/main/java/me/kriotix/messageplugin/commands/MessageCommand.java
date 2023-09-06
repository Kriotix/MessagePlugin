package me.kriotix.messageplugin.commands;

import me.kriotix.messageplugin.MessagePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MessageCommand implements CommandExecutor {

   private MessagePlugin plugin;
   public MessageCommand(MessagePlugin plugin){
       this.plugin = plugin;
   }
   public HashMap<Player, Player> replyPlayer = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player))
            return true;
        Player sender = (Player) commandSender;
        if (!sender.hasPermission("MessagePlugin.message")){
            sender.sendMessage("No permission.");
            return true;
        }

        Player receiver = Bukkit.getPlayer(args[0]);

        if (receiver == null){
            sender.sendMessage("Recipient not found.");
            return true;
        }

        String receiverMessage = ChatColor.YELLOW + "From" + commandSender.getName() + ": ";
        String senderMessage = ChatColor.AQUA + "To" + commandSender.getName() + ": ";

        for (int i = 1; i < args.length; i++){
            receiverMessage += args[i];
            senderMessage += args[i];
        }

        commandSender.sendMessage(senderMessage);
        receiver.sendMessage(receiverMessage);

        replyPlayer.put(sender, receiver);
       return true;
    }
}
