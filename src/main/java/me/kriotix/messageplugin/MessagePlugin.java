package me.kriotix.messageplugin;

import me.kriotix.messageplugin.commands.MessageCommand;
import me.kriotix.messageplugin.commands.ReplyCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessagePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("msg").setExecutor(new MessageCommand(this));

        getCommand("r").setExecutor(new ReplyCommand(this));

    }
}
