package fr.brangers.rpchat.event.chat;

import fr.brangers.rpchat.Main;
import fr.brangers.rpchat.chat.Chat;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ChatListener implements Listener {

    private Main main;
    private List<Entity> entity = new ArrayList<>();

    private HashMap<Character, Chat> configChat = new HashMap<>();
    private Chat globalChat;
    private Chat rpChatMode;

    public ChatListener (Main main) {
        this.main = main;
        configChat.put('!', new Chat("!", "%1$s crie: "+ ChatColor.RED + "%2$s", 100, false));
        configChat.put('#', new Chat("#", "%1$s chuchote: " + ChatColor.GREEN + "%2$s", 3, false));
        configChat.put('*', new Chat("\\*", "%1$s " + ChatColor.GOLD + "%2$s", 20, false));
        configChat.put(':', new Chat("\\:", ChatColor.LIGHT_PURPLE + "[GENERAL] %1$s " + ChatColor.WHITE + "%2$s", 20, true));
        configChat.put('(', new Chat("\\(", ChatColor.GRAY + "[HRP] %1$s " + ChatColor.GRAY + "%2$s", 20, false));
        globalChat = new Chat("", "%1$s dit: %2$s", 20, false);
        rpChatMode = new Chat("", ChatColor.LIGHT_PURPLE + "[GENERAL] %1$s " + ChatColor.WHITE + "%2$s", 20, true);
    }
    @EventHandler
    public void onPlayerJoined(PlayerJoinEvent event) {
        main.localChat.add(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        main.localChat.remove(event.getPlayer().getUniqueId());
        main.isRP.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerJoin(AsyncPlayerChatEvent event)
    {
        Player p = event.getPlayer();
        main.isRP.putIfAbsent(p.getUniqueId(), false);
        Set<Player> recipients = event.getRecipients();
        int mode = 0;
        Chat chat = null;
        if (Main.localChat.contains(p.getUniqueId()))
            if (main.isRP.get(p.getUniqueId()) == false)
                chat = rpChatMode;
            else
                chat = configChat.get(event.getMessage().charAt(0));

            if (chat == null) {
                Chat.PlayerSendMessage(event, globalChat, p, recipients);
            } else {
                Chat.PlayerSendMessage(event, chat, p, recipients);
            }
    }

}
