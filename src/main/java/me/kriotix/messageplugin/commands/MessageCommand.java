package me.kriotix.messageplugin.commands;

import me.kriotix.messageplugin.MessagePlugin;
import me.kriotix.messageplugin.ReplyMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

   private MessagePlugin plugin;
   public MessageCommand(MessagePlugin plugin){
       this.plugin = plugin;
   }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        //Checks if player sent command
        if (!(commandSender instanceof Player)){
            System.out.println("Command meant for players.");
            return true;
        }

        //Holds the user that sent the command
        Player sender = (Player) commandSender;

        //Check if messaging permission
        if (!sender.hasPermission("MessagePlugin.message")){
            sender.sendMessage("No permission.");
            return true;
        }

        //If there is no recipient or no message
        if (args.length <= 1){
            sender.sendMessage("Usage: /msg (Player) (Message)");
            return true;
        }

        //In /msg, recipient will be our first argument
        Player receiver = Bukkit.getPlayer(args[0]);

        //If said argument isn't a player online
        if (receiver == null){
            sender.sendMessage("Recipient not found.");
            return true;
        }

        //Combines string array into one string
        String message = String.join(" ", args);

        //Adds flavor text to message
        String senderMessage = ChatColor.AQUA + "To " + sender.getName() + ": " + ChatColor.GRAY + message;
        String receiverMessage = ChatColor.YELLOW + "From " + sender.getName() + ": " + ChatColor.GRAY + message;

        sender.sendMessage(senderMessage);
        receiver.sendMessage(receiverMessage);

        //Update the map with the conversation values
        ReplyMap.getInstance().updateReplyMap(sender,receiver);

       return true;
    }

}
