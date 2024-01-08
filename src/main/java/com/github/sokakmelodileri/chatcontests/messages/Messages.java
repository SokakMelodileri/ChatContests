package com.github.sokakmelodileri.chatcontests.messages;

import com.github.sokakmelodileri.chatcontests.ChatContests;
import org.bukkit.Bukkit;

import static org.bukkit.Bukkit.getServer;
public class Messages {
    ChatContests plugin;
    public int randomNumber;

    public Messages(ChatContests plugin) {
        this.plugin = plugin;
    }
    public void messageSender() {
        if (plugin.isRunning) return;
        randomNumber = getRandomInt(plugin.config.getInt("numberofQuestions"));
        plugin.getServer().broadcastMessage(plugin.pluginTag + plugin.config.getString("Questions." + randomNumber).replace("&", "§"));
        plugin.isRunning = true;
        plugin.randomNumberr = randomNumber;
    }

    public int getRandomInt(int number) {
        return (int) ((Math.random() * number) + 1);
    }
}

