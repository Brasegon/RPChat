package fr.brangers.rpchat.chat;

import fr.brangers.rpchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Set;

public class Chat {

    private String type;
    private String messageTpl;
    private int distance;
    private boolean isGlobal;
    public Chat(String type, String messageTpl, int distance, boolean isGlobal) {
        this.type = type;
        this.messageTpl = messageTpl;
        this.distance = distance;
        this.isGlobal = isGlobal;
    }


    public static boolean PlayerSendMessage(AsyncPlayerChatEvent event, Chat chat, Player player, Set<Player> recipients) {
        event.setFormat(chat.getMessageTpl());
        event.setMessage(event.getMessage().replaceFirst(chat.getType(), ""));

        for (Player receiver : Bukkit.getOnlinePlayers()) {
            recipients.remove(receiver);
            if (chat.isGlobal()) {
                recipients.remove(receiver);
                recipients.add(player);
                recipients.add(receiver);
            } else {
                if (Main.inRange(player, receiver, chat.getDistance())) {
                    recipients.add(player);
                    recipients.add(receiver);
                }
            }
        }
        return true;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessageTpl() {
        return messageTpl;
    }

    public void setMessageTpl(String messageTpl) {
        this.messageTpl = messageTpl;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public void setGlobal(boolean global) {
        isGlobal = global;
    }

}
