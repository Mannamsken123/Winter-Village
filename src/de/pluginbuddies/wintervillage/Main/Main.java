//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Main;

import de.pluginbuddies.wintervillage.Commands.*;
import de.pluginbuddies.wintervillage.Listener.BlockPortalListener;
import de.pluginbuddies.wintervillage.Listener.JoinListener;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {

    private static Main plugin;
    public final String PREFIX = "§aServer " + "§8>> §r";

    //putsch
    private boolean putschRot = false;
    private boolean putschBlau = false;
    //booleans for TIME CHECKER
    private boolean netherOpen = false;
    private boolean endOpen = false;
    private boolean nikolausOpen = false;
    private boolean voteOpen = false;

    //Bürgermeister - Permisson - Vote
    public static HashMap<Player, PermissionAttachment> Bürgermeister = new HashMap<Player, PermissionAttachment>();
    public List<String> voted = new ArrayList<>();
    public HashMap<String, Integer> votes = new HashMap<>();
    public List<String> names = new ArrayList<>();

    //Bürgermeister - Permisson - Vote end

    public static Main getPlugin() {
        return plugin;
    }

    //blockportals
    static File configBlockPortal = new File("plugins//BlockPortal//config.yml");
    public static YamlConfiguration ymlConfigBlockPortal = YamlConfiguration.loadConfiguration(configBlockPortal);
    //end blockportals

    //adventskalender
    static File fileAdvent = new File("plugins//Adventskalender//data.yml");
    static YamlConfiguration ymlFileAdvent = YamlConfiguration.loadConfiguration(fileAdvent);
    static File configAdvent = new File("plugins//Adventskalender//config.yml");
    public static YamlConfiguration ymlConfigAdvent = YamlConfiguration.loadConfiguration(configAdvent);

    public static void setUsed(String UUID, int Day) {
        ymlFileAdvent.set(UUID + ".day" + Day, true);
        try {
            ymlFileAdvent.save(fileAdvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasUsed(String UUID, int Day) {
        if (ymlFileAdvent.getString(UUID + ".day" + Day) != null) {
            if (ymlFileAdvent.getBoolean(UUID + ".day" + Day) == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //end adventskalender
    private boolean voteClose = false;

    public boolean getPutschRot() {
        return putschRot;
    }

    public void setPutschRot(boolean putschRot) {
        this.putschRot = putschRot;
    }

    public boolean getPutschBlau() {
        return putschBlau;
    }

    public void setPutschBlau(boolean putschBlau) {
        this.putschBlau = putschBlau;
    }

    public boolean getNetherOpen() {
        return netherOpen;
    }

    public void setNetherOpen(boolean netherOpen) {
        this.netherOpen = netherOpen;
    }

    public boolean getEndOpen() {
        return endOpen;
    }

    public void setEndOpen(boolean endOpen) {
        this.endOpen = endOpen;
    }

    public boolean getNikolausOpen() {
        return nikolausOpen;
    }

    public void setNikolausOpen(boolean nikolausOpen) {
        this.nikolausOpen = nikolausOpen;
    }

    public boolean getVoteOpen() {
        return voteOpen;
    }

    public void setVoteOpen(boolean voteOpen) {
        this.voteOpen = voteOpen;
    }

    public boolean getVoteClose() {
        return voteClose;
    }

    public void setVoteClose(boolean voteClose) {
        this.voteClose = voteClose;
    }
    //end booleans for TIME CHECKER

    @Override
    public void onEnable() {
        plugin = this;

        //voteing
        names.add("mullemann25");
        names.add("mannam01");

        for (String all : names) {
            votes.put(all, 0);
        }

        Bukkit.getWorld("world").setDifficulty(Difficulty.PEACEFUL);
        PrisonCommand prisonCommand = new PrisonCommand();

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setGameMode(GameMode.SURVIVAL);
            all.sendMessage("§aServer " + "§8>> " + "§aPlugin geladen.");
        }

        //TIME CHECKER

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size() != 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

                    //NETHER ÖFFNEN && CLOSE VOTE 1
                    Date netherDate = null;
                    String nether = "2020/11/27";
                    try {
                        netherDate = sdf.parse(nether);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //END ÖFFNEN
                    Date endDate = null;
                    String end = "2020/12/01";
                    try {
                        endDate = sdf.parse(end);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //Nikolaus
                    Date nikolausDate = null;
                    String nikolaus = "2020/12/06";
                    try {
                        nikolausDate = sdf.parse(nikolaus);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 1
                    Date vote1Date = null;
                    String vote1 = "2020/11/26";
                    try {
                        vote1Date = sdf.parse(vote1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 2
                    Date vote2Date = null;
                    String vote2 = "2020/12/04";
                    try {
                        vote2Date = sdf.parse(vote2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 2 CLOSE
                    Date vote2closeDate = null;
                    String vote2close = "2020/12/05";
                    try {
                        vote2closeDate = sdf.parse(vote2close);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 3
                    Date vote3Date = null;
                    String vote3 = "2020/12/12";
                    try {
                        vote3Date = sdf.parse(vote3);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 3 CLOSE
                    Date vote3closeDate = null;
                    String vote3close = "2020/12/13";
                    try {
                        vote3closeDate = sdf.parse(vote3close);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 4
                    Date vote4Date = null;
                    String vote4 = "2020/12/20";
                    try {
                        vote4Date = sdf.parse(vote4);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 4 CLOSE
                    Date vote4closeDate = null;
                    String vote4close = "2020/12/21";
                    try {
                        vote4closeDate = sdf.parse(vote4close);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //putsch -> vote sys
                    if (getPutschRot() == true) {
                    }

                    if (getPutschBlau() == true) {
                    }

                    Date currentDate = new Date();

                    if (!netherDate.after(currentDate)) {
                        setNetherOpen(true);
                        Bukkit.broadcastMessage(PREFIX + "§bDer Nether kann ab jetzt über das Portal am Spawn betreten werden!");
                        World world = Bukkit.getWorld("world");
                        Location loc1 = new Location(world, 129, 40, -77);
                        Location loc2 = new Location(world, 129, 44, -83);
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
                    }

                    if (!endDate.after(currentDate)) {
                        setEndOpen(true);
                        Bukkit.broadcastMessage(PREFIX + "§bDas End kann ab jetzt über die Farmwelt betreten werden!");
                        //open worldguard file and edit that you can go into area endportal
                    }

                    if (!nikolausDate.after(currentDate)) {
                        //nikolaus
                        //set boolean to true for  double ore listener or other stuff
                    }

                    if (!vote1Date.after(currentDate) || !vote2Date.after(currentDate) || !vote3Date.after(currentDate) || !vote4Date.after(currentDate)) {
                        //vote open
                        setVoteOpen(true);
                        Bukkit.broadcastMessage(PREFIX + "§bEs kann ab jetzt ein neuer Bürgermeister gewählt werden! Nutze §r/vote§b.");
                    }


                    if (!endDate.after(currentDate) || !vote2closeDate.after(currentDate) || !vote3closeDate.after(currentDate) || !vote4closeDate.after(currentDate)) {
                        //vote close
                        setVoteOpen(false);
                        setVoteClose(true);
                    }

                }
            }
        }, 100, 600 * 20);
        //END TIME CHECKER

        getCommand("gm").setExecutor(new GamemodesCommand());
        getCommand("gm").setTabCompleter(new GamemodesTabComplete());
        getCommand("village").setExecutor(new VillageCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("prison").setExecutor(prisonCommand);
        getCommand("adventskalender").setExecutor(new AdventskalenderCommand());
        getCommand("vote").setExecutor(new BürgermeisterVoteCommand());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(prisonCommand, this);
        pluginManager.registerEvents(new AdventskalenderCommand(), this);
        pluginManager.registerEvents(new BlockPortalListener(), this);

        //blockportals
        File folderBlockPortal = new File("plugins//BlockPortal");

        if (!folderBlockPortal.exists()) {
            folderBlockPortal.mkdir();
        }
        if (!configBlockPortal.exists()) {
            try {
                configBlockPortal.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ymlConfigBlockPortal.options().copyDefaults(true);
        ymlConfigBlockPortal.addDefault("EndSpawn", "false");

        try {
            ymlConfigBlockPortal.save(configBlockPortal);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //end blockportals

        //adventkalender
        File folderAdvent = new File("plugins//Adventskalender");
        if (!folderAdvent.exists()) {
            folderAdvent.mkdir();
        }
        if (!fileAdvent.exists()) {
            try {
                fileAdvent.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!configAdvent.exists()) {
            try {
                configAdvent.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ymlConfigAdvent.options().copyDefaults(true);
        ymlConfigAdvent.addDefault("Reward.1", "minecraft:diamond 3");
        ymlConfigAdvent.addDefault("Reward.2", "minecraft:cookie 53");
        ymlConfigAdvent.addDefault("Reward.3", "minecraft:iron_ingot 21");
        ymlConfigAdvent.addDefault("Reward.4", "minecraft:anvil 1");
        ymlConfigAdvent.addDefault("Reward.5", "minecraft:enchanted_book{StoredEnchantments:[{id:'minecraft:power',lvl:2}]} 1");
        ymlConfigAdvent.addDefault("Reward.6", "minecraft::netherite_scrap 2");
        ymlConfigAdvent.addDefault("Reward.7", "minecraft:minecraft:turtle_helmet 1");
        ymlConfigAdvent.addDefault("Reward.8", "minecraft::enchanted_book{StoredEnchantments:[{id:'minecraft:frost_walker',lvl:1}]} 2");
        ymlConfigAdvent.addDefault("Reward.9", "minecraft::experience_bottle 41");
        ymlConfigAdvent.addDefault("Reward.10", "minecraft:horse_spawn_egg 1");
        ymlConfigAdvent.addDefault("Reward.11", "minecraft:minecraft:name_tag 1");
        ymlConfigAdvent.addDefault("Reward.12", "minecraft:saddle 1");
        ymlConfigAdvent.addDefault("Reward.13", "minecraft:slime_ball 22");
        ymlConfigAdvent.addDefault("Reward.14", "minecraft:wolf_spawn_egg 1");
        ymlConfigAdvent.addDefault("Reward.15", "minecraft:iron_horse_armor 1");
        ymlConfigAdvent.addDefault("Reward.16", "minecraft:enchanting_table 1");
        ymlConfigAdvent.addDefault("Reward.17", "minecraft:enchanted_book{StoredEnchantments:[{id:'minecraft:sharpness',lvl:3}]} 1");
        ymlConfigAdvent.addDefault("Reward.18", "minecraft:firework_rocket 64");
        ymlConfigAdvent.addDefault("Reward.19", "minecraft:enchanted_book{StoredEnchantments:[{id:'minecraft:mending',lvl:3}]} 1");
        ymlConfigAdvent.addDefault("Reward.20", "minecraft:elytra 1");
        ymlConfigAdvent.addDefault("Reward.21", "minecraft:diamond 12");
        ymlConfigAdvent.addDefault("Reward.22", "minecraft:enchanted_book{StoredEnchantments:[{id:'minecraft:efficiency',lvl:5}]} 1");
        ymlConfigAdvent.addDefault("Reward.23", "minecraft:enchanted_golden_apple 1");
        ymlConfigAdvent.addDefault("Reward.24", "minecraft:diamond 64");

        try {
            ymlConfigAdvent.save(configAdvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //end adventskalender
    }
}
