//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GamemodesTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("wintervillage.gm")) {
                if (args.length == 1) {

                    List<String> gamemodes = new ArrayList<>();
                    gamemodes.add("0");
                    gamemodes.add("1");
                    gamemodes.add("2");
                    gamemodes.add("3");

                    return gamemodes;

                }
            }

        }
        return null;
    }

}
