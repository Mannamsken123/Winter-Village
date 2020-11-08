//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class testcmd implements CommandExecutor {
    public static String getName(String uuid) {
        UUID id = UUID.fromString(uuid);
        String name = Bukkit.getPlayer(id).getDisplayName();
        return name;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            Bukkit.broadcastMessage(getName("7543d7d1-1ccd-4b4f-89ef-e25c1f1f9341"));


        }
        return false;


    }

}
