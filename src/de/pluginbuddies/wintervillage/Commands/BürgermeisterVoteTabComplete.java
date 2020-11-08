//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BürgermeisterVoteTabComplete implements TabCompleter {

    static File configteams = new File("plugins//Teams//config.yml");
    public static YamlConfiguration ymlConfigteams = YamlConfiguration.loadConfiguration(configteams);

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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("wintervillage.redteam") || p.hasPermission("wintervillage.prisonred")) {
                if (Main.getPlugin().getVoteOpen() == "true" || Main.getPlugin().getPutschRot() == true) {
                    if (args.length == 1) {
                       /* List<String> rotList = new ArrayList<String>();
                        Scanner scanner = null;
                        boolean rot = false;
                        try {
                            scanner = new Scanner(new File("plugins//Teams//config.yml"));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (line.equals("Rot::")) {
                                rot = true;
                            } else if (line.equals("Blau:") || line.equals("RotMeister:") || line.equals("BlauMeister:")) {
                                rot = false;
                            }
                            if (rot == true) {
                                if (line.length() > 15) {
                                    rotList.add(getName(line.substring(7)));
                                }
                            }
                        }
                        return rotList;*/
                        List<String> rot = new ArrayList<String>();
                        for (int i = 1; i <= 10; i++) {
                            if (!ymlConfigteams.getString("Rot." + i).isEmpty()) {
                                rot.add(getName(ymlConfigteams.getString("Rot." + i)));
                            }
                        }
                        return rot;
                    }
                }
            }
            if (p.hasPermission("wintervillage.blueteam") || p.hasPermission("wintervillage.prisonblue")) {
                if (Main.getPlugin().getVoteOpen() == "true" || Main.getPlugin().getPutschBlau() == true) {
                    if (args.length == 1) {
                       /* List<String> blauList = new ArrayList<String>();
                        Scanner scanner = null;
                        boolean blau = false;
                        try {
                            scanner = new Scanner(new File("plugins//Teams//config.yml"));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (line.equals("Blau:")) {
                                blau = true;
                            } else if (line.equals("Rot:") || line.equals("RotMeister:") || line.equals("BlauMeister:")) {
                                blau = false;
                            }
                            if (blau == true) {
                                if (line.length() > 15) {
                                    blauList.add((line.substring(7)));
                                }
                            }
                        }
                        return blauList;*/
                        List<String> blau = new ArrayList<String>();
                        for (int i = 1; i <= 10; i++) {
                            if (!ymlConfigteams.getString("Blau." + i).isEmpty()) {
                                blau.add(getName(ymlConfigteams.getString("Blau." + i)));
                            }
                        }
                        return blau;
                    }
                }
            }
        }
        return null;
    }
}
