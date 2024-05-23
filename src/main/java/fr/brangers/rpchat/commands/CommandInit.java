package fr.brangers.rpchat.commands;

import fr.brangers.rpchat.Main;
import fr.brangers.rpchat.commands.rpcommand.RPCommand;
import fr.brangers.rpchat.commands.rpcommand.RPCommandTab;

public class CommandInit {
    public static void init() {
        Main.instance.getCommand("rp").setExecutor(new RPCommand());
        Main.instance.getCommand("rp").setTabCompleter(new RPCommandTab());
    }

}
