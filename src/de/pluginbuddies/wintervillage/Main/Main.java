//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Main;

import de.pluginbuddies.wintervillage.Commands.*;
import de.pluginbuddies.wintervillage.Listener.*;
import de.pluginbuddies.wintervillage.Util.Clash;
import de.pluginbuddies.wintervillage.Util.TabList;
import de.pluginbuddies.wintervillage.Util.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    //putsch
    private boolean putschRot = false;
    private boolean putschBlau = false;

    //TravelWithEntity
    private boolean travelWithEntity = false;

    public boolean getTravelWithEntity() {
        return travelWithEntity;
    }

    public void setTravelWithEntity(boolean travelWithEntity) {
        this.travelWithEntity = travelWithEntity;
    }

    private boolean travelUse = false;
    private Player current;

    public boolean getTravelUse() {
        return travelUse;
    }

    public void setTravelUse(boolean travelUse) {
        this.travelUse = travelUse;
    }

    public Player getCurrent() {
        return current;
    }

    public void setCurrent(Player current) {
        this.current = current;
    }

    //booleans for TIME CHECKER
    private String netherOpen;
    private String endOpen;
    private String adventskalenderOpen;
    private String nikolausOpen;
    private String voteOpen;
    private String voteClose;
    //clash
    static File configClash = new File("plugins//Clash//Dates//config.yml");
    public static YamlConfiguration ymlConfigClash = YamlConfiguration.loadConfiguration(configClash);
    private String clashOpen;
    private String clashOpen2 = "false";

    public String getClashOpen2() {
        return clashOpen2;
    }

    public void setClashOpen2(String clashOpen2) {
        this.clashOpen2 = clashOpen2;
    }

    private String clash1 = getYmlConfigClash().getString("clash1");
    private String clash2 = getYmlConfigClash().getString("clash2");

    //Bürgermeister - Permisson - Vote
    static File configVote = new File("plugins//Vote//config.yml");
    public static YamlConfiguration ymlConfigVote = YamlConfiguration.loadConfiguration(configVote);

    static File votesRed = new File("plugins//Vote//votesred.yml");
    public static YamlConfiguration ymlvotesRed = YamlConfiguration.loadConfiguration(votesRed);
    static File votesBlue = new File("plugins//Vote//votesblue.yml");
    public static YamlConfiguration ymlvotesBlue = YamlConfiguration.loadConfiguration(votesBlue);
    static File hasVoted = new File("plugins//Vote//voted.yml");
    public static YamlConfiguration ymlHasVoted = YamlConfiguration.loadConfiguration(hasVoted);

    static File putschVotesRed = new File("plugins//Vote//putschvotesred.yml");
    public static YamlConfiguration ymlPutschVotesRed = YamlConfiguration.loadConfiguration(putschVotesRed);
    static File putschVotesBlue = new File("plugins//Vote//putschvotesblue.yml");
    public static YamlConfiguration ymlPutschVotesBlue = YamlConfiguration.loadConfiguration(putschVotesBlue);
    static File putschHasVoted = new File("plugins//Vote//putschvoted.yml");
    public static YamlConfiguration ymlputschHasVoted = YamlConfiguration.loadConfiguration(putschHasVoted);

    public static HashMap<Player, PermissionAttachment> Buergermeisterblue = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> Buergermeisterred = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> RotBuerger = new HashMap<Player, PermissionAttachment>();
    public static HashMap<Player, PermissionAttachment> BlauBuerger = new HashMap<Player, PermissionAttachment>();

    /*public HashMap<String, Integer> votesblue = new HashMap<>();
    public HashMap<String, Integer> votesred = new HashMap<>();
    public List<String> voted = new ArrayList<>();*/

    public List<String> namesred = new ArrayList<>();
    public List<String> namesblue = new ArrayList<>();
    BürgermeisterVoteCommand bvc = new BürgermeisterVoteCommand();

    public static Main getPlugin() {
        return plugin;
    }

    //Bürgermeister - Permisson - Vote end

    //teams
    public static File configteams = new File("plugins//Teams//config.yml");
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

    //fix dates
    private String vote1 = getYmlConfigVote().getString("vote1");
    private String vote1close = getYmlConfigVote().getString("vote1close");
    private String vote2 = getYmlConfigVote().getString("vote2");
    private String vote2close = getYmlConfigVote().getString("vote2close");
    private String vote3 = getYmlConfigVote().getString("vote3");
    private String vote3close = getYmlConfigVote().getString("vote3close");
    private String vote4 = getYmlConfigVote().getString("vote4");
    private String vote4close = getYmlConfigVote().getString("vote4close");
    private String clash3 = getYmlConfigClash().getString("clash3");
    private String clash4 = getYmlConfigClash().getString("clash4");

    public static YamlConfiguration getYmlConfigClash() {
        return ymlConfigClash;
    }

    public static void setYmlConfigClash(YamlConfiguration ymlConfigClash) {
        Main.ymlConfigClash = ymlConfigClash;
    }

    public static File getConfigClash() {
        return configClash;
    }

    public static void setConfigClash(File configClash) {
        Main.configClash = configClash;
    }

    public String getClashOpen() {
        return clashOpen;
    }

    public void setClashOpen(String clashOpen) {
        this.clashOpen = clashOpen;
    }

    public String getClash1() {
        return clash1;
    }

    public void setClash1(String clash1) {
        this.clash1 = clash1;
    }

    public String getClash2() {
        return clash2;
    }

    public void setClash2(String clash2) {
        this.clash2 = clash2;
    }

    public String getClash3() {
        return clash3;
    }

    public void setClash3(String clash3) {
        this.clash3 = clash3;
    }

    public String getClash4() {
        return clash4;
    }

    public void setClash4(String clash4) {
        this.clash4 = clash4;
    }

    public static File getConfigVote() {
        return configVote;
    }

    public static void setConfigVote(File configVote) {
        Main.configVote = configVote;
    }

    public static YamlConfiguration getYmlConfigVote() {
        return ymlConfigVote;
    }

    public static void setYmlConfigVote(YamlConfiguration ymlConfigVote) {
        Main.ymlConfigVote = ymlConfigVote;
    }

    public String getVote1() {
        return vote1;
    }

    public void setVote1(String vote1) {
        this.vote1 = vote1;
    }

    public String getVote1close() {
        return vote1close;
    }

    public void setVote1close(String vote1close) {
        this.vote1close = vote1close;
    }

    public String getVote2() {
        return vote2;
    }

    public void setVote2(String vote2) {
        this.vote2 = vote2;
    }

    public String getVote2close() {
        return vote2close;
    }

    public void setVote2close(String vote2close) {
        this.vote2close = vote2close;
    }

    public String getVote3() {
        return vote3;
    }

    public void setVote3(String vote3) {
        this.vote3 = vote3;
    }

    public String getVote3close() {
        return vote3close;
    }

    public void setVote3close(String vote3close) {
        this.vote3close = vote3close;
    }

    public String getVote4() {
        return vote4;
    }

    public void setVote4(String vote4) {
        this.vote4 = vote4;
    }

    public String getVote4close() {
        return vote4close;
    }

    public void setVote4close(String vote4close) {
        this.vote4close = vote4close;
    }

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

    private Inventory backpackRed = Bukkit.createInventory(null, 9 * 3, "§cVillage Backpack");
    private Inventory backpackBlue = Bukkit.createInventory(null, 9 * 3, "§9Village Backpack");

    public Inventory getBackpackRed() {
        return backpackRed;
    }

    public Inventory getBackpackBlue() {
        return backpackBlue;
    }

    @Override
    public void onEnable() {
        plugin = this;

        backpackRed.clear();
        File file = new File("plugins//Backpacks//red.yml");

        if (file.exists()) {
            YamlConfiguration inv = YamlConfiguration.loadConfiguration(file);
            List<?> list = inv.getList("Backpack");
            List<?> slot = inv.getList("Slot");

            for (int i = 0; i < list.size(); i++) {
                Bukkit.broadcastMessage("Hallo");
                backpackRed.setItem((Integer) slot.get(i), (ItemStack) list.get(i));
            }
        }
        backpackBlue.clear();
        File file2 = new File("plugins//Backpacks//blue.yml");
        if (file2.exists()) {
            YamlConfiguration inv = YamlConfiguration.loadConfiguration(file2);
            List<?> list = inv.getList("Backpack");
            List<?> slot = inv.getList("Slot");

            for (int i = 0; i < list.size(); i++) {
                backpackBlue.setItem((Integer) slot.get(i), (ItemStack) list.get(i));
            }
        }

        TabList teams = new TabList();

        teams.create("redteam", 11, "§cRot: §r", null, "wintervillage.redteam");
        teams.create("blueteam", 21, "§9Blau: §r", null, "wintervillage.blueteam");
        teams.create("prisonred", 10, "§4RotMeister: §r", null, "wintervillage.prisonred");
        teams.create("prisonblue", 20, "§1BlauMeister: §r", null, "wintervillage.prisonblue");

        teams.update();

        namesred.clear();
        namesblue.clear();

        //voteing


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
        ymlConfigteams.options().copyDefaults(true);
        ymlConfigteams.addDefault("Rot.1", "a48f82c1-d0e3-4d59-bad1-92a4dc8dd02c");//Mulli
        ymlConfigteams.addDefault("Rot.2", "95ec2fa6-10cc-4311-be3b-c346153c6bd3");//Maxi
        ymlConfigteams.addDefault("Rot.3", "7543d7d1-1ccd-4b4f-89ef-e25c1f1f9341");//Tim
        ymlConfigteams.addDefault("Rot.4", "105b02c7-9004-45ed-a668-971359021f82");//Marc
        ymlConfigteams.addDefault("Rot.5", "ed85c100-78c1-46b9-85c1-0c902e2352f0");//Valentin
        ymlConfigteams.addDefault("Rot.6", "e466347d-6c55-4619-bdd9-7404f2bc874b");//Chris
        ymlConfigteams.addDefault("Rot.7", "598fc90a-ffe6-4500-a3c4-6a1207e09812");//Nils
        ymlConfigteams.addDefault("Rot.8", "558393f4-4a5b-4615-b780-1ebf2d8f2e9d");//Julius
        ymlConfigteams.addDefault("Rot.9", "a93bfa25-c754-4ea2-90ef-4bac3115febe");//Max Marten
        ymlConfigteams.addDefault("Rot.10", "0e36b1ea-d12d-47c7-974d-7ce39058fbe0");//Freddy
        ymlConfigteams.addDefault("Blau.1", "309f61d4-d7dd-4449-aa1d-13e212946920");//Fabio
        ymlConfigteams.addDefault("Blau.2", "914dc737-befe-46dd-8246-4a352c0ecb62");//Julian
        ymlConfigteams.addDefault("Blau.3", "2c13d227-9811-48e7-a15d-0450e624c1d4");//Cedric
        ymlConfigteams.addDefault("Blau.4", "8c500465-dcdd-4a5f-829c-3c34fe1d1904");//Vito
        ymlConfigteams.addDefault("Blau.5", "666d78a6-c431-474b-bd80-9498e0c58923");//Janni
        ymlConfigteams.addDefault("Blau.6", "2feb1630-f1ca-4400-938d-09349fccf5de");//Anton
        ymlConfigteams.addDefault("Blau.7", "438febad-85db-4720-be25-49fe22c8d0cc");//Tobi
        ymlConfigteams.addDefault("Blau.8", "896088f9-d998-49ce-a743-b566c478a1e6");//Friedrich
        ymlConfigteams.addDefault("Blau.9", "b1de1149-e8d6-4104-a003-cbde6bd4aa64");//Jannis
        ymlConfigteams.addDefault("Blau.10", "f033f53b-f8c4-4e75-817a-1db8b0cf93a5");//Tilo
        ymlConfigteams.addDefault("RotMeister.1", "");
        ymlConfigteams.addDefault("BlauMeister.1", "");

        try {
            ymlConfigteams.save(configteams);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //end teams


        for (int i = 1; i <= 10; i++) {
            if (!ymlConfigteams.getString("Rot." + i).isEmpty()) {
                namesred.add(getName(ymlConfigteams.getString("Rot." + i)).toLowerCase());
            }
        }
        for (int i = 1; i <= 10; i++) {
            if (!ymlConfigteams.getString("Blau." + i).isEmpty()) {
                namesblue.add(getName(ymlConfigteams.getString("Blau." + i)).toLowerCase());
            }
        }

        //VOTEING ENDs

        PrisonCommand prisonCommand = new PrisonCommand();
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
                    try {
                        vote1Date = sdf.parse(getVote1());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 1 CLOSE
                    Date vote1closeDate = null;
                    try {
                        vote1closeDate = sdf.parse(getVote1close());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 2
                    Date vote2Date = null;
                    try {
                        vote2Date = sdf.parse(getVote2());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 2 CLOSE
                    Date vote2closeDate = null;
                    try {
                        vote2closeDate = sdf.parse(getVote2close());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 3
                    Date vote3Date = null;
                    try {
                        vote3Date = sdf.parse(getVote3());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 3 CLOSE
                    Date vote3closeDate = null;
                    try {
                        vote3closeDate = sdf.parse(getVote3close());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 4
                    Date vote4Date = null;
                    try {
                        vote4Date = sdf.parse(getVote4());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //VOTE 4 CLOSE
                    Date vote4closeDate = null;
                    try {
                        vote4closeDate = sdf.parse(getVote4close());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //CLASH 1
                    Date clash1Date = null;
                    try {
                        clash1Date = sdf.parse(getClash1());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //CLASH 2
                    Date clash2Date = null;
                    try {
                        clash2Date = sdf.parse(getClash2());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //CLASH 3
                    Date clash3Date = null;
                    try {
                        clash3Date = sdf.parse(getClash3());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //CLASH 4
                    Date clash4Date = null;
                    try {
                        clash4Date = sdf.parse(getClash4());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Date currentDate = new Date();

                    if (!netherDate.after(currentDate)) {
                        if (getNetherOpen() == null) {
                            setNetherOpen("true");
                            World world = Bukkit.getWorld("world");
                            Location loc1 = new Location(world, 129, 39, -78);
                            Location loc2 = new Location(world, 129, 43, -82);
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
                                    all.sendMessage(PREFIX + "§3Der Nether kann ab jetzt über das Portal am Spawn betreten werden!");
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
                                    all.sendMessage(PREFIX + "§3Du kannst ab jetzt mit §r/advent §3jeden Tag dein Adventskalender-Türchen öffnen!");
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
                                    all.sendMessage(PREFIX + "§3Das End kann ab jetzt über die Farmwelt betreten werden!");
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
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Schönen Nikolaus! Hast du schon in deine Schuhe geguckt?!");
                                    try {
                                        ymlConfigMessages.save(configMessages);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }

                    if (!clash1Date.after(currentDate) || !clash2Date.after(currentDate) || !clash3Date.after(currentDate) || !clash4Date.after(currentDate)) {
                        if (getClashOpen() == null) {
                            setClashOpen("true");

                            if (!clash1Date.after(currentDate)) {
                                setClash1("2025/01/01");
                                getYmlConfigClash().set("clash1", "2025/01/01");
                                try {
                                    getYmlConfigClash().save(getConfigClash());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (!clash2Date.after(currentDate)) {
                                setClash2("2025/01/01");
                                getYmlConfigClash().set("clash2", "2025/01/01");
                                try {
                                    getYmlConfigClash().save(getConfigClash());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (!clash3Date.after(currentDate)) {
                                setClash3("2025/01/01");
                                getYmlConfigClash().set("clash3", "2025/01/01");
                                try {
                                    getYmlConfigClash().save(getConfigClash());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (!clash4Date.after(currentDate)) {
                                setClash4("2025/01/01");
                                getYmlConfigClash().set("clash4", "2025/01/01");
                                try {
                                    getYmlConfigClash().save(getConfigClash());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "clash start");
                        }


                    }


                    if (!vote1Date.after(currentDate) || !vote2Date.after(currentDate) || !vote3Date.after(currentDate) || !vote4Date.after(currentDate)) {
                        if (getVoteOpen() == null) {
                            //vote open
                            setVoteOpen("true");

                            if (getVoteClose() != null) {
                                setVoteClose(null);
                            }

                            if (!vote1Date.after(currentDate)) {
                                setVote1("2025/01/01");
                                getYmlConfigVote().set("vote1", "2025/01/01");
                                try {
                                    getYmlConfigVote().save(getConfigVote());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (!vote2Date.after(currentDate)) {
                                setVote2("2025/01/01");
                                getYmlConfigVote().set("vote2", "2025/01/01");
                                try {
                                    getYmlConfigVote().save(getConfigVote());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (!vote3Date.after(currentDate)) {
                                setVote3("2025/01/01");
                                getYmlConfigVote().set("vote3", "2025/01/01");
                                try {
                                    getYmlConfigVote().save(getConfigVote());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (!vote4Date.after(currentDate)) {
                                setVote4("2025/01/01");
                                getYmlConfigVote().set("vote4", "2025/01/01");
                                try {
                                    getYmlConfigVote().save(getConfigVote());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            for (Player all : Bukkit.getOnlinePlayers()) {
                                File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

                                String xxx = ymlConfigMessages.getString("VoteOpen");

                                if (xxx.equals("false")) {
                                    ymlConfigMessages.set("VoteOpen", "true");
                                    ymlConfigMessages.set("VoteClose", "false");
                                    all.sendMessage(PREFIX + "§3Es kann ab jetzt ein neuer Bürgermeister gewählt werden! Nutze §r/vote <Name>§3.");
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

                            if (!vote1closeDate.after(currentDate)) {
                                setVote1close("2025/01/01");
                                getYmlConfigVote().set("vote1close", "2025/01/01");
                                try {
                                    getYmlConfigVote().save(getConfigVote());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                File file = new File("plugins//Messages");
                                String contents[] = file.list();
                                for (int i = 0; i < contents.length; i++) {
                                    try (PrintWriter output = new PrintWriter(new FileWriter("plugins//Messages//" + contents[i], true))) {
                                        output.printf("%s\r\n", "Vote1end: 'true'");
                                    } catch (Exception e) {
                                    }
                                }
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                    YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);
                                    ymlConfigMessages.set("Vote1end", "false");
                                    try {
                                        ymlConfigMessages.save(configMessages);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (!vote2closeDate.after(currentDate)) {
                                setVote2close("2025/01/01");
                                getYmlConfigVote().set("vote2close", "2025/01/01");
                                try {
                                    getYmlConfigVote().save(getConfigVote());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                File file = new File("plugins//Messages");
                                String contents[] = file.list();
                                for (int i = 0; i < contents.length; i++) {
                                    try (PrintWriter output = new PrintWriter(new FileWriter("plugins//Messages//" + contents[i], true))) {
                                        output.printf("%s\r\n", "Vote2end: 'true'");
                                    } catch (Exception e) {
                                    }
                                }
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                    YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);
                                    ymlConfigMessages.set("Vote2end", "false");
                                    try {
                                        ymlConfigMessages.save(configMessages);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (!vote3closeDate.after(currentDate)) {
                                setVote3close("2025/01/01");
                                getYmlConfigVote().set("vote3close", "2025/01/01");
                                try {
                                    getYmlConfigVote().save(getConfigVote());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                File file = new File("plugins//Messages");
                                String contents[] = file.list();
                                for (int i = 0; i < contents.length; i++) {
                                    try (PrintWriter output = new PrintWriter(new FileWriter("plugins//Messages//" + contents[i], true))) {
                                        output.printf("%s\r\n", "Vote3end: 'true'");
                                    } catch (Exception e) {
                                    }
                                }
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                    YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);
                                    ymlConfigMessages.set("Vote3end", "false");
                                    try {
                                        ymlConfigMessages.save(configMessages);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (!vote4closeDate.after(currentDate)) {
                                setVote4close("2025/01/01");
                                getYmlConfigVote().set("vote4close", "2025/01/01");
                                try {
                                    getYmlConfigVote().save(getConfigVote());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                File file = new File("plugins//Messages");
                                String contents[] = file.list();
                                for (int i = 0; i < contents.length; i++) {
                                    try (PrintWriter output = new PrintWriter(new FileWriter("plugins//Messages//" + contents[i], true))) {
                                        output.printf("%s\r\n", "Vote4end: 'true'");
                                    } catch (Exception e) {
                                    }
                                }
                                for (Player all : Bukkit.getOnlinePlayers()) {
                                    File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                    YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);
                                    ymlConfigMessages.set("Vote4end", "false");
                                    try {
                                        ymlConfigMessages.save(configMessages);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            try {
                                bvc.getResult();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
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
                                if (all.hasPermission("wintervillage.prisonred")) {
                                    File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                    YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

                                    String xxx = ymlConfigMessages.getString("VoteClose");

                                    if (xxx.equals("false")) {
                                        ymlConfigMessages.set("VoteClose", "true");
                                        ymlConfigMessages.set("VoteOpen", "false");
                                        try {
                                            ymlConfigMessages.save(configMessages);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    all.kickPlayer(Main.getPlugin().PREFIX + "§cDu wurdest deines Amtes enthoben! §6Bitte trete dem Server erneut bei. Neue Bürgermeister sind: \n§4RotMeister: §7" + winnerrot.toUpperCase() + "\n§1BlauMeister: §7" + winnerblau.toUpperCase());
                                }
                                if (all.hasPermission("wintervillage.prisonblue")) {
                                    File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                    YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

                                    String xxx = ymlConfigMessages.getString("VoteClose");

                                    if (xxx.equals("false")) {
                                        ymlConfigMessages.set("VoteClose", "true");
                                        ymlConfigMessages.set("VoteOpen", "false");
                                        try {
                                            ymlConfigMessages.save(configMessages);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    all.kickPlayer(Main.getPlugin().PREFIX + "§cDu wurdest deines Amtes enthoben! §6Bitte trete dem Server erneut bei. Neue Bürgermeister sind: \n§4RotMeister: §7" + winnerrot.toUpperCase() + "\n§1BlauMeister: §7" + winnerblau.toUpperCase());
                                }
                            }


                            BlauBuerger.clear();
                            RotBuerger.clear();
                            Buergermeisterred.clear();
                            Buergermeisterblue.clear();
                            Team.maketeams();

                            for (Player all : Bukkit.getOnlinePlayers()) {
                                File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

                                String xxx = ymlConfigMessages.getString("VoteClose");

                                if (xxx.equals("false")) {
                                    ymlConfigMessages.set("VoteClose", "true");
                                    ymlConfigMessages.set("VoteOpen", "false");

                                    all.sendMessage(PREFIX + "§3Neue Bürgermeister wurden gewählt! \n§4RotMeister: §7" + winnerrot.toUpperCase() + "\n§1BlauMeister: §7" + winnerblau.toUpperCase());

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
        }, 100, 10 * 20);
        //END TIME CHECKER

        getCommand("gm").setExecutor(new GamemodesCommand());
        getCommand("gm").setTabCompleter(new GamemodesTabComplete());
        getCommand("village").setExecutor(new VillageCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("spawn").setTabCompleter(new SpawnTabComplete());
        getCommand("prison").setExecutor(prisonCommand);
        getCommand("adventskalender").setExecutor(new AdventskalenderCommand());
        getCommand("vote").setExecutor(new BürgermeisterVoteCommand());
        getCommand("meet").setExecutor(new MeetVillage());
        getCommand("meet").setTabCompleter(new MeetVillageTabComplete());
        getCommand("bp").setExecutor(new BackpackCommand());
        getCommand("confi").setExecutor(new ConfiBothVillageCommand());
        getCommand("putsch").setExecutor(new PutschCommand());
        getCommand("clash").setExecutor(new Clash());
        getCommand("wintervillagestart").setExecutor(new WinterVillageStartCommand());
        getCommand("pos").setExecutor(new PositionSafeCommand());
        getCommand("pos").setTabCompleter(new PositionSafeTabComplete());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(prisonCommand, this);
        pluginManager.registerEvents(new AdventskalenderCommand(), this);
        pluginManager.registerEvents(new AdventskalenderListener(), this);
        pluginManager.registerEvents(new BlockPortalListener(), this);
        pluginManager.registerEvents(new ChatColorListener(), this);
        pluginManager.registerEvents(new VillageCommand(), this);
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new BackpackCommand(), this);
        pluginManager.registerEvents(new SpawnCommand(), this);
        pluginManager.registerEvents(new Clash(), this);

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
        //positionen
        File folderPos = new File("plugins//Positionen");
        if (!folderPos.exists()) {
            folderPos.mkdir();
        }
        File folderPosRot = new File("plugins//Positionen//TeamRot");
        if (!folderPosRot.exists()) {
            folderPosRot.mkdir();
        }
        File folderPosRot1 = new File("plugins//Positionen//TeamRot//FarmweltNr1");
        if (!folderPosRot1.exists()) {
            folderPosRot1.mkdir();
        }
        File folderPosRot2 = new File("plugins//Positionen//TeamRot//world_nether");
        if (!folderPosRot2.exists()) {
            folderPosRot2.mkdir();
        }
        File folderPosRot3 = new File("plugins//Positionen//TeamRot//world_the_end");
        if (!folderPosRot3.exists()) {
            folderPosRot3.mkdir();
        }

        File folderPosBlau = new File("plugins//Positionen//TeamBlau");
        if (!folderPosBlau.exists()) {
            folderPosBlau.mkdir();
        }
        File folderPosBlau1 = new File("plugins//Positionen//TeamBlau//FarmweltNr1");
        if (!folderPosBlau1.exists()) {
            folderPosBlau1.mkdir();
        }
        File folderPosBlau2 = new File("plugins//Positionen//TeamBlau//world_nether");
        if (!folderPosBlau2.exists()) {
            folderPosBlau2.mkdir();
        }
        File folderPosBlau3 = new File("plugins//Positionen//TeamBlau//world_the_end");
        if (!folderPosBlau3.exists()) {
            folderPosBlau3.mkdir();
        }

        //clashreward
        File fileReward = new File("plugins//Clash//rewarddata.yml");
        if (!fileReward.exists()) {
            try {
                fileReward.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        ymlConfigAdvent.addDefault("Reward.3", "minecraft:iron_ingot 19");
        ymlConfigAdvent.addDefault("Reward.4", "minecraft:anvil 1");
        ymlConfigAdvent.addDefault("Reward.5", "minecraft:enchanted_book{StoredEnchantments:[{id:'minecraft:power',lvl:2}]}");
        ymlConfigAdvent.addDefault("Reward.6", "minecraft:netherite_scrap 1");
        ymlConfigAdvent.addDefault("Reward.7", "minecraft:turtle_helmet 1");
        ymlConfigAdvent.addDefault("Reward.8", "minecraft:enchanted_book{StoredEnchantments:[{id:'minecraft:frost_walker',lvl:1}]}");
        ymlConfigAdvent.addDefault("Reward.9", "minecraft:experience_bottle 41");
        ymlConfigAdvent.addDefault("Reward.10", "minecraft:horse_spawn_egg 1");
        ymlConfigAdvent.addDefault("Reward.11", "minecraft:name_tag 1");
        ymlConfigAdvent.addDefault("Reward.12", "minecraft:saddle 1");
        ymlConfigAdvent.addDefault("Reward.13", "minecraft:slime_ball 12");
        ymlConfigAdvent.addDefault("Reward.14", "minecraft:wolf_spawn_egg 1");
        ymlConfigAdvent.addDefault("Reward.15", "minecraft:iron_horse_armor 1");
        ymlConfigAdvent.addDefault("Reward.16", "minecraft:enchanting_table 1");
        ymlConfigAdvent.addDefault("Reward.17", "minecraft:enchanted_book{StoredEnchantments:[{id:'minecraft:sharpness',lvl:3}]}");
        ymlConfigAdvent.addDefault("Reward.18", "minecraft:firework_rocket 64");
        ymlConfigAdvent.addDefault("Reward.19", "minecraft:enchanted_book{StoredEnchantments:[{id:'minecraft:mending'}]}");
        ymlConfigAdvent.addDefault("Reward.20", "minecraft:gold_ingot 42");
        ymlConfigAdvent.addDefault("Reward.21", "minecraft:diamond 12");
        ymlConfigAdvent.addDefault("Reward.22", "minecraft:enchanted_book{StoredEnchantments:[{id:'minecraft:efficiency',lvl:5}]}");
        ymlConfigAdvent.addDefault("Reward.23", "minecraft:enchanted_golden_apple 1");
        ymlConfigAdvent.addDefault("Reward.24", "minecraft:diamond 64");

        try {
            ymlConfigAdvent.save(configAdvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //end adventskalender

        //vote
        File folderVote = new File("plugins//Vote");
        if (!folderVote.exists()) {
            folderVote.mkdir();
        }
        if (!configVote.exists()) {
            try {
                configVote.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //fix dates
        ymlConfigVote.options().copyDefaults(true);
        ymlConfigVote.addDefault("vote1", "2020/11/26");
        ymlConfigVote.addDefault("vote1close", "2020/11/27");
        ymlConfigVote.addDefault("vote2", "2020/12/04");
        ymlConfigVote.addDefault("vote2close", "2020/12/05");
        ymlConfigVote.addDefault("vote3", "2020/12/12");
        ymlConfigVote.addDefault("vote3close", "2020/12/13");
        ymlConfigVote.addDefault("vote4", "2020/12/20");
        ymlConfigVote.addDefault("vote4close", "2020/12/21");

        try {
            ymlConfigVote.save(configVote);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //end vote

        //clash

        File folderClash = new File("plugins//Clash//Dates");
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

        ymlConfigClash.options().copyDefaults(true);
        ymlConfigClash.addDefault("clash1", "2020/12/02");
        ymlConfigClash.addDefault("clash2", "2020/12/09");
        ymlConfigClash.addDefault("clash3", "2020/12/16");
        ymlConfigClash.addDefault("clash4", "2020/12/23");

        try {
            ymlConfigClash.save(configClash);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!votesRed.exists()) {
            try {
                votesRed.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!votesBlue.exists()) {
            try {
                votesBlue.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!hasVoted.exists()) {
            try {
                hasVoted.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!putschVotesRed.exists()) {
            try {
                putschVotesRed.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!putschVotesBlue.exists()) {
            try {
                putschVotesBlue.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!putschHasVoted.exists()) {
            try {
                putschHasVoted.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File folderClashWins = new File("plugins//Clash//Wins");
        File configClashWins = new File("plugins//Clash//Wins//config.yml");
        YamlConfiguration ymlConfigClashWins = YamlConfiguration.loadConfiguration(configClashWins);

        if (!folderClashWins.exists()) {
            folderClashWins.mkdir();
        }
        if (!configClashWins.exists()) {
            try {
                configClashWins.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ymlConfigClashWins.options().copyDefaults(true);
        ymlConfigClashWins.addDefault("winner1", "null");
        ymlConfigClashWins.addDefault("winner2", "null");
        ymlConfigClashWins.addDefault("winner3", "null");
        ymlConfigClashWins.addDefault("winner4", "null");

        try {
            ymlConfigClashWins.save(configClashWins);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("[PluginBuddies] Plugin succesfully loaded!");

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
