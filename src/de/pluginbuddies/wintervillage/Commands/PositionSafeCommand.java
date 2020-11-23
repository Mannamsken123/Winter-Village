//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class PositionSafeCommand implements CommandExecutor {

    String pos;
    double pX;
    double pY;
    double pZ;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String w = player.getWorld().getName();
            if (!w.equals("world-clash") && Main.getPlugin().getClashOpen2() != "true") {
                if (args.length == 1 && args.length != 2) {
                    if (player.hasPermission("wintervillage.redteam") || player.hasPermission("wintervillage.prisonred")) {
                        File file = new File("plugins//Positionen//TeamRot//" + args[0] + ".yml");
                        YamlConfiguration posi = YamlConfiguration.loadConfiguration(file);
                        DecimalFormat format = new DecimalFormat("0.00");
                        if (file.exists()) {
                            try {
                                posi.load("plugins//Positionen//TeamRot//" + args[0] + ".yml");
                            } catch (IOException | InvalidConfigurationException e) {
                                e.printStackTrace();
                            }
                            pos = posi.getString("Name");
                            pX = posi.getDouble("X");
                            pY = posi.getDouble("Y");
                            pZ = posi.getDouble("Z");
                            player.sendMessage(Main.getPlugin().PREFIX + "§6" + pos + " §3ist bei §c<§6" + format.format(pX) + "§c, §6" + format.format(pY) + "§c, §6" + format.format(pZ) + "§c>§3!");
                        } else {
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            pos = args[0];
                            pX = player.getLocation().getX();
                            pY = player.getLocation().getY();
                            pZ = player.getLocation().getZ();

                            player.sendMessage(Main.getPlugin().PREFIX + "§3Die Position §6" + pos + " §3bei §c<§6" + format.format(pX) + "§c, §6" + format.format(pY) + "§c, §6" + format.format(pZ) + "§c> §3wurde gespeichert!");

                            posi.set("Name", pos);
                            posi.set("X", pX);
                            posi.set("Y", pY);
                            posi.set("Z", pZ);
                            try {
                                posi.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (player.hasPermission("wintervillage.blueteam") || player.hasPermission("wintervillage.prisonblue")) {
                        File file = new File("plugins//Positionen//TeamBlau//" + args[0] + ".yml");
                        YamlConfiguration posi = YamlConfiguration.loadConfiguration(file);
                        DecimalFormat format = new DecimalFormat("0.00");
                        if (file.exists()) {
                            try {
                                posi.load("plugins//Positionen//TeamBlau//" + args[0] + ".yml");
                            } catch (IOException | InvalidConfigurationException e) {
                                e.printStackTrace();
                            }
                            pos = posi.getString("Name");
                            pX = posi.getDouble("X");
                            pY = posi.getDouble("Y");
                            pZ = posi.getDouble("Z");
                            player.sendMessage(Main.getPlugin().PREFIX + "§6" + pos + " §3ist bei §c<§6" + format.format(pX) + "§c, §6" + format.format(pY) + "§c, §6" + format.format(pZ) + "§c>§3!");
                        } else {
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            pos = args[0];
                            pX = player.getLocation().getX();
                            pY = player.getLocation().getY();
                            pZ = player.getLocation().getZ();

                            player.sendMessage(Main.getPlugin().PREFIX + "§3Die Position §6" + pos + " §3bei §c<§6" + format.format(pX) + "§c, §6" + format.format(pY) + "§c, §6" + format.format(pZ) + "§c> §3wurde gespeichert!");

                            posi.set("Name", pos);
                            posi.set("X", pX);
                            posi.set("Y", pY);
                            posi.set("Z", pZ);
                            try {
                                posi.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (args.length != 2) {
                    player.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/pos <NAME-POSITION> §coder §r/pos <Name> delete§c!");
                }
                if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("delete")) {
                        if (player.hasPermission("wintervillage.redteam") || player.hasPermission("wintervillage.prisonred")) {
                            File file = new File("plugins//Positionen//TeamRot//" + args[0] + ".yml");
                            YamlConfiguration posi = YamlConfiguration.loadConfiguration(file);
                            if (file.exists()) {
                                try {
                                    posi.load("plugins//Positionen//TeamRot//" + args[0] + ".yml");
                                } catch (IOException | InvalidConfigurationException e) {
                                    e.printStackTrace();
                                }
                                pos = posi.getString("Name");
                                player.sendMessage(Main.getPlugin().PREFIX + "§6" + pos + " §3wurde gelöscht!");
                                file.delete();
                            }
                        }
                        if (player.hasPermission("wintervillage.redteam") || player.hasPermission("wintervillage.prisonred")) {
                            File file = new File("plugins//Positionen//TeamRot//" + args[0] + ".yml");
                            YamlConfiguration posi = YamlConfiguration.loadConfiguration(file);
                            if (file.exists()) {
                                try {
                                    posi.load("plugins//Positionen//TeamRot//" + args[0] + ".yml");
                                } catch (IOException | InvalidConfigurationException e) {
                                    e.printStackTrace();
                                }
                                pos = posi.getString("Name");
                                player.sendMessage(Main.getPlugin().PREFIX + "§6" + pos + " §3wurde gelöscht!");
                                file.delete();
                            }
                        }
                    } else {
                        player.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/pos <Name> delete§c!");
                    }
                }
            } else
                player.sendMessage(Main.getPlugin().PREFIX + "§cDu kannst dich während eines Clashes nicht tun!");
        }
        return false;
    }
}
