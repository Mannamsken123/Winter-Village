//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PutschCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("wintervillage.redteam") && !p.hasPermission("wintervillage.prisonred")) {
                if (args.length == 0) {
                    if (Main.getPlugin().getVoteOpen() != "true") {
                        Main.getPlugin().setPutschRot(true);
                    } else
                        p.sendMessage(Main.getPlugin().PREFIX + "§cDie geht nicht während der Voting-Phase!");
                } else
                    p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/putsch§c!");
            }
            if (p.hasPermission("wintervillage.blueteam") && !p.hasPermission("wintervillage.prisonblue")) {
                if (args.length == 0) {
                    if (Main.getPlugin().getVoteOpen() != "true") {
                        Main.getPlugin().setPutschBlau(true); //PENIS PROBLEM  = WENN einer /putsch macht kommt ddas wenn wir die Voteopen sache benutzen erst nach 10 min
                        //PENIS Theoretisch müsste voteopen hier rein damit nur die leute aus dem team eine nachricht bekommen

                        //PENIS VARIABLE FÜR VOTEOPEN HIER MACHEN! VOTECLOSE brauchen wir nicht da das in vote ausgewertet ist sofern 5 leute abegestimmt haben


                        p.sendMessage(Main.getPlugin().PREFIX + "§3Die anderen Mitbürger werden benachrichtigt! Stimme mit §r/vote <NAME> §3für einen neuen Bürgermeister ab!");
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("wintervillage.blueteam")) {
                                if (all != p) {
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Achtung! " + p.getName() + " §3hat einen Putsch gestartet. Unterstütze ihn, indem du §r/vote <Name> §3benutzt, um einen neuen BlauMeister zu wählen!");
                                }
                            }
                        }

                    } else
                        p.sendMessage(Main.getPlugin().PREFIX + "§cDie geht nicht während der Voting-Phase!");
                } else
                    p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/putsch§c!");
            }
        }
        return false;
    }
}