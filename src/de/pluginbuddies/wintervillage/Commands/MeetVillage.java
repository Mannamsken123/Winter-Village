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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class MeetVillage implements CommandExecutor {

    public static List<String> namesred = new ArrayList<>();
    public static List<String> namesblue = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String w = p.getWorld().getName();
            if (!w.equals("world-clash") && Main.getPlugin().getClashOpen() != "true") {
                if (p.hasPermission("wintervillage.prisonred")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("all")) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                if (all.hasPermission("wintervillage.redteam") && !all.hasPermission("wintervillage.prisonred")) {
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Der RotMeister §4" + p.getName() + " §3möchte mit euch sprechen!");
                                }
                            }
                            new BukkitRunnable() {
                                int time = 4;
                                World world2 = Bukkit.getWorld("world");
                                Location location = new Location(world2, 55.5, 40, 106.5, -90, -3);

                                @Override
                                public void run() {
                                    time--;
                                    if (time == 0) {
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.redteam") && !all.hasPermission("wintervillage.prisonred")) {
                                                all.teleport(location);
                                            }
                                        }
                                        p.teleport(location);
                                        p.setGameMode(GameMode.SURVIVAL);
                                        cancel();
                                    } else {
                                        p.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");

                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.redteam") && !all.hasPermission("wintervillage.prisonred")) {
                                                all.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                            }
                                        }

                                    }
                                }
                            }.runTaskTimer(Main.getPlugin(), 0L, 20L);

                        } else {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (p == target) {
                                p.sendMessage("§aServer " + "§8>> " + "§cDu kannst dich nicht mit dir selber treffen!");
                                return false;
                            } else if (target != null) {
                                if (target.hasPermission("wintervillage.redteam")) {
                                    target.sendMessage(Main.getPlugin().PREFIX + "§3Der RotMeister §4" + p.getName() + " §bmöchte mit dir sprechen!");
                                    new BukkitRunnable() {
                                        int time = 4;

                                        @Override
                                        public void run() {
                                            time--;
                                            if (time == 0) {
                                                World world2 = Bukkit.getWorld("world");
                                                Location location = new Location(world2, 55.5, 40, 106.5, -90, -3);
                                                target.teleport(location);
                                                target.setGameMode(GameMode.SURVIVAL);
                                                p.teleport(location);
                                                p.setGameMode(GameMode.SURVIVAL);
                                                cancel();
                                            } else
                                                p.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                            target.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                        }
                                    }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                                } else
                                    p.sendMessage(Main.getPlugin().PREFIX + "§cDer Spieler §r" + target.getName() + " §cist nicht in deinem Village!");
                            }
                        }
                    } else
                        p.sendMessage("§aServer " + "§8>> " + "§cBitte benutze §r/meet <SPIELER oder all>§c!");


                } else if (p.hasPermission("wintervillage.prisonblue")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("all")) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                if (all.hasPermission("wintervillage.blueteam") && !all.hasPermission("wintervillage.prisonblue")) {
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Der BlauMeister §1" + p.getName() + " §bmöchte mit euch sprechen!");
                                }
                            }
                            new BukkitRunnable() {
                                int time = 4;
                                World world2 = Bukkit.getWorld("world");
                                Location location = new Location(world2, 149.5, 40, -229.5, 90, -3);

                                @Override
                                public void run() {
                                    time--;
                                    if (time == 0) {
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.blueteam") && !all.hasPermission("wintervillage.prisonblue")) {
                                                all.teleport(location);
                                            }
                                        }
                                        p.teleport(location);
                                        p.setGameMode(GameMode.SURVIVAL);
                                        cancel();
                                    } else {
                                        p.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.blueteam") && !all.hasPermission("wintervillage.prisonblue")) {
                                                all.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                            }
                                        }
                                    }

                                }
                            }.runTaskTimer(Main.getPlugin(), 0L, 20L);

                        } else {
                            Player target = Bukkit.getPlayer(args[0]);
                            if (p == target) {
                                p.sendMessage("§aServer " + "§8>> " + "§cDu kannst dich nicht mit dir selber treffen!");
                                return false;
                            } else if (target != null) {
                                if (target.hasPermission("wintervillage.blueteam")) {
                                    target.sendMessage(Main.getPlugin().PREFIX + "§3Der BlauMeister §1" + p.getName() + " §bmöchte mit dir sprechen!");
                                    new BukkitRunnable() {
                                        int time = 4;

                                        @Override
                                        public void run() {
                                            time--;
                                            if (time == 0) {
                                                World world2 = Bukkit.getWorld("world");
                                                Location location = new Location(world2, 149.5, 40, -229.5, 90, -3);
                                                p.teleport(location);
                                                p.setGameMode(GameMode.SURVIVAL);
                                                target.teleport(location);
                                                target.setGameMode(GameMode.SURVIVAL);
                                                cancel();
                                            } else
                                                p.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                            target.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                        }
                                    }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                                } else
                                    p.sendMessage(Main.getPlugin().PREFIX + "§cDer Spieler §r" + target.getName() + " §cist nicht in deinem Village!");
                            }
                        }
                    } else
                        p.sendMessage("§aServer " + "§8>> " + "§cBitte benutze §r/meet <SPIELER oder all>§c!");
                } else
                    p.sendMessage("§aServer " + "§8>> " + "§cNur der Bürgermeister darf dies!");
            } else
                p.sendMessage(Main.getPlugin().PREFIX + "§cDies darfst du während des Clashes nicht tun!");
        }
        return false;
    }


}
