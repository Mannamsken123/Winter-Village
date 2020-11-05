//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BürgermeisterVoteCommand implements CommandExecutor {

    private String rEr = ""; //FINAL Winner Rot
    private String rEb = ""; //FINAL Winner Blau

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Main.getPlugin().getVoteOpen() == "true" || Main.getPlugin().getPutschRot() == true) {
                if (p.hasPermission("wintervillage.redteam") || p.hasPermission("wintervillage.prisonred")) {
                    if (args.length == 1) {
                        if (!Main.getPlugin().voted.contains(p.getName())) {
                            if (Main.getPlugin().namesred.contains(args[1].toLowerCase())) {
                                Main.getPlugin().votesred.put(args[1].toLowerCase(), Main.getPlugin().votesred.get(args[1].toLowerCase()) + 1);
                                p.sendMessage(Main.getPlugin().PREFIX + "§bDu hast für §c" + args[1] + " §bgestimmt!");
                                Main.getPlugin().voted.add(p.getName());
                            } else
                                p.sendMessage(Main.getPlugin().PREFIX + "§cDieser Spieler existiert nicht!");
                        } else
                            p.sendMessage(Main.getPlugin().PREFIX + "§cDu hast bereits abgestimmt!");
                    } else
                        p.sendMessage("§aServer " + "§8>> " + "§cBitte benutze §r/vote <Name>§c!");
                }
            } else
                p.sendMessage(Main.getPlugin().PREFIX + "§cEs ist gerade keine Voting-Phase. \n§6Wenn du unzufrieden mit deinem Bürgermeister bist schreibe §r/putsch§6, um zu versuchen ihn zu stürzen!");

            if (Main.getPlugin().getVoteOpen() == "true" || Main.getPlugin().getPutschBlau() == true) {
                if (p.hasPermission("wintervillage.blueteam") || p.hasPermission("wintervillage.prisonblue")) {
                    if (args.length == 1) {
                        if (!Main.getPlugin().voted.contains(p.getName())) {
                            if (Main.getPlugin().namesblue.contains(args[1].toLowerCase())) {
                                Main.getPlugin().votesblue.put(args[1].toLowerCase(), Main.getPlugin().votesblue.get(args[1].toLowerCase()) + 1);
                                p.sendMessage(Main.getPlugin().PREFIX + "§bDu hast für §9" + args[1] + " §bgestimmt!");
                                Main.getPlugin().voted.add(p.getName());
                            } else
                                p.sendMessage(Main.getPlugin().PREFIX + "§cDieser Spieler existiert nicht!");
                        } else
                            p.sendMessage(Main.getPlugin().PREFIX + "§cDu hast bereits abgestimmt!");
                    } else
                        p.sendMessage("§aServer " + "§8>> " + "§cBitte benutze §r/vote <Name>§c!");
                }
            } else
                p.sendMessage(Main.getPlugin().PREFIX + "§cEs ist gerade keine Voting-Phase. \n§6Wenn du unzufrieden mit deinem Bürgermeister bist schreibe §r/putsch§6, um zu versuchen ihn zu stürzen!");
        }

        return false;
    }

    public String getrEr() {
        return rEr;
    }

    public String getrEb() {
        return rEb;
    }

    public void getResult() {
        int maxred = 0;
        for (int i : Main.getPlugin().votesred.values()) {
            if (i > maxred) {
                maxred = i;
            }
        }
        int maxblue = 0;
        for (int i : Main.getPlugin().votesblue.values()) {
            if (i > maxblue) {
                maxblue = i;
            }
        }
        List<String> winnerred = new ArrayList<>();
        int wr = 0;
        for (String all : Main.getPlugin().votesred.keySet()) {
            if (Main.getPlugin().votesred.get(all) == maxred) {
                winnerred.add(all);
                wr++;
            }
        }
        List<String> winnerblue = new ArrayList<>();
        int wb = 0;
        for (String all : Main.getPlugin().votesblue.keySet()) {
            if (Main.getPlugin().votesblue.get(all) == maxblue) {
                winnerblue.add(all);
                wb++;
            }
        }
        Random rand = new Random();
        for (int i = 0; i < wr; i++) {
            int ri = rand.nextInt(winnerred.size());
            rEr = winnerred.get(ri);
        }
        for (int i = 0; i < wb; i++) {
            int ri = rand.nextInt(winnerblue.size());
            rEb = winnerblue.get(ri);
        }

        //PENIS in config bürgermeister von false auf true


        Main.getPlugin().votesred.clear();
        Main.getPlugin().votesblue.clear();
        Main.getPlugin().namesred.clear();
        Main.getPlugin().namesblue.clear();
        Main.getPlugin().voted.clear();
    }
}
