//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MeetVillageTabComplete implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("wintervillage.prisonred")) {
                if (args.length == 1) {
                    List<String> rot = new ArrayList<>();
                    rot.add("all");
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission("wintervillage.redteam")) {
                            rot.add(all.getName());
                        }
                    }

                    return rot;

                }
            }
            if (p.hasPermission("wintervillage.prisonblue")) {
                if (args.length == 1) {
                    List<String> blau = new ArrayList<>();
                    blau.add("all");
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission("wintervillage.blueteam")) {
                            blau.add(all.getName());
                        }
                    }

                    return blau;

                }
            }
        }


        return null;
    }


}
