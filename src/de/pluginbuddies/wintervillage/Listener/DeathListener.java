//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void deathmessage(PlayerDeathEvent e) {
        Player p = e.getEntity();

        String w = p.getWorld().getName();

        if (!w.equals("world-clash")) {
            if (p.hasPermission("wintervillage.redteam") && !p.hasPermission("wintervillage.prisonred")) {
                e.setDeathMessage("§7[§c" + p.getName() + "§7]§r §rist gestorben!");
            }
            if (p.hasPermission("wintervillage.blueteam") && !p.hasPermission("wintervillage.prisonblue")) {
                e.setDeathMessage("§7[§9" + p.getName() + "§7]§r §rist gestorben!");
            }
            if (p.hasPermission("wintervillage.prisonred")) {
                e.setDeathMessage("§7[§4" + p.getName() + "§7]§r §rist gestorben!");
            }
            if (p.hasPermission("wintervillage.prisonblue")) {
                e.setDeathMessage("§7[§1" + p.getName() + "§7]§r §rist gestorben!");
            }
        }

    }


}
