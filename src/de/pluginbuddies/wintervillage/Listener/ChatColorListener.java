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
        if (p.hasPermission("wintervillage.redteam") && !p.hasPermission("wintervillage.prisonred")) {
            String msg = e.getMessage();
            e.setCancelled(true);
            if (e.getMessage().startsWith("@v")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("wintervillage.redteam")) {
                        all.sendMessage("§7[§c" + p.getName() + "§7] @village§r: §r" + msg.substring(3));
                    }
                }
            } else {
                Bukkit.broadcastMessage("§7[§c" + p.getName() + "§7]§r: §r" + msg);
            }
        }
        if (p.hasPermission("wintervillage.blueteam") && !p.hasPermission("wintervillage.prisonblue")) {
            String msg = e.getMessage();
            e.setCancelled(true);
            if (e.getMessage().startsWith("@v")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("wintervillage.blueteam")) {
                        all.sendMessage("§7[§9" + p.getName() + "§7] @village§r: §r" + msg.substring(3));
                    }
                }
            } else {
                Bukkit.broadcastMessage("§7[§9" + p.getName() + "§7]§r: §r" + msg);
            }

        }
        if (p.hasPermission("wintervillage.prisonred")) {
            String msg = e.getMessage();
            e.setCancelled(true);
            if (e.getMessage().startsWith("@v")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("wintervillage.redteam")) {
                        all.sendMessage("§7[§4" + p.getName() + "§7] @village§r: §r" + msg.substring(3));
                    }
                }
            } else {
                Bukkit.broadcastMessage("§7[§4" + p.getName() + "§7]§r: §r" + msg);
            }

        }
        if (p.hasPermission("wintervillage.prisonblue")) {
            String msg = e.getMessage();
            e.setCancelled(true);
            if (e.getMessage().startsWith("@v")) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("wintervillage.blueteam")) {
                        all.sendMessage("§7[§1" + p.getName() + "§7] @village§r: §r" + msg.substring(3));
                    }
                }
            } else {
                Bukkit.broadcastMessage("§7[§7" + p.getName() + "§7]§r: §r" + msg);
            }
        }

    }
}
