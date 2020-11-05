//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onRespawnEVENT(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        World world = Bukkit.getWorld("world");
        Location location = new Location(world, 114.528, 41, -71.520, -90, -3);
        p.setGameMode(GameMode.SURVIVAL);
        e.setRespawnLocation(location);

    }


}
