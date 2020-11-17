//Plugin by: PluginBuddies
//-> Mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Util;

import de.pluginbuddies.wintervillage.Main.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R2.PacketPlayInClientCommand;
import net.minecraft.server.v1_16_R2.PacketPlayOutTitle;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Clash implements CommandExecutor, Listener {

    private BossBar bar;
    private int fighterRed;
    private int fighterBlue;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if (args[0].equalsIgnoreCase("start")) {
                createBar();
                fighterRed = 0;
                fighterBlue = 0;
            }
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {

            }
        }
        return false;
    }

    public void addPlayer(Player player) {
        bar.addPlayer(player);
    }

    public void createBar() {
        bar = Bukkit.createBossBar("§b§lClash: Heute um 21:00 Uhr!", BarColor.WHITE, BarStyle.SOLID);
        bar.setVisible(true);
        cast();
    }

    public void cast() {
        new BukkitRunnable() {
            int count = -1;
            double progress = 1.0;
            double time = 1.0 / (10); //PENIS (1.0/(60 * 60)
            int lastHour = 3600;
            int sec = 60;
            int min = 59;

            @Override
            public void run() {
                bar.setProgress(progress);
                if (count == 0) { //PENIS 20
                    lastHour--;
                    sec--;
                    if (sec == 0) {
                        sec = 60;
                        min--;
                    }

                    bar.setColor(BarColor.PINK);
                    bar.setTitle("§b§lClash in: §6" + min + "min:" + sec + "sec");
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        addPlayer(all);
                    }
                } else if (count == 1) { //PENIS 21
                    if (Bukkit.getOnlinePlayers().size() > 0) {
                        int onlyred = 0;
                        int onlyblue = 0;
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("wintervillage.redteam")) {
                                onlyred++;
                            }
                            if (all.hasPermission("wintervillage.blueteam")) {
                                onlyblue++;
                            }
                        }
                        if (onlyred == 0) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                if (all.hasPermission("wintervillage.blueteam")) {
                                    st(all, "§9Village Blau", "§7hat den Clash gewonnen!", 5, 100, 5);
                                    all.playSound(all.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
                                    //PENIS schreibe wer den clash gewonnen hat -> MENÜ
                                }
                            }
                            bar.removeAll();
                            Main.getPlugin().setClashOpen(null);
                            cancel();
                        } else if (onlyblue == 0) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                if (all.hasPermission("wintervillage.redteam")) {
                                    st(all, "§cVillage Rot", "§7hat den Clash gewonnen!", 5, 100, 5);
                                    all.playSound(all.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
                                    //PENIS schreibe wer den clash gewonnen hat -> MENÜ
                                }
                            }
                            bar.removeAll();
                            Main.getPlugin().setClashOpen(null);
                            cancel();
                        } else {
                            bar.removeAll();

                            //create world-clash
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mv clone world world-clash");

                            //save inventories
                            checkDirectory();
                            ArrayList<ItemStack> list = new ArrayList<>();
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                File file = new File("plugins//Clash//Inventories//" + all.getName() + ".yml");

                                try {
                                    file.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                YamlConfiguration inv = YamlConfiguration.loadConfiguration(file);

                                ArrayList<Integer> slot = new ArrayList<>();

                                for (int i = 0; i <= all.getInventory().getSize(); i++) {
                                    if (all.getInventory().getItem(i) != null) {
                                        slot.add(i);
                                    }
                                }

                                ItemStack[] contents = all.getInventory().getContents();
                                double health = all.getHealth();
                                int level = all.getLevel();
                                double exp = all.getExp();
                                int hunger = all.getFoodLevel();

                                for (int i = 0; i < contents.length; i++) {
                                    ItemStack item = contents[i];

                                    if (!(item == null)) {
                                        list.add(item);
                                    }
                                }

                                double X = all.getLocation().getX();
                                double Y = all.getLocation().getY();
                                double Z = all.getLocation().getZ();

                                inv.set("Slot", slot);
                                inv.set("Inventory", list);
                                inv.set("Health", health);
                                inv.set("Exp", exp);
                                inv.set("Level", level);
                                inv.set("Hunger", hunger);
                                inv.set("X", X);
                                inv.set("Y", Y);
                                inv.set("Z", Z);

                                try {
                                    inv.save(file);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                all.setFoodLevel(20);
                                all.setMaxHealth(20.0);
                                all.setHealth(20);
                                all.setTotalExperience(0);
                                all.setExp(0);
                                all.setLevel(0);
                            }

                            new BukkitRunnable() {
                                int time = 4;

                                @Override
                                public void run() {
                                    time--;
                                    if (time == 0) {
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.redteam")) {
                                                fighterRed++;
                                                World w = Bukkit.getWorld("world-clash");
                                                Location location = new Location(w, 55.5, 40, 106.5, -90, -3);
                                                all.teleport(location);
                                            } else if (all.hasPermission("wintervillage.blueteam")) {
                                                fighterBlue++;
                                                World w = Bukkit.getWorld("world-clash");
                                                Location location = new Location(w, 149.5, 40, -229.5, 90, -3);
                                                all.teleport(location);
                                            }
                                        }

                                        World clash = Bukkit.getWorld("world-clash");
                                        WorldBorder border = clash.getWorldBorder();
                                        border.setCenter(114.528, -71.520);
                                        border.setSize(30, 45 * 60);
                                        border.setWarningDistance(5);
                                        border.setDamageAmount(2);

                                        new BukkitRunnable() {
                                            World clash = Bukkit.getWorld("world-clash");
                                            WorldBorder border = clash.getWorldBorder();

                                            @Override
                                            public void run() {
                                                if (fighterRed == 0 || fighterBlue == 0) {
                                                    cancel();
                                                } else
                                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                                        String message = "§6§lBorder: " + (int) border.getSize() + " x " + (int) border.getSize() + "  §7||  " + "§c§lRot: " + fighterRed + "  §7||  " + " §9§lBlau: " + fighterBlue;
                                                        all.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                                                    }
                                            }
                                        }.runTaskTimer(Main.getPlugin(), 0L, 20L);


                                        cancel();
                                    } else
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            all.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                        }
                                }
                            }.runTaskTimer(Main.getPlugin(), 0L, 20L);

                            cancel();
                        }
                    } else {
                        count = 0; //PENIS 20
                    }

                } else {
                    bar.setColor(BarColor.WHITE);
                    bar.setTitle("§b§lClash: Heute um 21:00 Uhr!");
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        addPlayer(all);
                    }
                }

                progress = progress - time;
                if (progress <= 0) {
                    count++;
                    progress = 1.0;
                }
            }

        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        String w = p.getWorld().getName();

        if (w.equals("world-clash")) {

            new BukkitRunnable() {
                int time = 2;

                @Override
                public void run() {
                    time--;
                    if (time == 0) {
                        PacketPlayInClientCommand packet = new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN);
                        ((CraftPlayer) p).getHandle().playerConnection.a(packet);
                        cancel();
                    }
                }
            }.runTaskTimer(Main.getPlugin(), 0L, 20L);

            e.setDeathMessage(p.getName() + "  §7ist in der Rauferei umgekommen!");
            File file = new File("plugins//Clash//Inventories//" + p.getName() + ".yml");
            YamlConfiguration inv = YamlConfiguration.loadConfiguration(file);
            double sX = p.getLocation().getX();
            double sY = p.getLocation().getY();
            double sZ = p.getLocation().getZ();
            inv.set("DX", sX);
            inv.set("DY", sY);
            inv.set("DZ", sZ);

            try {
                inv.save(file);
            } catch (IOException v) {
                v.printStackTrace();
            }

            if (p.hasPermission("wintervillage.redteam")) {
                fighterRed--;
                Bukkit.broadcastMessage(String.valueOf(fighterRed));
            } else if (p.hasPermission("wintervillage.blueteam")) {
                fighterBlue--;
                Bukkit.broadcastMessage(String.valueOf(fighterBlue));
            }

            if (fighterRed == 0 || fighterBlue == 0) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (fighterRed == 0) {
                        st(all, "§9Village Blau", "§7hat den Clash gewonnen!", 5, 500, 5);
                        //PENIS schreibe wer den clash gewonnen hat -> MENÜ
                    } else if (fighterBlue == 0) {
                        st(all, "§cVillage Rot", "§7hat den Clash gewonnen!", 5, 500, 5);
                        //PENIS schreibe wer den clash gewonnen hat -> MENÜ
                    }
                    all.playSound(all.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
                }

                new BukkitRunnable() {
                    int time = 16;

                    @Override
                    public void run() {
                        time--;
                        if (time == 0) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                File inventory = new File("plugins//Clash//Inventories//" + all.getName() + ".yml");
                                if (inventory.exists()) {
                                    YamlConfiguration inv = YamlConfiguration.loadConfiguration(inventory);
                                    all.getInventory().clear();

                                    try {
                                        inv.load("plugins//Clash//Inventories//" + all.getName() + ".yml");
                                    } catch (IOException | InvalidConfigurationException e) {
                                        e.printStackTrace();
                                    }

                                    double health = inv.getDouble("Health");
                                    all.setHealth(health);
                                    double exp = inv.getDouble("Exp");
                                    all.setExp((float) exp);
                                    int level = inv.getInt("Level");
                                    all.setLevel(level);
                                    int hunger = inv.getInt("Hunger");
                                    all.setFoodLevel(hunger);


                                    World world = Bukkit.getWorld("world");
                                    Double X = inv.getDouble("X");
                                    Double Y = inv.getDouble("Y");
                                    Double Z = inv.getDouble("Z");
                                    Location loc = new Location(world, X, Y + 1, Z);
                                    all.teleport(loc);
                                    inventory.delete();
                                } else {
                                    World world = Bukkit.getWorld("world");
                                    Location location = new Location(world, 114.528, 41, -71.520, -90, -3);
                                    all.teleport(location);
                                }
                            }
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mv delete world-clash");
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mvconfirm");
                            Main.getPlugin().setClashOpen(null);

                            cancel();
                        } else {
                            if (time < 6) {
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                }
                            }
                        }
                    }
                }.runTaskTimer(Main.getPlugin(), 0L, 20L);
            }
        }
    }

    @EventHandler
    public void onRespawnEVENT(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        String w = p.getWorld().getName();

        if (w.equals("world-clash")) {
            p.setGameMode(GameMode.SPECTATOR);
            File inventory = new File("plugins//Clash//Inventories//" + p.getName() + ".yml");
            YamlConfiguration inv = YamlConfiguration.loadConfiguration(inventory);
            try {
                inv.load("plugins//Clash//Inventories//" + p.getName() + ".yml");
            } catch (IOException | InvalidConfigurationException v) {
                v.printStackTrace();
            }
            World world = Bukkit.getWorld("world-clash");
            Double dX = inv.getDouble("DX");
            Double dY = inv.getDouble("DY");
            Double dZ = inv.getDouble("DZ");
            Location loc = new Location(world, dX, dY + 1, dZ);

            e.setRespawnLocation(loc);
        }
    }

    @EventHandler
    public void handlePlayerLeave(PlayerQuitEvent e) {
        Bukkit.broadcastMessage(Main.getPlugin().getClashOpen());
        Bukkit.broadcastMessage("hello1");
        if (Main.getPlugin().getClashOpen() == "true") {
            Bukkit.broadcastMessage("hello2");
            if (e.getPlayer().hasPermission("wintervillage.redteam")) {
                fighterRed--;

            } else if (e.getPlayer().hasPermission("wintervillage.blueteam")) {
                fighterBlue--;
            }
            Bukkit.broadcastMessage("hallo");

            if (fighterRed == 0 || fighterBlue == 0) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (fighterRed == 0) {
                        st(all, "§9Village Blau", "§7hat den Clash gewonnen!", 5, 500, 5);
                    } else if (fighterBlue == 0) {
                        st(all, "§cVillage Rot", "§7hat den Clash gewonnen!", 5, 500, 5);
                    }
                    all.playSound(all.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
                }

                new BukkitRunnable() {
                    int time = 16;

                    @Override
                    public void run() {
                        time--;
                        if (time == 0) {
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                File inventory = new File("plugins//Clash//Inventories//" + all.getName() + ".yml");
                                if (inventory.exists()) {
                                    YamlConfiguration inv = YamlConfiguration.loadConfiguration(inventory);
                                    all.getInventory().clear();

                                    try {
                                        inv.load("plugins//Clash//Inventories//" + all.getName() + ".yml");
                                    } catch (IOException | InvalidConfigurationException e) {
                                        e.printStackTrace();
                                    }

                                    double health = inv.getDouble("Health");
                                    all.setHealth(health);
                                    double exp = inv.getDouble("Exp");
                                    all.setExp((float) exp);
                                    int level = inv.getInt("Level");
                                    all.setLevel(level);
                                    int hunger = inv.getInt("Hunger");
                                    all.setFoodLevel(hunger);


                                    World world = Bukkit.getWorld("world");
                                    Double X = inv.getDouble("X");
                                    Double Y = inv.getDouble("Y");
                                    Double Z = inv.getDouble("Z");
                                    Location loc = new Location(world, X, Y + 1, Z);
                                    all.teleport(loc);
                                    inventory.delete();
                                } else {
                                    World world = Bukkit.getWorld("world");
                                    Location location = new Location(world, 114.528, 41, -71.520, -90, -3);
                                    all.teleport(location);
                                }
                            }
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mv delete world-clash");
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mvconfirm");
                            Main.getPlugin().setClashOpen(null);

                            cancel();
                        } else {
                            if (time < 6) {
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                                }
                            }
                        }
                    }
                }.runTaskTimer(Main.getPlugin(), 0L, 20L);
            }
        }
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
        if (e.getFrom().equals(Bukkit.getWorld("world-clash"))) {
            Player p = e.getPlayer();
            File inventory = new File("plugins//Clash//Inventories//" + p.getName() + ".yml");
            if (inventory.exists()) {
                YamlConfiguration inv = YamlConfiguration.loadConfiguration(inventory);
                p.getInventory().clear();

                try {
                    inv.load("plugins//Clash//Inventories//" + p.getName() + ".yml");
                } catch (IOException | InvalidConfigurationException v) {
                    v.printStackTrace();
                }

                List<?> list = inv.getList("Inventory");
                List<?> slot = inv.getList("Slot");

                for (int j = 0; j <= p.getInventory().getSize(); j++) {
                    p.getInventory().setItem((Integer) slot.get(j), (ItemStack) list.get(j));
                }
            }
        }
    }

    public void checkDirectory() {
        File file = new File("plugins//Clash//Inventories");
        if (!file.exists()) {
            file.mkdir();
        }
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
