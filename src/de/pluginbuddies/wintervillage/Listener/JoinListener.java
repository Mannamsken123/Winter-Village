//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Listener;

import de.pluginbuddies.wintervillage.Main.Main;
import de.pluginbuddies.wintervillage.Util.TabList;
import de.pluginbuddies.wintervillage.Util.Team;
import net.minecraft.server.v1_16_R2.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
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
    public void handlePlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Main.BlauBuerger.clear();
        Main.RotBuerger.clear();
        Main.Buergermeisterred.clear();
        Main.Buergermeisterblue.clear();
        Team.maketeams();

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
            //PENIS BÜRGERMEISTER JA ODER NEIN

            try {
                ymlConfigMessages.save(configMessages);
            } catch (IOException e) {
                e.printStackTrace();
            }
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

            String nether = ymlConfigMessages.getString("Nikolaus");

            if (nether.equals("false")) {
                ymlConfigMessages.set("Nikolaus", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //PENIS NIKOLAUS MESSAGE
                //player.sendMessage(Main.getPlugin().PREFIX + "§bDu kannst ab jetzt mit §r/advent §bjeden Tag dein Adventskalender-Türchen öffnen!");
            }
        }
        if (Main.getPlugin().getVoteOpen() == "true") {
            File configMessages = new File("plugins//Messages//" + player.getUniqueId() + ".yml");
            YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

            String voteOpen = ymlConfigMessages.getString("VoteOpen");

            if (voteOpen.equals("false")) {
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

}



