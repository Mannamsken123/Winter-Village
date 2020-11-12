//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import net.minecraft.server.v1_16_R2.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class VillageCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String w = p.getWorld().getName();

            if (args.length == 0) {
                if (!w.equals("world-clash")) {
                    new BukkitRunnable() {
                        int time = 4;

                        @Override
                        public void run() {
                            time--;
                            if (time == 0) {
                                if (p.hasPermission("wintervillage.redteam") || p.hasPermission("wintervillage.prisonred")) {
                                    World world2 = Bukkit.getWorld("world");
                                    Location location = new Location(world2, 55.5, 40, 106.5, -90, -3);
                                    p.teleport(location);
                                    p.setGameMode(GameMode.SURVIVAL);
                                    st(p.getPlayer(), "", "§cDu befindest dich in deinem Village!", 5, 80, 20);
                                } else if (p.hasPermission("wintervillage.blueteam") || p.hasPermission("wintervillage.prisonblue")) {
                                    World world2 = Bukkit.getWorld("world");
                                    Location location = new Location(world2, 149.5, 40, -229.5, 90, -3);
                                    p.teleport(location);
                                    p.setGameMode(GameMode.SURVIVAL);
                                    st(p.getPlayer(), "", "§9Du befindest dich in deinem Village!", 5, 80, 20);
                                } else
                                    p.sendMessage(Main.getPlugin().PREFIX + "§cDazu hast du keine Rechte!");
                                cancel();
                            } else
                                p.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                        }
                    }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                } else
                    p.sendMessage(Main.getPlugin().PREFIX + "§cDu kannst dich während eines Clashes nicht teleportieren!");
            } else
                p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/village§c!");
        }
        return false;
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
