//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Listener;

import de.pluginbuddies.wintervillage.Main.Main;
import de.pluginbuddies.wintervillage.Util.PlayerSerialize;
import de.pluginbuddies.wintervillage.Util.TabList;
import net.minecraft.server.v1_16_R2.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;


public class JoinListener implements Listener {

    World world = Bukkit.getWorld("world");

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

    @EventHandler
    public void handlePlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("wintervillage.blueteam") && !player.hasPermission("wintervillage.prisonblue")) {
            event.setQuitMessage("§c§l<< §9" + player.getName() + " §7hat verlassen!");
            new TabList().removePlayer(player);
        }
        if (player.hasPermission("wintervillage.redteam") && !player.hasPermission("wintervillage.prisonred")) {
            event.setQuitMessage("§c§l<< §c" + player.getName() + " §7hat verlassen!");
            new TabList().removePlayer(player);
        }
        if (player.hasPermission("wintervillage.prisonblue")) {
            event.setQuitMessage("§c§l<< §1" + player.getName() + " §7hat verlassen!");
            new TabList().removePlayer(player);
        }
        if (player.hasPermission("wintervillage.prisonred")) {
            event.setQuitMessage("§c§l<< §4" + player.getName() + " §7hat verlassen!");
            new TabList().removePlayer(player);
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

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String uuid = player.getUniqueId().toString();
        String uuidShort = uuid.replace("-", "");

        if (!Main.ymlConfigteams.getString("RotMeister.1").contains(uuidShort) && !Main.ymlConfigteams.getString("BlauMeister.1").contains(uuidShort)) {
            for (int i = 1; i <= 10; i++) {
                if (Main.ymlConfigteams.getString("Rot." + i).contains(uuid)) {
                    PermissionAttachment att = player.addAttachment(Main.getPlugin());
                    att.setPermission("wintervillage.redteam", true);
                    Main.RotBuerger.put(player, att);
                }
            }

            for (int j = 1; j <= 10; j++) {
                if (Main.ymlConfigteams.getString("Blau." + j).contains(uuid)) {
                    PermissionAttachment att = player.addAttachment(Main.getPlugin());
                    att.setPermission("wintervillage.blueteam", true);
                    Main.BlauBuerger.put(player, att);
                }
            }
        } else if (Main.ymlConfigteams.getString("RotMeister.1").contains(uuidShort)) {
            PermissionAttachment att2 = player.addAttachment(Main.getPlugin());
            att2.setPermission("wintervillage.prisonred", true);
            Main.Buergermeisterred.put(player, att2);
        } else if (Main.ymlConfigteams.getString("BlauMeister.1").contains(uuidShort)) {
            PermissionAttachment att = player.addAttachment(Main.getPlugin());
            att.setPermission("wintervillage.prisonblue", true);
            Main.Buergermeisterblue.put(player, att);
        }

        TabList tl = new TabList();
        tl.update();


        if (!player.hasPlayedBefore()) {
            player.setGameMode(GameMode.SURVIVAL);
            //Spawn-TP
            Location location = world.getSpawnLocation();
            location.setY(world.getHighestBlockYAt(location) + 1);
            player.teleport(location);
            //Player-Attribute
            player.setFoodLevel(20);
            player.setHealth(20);
            player.setTotalExperience(0);
            player.setExp(0);
            player.setLevel(0);
            player.getInventory().clear();
            player.sendMessage(Main.getPlugin().PREFIX + "§3Herzlich Willkommen bei Winter Village!");
            st(player.getPlayer(), "§3Winter Village", "§7by mullemann25 & Mannam01", 5, 50, 5);

            File configMessages = new File("plugins//Messages//" + player.getUniqueId() + ".yml");
            YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

            if (!configMessages.exists()) {
                try {
                    configMessages.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ymlConfigMessages.options().copyDefaults(true);
            ymlConfigMessages.addDefault("Nether", "false");
            ymlConfigMessages.addDefault("Adventskalender", "false");
            ymlConfigMessages.addDefault("End", "false");
            ymlConfigMessages.addDefault("Nikolaus", "false");
            ymlConfigMessages.addDefault("VoteOpen", "false");
            ymlConfigMessages.addDefault("VoteClose", "false");
            ymlConfigMessages.addDefault("givebackstuff", "false");

            try {
                ymlConfigMessages.save(configMessages);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //clash
        if (Main.getPlugin().getClashOpen2() == "true") {
            World w = Bukkit.getWorld("world-clash");
            Player p = event.getPlayer();
            Location location = new Location(w, 114.528, 75, -71.520, -90, -3);
            p.teleport(location);

            new BukkitRunnable() {
                int time = 2;

                @Override
                public void run() {
                    time--;
                    if (time == 0) {
                        p.setGameMode(GameMode.SPECTATOR);
                        cancel();
                    }
                }
            }.runTaskTimer(Main.getPlugin(), 0L, 20L);
        }

        if (Main.getPlugin().getNetherOpen() == "true") {
            File configMessages = new File("plugins//Messages//" + player.getUniqueId() + ".yml");
            YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

            String nether = ymlConfigMessages.getString("Nether");

            if (nether.equals("false")) {
                ymlConfigMessages.set("Nether", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendMessage(Main.getPlugin().PREFIX + "§3Der Nether kann ab jetzt über das Portal am Spawn betreten werden!");
            }
        }
        if (Main.getPlugin().getAdventskalenderOpen() == "true") {
            File configMessages = new File("plugins//Messages//" + player.getUniqueId() + ".yml");
            YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

            String nether = ymlConfigMessages.getString("Adventskalender");

            if (nether.equals("false")) {
                ymlConfigMessages.set("Adventskalender", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendMessage(Main.getPlugin().PREFIX + "§3Du kannst ab jetzt mit §r/advent §3jeden Tag dein Adventskalender-Türchen öffnen!");
            }
        }
        if (Main.getPlugin().getEndOpen() == "true") {
            File configMessages = new File("plugins//Messages//" + player.getUniqueId() + ".yml");
            YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

            String end = ymlConfigMessages.getString("End");
            String getFalse = Main.getPlugin().getYmlConfigBlockPortal().getString("EndSpawn");

            //end auf und enderdrache dead
            if (getFalse.equals("true")) {
                ymlConfigMessages.set("End", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.sendMessage(Main.getPlugin().PREFIX + "§3Das End kann ab jetzt über das Portal am Spawn betreten werden!");

                //end frei noch enderdrache alive
            } else if (end.equals("false")) {
                ymlConfigMessages.set("End", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.sendMessage(Main.getPlugin().PREFIX + "§3Das End kann ab jetzt über die Farmwelt betreten werden!");
            }
        }
        if (Main.getPlugin().getNikolausOpen() == "true") {
            File configMessages = new File("plugins//Messages//" + player.getUniqueId() + ".yml");
            YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

            String xxx = ymlConfigMessages.getString("Nikolaus");

            if (xxx.equals("false")) {
                ymlConfigMessages.set("Nikolaus", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendMessage(Main.getPlugin().PREFIX + "§3Schönen Nikolaus! Hast du schon in deine Schuhe geguckt?!");
            }
        }
        if (Main.getPlugin().getVoteOpen() == "true") {
            File configMessages = new File("plugins//Messages//" + player.getUniqueId() + ".yml");
            YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

            String xxx = ymlConfigMessages.getString("VoteOpen");

            if (xxx.equals("false")) {
                ymlConfigMessages.set("VoteOpen", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.sendMessage(Main.getPlugin().PREFIX + "§3Es kann ab jetzt ein neuer Bürgermeister gewählt werden! Nutze §r/vote <Name>§3.");
            }
        }
        File configMessages = new File("plugins//Messages//" + player.getUniqueId() + ".yml");
        YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

        if (ymlConfigMessages.contains("Vote1end")) {
            String Vote1end = ymlConfigMessages.getString("Vote1end");
            if (Vote1end.equals("true")) {
                ymlConfigMessages.set("Vote1end", "false");
                ymlConfigMessages.set("VoteOpen", "false");
                ymlConfigMessages.set("VoteClose", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String redmeister = Main.ymlConfigteams.getString("RotMeister.1");
                String bluemeister = Main.ymlConfigteams.getString("BlauMeister.1");
                player.sendMessage(Main.getPlugin().PREFIX + "§3Neue Bürgermeister wurden gewählt! \n§4RotMeister: §7" + getName(redmeister).toUpperCase() + "\n§1BlauMeister: §7" + getName(bluemeister).toUpperCase());
            }
        }
        if (ymlConfigMessages.contains("Vote2end")) {
            String Vote2end = ymlConfigMessages.getString("Vote2end");
            if (Vote2end.equals("true")) {
                ymlConfigMessages.set("Vote2end", "false");
                ymlConfigMessages.set("VoteOpen", "false");
                ymlConfigMessages.set("VoteClose", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String redmeister = Main.ymlConfigteams.getString("RotMeister.1");
                String bluemeister = Main.ymlConfigteams.getString("BlauMeister.1");
                player.sendMessage(Main.getPlugin().PREFIX + "§3Neue Bürgermeister wurden gewählt! \n§4RotMeister: §7" + getName(redmeister).toUpperCase() + "\n§1BlauMeister: §7" + getName(bluemeister).toUpperCase());
            }
        }
        if (ymlConfigMessages.contains("Vote3end")) {
            String Vote3end = ymlConfigMessages.getString("Vote3end");
            if (Vote3end.equals("true")) {
                ymlConfigMessages.set("Vote3end", "false");
                ymlConfigMessages.set("VoteOpen", "false");
                ymlConfigMessages.set("VoteClose", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String redmeister = Main.ymlConfigteams.getString("RotMeister.1");
                String bluemeister = Main.ymlConfigteams.getString("BlauMeister.1");
                player.sendMessage(Main.getPlugin().PREFIX + "§3Neue Bürgermeister wurden gewählt! \n§4RotMeister: §7" + getName(redmeister).toUpperCase() + "\n§1BlauMeister: §7" + getName(bluemeister).toUpperCase());
            }
        }
        if (ymlConfigMessages.contains("Vote4end")) {
            String Vote4end = ymlConfigMessages.getString("Vote4end");
            if (Vote4end.equals("true")) {
                ymlConfigMessages.set("Vote4end", "false");
                ymlConfigMessages.set("VoteOpen", "false");
                ymlConfigMessages.set("VoteClose", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String redmeister = Main.ymlConfigteams.getString("RotMeister.1");
                String bluemeister = Main.ymlConfigteams.getString("BlauMeister.1");
                player.sendMessage(Main.getPlugin().PREFIX + "§3Neue Bürgermeister wurden gewählt! \n§4RotMeister: §7" + getName(redmeister).toUpperCase() + "\n§1BlauMeister: §7" + getName(bluemeister).toUpperCase());
            }
        }

        st(player.getPlayer(), "§3Winter Village", "§7by mullemann25 & Mannam01", 5, 50, 5);

        if (player.hasPermission("wintervillage.blueteam") && !player.hasPermission("wintervillage.prisonblue")) {
            event.setJoinMessage("§a§l>> §9" + player.getName() + " §7ist beigetreten!");
            new TabList().addPlayer(player);
        }
        if (player.hasPermission("wintervillage.redteam") && !player.hasPermission("wintervillage.prisonred")) {
            event.setJoinMessage("§a§l>> §c" + player.getName() + " §7ist beigetreten!");
            new TabList().addPlayer(player);
        }
        if (player.hasPermission("wintervillage.prisonblue")) {
            event.setJoinMessage("§a§l>> §1" + player.getName() + " §7ist beigetreten!");
            new TabList().addPlayer(player);
        }
        if (player.hasPermission("wintervillage.prisonred")) {
            event.setJoinMessage("§a§l>> §4" + player.getName() + " §7ist beigetreten!");
            new TabList().addPlayer(player);
        }


        File configMessages2 = new File("plugins//Messages//" + player.getUniqueId() + ".yml");
        YamlConfiguration ymlConfigMessages2 = YamlConfiguration.loadConfiguration(configMessages2);
        try {
            ymlConfigMessages2.load("plugins//Messages//" + player.getUniqueId() + ".yml");
        } catch (IOException | InvalidConfigurationException v) {
            v.printStackTrace();
        }
        if (ymlConfigMessages2.getString("givebackstuff").equals("true")) {
            File inventory = new File("plugins//Clash//Inventories2//" + player.getName() + ".yml");
            if (Main.getPlugin().getClashOpen2() == "true") {
                ymlConfigMessages2.set("givebackstuff", "false");
                try {
                    ymlConfigMessages2.save(configMessages2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                if (inventory.exists()) {
                    YamlConfiguration inv = YamlConfiguration.loadConfiguration(inventory);

                    try {
                        inv.load("plugins//Clash//Inventories2//" + player.getName() + ".yml");
                    } catch (IOException | InvalidConfigurationException e) {
                        e.printStackTrace();
                    }

                    World world = Bukkit.getWorld(inv.getString("World"));
                    Double X = inv.getDouble("X");
                    Double Y = inv.getDouble("Y");
                    Double Z = inv.getDouble("Z");
                    Location loc = new Location(world, X, Y, Z);
                    player.teleport(loc);
                    inventory.delete();

                } else {
                    World world = Bukkit.getWorld("world");
                    Location location = new Location(world, 114.528, 41, -71.520, -90, -3);
                    player.teleport(location);
                }

                PlayerSerialize newInv = null;

                File f = new File("plugins//Clash//Inventories//" + player.getName() + ".playerinv");

                if (!f.exists())
                    try {
                        f.getParentFile().mkdir();
                        f.createNewFile();
                    } catch (IOException v) {
                        v.printStackTrace();
                        return;
                    }

                try (FileInputStream fileIn = new FileInputStream(f);
                     BukkitObjectInputStream in = new BukkitObjectInputStream(fileIn)) {
                    newInv = (PlayerSerialize) in.readObject();
                } catch (IOException | ClassNotFoundException v) {
                    v.printStackTrace();
                    return;
                }

                if (newInv != null) {
                    player.getInventory().setContents(newInv.inventory);
                    player.setHealth(newInv.health);
                    player.setFoodLevel((int) newInv.hunger);
                    player.setFlySpeed((float) newInv.flySpeed);
                    player.setWalkSpeed((float) newInv.walkSpeed);
                    player.setLevel(newInv.levels);
                    player.setExp((float) newInv.xp);
                    player.setSaturation((float) newInv.saturation);
                    player.setExhaustion((float) newInv.fatigue);
                }
                f.delete();

                ymlConfigMessages2.set("givebackstuff", "false");
                try {
                    ymlConfigMessages2.save(configMessages2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.setGameMode(GameMode.SURVIVAL);
            }


        }

    }


}



