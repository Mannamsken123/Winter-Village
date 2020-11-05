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

public class VillageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                if (player.hasPermission("wintervillage.redteam") || player.hasPermission("wintervillage.prisonred")) {
                    World world2 = Bukkit.getWorld("world");
                    Location location = new Location(world2, 55.5, 40, 106.5, -90, -3);
                    player.teleport(location);
                    player.setGameMode(GameMode.SURVIVAL);
                    st(player.getPlayer(), "", "§cDu befindest dich in deinem Village!", 5, 80, 20);
                } else if (player.hasPermission("wintervillage.blueteam") || player.hasPermission("wintervillage.prisonblue")) {
                    World world2 = Bukkit.getWorld("world");
                    Location location = new Location(world2, 149.5, 40, -229.5, 90, -3);
                    player.teleport(location);
                    player.setGameMode(GameMode.SURVIVAL);
                    st(player.getPlayer(), "", "§9Du befindest dich in deinem Village!", 5, 80, 20);
                } else
                    player.sendMessage(Main.getPlugin().PREFIX + "§cDazu hast du keine Rechte!");
            } else
                player.sendMessage("§aServer " + "§8>> " + "§cBitte benutze §6/village§c!");
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
