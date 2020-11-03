//Plugin by: PluginBuddies
//-> Mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Listener;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.text.SimpleDateFormat;
import java.util.Date;


public class BlockPortalListener implements Listener {

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {

        if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)) {
            String date = "2020/11/27"; //NETHER ÖFFNET SICH AM 27.11.2020
            Date enteredDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                enteredDate = sdf.parse(date);
            } catch (Exception ex) {

            }
            Date currentDate = new Date();

            if (enteredDate.after(currentDate)) {
                e.getPlayer().sendMessage(Main.getPlugin().getPlugin().PREFIX + "§cDas Nether ist noch geschlossen. \n§6Es wird sich am 28.11. öffnen und kann dann über das Portal am spawn betreten werden.");
            } else {
                e.getPlayer().sendMessage(Main.getPlugin().getPlugin().PREFIX + "§cBitte benutze das Netherportal am Spawn.");
            }
            e.setCancelled(true);
        }

        if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)) {

            String date = "2020/10/01"; //END ÖFFNET SICH AM 01.12.2020
            Date enteredDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                enteredDate = sdf.parse(date);
            } catch (Exception ex) {

            }
            Date currentDate = new Date();

            if (enteredDate.after(currentDate) && Main.ymlConfigBlockPortal.getString("EndSpawn").equals("false")) {
                e.getPlayer().sendMessage(Main.getPlugin().getPlugin().PREFIX + "§cDas End ist noch geschlossen. \n§6Es wird sich am 01.12. öffnen.");
                e.setCancelled(true);
            } else if (enteredDate.after(currentDate) && Main.ymlConfigBlockPortal.getString("EndSpawn").equals("true")) {
                e.getPlayer().sendMessage(Main.getPlugin().getPlugin().PREFIX + "§cDas End ist bereits freigeschaltet worden und kann über das Portal am Spawn betreten werden.");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerKillEnderDragon(EntityDeathEvent event) {
        Entity dead = event.getEntity();
        if (dead.getType().equals(EntityType.ENDER_DRAGON)) {
            if (Main.ymlConfigBlockPortal.getString("EndSpawn").equals("false")) {
                //NEED FIX
                String replace = "true";
                Main.ymlConfigBlockPortal.set("EndSpawn", replace);
                //end NEED FIX
                Bukkit.broadcastMessage(Main.getPlugin().getPlugin().PREFIX + "§2Herzlichen Glückwunsch!!! §6Der Enderdragon wurde getötet. Ab jetzt kann man über das neue Portal am Spawn in das End reisen!");
            }
        }
    }
}
