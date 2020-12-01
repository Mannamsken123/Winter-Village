//Plugin by: PluginBuddies
//-> Mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Listener;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BlockPortalListener implements Listener {

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)) {
            String date = "2020/11/27";
            Date enteredDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                enteredDate = sdf.parse(date);
            } catch (Exception ex) {

            }
            Date currentDate = new Date();

            if (enteredDate.after(currentDate)) {
                p.sendMessage(Main.getPlugin().getPlugin().PREFIX + "§cDas Nether ist noch geschlossen. \n§6Es wird sich am 27.11. öffnen und kann dann über das Portal am spawn betreten werden.");
            } else {
                p.sendMessage(Main.getPlugin().getPlugin().PREFIX + "§cBitte benutze das Netherportal am Spawn.");
            }
            e.setCancelled(true);
        }

        if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)) {

            String date = "2020/12/02";
            Date enteredDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                enteredDate = sdf.parse(date);
            } catch (Exception ex) {

            }
            Date currentDate = new Date();

            String getFalse = Main.getPlugin().getYmlConfigBlockPortal().getString("EndSpawn");

            if (enteredDate.after(currentDate) && getFalse.equals("false")) {
                p.sendMessage(Main.getPlugin().getPlugin().PREFIX + "§cDas End ist noch geschlossen. \n§6Es wird sich am 01.12. öffnen.");
                e.setCancelled(true);
            } else if (enteredDate.after(currentDate) && getFalse.equals("true")) {
                p.sendMessage(Main.getPlugin().getPlugin().PREFIX + "§cDas End ist bereits freigeschaltet worden und kann über das Portal am Spawn betreten werden.");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerKillEnderDragon(EntityDeathEvent event) throws IOException {
        Entity dead = event.getEntity();
        if (dead.getType().equals(EntityType.ENDER_DRAGON)) {
            String getFalse = Main.getPlugin().getYmlConfigBlockPortal().getString("EndSpawn");
            if (getFalse.equals("false")) {

                Main.getPlugin().getYmlConfigBlockPortal().set("EndSpawn", "true");
                Main.ymlConfigBlockPortal.save(Main.getConfigBlockPortal());
                Bukkit.broadcastMessage(Main.getPlugin().getPlugin().PREFIX + "§2Herzlichen Glückwunsch!!! §6Der Enderdragon wurde getötet. Ab jetzt kann man über das neue Portal am Spawn in das End reisen!");
                World world = Bukkit.getWorld("world");
                Location loc1 = new Location(world, 119, 33, -60);
                Location loc2 = new Location(world, 119, 31, -60);
                int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
                int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
                int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
                int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
                int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
                int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());

                for (int x = minX; x <= maxX; x++) {
                    for (int y = minY; y <= maxY; y++) {
                        for (int z = minZ; z <= maxZ; z++) {
                            Block block = world.getBlockAt(x, y, z);
                            block.setType(Material.AIR);
                        }
                    }
                }

                Location loc3 = new Location(world, 118, 31, -59);
                Location loc4 = new Location(world, 118, 33, -62);
                int minX2 = Math.min(loc3.getBlockX(), loc4.getBlockX());
                int minY2 = Math.min(loc3.getBlockY(), loc4.getBlockY());
                int minZ2 = Math.min(loc3.getBlockZ(), loc4.getBlockZ());
                int maxX2 = Math.max(loc3.getBlockX(), loc4.getBlockX());
                int maxY2 = Math.max(loc3.getBlockY(), loc4.getBlockY());
                int maxZ2 = Math.max(loc3.getBlockZ(), loc4.getBlockZ());

                for (int x = minX2; x <= maxX2; x++) {
                    for (int y = minY2; y <= maxY2; y++) {
                        for (int z = minZ2; z <= maxZ2; z++) {
                            Block block2 = world.getBlockAt(x, y, z);
                            block2.setType(Material.AIR);
                        }
                    }
                }

            }
        }
    }
}
