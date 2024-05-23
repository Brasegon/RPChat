package fr.brangers.rpchat.commands.rpcommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class RPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage("Utilisation: /rp <on/off>");
                return false;
            }
            if (args[0].equalsIgnoreCase("on")) {
                if (player.hasPermission("rpchat.rp")) {
                    player.sendMessage("Vous etes desormais en mode RP.");
                    fr.brangers.rpchat.Main.isRP.put(player.getUniqueId(), true);
                    return true;
                }
                player.sendMessage("Vous n'avez pas la permission d'utiliser cette commande.");
                return false;
            }
            if (args[0].equalsIgnoreCase("off")) {
                if (player.hasPermission("rpchat.rp")) {
                    player.sendMessage("Vous n'etes plus en mode RP.");
                    fr.brangers.rpchat.Main.isRP.put(player.getUniqueId(), false);
                    return true;
                }
                player.sendMessage("Vous n'avez pas la permission d'utiliser cette commande.");
                return false;
            }
            player.sendMessage("Utilisation: /rp <on/off>");
            return false;
        }
        return false;
    }
}
