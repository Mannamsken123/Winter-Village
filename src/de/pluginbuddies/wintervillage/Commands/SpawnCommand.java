//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String w = p.getWorld().getName();

            if (!w.equals("world-clash") && Main.getPlugin().getClashOpen() != "true") {
                if (args.length == 0) {
                    new BukkitRunnable() {
                        int time = 4;

                        @Override
                        public void run() {
                            time--;
                            if (time == 0) {
                                World world = Bukkit.getWorld("world");
                                Location location = new Location(world, 114.528, 41, -71.520, -90, -3);
                                p.teleport(location);
                                p.setGameMode(GameMode.SURVIVAL);
                                cancel();
                            } else
                                p.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                        }
                    }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                } else {
                    if (args.length != 1) {
                        p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/spawn §coder §r/spawn withentity§c!");
                    }
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("withentity")) {
                        if (w.equals("FarmweltNr1")) {
                            if (Main.getPlugin().getTravelUse() == false) {
                                Main.getPlugin().setCurrent(p);
                                Main.getPlugin().setTravelUse(true);
                                p.sendMessage(Main.getPlugin().PREFIX + "§6Du kannst jetzt ein Entity mit dir mit teleportieren, indem du es mit einem §rRechtsklick §6markierst!");
                                Main.getPlugin().setTravelWithEntity(true);
                                new BukkitRunnable() {
                                    int time = 7;

                                    @Override
                                    public void run() {
                                        time--;

                                        if (time == 0) {
                                            World world = Bukkit.getWorld("world");
                                            Location location = new Location(world, 114.528, 41, -71.520, -90, -3);
                                            p.teleport(location);
                                            p.setGameMode(GameMode.SURVIVAL);
                                            Main.getPlugin().setTravelWithEntity(false);
                                            Main.getPlugin().setTravelUse(false);
                                            cancel();
                                        } else {
                                            if (time <= 3) {
                                                p.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                            }
                                        }

                                    }
                                }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                            } else
                                p.sendMessage(Main.getPlugin().PREFIX + "§cWarte kurz, nur einer zur Zeit!");
                        } else
                            p.sendMessage(Main.getPlugin().PREFIX + "§cDu kannst dies nur in der Farmwelt!");
                    } else
                        p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/spawn withentity§c!");
                }
            } else
                p.sendMessage(Main.getPlugin().PREFIX + "§cDies darfst du während des Clashes nicht tun!");
        }
        return false;
    }


    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        if (Main.getPlugin().getTravelWithEntity() == true) {
            Entity entity = e.getRightClicked();
            Player p = e.getPlayer();
            if (Main.getPlugin().getTravelUse() == true) {
                if (p == Main.getPlugin().getCurrent()) {
                    if (entity instanceof Animals || entity instanceof Villager || entity instanceof Dolphin || entity instanceof WanderingTrader) {
                        if (entity instanceof Villager || entity instanceof WanderingTrader) {
                            e.setCancelled(true);
                        }
                        String t2 = String.format(entity.getName() + " §2✔");
                        String message2 = t2;
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message2));

                        new BukkitRunnable() {
                            int time = 7;

                            @Override
                            public void run() {
                                time--;
                                if (time == 0) {
                                    Location loc = p.getLocation();
                                    entity.teleport(loc);
                                    Main.getPlugin().setTravelUse(false);
                                    cancel();
                                }
                            }
                        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                    }
                }
            }
            Main.getPlugin().setTravelWithEntity(false);
        }
    }
}

