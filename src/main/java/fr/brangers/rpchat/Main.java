package fr.brangers.rpchat;

import java.util.ArrayList;
import java.util.UUID;

import fr.brangers.rpchat.event.EventInit;
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

	public static Main instance;
	public static ArrayList<UUID> localChat = new ArrayList<>();
	public static ArrayList<UUID> GlobalChat = new ArrayList<>();
	@Override
	public void onEnable() {
		instance = this;
		EventInit.init();
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

