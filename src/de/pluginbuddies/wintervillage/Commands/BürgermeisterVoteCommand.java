//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BürgermeisterVoteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("result")) {
                if (player.hasPermission("wintervillage.voteresult")) {
                    getResult();
                } else
                    player.sendMessage(Main.getPlugin().PREFIX + "§cDu besitzt keine Rechte, um dies zu tun!");
            }
            if (args[0].equalsIgnoreCase("list")) {
                getList(player);
            }
            if (args[0].equalsIgnoreCase("rot")) {

                if (!Main.getPlugin().voted.contains(player.getName())) {
                    if (Main.getPlugin().names.contains(args[1].toLowerCase())) {

                        Main.getPlugin().votes.put(args[1].toLowerCase(), Main.getPlugin().votes.get(args[1].toLowerCase()) + 1);

                        player.sendMessage(Main.getPlugin().PREFIX + "§cDu hast für §6" + args[1] + " §cgestimmt!");
                        Main.getPlugin().voted.add(player.getName());
                    } else
                        player.sendMessage(Main.getPlugin().PREFIX + "§cDieser Spieler existiert nicht!");
                } else
                    player.sendMessage(Main.getPlugin().PREFIX + "§cDu hast bereits abgestimmt!");
            }
        }

        return false;
    }

    public void getList(Player p) {
        p.sendMessage("§6----Wer soll Bürgermeister werden?----" + "\n ");
        for (String all : Main.getPlugin().names) {
            p.sendMessage("§b" + all.toUpperCase());
        }
    }

    public void getResult() {

        int max = 0;
        for (int i : Main.getPlugin().votes.values()) {
            if (i > max) {
                max = i;
            }
        }

        String winner = "";

        for (String all : Main.getPlugin().votes.keySet()) {
            if (Main.getPlugin().votes.get(all) == max) {
                winner = all;
            }
        }
        Bukkit.broadcastMessage(Main.getPlugin().PREFIX + "§7Bürgermeister ist §9" + winner.toUpperCase() + "§7!");


        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
        }


        Main.getPlugin().votes.clear();
        Main.getPlugin().voted.clear();
        Main.getPlugin().names.clear();
    }

}
