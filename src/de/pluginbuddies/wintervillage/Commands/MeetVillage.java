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
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MeetVillage implements CommandExecutor {

    public static List<String> namesred = new ArrayList<>();
    public static List<String> namesblue = new ArrayList<>();

    public static String getName(String uuid) {
        String url = "https://api.mojang.com/user/profiles/" + uuid.replace("-", "") + "/names";
        try {
            @SuppressWarnings("deprecation")
            String nameJson = IOUtils.toString(new URL(url));
            JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);
            String playerSlot = nameValue.get(nameValue.size() - 1).toString();
            JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);
            return nameObject.get("name").toString();
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public static void getDorf() {
        for (int i = 1; i <= 10; i++) {
            String name = getName(Main.ymlConfigteams.getString("Rot." + i)).toLowerCase();
            if (name != "") {
                namesred.add(name);
            }
        }
        for (int i = 1; i <= 10; i++) {
            String name = getName(Main.ymlConfigteams.getString("Blau." + i)).toLowerCase();
            if (name != "") {
                namesblue.add(name);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("wintervillage.prisonred")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("all")) {
                        for (int p = 1; p <= 10; p++) {
                            String name = getName(Main.ymlConfigteams.getString("Rot." + p));
                            if (name != "") {
                                Player pl = Bukkit.getPlayerExact(name);
                                if (pl != null) {
                                    Bukkit.getPlayerExact(name).sendMessage(Main.getPlugin().PREFIX + "§bDer RotMeister §4" + player.getName() + " §bmöchte mit dir sprechen!");
                                }

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
                                    for (int i = 1; i <= 10; i++) {
                                        String name = getName(Main.ymlConfigteams.getString("Rot." + i));
                                        if (name != "") {
                                            Player pl = Bukkit.getPlayerExact(name);
                                            if (pl != null) {
                                                Bukkit.getPlayerExact(name).teleport(location);
                                            }

                                        }
                                    }
                                    player.teleport(location);
                                    player.setGameMode(GameMode.SURVIVAL);
                                    cancel();
                                } else {
                                    player.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                    for (int i = 1; i <= 10; i++) {
                                        String name = getName(Main.ymlConfigteams.getString("Rot." + i));
                                        if (name != "") {
                                            Player pl = Bukkit.getPlayerExact(name);
                                            if (pl != null) {
                                                Bukkit.getPlayerExact(name).sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                            }
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
                            getDorf();
                            if (!namesred.contains(target.getName().toLowerCase())) {
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
                                    }
                                }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                            } else
                                player.sendMessage(Main.getPlugin().PREFIX + "§cDer Spieler §r" + target.getName() + " §c ist nicht in deinem Village!");
                        }
                    }
                } else
                    player.sendMessage("§aServer " + "§8>> " + "§cBitte benutze §r/meet <SPIELER oder all>§c!");


            } else if (player.hasPermission("wintervillage.prisonblue")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("all")) {
                        for (int p = 1; p <= 10; p++) {
                            String name = Main.ymlConfigteams.getString("Blau." + p);
                            if (name != "") {
                                Bukkit.getPlayer(name).sendMessage(Main.getPlugin().PREFIX + "§bDer BlauMeister §1" + player.getName() + " §bmöchte mit dir sprechen!");
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
                                    for (int i = 1; i <= 10; i++) {
                                        String name = Main.ymlConfigteams.getString("Rot." + i);
                                        if (name != "") {
                                            Bukkit.getPlayer(name).teleport(location);
                                        }
                                    }
                                    player.teleport(location);
                                    player.setGameMode(GameMode.SURVIVAL);
                                    cancel();
                                } else {
                                    player.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                    for (int i = 1; i <= 10; i++) {
                                        String name = Main.ymlConfigteams.getString("Blau." + i);
                                        if (name != "") {
                                            Bukkit.getPlayer(name).sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
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
                            getDorf();
                            if (!namesblue.contains(target.getName().toLowerCase())) {
                                target.sendMessage(Main.getPlugin().PREFIX + "§bDer BlauMeister §1" + player.getName() + " §bmöchte mit dir sprechen!");
                                new BukkitRunnable() {
                                    int time = 4;

                                    @Override
                                    public void run() {
                                        time--;
                                        if (time == 0) {
                                            World world2 = Bukkit.getWorld("world");
                                            Location location = new Location(world2, 149.5, 40, -229.5, 90, -3);
                                            target.teleport(location);
                                            target.setGameMode(GameMode.SURVIVAL);
                                            player.teleport(location);
                                            player.setGameMode(GameMode.SURVIVAL);
                                            cancel();
                                        } else
                                            player.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                    }
                                }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                            } else
                                player.sendMessage(Main.getPlugin().PREFIX + "§cDer Spieler §r" + target.getName() + " §c ist nicht in deinem Village!");
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
