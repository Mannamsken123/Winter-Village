//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Listener;

import de.pluginbuddies.wintervillage.Main.Main;
import de.pluginbuddies.wintervillage.Util.Team;
import net.minecraft.server.v1_16_R2.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class JoinListener implements Listener {

    World world = Bukkit.getWorld("world");

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Main.Bürgermeisterblue.clear();
        Main.Bürgermeisterred.clear();
        Team.maketeams();


        if (!player.hasPlayedBefore()) {
            player.setGameMode(GameMode.SURVIVAL);
            //Spawn-TP
            Location location = world.getSpawnLocation();
            location.setY(world.getHighestBlockYAt(location) + 1);
            player.teleport(location);
            //Player-Attribute
            player.setFoodLevel(20);
            player.setHealth(20);
            player.setTotalExperience(0);
            player.setExp(0);
            player.setLevel(0);
            player.getInventory().clear();
            player.sendMessage(Main.getPlugin().PREFIX + "§bHerzlich Willkommen bei Winter Village!");
            st(player.getPlayer(), "§bWinter Village", "§7by mullemann25 & Mannam01", 5, 50, 5);
        }

        //event.setJoinMessage("§a§l>> §7" + player.getName() + " ist beigetreten!");
        st(player.getPlayer(), "§bWinter Village", "§7by mullemann25 & Mannam01", 5, 50, 5);


        if (player.hasPermission("wintervillage.prisonblue")) {
            Team.prefix(player, "&1BlauMeister: ");
            event.setJoinMessage("§a§l>> §1" + player.getName() + " §7ist beigetreten!");
        }
        if (player.hasPermission("wintervillage.prisonred")) {
            Team.prefix(player, "&4RotMeister: ");
            event.setJoinMessage("§a§l>> §4" + player.getName() + " §7ist beigetreten!");
        }

        if (player.hasPermission("wintervillage.blueteam")) {
            Team.prefix(player, "&9Blau: ");
            event.setJoinMessage("§a§l>> §9" + player.getName() + " §7ist beigetreten!");
        }
        if (player.hasPermission("wintervillage.redteam")) {
            Team.prefix(player, "&cRot: ");
            event.setJoinMessage("§a§l>> §c" + player.getName() + " §7ist beigetreten!");
        }


    }

    @EventHandler
    public void handlePlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("wintervillage.prisonblue")) {
            event.setQuitMessage("§a§l>> §1" + player.getName() + " §7ist beigetreten!");
        }
        if (player.hasPermission("wintervillage.prisonred")) {
            event.setQuitMessage("§a§l>> §4" + player.getName() + " §7ist beigetreten!");
        }

        if (player.hasPermission("wintervillage.blueteam")) {
            event.setQuitMessage("§a§l>> §9" + player.getName() + " §7ist beigetreten!");
        }
        if (player.hasPermission("wintervillage.redteam")) {
            event.setQuitMessage("§a§l>> §c" + player.getName() + " §7ist beigetreten!");
        }
    }

    public void st(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        PacketPlayOutTitle times;
        if (title != null) {
            times = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, CraftChatMessage.fromString(title)[0]);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(times);
        }
        if (subtitle != null) {
            times = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, CraftChatMessage.fromString(subtitle)[0]);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(times);
        }
        times = new PacketPlayOutTitle(fadeIn, stay, fadeOut);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(times);
    }

}



