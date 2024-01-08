package com.github.sokakmelodileri.chatcontests;

import com.github.sokakmelodileri.chatcontests.listeners.Listeners;
import com.github.sokakmelodileri.chatcontests.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatContests extends JavaPlugin {
    public FileConfiguration config = getConfig();
    public String pluginTag = "§8[§6ChatContests§8]§r ";
    Messages messages = new Messages(this);
    public Boolean isRunning = false;
    public int randomNumberr;
    @Override
    public void onEnable() {
        // Plugin startup logic
        configYenile();
        getServer().getConsoleSender().sendMessage("§aChatContests is enabled!");
        getServer().getPluginManager().registerEvents(new Listeners(this), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                messages.messageSender();
            }
        }, 0L, config.getInt("messageInterval") * 20L);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage("§cChatContests is disabled!");
    }

    public void configYenile(){
        reloadConfig();
        saveDefaultConfig();
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }
    public void sendMessage(CommandSender receiver, String path, String... args) {
        String rawMessage = getConfig().getString("messages." + path);
        rawMessage = rawMessage.replace("&", "§");
        String formattedMessage = String.format(rawMessage, (Object[]) args);
        receiver.sendMessage(pluginTag + formattedMessage);
    }


}
