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
            Player player = (Player) sender;

            if (player.hasPermission("wintervillage.prisonred")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("all")) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("wintervillage.redteam")) {
                                all.sendMessage(Main.getPlugin().PREFIX + "§bDer RotMeister §4" + player.getName() + " §bmöchte mit euch sprechen!");
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
                                        if (all.hasPermission("wintervillage.redteam")) {
                                            all.teleport(location);
                                        }
                                    }
                                    player.teleport(location);
                                    player.setGameMode(GameMode.SURVIVAL);
                                    cancel();
                                } else {
                                    player.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");

                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        if (all.hasPermission("wintervillage.redteam")) {
                                            all.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                        }
                                    }

                                }
                            }
                        }.runTaskTimer(Main.getPlugin(), 0L, 20L);

                    } else {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (player == target) {
                            player.sendMessage("§aServer " + "§8>> " + "§cDu kannst dich nicht mit dir selber treffen!");
                            return false;
                        } else if (target != null) {
                            if (target.hasPermission("wintervillage.redteam")) {
                                target.sendMessage(Main.getPlugin().PREFIX + "§bDer RotMeister §4" + player.getName() + " §bmöchte mit dir sprechen!");
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
                                            player.teleport(location);
                                            player.setGameMode(GameMode.SURVIVAL);
                                            cancel();
                                        } else
                                            player.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                        target.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                    }
                                }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                            } else
                                player.sendMessage(Main.getPlugin().PREFIX + "§cDer Spieler §r" + target.getName() + " §cist nicht in deinem Village!");
                        }
                    }
                } else
                    player.sendMessage("§aServer " + "§8>> " + "§cBitte benutze §r/meet <SPIELER oder all>§c!");


            } else if (player.hasPermission("wintervillage.prisonblue")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("all")) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("wintervillage.blueteam")) {
                                all.sendMessage(Main.getPlugin().PREFIX + "§bDer BlauMeister §1" + player.getName() + " §bmöchte mit euch sprechen!");
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
                                        if (all.hasPermission("wintervillage.blueteam")) {
                                            all.teleport(location);
                                        }
                                    }
                                    player.teleport(location);
                                    player.setGameMode(GameMode.SURVIVAL);
                                    cancel();
                                } else {
                                    player.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        if (all.hasPermission("wintervillage.blueteam")) {
                                            all.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                        }
                                    }
                                }

                            }
                        }.runTaskTimer(Main.getPlugin(), 0L, 20L);

                    } else {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (player == target) {
                            player.sendMessage("§aServer " + "§8>> " + "§cDu kannst dich nicht mit dir selber treffen!");
                            return false;
                        } else if (target != null) {
                            if (target.hasPermission("wintervillage.blueteam")) {
                                target.sendMessage(Main.getPlugin().PREFIX + "§bDer BlauMeister §1" + player.getName() + " §bmöchte mit dir sprechen!");
                                new BukkitRunnable() {
                                    int time = 4;

                                    @Override
                                    public void run() {
                                        time--;
                                        if (time == 0) {
                                            World world2 = Bukkit.getWorld("world");
                                            Location location = new Location(world2, 149.5, 40, -229.5, 90, -3);
                                            player.teleport(location);
                                            player.setGameMode(GameMode.SURVIVAL);
                                            target.teleport(location);
                                            target.setGameMode(GameMode.SURVIVAL);
                                            cancel();
                                        } else
                                            player.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                        target.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                    }
                                }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                            } else
                                player.sendMessage(Main.getPlugin().PREFIX + "§cDer Spieler §r" + target.getName() + " §cist nicht in deinem Village!");
                        }
                    }
                } else
                    player.sendMessage("§aServer " + "§8>> " + "§cBitte benutze §r/meet <SPIELER oder all>§c!");
            } else
                player.sendMessage("§aServer " + "§8>> " + "§cNur der Bürgermeister darf dies!");
        }
        return false;
    }


}
