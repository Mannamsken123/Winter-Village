//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("wintervillage.gm")) {
                if (args.length == 1) {

                    if (args[0].equalsIgnoreCase("0")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(Main.getPlugin().PREFIX + "§cDu bist nun im Überlebens-Modus!");
                    }
                    if (args[0].equalsIgnoreCase("1")) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(Main.getPlugin().PREFIX + "§cDu bist nun im Kreativ-Modus!");
                    }
                    if (args[0].equalsIgnoreCase("2")) {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(Main.getPlugin().PREFIX + "§cDu bist nun im Abenteuer-Modus!");
                    }
                    if (args[0].equalsIgnoreCase("3")) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(Main.getPlugin().PREFIX + "§cDu bist nun im Zuschauer-Modus!");
                    }

                } else
                    player.sendMessage("§aServer " + "§8>> " + "§cBitte benutze §6/gm <0-3>§c!");
            } else
                player.sendMessage("§aServer " + "§8>> " + "§cDazu hast du keine Rechte!");
        }
        return false;
    }

}
