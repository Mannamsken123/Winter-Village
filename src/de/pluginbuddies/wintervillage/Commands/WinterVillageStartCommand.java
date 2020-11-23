//Plugin by: PluginBuddies
//-> Mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R2.PacketPlayOutTitle;
import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class WinterVillageStartCommand implements CommandExecutor {

    public static void spawnRandomFirework(final Location loc) {
        final Firework firework = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        final FireworkMeta fireworkMeta = firework.getFireworkMeta();
        final Random random = new Random();
        final FireworkEffect effect = FireworkEffect.builder().flicker(random.nextBoolean()).withColor(getColor(random.nextInt(17) + 1)).withFade(getColor(random.nextInt(17) + 1)).with(Type.values()[random.nextInt(Type.values().length)]).trail(random.nextBoolean()).build();
        fireworkMeta.addEffect(effect);
        fireworkMeta.setPower(random.nextInt(2) + 1);
        firework.setFireworkMeta(fireworkMeta);
    }

    private static Color getColor(final int i) {
        switch (i) {
            case 1:
                return Color.AQUA;
            case 2:
                return Color.BLACK;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.FUCHSIA;
            case 5:
                return Color.GRAY;
            case 6:
                return Color.GREEN;
            case 7:
                return Color.LIME;
            case 8:
                return Color.MAROON;
            case 9:
                return Color.NAVY;
            case 10:
                return Color.OLIVE;
            case 11:
                return Color.ORANGE;
            case 12:
                return Color.PURPLE;
            case 13:
                return Color.RED;
            case 14:
                return Color.SILVER;
            case 15:
                return Color.TEAL;
            case 16:
                return Color.WHITE;
            case 17:
                return Color.YELLOW;
        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if (args.length == 0) {
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "difficulty peaceful");
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "time set night");
                new BukkitRunnable() {
                    int time = 16;
                    World world = Bukkit.getWorld("world");
                    Location loc1 = new Location(world, 114, 41, -71);
                    Location loc2 = new Location(world, 117, 41, -80);
                    Location loc3 = new Location(world, 125, 41, -66);
                    Location loc4 = new Location(world, 127, 41, -41);
                    Location loc5 = new Location(world, 121, 41, -89);
                    Location loc6 = new Location(world, 111, 42, -83);
                    Location loc7 = new Location(world, 99, 42, -78);
                    Location loc8 = new Location(world, 108, 55, -61);
                    Location loc9 = new Location(world, 94, 42, -57);
                    Location loc10 = new Location(world, 106, 41, -54);
                    Location loc11 = new Location(world, 122, 51, -59);
                    Location loc12 = new Location(world, 97, 39, -43);

                    @Override
                    public void run() {
                        time--;
                        if (time == 0) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
                                st(all.getPlayer(), "§3Winter Village", "§7by mullemann25 & Mannam01", 5, 200, 5);
                                all.sendMessage("§k69§3Herzlich Willkommen bei unserem Winter-Projekt! §r§k69");
                                all.sendMessage("§7Wir freuen uns, dass du da bist! Wenn du Hilfe brauchst:");
                                TextComponent tc = new TextComponent();
                                tc.setText("\n§7[§aKlicke hier§7]");
                                tc.setUnderlined(true);
                                tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://winter-village.bplaced.net/"));
                                tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Alles zum Projekt").create()));
                                all.spigot().sendMessage(tc);
                                spawnRandomFirework(loc1);
                                spawnRandomFirework(loc2);
                                spawnRandomFirework(loc5);
                                spawnRandomFirework(loc6);
                                spawnRandomFirework(loc3);
                                spawnRandomFirework(loc4);
                                spawnRandomFirework(loc6);
                            }
                            //region flags
                            File delete = new File("plugins//WorldGuard//worlds//world//regions.yml");
                            delete.delete();

                            Path source = Paths.get("plugins//WorldGuard//worlds//world//.regions.yml");
                            Path target = Paths.get("plugins//WorldGuard//worlds//world//regions.yml");
                            try {
                                Files.move(source, target);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            new BukkitRunnable() {
                                int time = 13;

                                @Override
                                public void run() {
                                    time--;
                                    if (time == 0) {
                                        //reload
                                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "rl");

                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            all.sendMessage(Main.getPlugin().PREFIX + "§3Begebt euch nun gerne in eure Villages!");
                                        }
                                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "time set day");
                                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "difficulty hard");
                                        cancel();
                                    } else if (time == 12) {
                                        spawnRandomFirework(loc1);
                                        spawnRandomFirework(loc2);
                                    } else if (time == 11) {
                                        spawnRandomFirework(loc3);
                                        spawnRandomFirework(loc4);
                                    } else if (time == 10) {
                                        spawnRandomFirework(loc5);
                                        spawnRandomFirework(loc6);
                                    } else if (time == 9) {
                                        spawnRandomFirework(loc7);
                                        spawnRandomFirework(loc8);
                                    } else if (time == 8) {
                                        spawnRandomFirework(loc9);
                                        spawnRandomFirework(loc10);
                                    } else if (time == 7) {
                                        spawnRandomFirework(loc11);
                                        spawnRandomFirework(loc12);
                                    } else if (time == 6) {
                                        spawnRandomFirework(loc1);
                                        spawnRandomFirework(loc2);
                                    } else if (time == 5) {
                                        spawnRandomFirework(loc3);
                                        spawnRandomFirework(loc4);
                                        spawnRandomFirework(loc6);
                                    } else if (time == 4) {
                                        spawnRandomFirework(loc9);
                                        spawnRandomFirework(loc10);
                                    } else if (time == 3) {
                                        spawnRandomFirework(loc11);
                                        spawnRandomFirework(loc12);
                                        spawnRandomFirework(loc2);
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            all.sendMessage(Main.getPlugin().PREFIX + "§cDas Projekt wird gestartet. Es wird einmal kurz laggen.");
                                        }
                                    } else if (time == 2) {
                                        spawnRandomFirework(loc3);
                                        spawnRandomFirework(loc4);
                                    }
                                }
                            }.runTaskTimer(Main.getPlugin(), 0L, 10L);

                            cancel();
                        } else
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                st(all.getPlayer(), "§3Winter Village", "§7startet in " + time + " Sekunden", 0, 40, 0);
                            }
                    }
                }.runTaskTimer(Main.getPlugin(), 0L, 20L);
            }
        }
        return false;
    }

    public void st(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        PacketPlayOutTitle times;
        if (title != null) {
            times = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, CraftChatMessage.fromString(title)[0]);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(times);
        }
        if (subtitle != null) {
            times = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, CraftChatMessage.fromString(subtitle)[0]);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(times);
        }
        times = new PacketPlayOutTitle(fadeIn, stay, fadeOut);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(times);
    }
}


