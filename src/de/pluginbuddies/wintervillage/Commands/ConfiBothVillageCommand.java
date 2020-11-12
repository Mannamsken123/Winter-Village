//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ConfiBothVillageCommand implements CommandExecutor {

    World world = Bukkit.getWorld("world");
    Location location = new Location(world, 114.528, 41, -71.520, -90, -3);
    private boolean confistart = false;

    public boolean getConfistart() {
        return confistart;
    }

    public void setConfistart(boolean confistart) {
        this.confistart = confistart;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String w = p.getWorld().getName();
            if (!w.equals("world-clash")) {
                if (p.hasPermission("wintervillage.prisonred")) {
                    if (args.length != 1) {
                        setConfistart(true);
                        p.sendMessage(Main.getPlugin().PREFIX + "§3Die Konferenzanfrage wurde abgeschickt. Warte auf eine Antwort!");
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("wintervillage.prisonblue")) {
                                all.sendMessage(Main.getPlugin().PREFIX + "§3Der andere Bürgermeister möchte eine Serverkonferenz starten!");
                                TextComponent tc = new TextComponent();
                                tc.setText("\n§7[§aKlicke hier um anzunehmen§7]");
                                tc.setUnderlined(true);
                                tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confi Loremetloremdoremichpoeplindernase"));
                                tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Aktzeptiert die angefragte Serverkonferenz!").create()));
                                all.spigot().sendMessage(tc);
                            }
                        }
                    } else if (args.length == 1) {
                        if (getConfistart() == true) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                if (!all.hasPermission("wintervillage.prisonred") && !all.hasPermission("wintervillage.prisonblue")) {
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Die Bürgermeister möchten mit euch sprechen!");
                                }
                                if (all.hasPermission("wintervillage.prisonblue")) {
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Der andere Bürgermeister hat aktzeptiert!");
                                }
                            }
                            new BukkitRunnable() {
                                int time = 4;

                                @Override
                                public void run() {
                                    time--;
                                    if (time == 0) {
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.redteam") || all.hasPermission("wintervillage.blueteam")) {
                                                all.teleport(location);
                                            }
                                        }
                                        p.teleport(location);
                                        p.setGameMode(GameMode.SURVIVAL);
                                        setConfistart(false);
                                        cancel();
                                    } else {
                                        p.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (!all.hasPermission("wintervillage.prisonred")) {
                                                all.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                            }
                                        }

                                    }
                                }
                            }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                        }
                    } else
                        p.sendMessage("§aServer " + "§8>> " + "§cBitte benutze §r/confi§c!");
                } else if (p.hasPermission("wintervillage.prisonblue")) {
                    if (args.length != 1) {
                        setConfistart(true);
                        p.sendMessage(Main.getPlugin().PREFIX + "§3Die Konferenzanfrage wurde abgeschickt. Warte auf eine Antwort!");
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("wintervillage.prisonred")) {
                                all.sendMessage(Main.getPlugin().PREFIX + "§3Der andere Bürgermeister möchte eine Serverkonferenz starten!");
                                TextComponent tc = new TextComponent();
                                tc.setText("\n§7[§aKlicke hier um anzunehmen§7]");
                                tc.setUnderlined(true);
                                tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confi Loremetloremdoremichpoeplindernase"));
                                tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Aktzeptiert die angefragte Serverkonferenz!").create()));
                                all.spigot().sendMessage(tc);
                            }
                        }
                    } else if (args.length == 1) {
                        if (getConfistart() == true) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                if (!all.hasPermission("wintervillage.prisonred") && !all.hasPermission("wintervillage.prisonblue")) {
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Die Bürgermeister möchten mit euch sprechen!");
                                }
                                if (all.hasPermission("wintervillage.prisonred")) {
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Der andere Bürgermeister hat aktzeptiert!");
                                }
                            }
                            new BukkitRunnable() {
                                int time = 4;

                                @Override
                                public void run() {
                                    time--;
                                    if (time == 0) {
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.redteam") || all.hasPermission("wintervillage.blueteam")) {
                                                all.teleport(location);
                                            }
                                        }
                                        p.teleport(location);
                                        p.setGameMode(GameMode.SURVIVAL);
                                        setConfistart(false);
                                        cancel();
                                    } else {
                                        p.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (!all.hasPermission("wintervillage.prisonblue")) {
                                                all.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                            }
                                        }

                                    }
                                }
                            }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                        }
                    } else
                        p.sendMessage("§aServer " + "§8>> " + "§cBitte benutze §r/confi§c!");
                } else
                    p.sendMessage("§aServer " + "§8>> " + "§cNur der Bürgermeister darf dies!");
            } else
                p.sendMessage(Main.getPlugin().PREFIX + "§cDies darfst du während des Clashes nicht tun!");
        }
        return false;
    }
}

