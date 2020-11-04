//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatColorListener implements Listener {

    @EventHandler
    public void handleChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("wintervillage.prisonred")) {
            String msg = e.getMessage();
            e.setCancelled(true);
            Bukkit.broadcastMessage("[§4" + p.getName() + "§r]: §r" + msg);
        }
        if (p.hasPermission("wintervillage.prisonblue")) {
            String msg = e.getMessage();
            e.setCancelled(true);
            Bukkit.broadcastMessage("[§1" + p.getName() + "§r]: §r" + msg);
        }
        if (p.hasPermission("wintervillage.redteam")) {
            String msg = e.getMessage();
            e.setCancelled(true);
            Bukkit.broadcastMessage("[§c" + p.getName() + "§r]: §r" + msg);
        }
        if (p.hasPermission("wintervillage.blueteam")) {
            String msg = e.getMessage();
            e.setCancelled(true);
            Bukkit.broadcastMessage("[§9" + p.getName() + "§r]: §r" + msg);
        }


    }


}
