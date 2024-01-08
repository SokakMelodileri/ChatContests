package com.github.sokakmelodileri.chatcontests.listeners;

import com.github.sokakmelodileri.chatcontests.ChatContests;
import com.github.sokakmelodileri.chatcontests.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.DIAMOND;

public class Listeners implements Listener {
    ChatContests plugin;
    Messages messages = new Messages(plugin);
    public Listeners(ChatContests plugin){
        this.plugin = plugin;
    }

@EventHandler
public void onPlayerChat (AsyncPlayerChatEvent event) {
    Player player = event.getPlayer();
    String message = event.getMessage();
    if (message.equals(plugin.config.getString("Answers." + plugin.randomNumberr))) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                plugin.sendMessage(event.getPlayer(), "rewardMessage");
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), plugin.config.getString("rewardCommand").replace("%player%", event.getPlayer().getName()));
                plugin.getServer().broadcastMessage(plugin.pluginTag + plugin.config.getString("messages.winnerMessage").replace("%player%", event.getPlayer().getName()).replace("&", "§"));
            }
        }, 5L);
        plugin.isRunning = false;
    }else {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                event.getPlayer().sendMessage("Wrong answer! / remove this message afterwards");
                event.getPlayer().sendMessage("Correct answer is: " + plugin.config.getString("Answers." + plugin.randomNumberr));
            }
        }, 5L);
    }

}

}

