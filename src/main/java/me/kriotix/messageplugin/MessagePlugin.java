package me.kriotix.messageplugin;

import me.kriotix.messageplugin.commands.MessageCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessagePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("msg").setExecutor(new MessageCommand(this));
    }
}
