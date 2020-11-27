//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import net.md_5.bungee.api.ChatMessageType;
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

import java.util.ArrayList;
import java.util.List;

public class MeetVillage implements CommandExecutor {

    public static List<String> namesred = new ArrayList<>();
    public static List<String> namesblue = new ArrayList<>();
    private boolean meetstartrot = false;
    private boolean meetstartblau = false;

    public boolean getmeetstartrot() {
        return meetstartrot;
    }

    public void setmeetstartrot(boolean meetstartrot) {
        this.meetstartrot = meetstartrot;
    }

    public boolean getmeetstartblau() {
        return meetstartblau;
    }

    public void setmeetstartblau(boolean meetstartblau) {
        this.meetstartblau = meetstartblau;
    }

    //PENIS NOCH MACHEN DASS ER NUR 15SEK HAT

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String w = p.getWorld().getName();
            if (!w.equals("world-clash") && Main.getPlugin().getClashOpen2() != "true") {

                if (p.hasPermission("wintervillage.blueteam") && !p.hasPermission("wintervillage.prisonblue")) { //NICHT MEHR BMEISTER SONDERN WENN ER ACCEPTED P=DORFBEWOHNER
                    if (getmeetstartblau() == true) {
                        if (args.length == 2) {
                            World world2 = Bukkit.getWorld("world");
                            Location location = new Location(world2, 149.5, 40, -229.5, 90, -3);
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                if (all.hasPermission("wintervillage.prisonblue")) {
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Der Mitbürger hat aktzeptiert!");
                                }
                            }
                            new BukkitRunnable() {
                                int time = 4;

                                @Override
                                public void run() {
                                    time--;
                                    if (time == 0) {
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.prisonblue")) {
                                                all.teleport(location);
                                                all.setGameMode(GameMode.SURVIVAL);
                                            }
                                        }
                                        p.teleport(location);
                                        p.setGameMode(GameMode.SURVIVAL);
                                        setmeetstartblau(false);
                                        cancel();
                                    } else {
                                        p.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.prisonblue")) {
                                                all.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                            }
                                        }

                                    }
                                }
                            }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                        }
                    } else {
                        p.sendMessage(Main.getPlugin().PREFIX + "§cDie Anfrage ist bereits abgelaufen!");
                        return false;
                    }
                }//PLAYER ABFRAGE BLue

                if (p.hasPermission("wintervillage.redteam") && !p.hasPermission("wintervillage.prisonred")) { //NICHT MEHR BMEISTER SONDERN WENN ER ACCEPTED P=DORFBEWOHNER
                    if (getmeetstartrot() == true) {
                        if (args.length == 2) {
                            World world2 = Bukkit.getWorld("world");
                            Location location = new Location(world2, 55.5, 40, 106.5, -90, -3);
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                if (all.hasPermission("wintervillage.prisonred")) {
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Der Mitbürger hat aktzeptiert!");
                                }
                            }

                            new BukkitRunnable() {
                                int time = 4;

                                @Override
                                public void run() {
                                    time--;
                                    if (time == 0) {
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.prisonred")) {
                                                all.teleport(location);
                                                all.setGameMode(GameMode.SURVIVAL);
                                            }
                                        }
                                        p.teleport(location);
                                        p.setGameMode(GameMode.SURVIVAL);
                                        setmeetstartrot(false);
                                        cancel();
                                    } else {
                                        p.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.prisonred")) {
                                                all.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                            }
                                        }

                                    }
                                }
                            }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                        }
                    } else {
                        p.sendMessage(Main.getPlugin().PREFIX + "§cDie Anfrage ist bereits abgelaufen!");
                        return false;
                    }
                }//PLAYER ABFRAGE red

                if (getmeetstartblau() == true || getmeetstartrot() == true) {
                    return false;
                } else {
                    if (p.hasPermission("wintervillage.prisonred")) {
                        if (args.length == 1 && args.length != 2) {
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
                                    p.sendMessage(Main.getPlugin().PREFIX + "§cDu kannst dich nicht mit dir selber treffen!");
                                    return false;
                                } else if (target != null) {
                                    setmeetstartrot(true);
                                    if (target.hasPermission("wintervillage.redteam") && getmeetstartrot() == true) {
                                        p.sendMessage(Main.getPlugin().PREFIX + "§3Die Anfrage wurde abgeschickt. Warte auf eine Antwort!");
                                        target.sendMessage(Main.getPlugin().PREFIX + "§3Der RotMeister §4" + p.getName() + " §3möchte mit dir sprechen!");
                                        target.sendMessage(Main.getPlugin().PREFIX + "§cDu hast 15 Sekunden, um anzunehmen!");
                                        TextComponent tc = new TextComponent();
                                        tc.setText("\n§7[§aKlicke hier um anzunehmen§7]");
                                        tc.setUnderlined(true);
                                        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/meet Loremetloremdasdasdasdasdoremichpoeplindernase asdasdasdafrgfrghbg"));
                                        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Aktzeptiert die Anfrage!").create()));
                                        target.spigot().sendMessage(tc);
                                        new BukkitRunnable() {
                                            int time = 16;
                                            String t1;
                                            String t2;

                                            @Override
                                            public void run() {
                                                time--;
                                                if (getmeetstartrot() == false) {
                                                    cancel();
                                                }
                                                if (time == 0) {
                                                    target.sendMessage(Main.getPlugin().PREFIX + "§cDie Anfrage ist abgelaufen!");
                                                    t2 = String.format("§6§lAnfrage abgelaufen!");
                                                    String message = t2;
                                                    target.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                                                    p.sendMessage(Main.getPlugin().PREFIX + "§cDer Mitbürger §r" + target.getName() + " §chat nicht angenommen! Versuche es erneut, wenn es dringlich ist.");
                                                    setmeetstartrot(false);
                                                    cancel();
                                                } else {
                                                    t1 = String.format("§6§lAnfrage noch: " + time + "sec");
                                                    String message = t1;
                                                    target.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));


                                                }
                                            }
                                        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                                    } else {
                                        p.sendMessage(Main.getPlugin().PREFIX + "§cDer Spieler §r" + target.getName() + " §cist nicht in deinem Village!");
                                        setmeetstartrot(false);
                                    }
                                }
                            }
                        } else
                            p.sendMessage("§aServer " + "§8>> " + "§cBitte benutze §r/meet <SPIELER oder all>§c!");
                    } else if (p.hasPermission("wintervillage.prisonblue")) {
                        if (args.length == 1 && args.length != 2) {
                            if (args[0].equalsIgnoreCase("all")) {
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    if (all.hasPermission("wintervillage.blueteam") && !all.hasPermission("wintervillage.prisonblue")) {
                                        all.sendMessage(Main.getPlugin().PREFIX + "§3Der BlauMeister §1" + p.getName() + " §3möchte mit euch sprechen!");
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
                                    setmeetstartblau(true);
                                    if (target.hasPermission("wintervillage.blueteam") && getmeetstartblau() == true) {
                                        p.sendMessage(Main.getPlugin().PREFIX + "§3Die Anfrage wurde abgeschickt. Warte auf eine Antwort!");
                                        target.sendMessage(Main.getPlugin().PREFIX + "§3Der BlauMeister §1" + p.getName() + " §3möchte mit dir sprechen!");
                                        target.sendMessage(Main.getPlugin().PREFIX + "§cDu hast 15 Sekunden, um anzunehmen!");
                                        TextComponent tc = new TextComponent();
                                        tc.setText("\n§7[§aKlicke hier um anzunehmen§7]");
                                        tc.setUnderlined(true);
                                        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/meet Loremetloremdasdaasdasddasdasdoremichpoeplindernase asdasdasgfbbdffgdrfg"));
                                        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Aktzeptiert die Anfrage!").create()));
                                        target.spigot().sendMessage(tc);
                                        new BukkitRunnable() {
                                            int time = 16;
                                            String t1;
                                            String t2;

                                            @Override
                                            public void run() {
                                                time--;
                                                if (getmeetstartblau() == false) {
                                                    cancel();
                                                }
                                                if (time == 0) {
                                                    target.sendMessage(Main.getPlugin().PREFIX + "§cDie Anfrage ist abgelaufen!");
                                                    t2 = String.format("§6§lAnfrage abgelaufen!");
                                                    String message = t2;
                                                    target.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                                                    p.sendMessage(Main.getPlugin().PREFIX + "§cDer Mitbürger §r" + target.getName() + " §chat nicht angenommen! Versuche es erneut, wenn es dringlich ist.");
                                                    setmeetstartrot(false);
                                                    cancel();
                                                } else {
                                                    t1 = String.format("§6§lAnfrage noch: " + time + "sec");
                                                    String message = t1;
                                                    target.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));


                                                }
                                            }
                                        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                                    } else {
                                        p.sendMessage(Main.getPlugin().PREFIX + "§cDer Spieler §r" + target.getName() + " §cist nicht in deinem Village!");
                                        setmeetstartblau(false);
                                    }
                                }
                            }
                        } else
                            p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/meet <SPIELER oder all>§c!");

                    } else {
                        p.sendMessage(Main.getPlugin().PREFIX + "§cNur der Bürgermeister darf dies!");
                    }
                }
            } else
                p.sendMessage(Main.getPlugin().PREFIX + "§cDies darfst du während des Clashes nicht tun!");
        }
        return false;
    }


}