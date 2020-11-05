//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Listener;

import de.pluginbuddies.wintervillage.Main.Main;
import de.pluginbuddies.wintervillage.Util.Team;
import net.minecraft.server.v1_16_R2.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;


public class JoinListener implements Listener {

    World world = Bukkit.getWorld("world");

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Main.Bürgermeisterred.clear();
        Main.Bürgermeisterblue.clear();
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
            player.sendMessage(Main.getPlugin().PREFIX + "§bHerzlich Willkommen bei Winter Village!");
            st(player.getPlayer(), "§bWinter Village", "§7by mullemann25 & Mannam01", 5, 50, 5);

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

                player.sendMessage(Main.getPlugin().PREFIX + "§bDer Nether kann ab jetzt über das Portal am Spawn betreten werden!");
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

                player.sendMessage(Main.getPlugin().PREFIX + "§bDu kannst ab jetzt mit §r/advent §bjeden Tag dein Adventskalender-Türchen öffnen!");
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
                player.sendMessage(Main.getPlugin().PREFIX + "§bDas End kann ab jetzt über das Portal am Spawn betreten werden!");

                //end frei noch enderdrache alive
            } else if (end.equals("false")) {
                ymlConfigMessages.set("End", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.sendMessage(Main.getPlugin().PREFIX + "§bDas End kann ab jetzt über die Farmwelt betreten werden!");
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
                player.sendMessage(Main.getPlugin().PREFIX + "§bEs kann ab jetzt ein neuer Bürgermeister gewählt werden! Nutze §r/vote <Name>§b.");
            }
        }
        if (Main.getPlugin().getVoteClose() == "true") {
            File configMessages = new File("plugins//Messages//" + player.getUniqueId() + ".yml");
            YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

            String voteClose = ymlConfigMessages.getString("VoteClose");

            if (voteClose.equals("false")) {
                ymlConfigMessages.set("VoteClose", "true");
                try {
                    ymlConfigMessages.save(configMessages);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String redmeister = Main.ymlConfigteams.getString("RotMeister.1");
                String bluemeister = Main.ymlConfigteams.getString("BlauMeister.1");
                player.sendMessage(Main.getPlugin().PREFIX + "§bNeue Bürgermeister wurden gewählt! \n§4RotMeister: §7" + Bukkit.getPlayer(redmeister).getName() + "\n§1BlauMeister: §7" + Bukkit.getPlayer(bluemeister).getName());
            }
        }


        //event.setJoinMessage("§a§l>> §7" + player.getName() + " ist beigetreten!");
        st(player.getPlayer(), "§bWinter Village", "§7by mullemann25 & Mannam01", 5, 50, 5);


        if (player.hasPermission("wintervillage.prisonblue")) {
            Team.prefix(player, "&1BlauMeister: ");
            event.setJoinMessage("§a§l>> §1" + player.getName() + " §7ist beigetreten!");
        }
        if (player.hasPermission("wintervillage.prisonred")) {
            Team.prefix(player, "&4RotMeister: ");
            event.setJoinMessage("§a§l>> §4" + player.getName() + " §7ist beigetreten!");
        }

        if (player.hasPermission("wintervillage.blueteam")) {
            Team.prefix(player, "&9Blau: ");
            event.setJoinMessage("§a§l>> §9" + player.getName() + " §7ist beigetreten!");
        }
        if (player.hasPermission("wintervillage.redteam")) {
            Team.prefix(player, "&cRot: ");
            event.setJoinMessage("§a§l>> §c" + player.getName() + " §7ist beigetreten!");
        }


    }

    @EventHandler
    public void handlePlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("wintervillage.prisonblue")) {
            event.setQuitMessage("§c§l<< §1" + player.getName() + " §7hat verlassen!");
        }
        if (player.hasPermission("wintervillage.prisonred")) {
            event.setQuitMessage("§c§l<< §4" + player.getName() + " §7hat verlassen!");
        }

        if (player.hasPermission("wintervillage.blueteam")) {
            event.setQuitMessage("§c§l<< §9" + player.getName() + " §7hat verlassen!");
        }
        if (player.hasPermission("wintervillage.redteam")) {
            event.setQuitMessage("§c§l<< §c" + player.getName() + " §7hat verlassen!");
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



