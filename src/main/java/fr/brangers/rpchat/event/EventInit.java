package fr.brangers.rpchat.event;

import fr.brangers.rpchat.Main;
import fr.brangers.rpchat.event.chat.ChatListener;
import org.bukkit.Bukkit;

public class EventInit {

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(Main.instance), Main.instance);
    }

}
