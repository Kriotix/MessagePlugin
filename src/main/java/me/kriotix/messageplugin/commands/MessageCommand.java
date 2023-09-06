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

        messageSender(sender,receiver,args);

        replyPlayer.put(sender,receiver);

       return true;
    }

    public HashMap<Player,Player> getHash(HashMap<Player,Player> replyPlayer){
        return replyPlayer;
    }

    public void messageSender(Player sender, Player receiver,String[] args){
        String receiverMessage = ChatColor.YELLOW + "From " + sender.getName() + ": ";
        String senderMessage = ChatColor.AQUA + "To " + sender.getName() + ": ";

        for (int i = 1; i < args.length; i++){
            receiverMessage += args[i] + " ";
            senderMessage += args[i] + " ";
        }

        sender.sendMessage(senderMessage);
        receiver.sendMessage(receiverMessage);
    }
}
