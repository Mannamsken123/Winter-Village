//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {

                World world2 = Bukkit.getWorld("world");
                Location location = new Location(world2, 114.528, 42, -71.520, -90, -3);
                player.teleport(location);
                player.setGameMode(GameMode.SURVIVAL);

                return true;

            } else
                player.sendMessage("§aServer " + "§8>> " + "§cBitte benutze den §6/spawn§c!");
        }


        return false;
    }

}

