package me.kriotix.messageplugin.commands;

import me.kriotix.messageplugin.MessagePlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ReplyCommand implements CommandExecutor {

    private MessagePlugin plugin;
    public ReplyCommand(MessagePlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player))
            return true;
        Player sender = (Player) commandSender;
        if (!sender.hasPermission("MessagePlugin.message")){
            sender.sendMessage("No permission.");
            return true;
        }

        HashMap<Player,Player> replyPlayer;

        MessageCommand messageCommand = new MessageCommand(plugin);

        replyPlayer = messageCommand.replyPlayer;

        Player receiver = replyPlayer.get(sender);
        sender.sendMessage(receiver.getName());
        messageSender(sender, receiver, args);

        return true;
    }

    public void messageSender(Player sender, Player receiver,String[] args){
        String receiverMessage = ChatColor.YELLOW + "From " + sender.getName() + ": ";
        String senderMessage = ChatColor.AQUA + "To " + sender.getName() + ": ";

        for (int i = 0; i < args.length; i++){
            receiverMessage += args[i] + " ";
            senderMessage += args[i] + " ";
        }

        sender.sendMessage(senderMessage);
        receiver.sendMessage(receiverMessage);
    }
}
