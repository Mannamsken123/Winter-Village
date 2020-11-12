//Plugin by: PluginBuddies
//-> Mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Listener;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class TravelWithEntity implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        if (Main.getPlugin().getTravelWithEntity() == true) {
            Entity entity = e.getRightClicked();
            Player p = e.getPlayer();
            if (entity instanceof Animals || entity instanceof Villager || entity instanceof Dolphin || entity instanceof WanderingTrader) {
                p.sendMessage(Main.getPlugin().PREFIX + entity.getName() + "§2✔");

                new BukkitRunnable() {
                    int time = 7;

                    @Override
                    public void run() {
                        time--;
                        if (time == 0) {
                            Location loc = p.getLocation();
                            entity.teleport(loc);
                            cancel();
                        }
                    }
                }.runTaskTimer(Main.getPlugin(), 0L, 20L);
            }

            Main.getPlugin().setTravelWithEntity(false);
        }
    }

}
