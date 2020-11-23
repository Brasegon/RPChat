package fr.brangers.SwtdrChat;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin {
	public static ArrayList<UUID> localChat = new ArrayList<>();
	public static ArrayList<UUID> GlobalChat = new ArrayList<>();
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new MyListener(this), this);
		for (Player all : Bukkit.getOnlinePlayers()) {
          localChat.add(all.getUniqueId());
          continue;
        } 
	}
	@Override
	public void onDisable() {
	}
	@Override
	public void onLoad() {
	}
	
	public static boolean inRange(Player player, Player receiver, int radius) {
	    if (receiver.getLocation().getWorld().equals(player.getLocation().getWorld()) && 
	      receiver.getLocation().distanceSquared(player.getLocation()) <= (radius * radius))
	      return true; 
	    return false;
	  }
}

