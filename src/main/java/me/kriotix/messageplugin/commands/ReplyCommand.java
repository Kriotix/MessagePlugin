package me.kriotix.messageplugin.commands;

import me.kriotix.messageplugin.MessagePlugin;
import me.kriotix.messageplugin.ReplyMap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor {

    private MessagePlugin plugin;
    public ReplyCommand(MessagePlugin plugin){
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
        if (args.length == 0){
            sender.sendMessage("Usage: /r (Message)");
            return true;
        }

        //In /r, the recipient is found in the map made by /msg
        Player receiver = ReplyMap.getInstance().getReplyMap().get(sender);

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
