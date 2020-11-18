//Plugin by: PluginBuddies
//-> Mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Util;

import de.pluginbuddies.wintervillage.Main.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R2.PacketPlayInClientCommand;
import net.minecraft.server.v1_16_R2.PacketPlayOutTitle;
import org.bukkit.*;
import org.bukkit.block.Block;
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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Clash implements CommandExecutor, Listener {

    private BossBar bar;
    private File folderClash = new File("plugins//Clash");
    private File configClash = new File("plugins//Clash//config.yml");
    private YamlConfiguration ymlConfigClash = YamlConfiguration.loadConfiguration(configClash);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if (args[0].equalsIgnoreCase("start")) {
                createBar();
                if (!folderClash.exists()) {
                    folderClash.mkdir();
                }
                if (!configClash.exists()) {
                    try {
                        configClash.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                ymlConfigClash.set("Fighter.Red", 0);
                ymlConfigClash.set("Fighter.Blue", 0);

                try {
                    ymlConfigClash.save(configClash);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                String w = p.getWorld().getName();
                if (!w.equals("world-clash") && Main.getPlugin().getClashOpen2() != "true") {
                    Inventory inv = Bukkit.createInventory(p, 36, "§6Clash Übersicht");

                    ItemStack Clash1 = new ItemStack(Material.DIAMOND_SWORD);
                    ItemMeta imOpen1 = Clash1.getItemMeta();
                    String name1;
                    if (Main.getPlugin().getYmlConfigClash().getString("clash1").equals("2025/01/01")) {
                        name1 = "§c§lClash 1 - 02/12/2020";
                    } else {
                        name1 = "§a§lClash 1 - 02/12/2020";
                    }
                    imOpen1.setDisplayName(name1);
                    Clash1.setItemMeta(imOpen1);
                    Clash1.setAmount(1);
                    inv.setItem(9 + 1, Clash1);

                    ItemStack Clash2 = new ItemStack(Material.DIAMOND_SWORD);
                    ItemMeta imOpen2 = Clash2.getItemMeta();
                    String name2;
                    if (Main.getPlugin().getYmlConfigClash().getString("clash2").equals("2025/01/01")) {
                        name2 = "§c§lClash 2 - 09/12/2020";
                    } else {
                        name2 = "§a§lClash 2 - 09/12/2020";
                    }
                    imOpen2.setDisplayName(name2);
                    Clash2.setItemMeta(imOpen2);
                    Clash2.setAmount(1);
                    inv.setItem(9 + 3, Clash2);

                    ItemStack Clash3 = new ItemStack(Material.DIAMOND_SWORD);
                    ItemMeta imOpen3 = Clash3.getItemMeta();
                    String name3;
                    if (Main.getPlugin().getYmlConfigClash().getString("clash3").equals("2025/01/01")) {
                        name3 = "§c§lClash 3 - 16/12/2020";
                    } else {
                        name3 = "§a§lClash 3 - 16/12/2020";
                    }
                    imOpen3.setDisplayName(name3);
                    Clash3.setItemMeta(imOpen3);
                    Clash3.setAmount(1);
                    inv.setItem(9 + 5, Clash3);

                    ItemStack Clash4 = new ItemStack(Material.DIAMOND_SWORD);
                    ItemMeta imOpen4 = Clash4.getItemMeta();
                    String name4;
                    if (Main.getPlugin().getYmlConfigClash().getString("clash4").equals("2025/01/01")) {
                        name4 = "§c§lClash 4 - 23/12/2020";
                    } else {
                        name4 = "§a§lClash 4 - 23/12/2020";
                    }
                    imOpen4.setDisplayName(name4);
                    Clash4.setItemMeta(imOpen4);
                    Clash4.setAmount(1);
                    inv.setItem(9 + 7, Clash4);

                    //colorblocks
                    File configClash = new File("plugins//Clash//Wins//config.yml");
                    YamlConfiguration ymlConfigClash = YamlConfiguration.loadConfiguration(configClash);

                    try {
                        ymlConfigClash.load("plugins//Clash//Wins//config.yml");
                    } catch (IOException | InvalidConfigurationException e) {
                        e.printStackTrace();
                    }

                    if (ymlConfigClash.getString("winner1").equals("blue")) {
                        ItemStack Win1 = new ItemStack(Material.BLUE_CONCRETE);
                        ItemMeta imOpenWin1 = Win1.getItemMeta();
                        imOpenWin1.setDisplayName("§9Village Blau hat diesen Clash gewonnen");
                        Win1.setItemMeta(imOpenWin1);
                        Win1.setAmount(1);
                        inv.setItem(9 + 9 + 1, Win1);
                    } else if (ymlConfigClash.getString("winner1").equals("red")) {
                        ItemStack Win1 = new ItemStack(Material.RED_CONCRETE);
                        ItemMeta imOpenWin1 = Win1.getItemMeta();
                        imOpenWin1.setDisplayName("§cVillage Rot hat diesen Clash gewonnen");
                        Win1.setItemMeta(imOpenWin1);
                        Win1.setAmount(1);
                        inv.setItem(9 + 9 + 1, Win1);
                    } else {
                        ItemStack Win1 = new ItemStack(Material.GRAY_CONCRETE);
                        ItemMeta imOpenWin1 = Win1.getItemMeta();
                        imOpenWin1.setDisplayName("§6Der Gewinner dieses Clashes ist noch ausstehend");
                        Win1.setItemMeta(imOpenWin1);
                        Win1.setAmount(1);
                        inv.setItem(9 + 9 + 1, Win1);
                    }

                    if (ymlConfigClash.getString("winner2").equals("blue")) {
                        ItemStack Win2 = new ItemStack(Material.BLUE_CONCRETE);
                        ItemMeta imOpenWin2 = Win2.getItemMeta();
                        imOpenWin2.setDisplayName("§9Village Blau hat diesen Clash gewonnen");
                        Win2.setItemMeta(imOpenWin2);
                        Win2.setAmount(1);
                        inv.setItem(9 + 9 + 3, Win2);
                    } else if (ymlConfigClash.getString("winner2").equals("red")) {
                        ItemStack Win2 = new ItemStack(Material.RED_CONCRETE);
                        ItemMeta imOpenWin2 = Win2.getItemMeta();
                        imOpenWin2.setDisplayName("§cVillage Rot hat diesen Clash gewonnen");
                        Win2.setItemMeta(imOpenWin2);
                        Win2.setAmount(1);
                        inv.setItem(9 + 9 + 3, Win2);
                    } else {
                        ItemStack Win2 = new ItemStack(Material.GRAY_CONCRETE);
                        ItemMeta imOpenWin2 = Win2.getItemMeta();
                        imOpenWin2.setDisplayName("§6Der Gewinner dieses Clashes ist noch ausstehend");
                        Win2.setItemMeta(imOpenWin2);
                        Win2.setAmount(1);
                        inv.setItem(9 + 9 + 3, Win2);
                    }

                    if (ymlConfigClash.getString("winner3").equals("blue")) {
                        ItemStack Win3 = new ItemStack(Material.BLUE_CONCRETE);
                        ItemMeta imOpenWin3 = Win3.getItemMeta();
                        imOpenWin3.setDisplayName("§9Village Blau hat diesen Clash gewonnen");
                        Win3.setItemMeta(imOpenWin3);
                        Win3.setAmount(1);
                        inv.setItem(9 + 9 + 5, Win3);
                    } else if (ymlConfigClash.getString("winner3").equals("red")) {
                        ItemStack Win3 = new ItemStack(Material.RED_CONCRETE);
                        ItemMeta imOpenWin3 = Win3.getItemMeta();
                        imOpenWin3.setDisplayName("§cVillage Rot hat diesen Clash gewonnen");
                        Win3.setItemMeta(imOpenWin3);
                        Win3.setAmount(1);
                        inv.setItem(9 + 9 + 5, Win3);
                    } else {
                        ItemStack Win3 = new ItemStack(Material.GRAY_CONCRETE);
                        ItemMeta imOpenWin3 = Win3.getItemMeta();
                        imOpenWin3.setDisplayName("§6Der Gewinner dieses Clashes ist noch ausstehend");
                        Win3.setItemMeta(imOpenWin3);
                        Win3.setAmount(1);
                        inv.setItem(9 + 9 + 5, Win3);
                    }

                    if (ymlConfigClash.getString("winner4").equals("blue")) {
                        ItemStack Win4 = new ItemStack(Material.BLUE_CONCRETE);
                        ItemMeta imOpenWin4 = Win4.getItemMeta();
                        imOpenWin4.setDisplayName("§9Village Blau hat diesen Clash gewonnen");
                        Win4.setItemMeta(imOpenWin4);
                        Win4.setAmount(1);
                        inv.setItem(9 + 9 + 7, Win4);
                    } else if (ymlConfigClash.getString("winner4").equals("red")) {
                        ItemStack Win4 = new ItemStack(Material.RED_CONCRETE);
                        ItemMeta imOpenWin4 = Win4.getItemMeta();
                        imOpenWin4.setDisplayName("§cVillage Rot hat diesen Clash gewonnen");
                        Win4.setItemMeta(imOpenWin4);
                        Win4.setAmount(1);
                        inv.setItem(9 + 9 + 7, Win4);
                    } else {
                        ItemStack Win4 = new ItemStack(Material.GRAY_CONCRETE);
                        ItemMeta imOpenWin4 = Win4.getItemMeta();
                        imOpenWin4.setDisplayName("§6Der Gewinner dieses Clashes ist noch ausstehend");
                        Win4.setItemMeta(imOpenWin4);
                        Win4.setAmount(1);
                        inv.setItem(9 + 9 + 7, Win4);
                    }

                    p.openInventory(inv);
                } else {
                    p.sendMessage(Main.getPlugin().PREFIX + "§cDies darfst du während des Clashes nicht tun!");
                }
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
            double time = 1.0 / (5); //PENIS (1.0/(60 * 60)
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

                                    File folderClash = new File("plugins//Clash//Wins");
                                    File configClash = new File("plugins//Clash//Wins//config.yml");
                                    YamlConfiguration ymlConfigClash = YamlConfiguration.loadConfiguration(configClash);

                                    if (!folderClash.exists()) {
                                        folderClash.mkdir();
                                    }
                                    if (!configClash.exists()) {
                                        try {
                                            configClash.createNewFile();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    if (ymlConfigClash.getString("winner1").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash1").equals("2025/01/01")) {
                                        ymlConfigClash.set("winner1", "blue");
                                    }
                                    if (ymlConfigClash.getString("winner2").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash2").equals("2025/01/01")) {
                                        ymlConfigClash.set("winner2", "blue");
                                    }
                                    if (ymlConfigClash.getString("winner3").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash3").equals("2025/01/01")) {
                                        ymlConfigClash.set("winner3", "blue");
                                    }
                                    if (ymlConfigClash.getString("winner4").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash4").equals("2025/01/01")) {
                                        ymlConfigClash.set("winner4", "blue");
                                    }

                                    try {
                                        ymlConfigClash.save(configClash);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
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

                                    File folderClash = new File("plugins//Clash//Wins");
                                    File configClash = new File("plugins//Clash//Wins//config.yml");
                                    YamlConfiguration ymlConfigClash = YamlConfiguration.loadConfiguration(configClash);
                                    if (!folderClash.exists()) {
                                        folderClash.mkdir();
                                    }
                                    if (!configClash.exists()) {
                                        try {
                                            configClash.createNewFile();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    if (ymlConfigClash.getString("winner1").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash1").equals("2025/01/01")) {
                                        ymlConfigClash.set("winner1", "red");
                                    }
                                    if (ymlConfigClash.getString("winner2").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash2").equals("2025/01/01")) {
                                        ymlConfigClash.set("winner2", "red");
                                    }
                                    if (ymlConfigClash.getString("winner3").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash3").equals("2025/01/01")) {
                                        ymlConfigClash.set("winner3", "red");
                                    }
                                    if (ymlConfigClash.getString("winner4").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash4").equals("2025/01/01")) {
                                        ymlConfigClash.set("winner4", "red");
                                    }

                                    try {
                                        ymlConfigClash.save(configClash);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            bar.removeAll();
                            Main.getPlugin().setClashOpen(null);
                            Main.getPlugin().setClashOpen2("false");
                            cancel();


                        } else {
                            bar.removeAll();

                            //create world-clash
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mv clone world world-clash");
                            Main.getPlugin().setClashOpen2("true");

                            //save inventories
                            checkDirectory();
                            //ArrayList<ItemStack> list = new ArrayList<>();

                            for (Player all : Bukkit.getOnlinePlayers()) {

                               /* try {
                                    FileOutputStream fileOut = new FileOutputStream("plugins//Clash//Inventories//" + all.getName() + ".playerinv");
                                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                                    out.writeObject(new PlayerSerialize(all));
                                    out.close();
                                    fileOut.close();
                                } catch (IOException e) {
                                    return;
                                }

                            */

                                Bukkit.broadcastMessage("nach inv");


                                File file = new File("plugins//Clash//Inventories2//" + all.getName() + ".yml");

                                try {
                                    file.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Bukkit.broadcastMessage("nach try/catch");


                                YamlConfiguration inv = YamlConfiguration.loadConfiguration(file);

                                /*ArrayList<Integer> slot = new ArrayList<>();

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

                                 */

                                double X = all.getLocation().getX();
                                double Y = all.getLocation().getY();
                                double Z = all.getLocation().getZ();

                                //inv.set("Slot", slot);
                                //inv.set("Inventory", list);
                                //inv.set("Health", health);
                                //inv.set("Exp", exp);
                                //inv.set("Level", level);
                                // inv.set("Hunger", hunger);
                                inv.set("X", X);    //PENIS sind nicht im Object
                                inv.set("Y", Y);    //PENIS sind nicht im Object
                                inv.set("Z", Z);    //PENIS sind nicht im Object

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
                                Bukkit.broadcastMessage("nach inv2");
                            }

                            new BukkitRunnable() {
                                int time = 4;

                                @Override
                                public void run() {
                                    time--;
                                    if (time == 0) {
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.redteam")) {
                                                try {
                                                    ymlConfigClash.load("plugins//Clash//config.yml");
                                                } catch (IOException | InvalidConfigurationException e) {
                                                    e.printStackTrace();
                                                }
                                                int tempRed = ymlConfigClash.getInt("Fighter.Red") + 1;
                                                ymlConfigClash.set("Fighter.Red", tempRed);
                                                try {
                                                    ymlConfigClash.save(configClash);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                World w = Bukkit.getWorld("world-clash");
                                                Location location = new Location(w, 55.5, 40, 106.5, -90, -3);
                                                all.teleport(location);
                                            } else if (all.hasPermission("wintervillage.blueteam")) {
                                                try {
                                                    ymlConfigClash.load("plugins//Clash//config.yml");
                                                } catch (IOException | InvalidConfigurationException e) {
                                                    e.printStackTrace();
                                                }
                                                int tempBlue = ymlConfigClash.getInt("Fighter.Blue") + 1;
                                                ymlConfigClash.set("Fighter.Blue", tempBlue);
                                                try {
                                                    ymlConfigClash.save(configClash);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                World w = Bukkit.getWorld("world-clash");
                                                Location location = new Location(w, 149.5, 40, -229.5, 90, -3);
                                                all.teleport(location);
                                            }
                                        }

                                        World world = Bukkit.getWorld("world-clash");
                                        Location loc1 = new Location(world, 90, 25, -75);
                                        Location loc2 = new Location(world, 99, 17, -84);
                                        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
                                        int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
                                        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
                                        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
                                        int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
                                        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());

                                        for (int x = minX; x <= maxX; x++) {
                                            for (int y = minY; y <= maxY; y++) {
                                                for (int z = minZ; z <= maxZ; z++) {
                                                    Block block = world.getBlockAt(x, y, z);
                                                    block.setType(Material.STONE);
                                                }
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
                                                try {
                                                    ymlConfigClash.load("plugins//Clash//config.yml");
                                                } catch (IOException | InvalidConfigurationException e) {
                                                    e.printStackTrace();
                                                }
                                                if (ymlConfigClash.getInt("Fighter.Red") == 0 || ymlConfigClash.getInt("Fighter.Blue") == 0) {
                                                    cancel();
                                                } else
                                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                                        String message = "§6§lBorder: " + (int) border.getSize() + " x " + (int) border.getSize() + "  §7||  " + "§c§lRot: " + ymlConfigClash.getInt("Fighter.Red") + "  §7||  " + " §9§lBlau: " + ymlConfigClash.getInt("Fighter.Blue");
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
                            Bukkit.broadcastMessage("komme hin");
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

            File file = new File("plugins//Clash//Inventories2//" + p.getName() + ".yml");
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
                try {
                    ymlConfigClash.load("plugins//Clash//config.yml");
                } catch (IOException | InvalidConfigurationException v) {
                    v.printStackTrace();
                }
                int tempRed = ymlConfigClash.getInt("Fighter.Red") - 1;
                ymlConfigClash.set("Fighter.Red", tempRed);
                try {
                    ymlConfigClash.save(configClash);
                } catch (IOException v) {
                    v.printStackTrace();
                }
            } else if (p.hasPermission("wintervillage.blueteam")) {
                try {
                    ymlConfigClash.load("plugins//Clash//config.yml");
                } catch (IOException | InvalidConfigurationException v) {
                    v.printStackTrace();
                }
                int tempBlue = ymlConfigClash.getInt("Fighter.Red") - 1;
                ymlConfigClash.set("Fighter.Blue", tempBlue);
                try {
                    ymlConfigClash.save(configClash);
                } catch (IOException v) {
                    v.printStackTrace();
                }
            }

            try {
                ymlConfigClash.load("plugins//Clash//config.yml");
            } catch (IOException | InvalidConfigurationException v) {
                v.printStackTrace();
            }
            if (ymlConfigClash.getInt("Fighter.Red") == 0 || ymlConfigClash.getInt("Fighter.Blue") == 0) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (ymlConfigClash.getInt("Fighter.Red") == 0) {
                        st(all, "§9Village Blau", "§7hat den Clash gewonnen!", 5, 500, 5);

                        File folderClash = new File("plugins//Clash//Wins");
                        File configClash = new File("plugins//Clash//Wins//config.yml");
                        YamlConfiguration ymlConfigClash = YamlConfiguration.loadConfiguration(configClash);

                        if (!folderClash.exists()) {
                            folderClash.mkdir();
                        }
                        if (!configClash.exists()) {
                            try {
                                configClash.createNewFile();
                            } catch (IOException v) {
                                v.printStackTrace();
                            }
                        }

                        if (ymlConfigClash.getString("winner1").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash1").equals("2025/01/01")) {
                            ymlConfigClash.set("winner1", "blue");
                        }
                        if (ymlConfigClash.getString("winner2").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash2").equals("2025/01/01")) {
                            ymlConfigClash.set("winner2", "blue");
                        }
                        if (ymlConfigClash.getString("winner3").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash3").equals("2025/01/01")) {
                            ymlConfigClash.set("winner3", "blue");
                        }
                        if (ymlConfigClash.getString("winner4").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash4").equals("2025/01/01")) {
                            ymlConfigClash.set("winner4", "blue");
                        }

                        try {
                            ymlConfigClash.save(configClash);
                        } catch (IOException v) {
                            v.printStackTrace();
                        }
                    } else if (ymlConfigClash.getInt("Fighter.Blue") == 0) {
                        st(all, "§cVillage Rot", "§7hat den Clash gewonnen!", 5, 500, 5);

                        File folderClash = new File("plugins//Clash//Wins");
                        File configClash = new File("plugins//Clash//Wins//config.yml");
                        YamlConfiguration ymlConfigClash = YamlConfiguration.loadConfiguration(configClash);

                        if (!folderClash.exists()) {
                            folderClash.mkdir();
                        }
                        if (!configClash.exists()) {
                            try {
                                configClash.createNewFile();
                            } catch (IOException v) {
                                v.printStackTrace();
                            }
                        }

                        if (ymlConfigClash.getString("winner1").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash1").equals("2025/01/01")) {
                            ymlConfigClash.set("winner1", "red");
                        }
                        if (ymlConfigClash.getString("winner2").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash2").equals("2025/01/01")) {
                            ymlConfigClash.set("winner2", "red");
                        }
                        if (ymlConfigClash.getString("winner3").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash3").equals("2025/01/01")) {
                            ymlConfigClash.set("winner3", "red");
                        }
                        if (ymlConfigClash.getString("winner4").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash4").equals("2025/01/01")) {
                            ymlConfigClash.set("winner4", "red");
                        }

                        try {
                            ymlConfigClash.save(configClash);
                        } catch (IOException v) {
                            v.printStackTrace();
                        }
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
                                File inventory = new File("plugins//Clash//Inventories2//" + all.getName() + ".yml");
                                if (inventory.exists()) {
                                    YamlConfiguration inv = YamlConfiguration.loadConfiguration(inventory);

                                    try {
                                        inv.load("plugins//Clash//Inventories//" + all.getName() + ".yml");
                                    } catch (IOException | InvalidConfigurationException e) {
                                        e.printStackTrace();
                                    }

                                    /*
                                    double health = inv.getDouble("Health");
                                    all.setHealth(health);
                                    double exp = inv.getDouble("Exp");
                                    all.setExp((float) exp);
                                    int level = inv.getInt("Level");
                                    all.setLevel(level);
                                    int hunger = inv.getInt("Hunger");
                                    all.setFoodLevel(hunger);

                                     */

                                    World world = Bukkit.getWorld("world");
                                    Double X = inv.getDouble("X");
                                    Double Y = inv.getDouble("Y");
                                    Double Z = inv.getDouble("Z");
                                    Location loc = new Location(world, X, Y, Z);
                                    all.teleport(loc);

                                    inventory.delete();

                                } else {
                                    World world = Bukkit.getWorld("world");
                                    Location location = new Location(world, 114.528, 41, -71.520, -90, -3);
                                    all.teleport(location);
                                }


                                PlayerSerialize newInv = null;
                                File f = new File("plugins//Clash//Inventories//" + all.getName() + ".playerinv");

                                if (f.isFile() && f.canRead()) {
                                    try {
                                        FileInputStream fileIn = new FileInputStream("plugins//Clash//Inventories//" + all.getName() + ".playerinv");
                                        ObjectInputStream in = new ObjectInputStream(fileIn);
                                        newInv = (PlayerSerialize) in.readObject();
                                        in.close();
                                        fileIn.close();
                                    } catch (IOException | ClassNotFoundException ignored) {
                                        return;
                                    }
                                }

                                if (newInv != null) {
                                    all.getInventory().setContents(newInv.inventory.getContents());
                                    all.setHealth(newInv.health);
                                    all.setFoodLevel((int) newInv.hunger);
                                    all.setFlySpeed((float) newInv.flySpeed);
                                    all.setWalkSpeed((float) newInv.walkSpeed);
                                    all.setLevel(newInv.levels);
                                    all.setExp((float) newInv.xp);
                                    all.setSaturation((float) newInv.saturation);
                                    all.setExhaustion((float) newInv.fatigue);
                                }


                            }
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mv delete world-clash");
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mvconfirm");
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.setGameMode(GameMode.SURVIVAL);
                            }
                            Main.getPlugin().setClashOpen(null);
                            Main.getPlugin().setClashOpen2("false");
                            configClash.delete();

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
            File inventory = new File("plugins//Clash//Inventories2//" + p.getName() + ".yml");
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
        if (e.getPlayer().getGameMode() != GameMode.SPECTATOR) {
            if (Main.getPlugin().getClashOpen() == "true") {
                File configMessages = new File("plugins//Messages//" + e.getPlayer().getUniqueId() + ".yml");
                YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);
                ymlConfigMessages.set("givebackstuff", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException v) {
                    v.printStackTrace();
                }
                if (e.getPlayer().hasPermission("wintervillage.redteam")) {
                    try {
                        ymlConfigClash.load("plugins//Clash//config.yml");
                    } catch (IOException | InvalidConfigurationException v) {
                        v.printStackTrace();
                    }
                    int tempRed = ymlConfigClash.getInt("Fighter.Red") - 1;
                    ymlConfigClash.set("Fighter.Red", tempRed);
                    try {
                        ymlConfigClash.save(configClash);
                    } catch (IOException v) {
                        v.printStackTrace();
                    }
                } else if (e.getPlayer().hasPermission("wintervillage.blueteam")) {
                    try {
                        ymlConfigClash.load("plugins//Clash//config.yml");
                    } catch (IOException | InvalidConfigurationException v) {
                        v.printStackTrace();
                    }
                    int tempBlue = ymlConfigClash.getInt("Fighter.Red") - 1;
                    ymlConfigClash.set("Fighter.Blue", tempBlue);
                    try {
                        ymlConfigClash.save(configClash);
                    } catch (IOException v) {
                        v.printStackTrace();
                    }
                }

                if (ymlConfigClash.getInt("Fighter.Red") == 0 || ymlConfigClash.getInt("Fighter.Blue") == 0) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (ymlConfigClash.getInt("Fighter.Red") == 0) {
                            st(all, "§9Village Blau", "§7hat den Clash gewonnen!", 5, 500, 5);

                            File folderClash = new File("plugins//Clash//Wins");
                            File configClash = new File("plugins//Clash//Wins//config.yml");
                            YamlConfiguration ymlConfigClash = YamlConfiguration.loadConfiguration(configClash);

                            if (!folderClash.exists()) {
                                folderClash.mkdir();
                            }
                            if (!configClash.exists()) {
                                try {
                                    configClash.createNewFile();
                                } catch (IOException v) {
                                    v.printStackTrace();
                                }
                            }

                            if (ymlConfigClash.getString("winner1").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash1").equals("2025/01/01")) {
                                ymlConfigClash.set("winner1", "blue");
                            }
                            if (ymlConfigClash.getString("winner2").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash2").equals("2025/01/01")) {
                                ymlConfigClash.set("winner2", "blue");
                            }
                            if (ymlConfigClash.getString("winner3").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash3").equals("2025/01/01")) {
                                ymlConfigClash.set("winner3", "blue");
                            }
                            if (ymlConfigClash.getString("winner4").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash4").equals("2025/01/01")) {
                                ymlConfigClash.set("winner4", "blue");
                            }

                            try {
                                ymlConfigClash.save(configClash);
                            } catch (IOException v) {
                                v.printStackTrace();
                            }
                        } else if (ymlConfigClash.getInt("Fighter.Blue") == 0) {
                            st(all, "§cVillage Rot", "§7hat den Clash gewonnen!", 5, 500, 5);

                            File folderClash = new File("plugins//Clash//Wins");
                            File configClash = new File("plugins//Clash//Wins//config.yml");
                            YamlConfiguration ymlConfigClash = YamlConfiguration.loadConfiguration(configClash);

                            if (!folderClash.exists()) {
                                folderClash.mkdir();
                            }
                            if (!configClash.exists()) {
                                try {
                                    configClash.createNewFile();
                                } catch (IOException v) {
                                    v.printStackTrace();
                                }
                            }

                            if (ymlConfigClash.getString("winner1").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash1").equals("2025/01/01")) {
                                ymlConfigClash.set("winner1", "red");
                            }
                            if (ymlConfigClash.getString("winner2").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash2").equals("2025/01/01")) {
                                ymlConfigClash.set("winner2", "red");
                            }
                            if (ymlConfigClash.getString("winner3").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash3").equals("2025/01/01")) {
                                ymlConfigClash.set("winner3", "red");
                            }
                            if (ymlConfigClash.getString("winner4").equals("null") && Main.getPlugin().getYmlConfigClash().getString("clash4").equals("2025/01/01")) {
                                ymlConfigClash.set("winner4", "red");
                            }

                            try {
                                ymlConfigClash.save(configClash);
                            } catch (IOException v) {
                                v.printStackTrace();
                            }
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
                                    File inventory = new File("plugins//Clash//Inventories2//" + all.getName() + ".yml");
                                    if (inventory.exists()) {
                                        YamlConfiguration inv = YamlConfiguration.loadConfiguration(inventory);

                                        try {
                                            inv.load("plugins//Clash//Inventories//" + all.getName() + ".yml");
                                        } catch (IOException | InvalidConfigurationException e) {
                                            e.printStackTrace();
                                        }

                                        /*
                                        double health = inv.getDouble("Health");
                                        all.setHealth(health);
                                        double exp = inv.getDouble("Exp");
                                        all.setExp((float) exp);
                                        int level = inv.getInt("Level");
                                        all.setLevel(level);
                                        int hunger = inv.getInt("Hunger");
                                        all.setFoodLevel(hunger);

                                         */


                                        World world = Bukkit.getWorld("world");
                                        Double X = inv.getDouble("X");
                                        Double Y = inv.getDouble("Y");
                                        Double Z = inv.getDouble("Z");
                                        Location loc = new Location(world, X, Y, Z);
                                        all.teleport(loc);

                                        /*List<ItemStack> list1 = (List<ItemStack>) inv.getList("Inventory");
                                        List<Integer> slot1 = (List<Integer>) inv.getList("Slot");
                                        inventory.delete();

                                        for (int j = 0; j <= slot1.size(); j++) {
                                            all.getInventory().setItem(slot1.get(j), list1.get(j));
                                            if (j == slot1.size()) {
                                                list1.clear();
                                                slot1.clear();
                                            }
                                        }

                                         */

                                    } else {
                                        World world = Bukkit.getWorld("world");
                                        Location location = new Location(world, 114.528, 41, -71.520, -90, -3);
                                        all.teleport(location);
                                    }

                                    PlayerSerialize newInv = null;

                                    File f = new File("plugins//Clash//Inventories//" + all.getName() + ".playerinv");

                                    if (f.isFile() && f.canRead()) {
                                        try {
                                            FileInputStream fileIn = new FileInputStream("plugins//Clash//Inventories//" + all.getName() + ".playerinv");
                                            ObjectInputStream in = new ObjectInputStream(fileIn);
                                            newInv = (PlayerSerialize) in.readObject();
                                            in.close();
                                            fileIn.close();
                                        } catch (IOException | ClassNotFoundException ignored) {
                                            return;
                                        }
                                    }

                                    if (newInv != null) {
                                        all.getInventory().setContents(newInv.inventory.getContents());
                                        all.setHealth(newInv.health);
                                        all.setFoodLevel((int) newInv.hunger);
                                        all.setFlySpeed((float) newInv.flySpeed);
                                        all.setWalkSpeed((float) newInv.walkSpeed);
                                        all.setLevel(newInv.levels);
                                        all.setExp((float) newInv.xp);
                                        all.setSaturation((float) newInv.saturation);
                                        all.setExhaustion((float) newInv.fatigue);
                                    }

                                }
                                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mv delete world-clash");
                                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mvconfirm");
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    all.setGameMode(GameMode.SURVIVAL);
                                }
                                Main.getPlugin().setClashOpen(null);
                                Main.getPlugin().setClashOpen2("false");
                                configClash.delete();
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
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        if (e.getView().getTitle().equals("§6Clash Übersicht")) {
            e.setCancelled(true);
        }

    }


    /*@EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        if (e.getFrom().equals(Bukkit.getWorld("world-clash")) && Main.getPlugin().getClashOpen() == "true") {

            File inventory = new File("plugins//Clash//Inventories//" + p.getName() + ".yml");
            if (inventory.exists()) {
                YamlConfiguration inv = YamlConfiguration.loadConfiguration(inventory);
                p.getInventory().clear();

                try {
                    inv.load("plugins//Clash//Inventories//" + p.getName() + ".yml");
                } catch (IOException | InvalidConfigurationException v) {
                    v.printStackTrace();
                }


                List<ItemStack> list1 = (List<ItemStack>) inv.getList("Inventory");
                List<Integer> slot1 = (List<Integer>) inv.getList("Slot");

                for (int j = 0; j <= slot1.size(); j++) {
                    p.getInventory().setItem(slot1.get(j), list1.get(j));
                    if (j == slot1.size()) {
                        list1.clear();
                        slot1.clear();
                    }
                }
            }
        }
    }
    */

    public void checkDirectory() {
        File file = new File("plugins//Clash//Inventories");
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File("plugins//Clash//Inventories2");
        if (!file2.exists()) {
            file2.mkdir();
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
