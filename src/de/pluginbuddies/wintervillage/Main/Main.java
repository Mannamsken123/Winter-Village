//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Main;

import de.pluginbuddies.wintervillage.Commands.*;
import de.pluginbuddies.wintervillage.Listener.*;
import de.pluginbuddies.wintervillage.Util.Team;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {

    private static Main plugin;
    public final String PREFIX = "§aServer " + "§8>> §r";

    public List<String> voted = new ArrayList<>();

    //putsch
    private boolean putschRot = false;
    private boolean putschBlau = false;

    //booleans for TIME CHECKER
    private String netherOpen;
    private String endOpen;
    private String adventskalenderOpen;
    private String nikolausOpen;
    private String voteOpen;
    private String voteClose;
    public HashMap<String, Integer> votesred = new HashMap<>();

    //Bürgermeister - Permisson - Vote
    public static HashMap<Player, PermissionAttachment> Bürgermeisterblue = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> Bürgermeisterred = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> RotBürger1 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> RotBürger2 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> RotBürger3 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> RotBürger4 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> RotBürger5 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> RotBürger6 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> RotBürger7 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> RotBürger8 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> RotBürger9 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> RotBürger10 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> BlauBürger1 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> BlauBürger2 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> BlauBürger3 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> BlauBürger4 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> BlauBürger5 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> BlauBürger6 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> BlauBürger7 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> BlauBürger8 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> BlauBürger9 = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> BlauBürger10 = new HashMap<Player, PermissionAttachment>();
    public HashMap<String, Integer> votesblue = new HashMap<>();
    public List<String> namesred = new ArrayList<>();
    public List<String> namesblue = new ArrayList<>();
    BürgermeisterVoteCommand bvc = new BürgermeisterVoteCommand();

    public static Main getPlugin() {
        return plugin;
    }

    //Bürgermeister - Permisson - Vote end

    //teams
    static File configteams = new File("plugins//Teams//config.yml");
    public static YamlConfiguration ymlConfigteams = YamlConfiguration.loadConfiguration(configteams);

    //blockportals
    static File configBlockPortal = new File("plugins//BlockPortal//config.yml");

    public static File getConfigBlockPortal() {
        return configBlockPortal;
    }
    public static void setConfigBlockPortal(File configBlockPortal) {
        Main.configBlockPortal = configBlockPortal;
    }
    public static YamlConfiguration ymlConfigBlockPortal = YamlConfiguration.loadConfiguration(configBlockPortal);
    public static YamlConfiguration getYmlConfigBlockPortal() {
        return ymlConfigBlockPortal;
    }
    public static void setYmlConfigBlockPortal(YamlConfiguration ymlConfigBlockPortal) {
        Main.ymlConfigBlockPortal = ymlConfigBlockPortal;
    }
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

    public String getNetherOpen() {
        return netherOpen;
    }

    public void setNetherOpen(String netherOpen) {
        this.netherOpen = netherOpen;
    }

    public String getAdventskalenderOpen() {
        return adventskalenderOpen;
    }

    public void setAdventskalenderOpen(String adventskalenderOpen) {
        this.adventskalenderOpen = adventskalenderOpen;
    }

    public String getEndOpen() {
        return endOpen;
    }

    public void setEndOpen(String endOpen) {
        this.endOpen = endOpen;
    }

    public String getNikolausOpen() {
        return nikolausOpen;
    }

    public void setNikolausOpen(String nikolausOpen) {
        this.nikolausOpen = nikolausOpen;
    }

    public String getVoteOpen() {
        return voteOpen;
    }

    public void setVoteOpen(String voteOpen) {
        this.voteOpen = voteOpen;
    }

    public String getVoteClose() {
        return voteClose;
    }

    public void setVoteClose(String voteClose) {
        this.voteClose = voteClose;
    }
    //end booleans for TIME CHECKER

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

    private void load() {

        Bürgermeisterred.clear();
        Bürgermeisterblue.clear();
        Team.maketeams();

        Team.sb = Bukkit.getScoreboardManager().getNewScoreboard();

    }

    @Override
    public void onEnable() {
        plugin = this;

        //voteing

        for (int i = 1; i <= 10; i++) {
            String name = getName(ymlConfigteams.getString("Rot." + i)).toLowerCase();

            if (name != "") {
                namesred.add(name);
            }

        }
        for (int i = 1; i <= 10; i++) {
            String name = getName(ymlConfigteams.getString("Blau." + i)).toLowerCase();
            if (name != "") {
                namesblue.add(name);
            }
        }

        for (String all : namesred) {
            votesred.put(all, 0);
        }
        for (String all : namesblue) {
            votesblue.put(all, 0);
        }

        //VOTEING ENDs

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

                    //ADVENTSKALENDER ÖFFNEN
                    Date adventskalenderDate = null;
                    String adventskalender = "2020/12/01";
                    try {
                        adventskalenderDate = sdf.parse(adventskalender);
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

                    //VOTE 1 CLOSE
                    Date vote1closeDate = null;
                    String vote1close = "2020/11/27";
                    try {
                        vote1closeDate = sdf.parse(vote1close);
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
                        if (getNetherOpen() == null) {
                            setNetherOpen("true");
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
                                        block.setType(Material.AIR);
                                    }
                                }
                            }
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);
                                String xxx = ymlConfigMessages.getString("Nether");

                                if (xxx.equals("false")) {
                                    ymlConfigMessages.set("Nether", "true");
                                    all.sendMessage(PREFIX + "§bDer Nether kann ab jetzt über das Portal am Spawn betreten werden!");
                                    try {
                                        ymlConfigMessages.save(configMessages);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }

                    if (!adventskalenderDate.after(currentDate)) {
                        if (getAdventskalenderOpen() == null) {
                            setAdventskalenderOpen("true");

                            for (Player all : Bukkit.getOnlinePlayers()) {
                                File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

                                String xxx = ymlConfigMessages.getString("Adventskalender");

                                if (xxx.equals("false")) {
                                    ymlConfigMessages.set("Adventskalender", "true");
                                    all.sendMessage(PREFIX + "§bDu kannst ab jetzt mit §r/advent §bjeden Tag dein Adventskalender-Türchen öffnen!");
                                    try {
                                        ymlConfigMessages.save(configMessages);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }

                    if (!endDate.after(currentDate)) {
                        if (getEndOpen() == null) {
                            setEndOpen("true");

                            for (Player all : Bukkit.getOnlinePlayers()) {
                                File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

                                String xxx = ymlConfigMessages.getString("End");

                                if (xxx.equals("false")) {
                                    ymlConfigMessages.set("End", "true");
                                    all.sendMessage(PREFIX + "§bDas End kann ab jetzt über die Farmwelt betreten werden!");
                                    try {
                                        ymlConfigMessages.save(configMessages);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }

                    if (!nikolausDate.after(currentDate)) {
                        if (getNikolausOpen() == null) {
                            setNikolausOpen("true");
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

                                String xxx = ymlConfigMessages.getString("Nikolaus");

                                if (xxx.equals("false")) {
                                    ymlConfigMessages.set("Nikolaus", "true");
                                    //all.sendMessage(PREFIX + "§bNikolaus");
                                    try {
                                        ymlConfigMessages.save(configMessages);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            //set boolean to true for  double ore listener or other stuff
                        }
                    }

                    if (!vote1Date.after(currentDate) || !vote2Date.after(currentDate) || !vote3Date.after(currentDate) || !vote4Date.after(currentDate)) {
                        if (getVoteOpen() == null) {
                            //vote open

                            //PENIS FIX DATUM UNENDLICHER LOOP  GETTER SETTER für Hardcode Datum ->Zukunft


                            if (getVoteClose() != null) {
                                setVoteClose(null);
                            }
                            setVoteOpen("true");
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

                                String xxx = ymlConfigMessages.getString("VoteOpen");

                                if (xxx.equals("false")) {
                                    ymlConfigMessages.set("VoteOpen", "true");
                                    all.sendMessage(PREFIX + "§bEs kann ab jetzt ein neuer Bürgermeister gewählt werden! Nutze §r/vote <Name>§b.");
                                    try {
                                        ymlConfigMessages.save(configMessages);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }

                    if (!vote1closeDate.after(currentDate) || !vote2closeDate.after(currentDate) || !vote3closeDate.after(currentDate) || !vote4closeDate.after(currentDate)) {
                        if (getVoteClose() == null) {
                            //vote close
                            setVoteOpen(null);
                            setVoteClose("true");

                            bvc.getResult();
                            String winnerblau = bvc.getrEb();
                            String winnerrot = bvc.getrEr();
                            if (!bvc.getrEr().isEmpty()) {
                                ymlConfigteams.set("RotMeister.1", getUuid(winnerrot));
                            }
                            if (!bvc.getrEb().isEmpty()) {
                                ymlConfigteams.set("BlauMeister.1", getUuid(winnerblau));
                            }
                            try {
                                ymlConfigteams.save(configteams);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            for (Player all : Bukkit.getOnlinePlayers()) {
                                File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

                                String xxx = ymlConfigMessages.getString("VoteClose");

                                if (xxx.equals("false")) {
                                    ymlConfigMessages.set("VoteClose", "true");
                                    all.sendMessage(PREFIX + "§bNeue Bürgermeister wurden gewählt! \n§4RotMeister: §7" + Bukkit.getPlayer(getUuid(winnerrot)).getName() + "\n§1BlauMeister: §7" + Bukkit.getPlayer(getUuid(winnerrot)).getName());
                                    try {
                                        ymlConfigMessages.save(configMessages);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
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
        getCommand("meet").setExecutor(new MeetVillage());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(prisonCommand, this);
        pluginManager.registerEvents(new AdventskalenderCommand(), this);
        pluginManager.registerEvents(new AdventskalenderListener(), this);
        pluginManager.registerEvents(new BlockPortalListener(), this);
        pluginManager.registerEvents(new ChatColorListener(), this);
        pluginManager.registerEvents(new VillageCommand(), this);
        pluginManager.registerEvents(new DeathListener(), this);

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

        //messages
        File folderMessages = new File("plugins//Messages");
        if (!folderMessages.exists()) {
            folderMessages.mkdir();
        }

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
        ymlConfigAdvent.addDefault("Reward.8", "minecraft::enchanted_book{StoredEnchantments:[{id:'minecraft:frost_walker',lvl:2}]} 1");
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
        ymlConfigAdvent.addDefault("Reward.20", "minecraft:gold_ingot 42");
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

        //teams
        File folderTeams = new File("plugins//Teams");
        if (!folderTeams.exists()) {
            folderTeams.mkdir();
        }

        try {
            configteams.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ymlConfigteams.set("Rot.1", "a48f82c1-d0e3-4d59-bad1-92a4dc8dd02c");//Mulli
        ymlConfigteams.set("Rot.2", "");//Maxi
        ymlConfigteams.set("Rot.3", "7543d7d1-1ccd-4b4f-89ef-e25c1f1f9341");//Tim
        ymlConfigteams.set("Rot.4", "105b02c7-9004-45ed-a668-971359021f82");//Marc
        ymlConfigteams.set("Rot.5", "");
        ymlConfigteams.set("Rot.6", "");
        ymlConfigteams.set("Rot.7", "");
        ymlConfigteams.set("Rot.8", "");
        ymlConfigteams.set("Rot.9", "");
        ymlConfigteams.set("Rot.10", "");
        ymlConfigteams.set("Blau.1", "309f61d4-d7dd-4449-aa1d-13e212946920");//Fabio
        ymlConfigteams.set("Blau.2", "914dc737-befe-46dd-8246-4a352c0ecb62");//Julian
        ymlConfigteams.set("Blau.3", "2c13d227-9811-48e7-a15d-0450e624c1d4");//Cedric
        ymlConfigteams.set("Blau.4", "8c500465-dcdd-4a5f-829c-3c34fe1d1904");//Vito
        ymlConfigteams.set("Blau.5", "666d78a6-c431-474b-bd80-9498e0c58923");//Janni
        ymlConfigteams.set("Blau.6", "2feb1630-f1ca-4400-938d-09349fccf5de");//Anton
        ymlConfigteams.set("Blau.7", "");
        ymlConfigteams.set("Blau.8", "");
        ymlConfigteams.set("Blau.9", "");
        ymlConfigteams.set("Blau.10", "");

        if (!bvc.getrEr().isEmpty()) {
            ymlConfigteams.set("RotMeister.1", getUuid(bvc.getrEr()));
        } else {
            ymlConfigteams.set("RotMeister.1", "95ec2fa6-10cc-4311-be3b-c346153c6bd3");
        }
        if (!bvc.getrEb().isEmpty()) {
            ymlConfigteams.set("BlauMeister.1", getUuid(bvc.getrEb()));
        } else {
            ymlConfigteams.set("BlauMeister.1", "");
        }

        try {
            ymlConfigteams.save(configteams);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //end teams


        load();
    }

    public String getUuid(String name) {
        String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(url));
            if (UUIDJson.isEmpty()) return "invalid name";
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            return UUIDObject.get("id").toString();
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        return "error";
    }


}
