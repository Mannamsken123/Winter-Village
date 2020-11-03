//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Main;

import de.pluginbuddies.wintervillage.Commands.*;
import de.pluginbuddies.wintervillage.Listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {

    private static Main plugin;
    public final String PREFIX = "§aServer " + "§8>> §r";

    //Bürgermeister - Permisson - Vote
    public static HashMap<Player, PermissionAttachment> Bürgermeister = new HashMap<Player, PermissionAttachment>();
    public List<String> voted = new ArrayList<>();
    public HashMap<String, Integer> votes = new HashMap<>();
    public List<String> names = new ArrayList<>();

    //Bürgermeister - Permisson - Vote end

    public static Main getPlugin() {
        return plugin;
    }

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

    @Override
    public void onEnable() {
        plugin = this;

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
