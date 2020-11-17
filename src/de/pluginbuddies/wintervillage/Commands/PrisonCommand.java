//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;

public class PrisonCommand implements CommandExecutor, Listener {

    private ArrayList<String> knastplayers = new ArrayList<>();

    @EventHandler
    public void handlecommandoutput(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String w = p.getWorld().getName();
        if (!w.equals("world-clash") && Main.getPlugin().getClashOpen() != "true") {
            if (knastplayers.contains(p.getName())) {
                p.sendMessage(Main.getPlugin().PREFIX + "§cDu bist aktuell im Gefängnis und kannst dies nicht tun!");
                e.setCancelled(true);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String w = p.getWorld().getName();
            if (!w.equals("world-clash") && Main.getPlugin().getClashOpen() != "true") {
                if (p.hasPermission("wintervillage.prisonred")) {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (p == target) {
                            p.sendMessage(Main.getPlugin().PREFIX + "§cDu kannst dich nicht selbst einbuchten!");
                            return false;
                        } else if (target.hasPermission("wintervillage.blueteam") || target.hasPermission("wintervillage.prisonblue")) {
                            p.sendMessage(Main.getPlugin().PREFIX + "§cDu kannst nicht Bürger aus dem anderen Dorf einbuchten!");
                            return false;
                        } else if (target != null) {
                            if (!knastplayers.contains(target.getName())) {
                                knastplayers.add(target.getName());
                                Bukkit.broadcastMessage("§aServer " + "§8>> " + "§aDer Mitbürger §c" + target.getName() + " §awurde eingebuchtet!");
                                World world2 = Bukkit.getWorld("world");
                                Location location = new Location(world2, 107.422, 46, -75.498, -90, -6);
                                target.teleport(location);
                                target.setGameMode(GameMode.SURVIVAL);
                                target.sendMessage("§aServer " + "§8>> " + "§cDu wurdest von dem RotMeister §4" + p.getName() + " §cins Gefängnis gesteckt!");
                            } else {
                                knastplayers.remove(target.getName());
                                Bukkit.broadcastMessage("§aServer " + "§8>> " + "§aDer Mitbürger §c" + target.getName() + " §awurde entlassen!");
                                World world3 = Bukkit.getWorld("world");
                                Location location1 = new Location(world3, 114.528, 42, -71.520, -90, -3);
                                target.teleport(location1);
                                target.setGameMode(GameMode.SURVIVAL);
                                target.sendMessage("§aServer " + "§8>> " + "§aDu wurdest von dem RotMeister §4" + p.getName() + " §afreigelassen!");
                            }
                        } else
                            p.sendMessage(Main.getPlugin().PREFIX + "§cDer Spieler §c" + args[0] + " §c ist nicht in deinem Village!");
                    } else
                        p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/prison <SPIELER>§c!");
                } else if (p.hasPermission("wintervillage.prisonblue")) {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (p == target) {
                            p.sendMessage(Main.getPlugin().PREFIX + "§cDu kannst dich nicht selbst einbuchten!");
                            return false;
                        } else if (target.hasPermission("wintervillage.redteam") || target.hasPermission("wintervillage.prisonred")) {
                            p.sendMessage(Main.getPlugin().PREFIX + "§cDu kannst nicht Bürger aus dem anderen Team einbuchten!");
                            return false;
                        } else if (target != null) {
                            if (!knastplayers.contains(target.getName())) {
                                knastplayers.add(target.getName());
                                Bukkit.broadcastMessage("§aServer " + "§8>> " + "§aDer Mitbürger §9" + target.getName() + " §awurde eingebuchtet!");
                                World world2 = Bukkit.getWorld("world");
                                Location location = new Location(world2, 107.422, 46, -75.498, -90, -6);
                                target.teleport(location);
                                target.setGameMode(GameMode.SURVIVAL);
                                target.sendMessage("§aServer " + "§8>> " + "§cDu wurdest von dem BlauMeister §1" + p.getName() + " §cins Gefängnis gesteckt!");
                            } else {
                                knastplayers.remove(target.getName());
                                Bukkit.broadcastMessage("§aServer " + "§8>> " + "§aDer Mitbürger §9" + target.getName() + " §awurde entlassen!");
                                World world3 = Bukkit.getWorld("world");
                                Location location1 = new Location(world3, 114.528, 42, -71.520, -90, -3);
                                target.teleport(location1);
                                target.setGameMode(GameMode.SURVIVAL);
                                target.sendMessage("§aServer " + "§8>> " + "§aDu wurdest von dem BlauMeister §1" + p.getName() + " §afreigelassen!");
                            }
                        } else
                            p.sendMessage(Main.getPlugin().PREFIX + "§cDer Spieler §9" + args[0] + " §c ist nicht in deinem Village!");
                    } else
                        p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/prison <SPIELER>§c!");
                } else
                    p.sendMessage(Main.getPlugin().PREFIX + "§cNur der Bürgermeister darf dies!");
            } else
                p.sendMessage(Main.getPlugin().PREFIX + "§cDu kannst während eines Clashes niemanden einbuchten!");
        }
        return false;
    }


}
