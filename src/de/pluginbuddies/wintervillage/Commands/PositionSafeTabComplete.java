//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PositionSafeTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String w = player.getWorld().getName();
            if (!w.equals("world-clash") && Main.getPlugin().getClashOpen2() != "true") {

                if (args.length == 1) {
                    if (player.hasPermission("wintervillage.redteam") || player.hasPermission("wintervillage.prisonred")) {
                        File checkdir = new File("plugins//Positionen//TeamRot/");
                        File[] files = checkdir.listFiles();
                        List<String> positionsred = new ArrayList<String>();
                        List<String> noPosred = new ArrayList<String>();
                        if (checkdir.exists()) {
                            if (files.length == 0) {
                                noPosred.add("Noch keine Positionen gespeichert -> /pos <NAME>");
                                return noPosred;
                            } else {
                                for (File file : files) {
                                    if (file.isFile()) {
                                        positionsred.add(file.getName().replace(".yml", ""));
                                        return positionsred;
                                    }
                                }
                            }

                        }
                    }
                    if (player.hasPermission("wintervillage.blueteam") || player.hasPermission("wintervillage.prisonblue")) {
                        File checkdir = new File("plugins//Positionen//TeamBlau/");
                        File[] files = checkdir.listFiles();
                        List<String> positionsblue = new ArrayList<String>();
                        List<String> noPosblue = new ArrayList<String>();
                        if (checkdir.exists()) {
                            if (files.length == 0) {
                                noPosblue.add("Noch keine Positionen gespeichert -> /pos <NAME>");
                                return noPosblue;

                            } else {
                                for (File file : files) {
                                    if (file.isFile()) {
                                        positionsblue.add(file.getName().replace(".yml", ""));
                                        return positionsblue;
                                    }
                                }
                            }

                        }
                    }
                }
                if (args.length == 2) {
                    List<String> delete = new ArrayList<String>();
                    delete.add("delete");
                    return delete;
                }
            }

        }
        return null;
    }
}
