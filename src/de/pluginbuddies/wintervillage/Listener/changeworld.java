//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Listener;

import de.pluginbuddies.wintervillage.Util.PlayerSerialize;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.io.*;

public final class changeworld implements Listener {
    @EventHandler
    public void PlayerGoWorld(PlayerChangedWorldEvent event) {
        World from = event.getFrom();
        Player player = event.getPlayer();
        World to = player.getWorld();

        // Save current inventory
        try {
            FileOutputStream fileOut = new FileOutputStream("plugins//playerinvs//" + from.getName() + "//" + player.getUniqueId().toString() + ".playerinv");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(new PlayerSerialize(player));
            out.close();
            fileOut.close();
        } catch (IOException e) {
            return;
        }

        // Load world inventory

        PlayerSerialize newInv = null;

        File f = new File("/playerinvs/" + to.getName() + "/" + player.getUniqueId().toString() + ".playerinv");

        if (f.isFile() && f.canRead()) {
            try {
                FileInputStream fileIn = new FileInputStream("plugins//playerinvs//" + to.getName() + "//" + player.getUniqueId().toString() + ".playerinv");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                newInv = (PlayerSerialize) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException | ClassNotFoundException ignored) {
                return;
            }
        }

        if (newInv != null) {
            player.getInventory().setContents(newInv.inventory.getContents());
            player.setHealth(newInv.health);
            player.setFoodLevel((int) newInv.hunger);
            player.setFlySpeed((float) newInv.flySpeed);
            player.setWalkSpeed((float) newInv.walkSpeed);
            player.setLevel(newInv.levels);
            player.setExp((float) newInv.xp);
            player.setSaturation((float) newInv.saturation);
            player.setExhaustion((float) newInv.fatigue);
        }
    }
}