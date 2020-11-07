//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Util;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.scoreboard.Scoreboard;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.net.URL;

public class Team {

    public static Scoreboard sb;

    public static void maketeams() {
        String redmeister = Main.ymlConfigteams.getString("RotMeister.1");
        String bluemeister = Main.ymlConfigteams.getString("BlauMeister.1");

        for (int i = 1; i <= 10; i++) {
            String rt = getName(Main.ymlConfigteams.getString("Rot." + i));
            Player p = Bukkit.getPlayerExact(rt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayer(rt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.redteam", true);
                Main.RotBuerger.put(Bukkit.getPlayer(rt), att);
            }
        }
        if (!redmeister.isEmpty()) {
            //RoterMeister
            String rmeister = getName(redmeister);
            Player p = Bukkit.getPlayerExact(rmeister);
            if (p != null) {
                PermissionAttachment att2 = Bukkit.getPlayer(rmeister).addAttachment(Main.getPlugin());
                att2.setPermission("wintervillage.prisonred", true);
                Main.Buergermeisterred.put(Bukkit.getPlayer(rmeister), att2);
            }
        }

        for (int i = 1; i <= 10; i++) {
            String bt = getName(Main.ymlConfigteams.getString("Blau." + i));
            Player p = Bukkit.getPlayerExact(bt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayer(bt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.redteam", true);
                Main.BlauBuerger.put(Bukkit.getPlayer(bt), att);
            }
        }

        if (!bluemeister.isEmpty()) {
            //BlauerMeister
            String bmeister = getName(bluemeister);
            Player p = Bukkit.getPlayerExact(bmeister);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayer(bmeister).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.prisonblue", true);
                Main.Buergermeisterblue.put(Bukkit.getPlayer(bmeister), att);
            }

        }
    }

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

    public static void prefix(Player p, String prefix) {
        String pr = prefix.replace("&", "§").replace("_", " ");
        org.bukkit.scoreboard.Team t = sb.getTeam(prefix);

        if (t == null) {
            t = sb.registerNewTeam(prefix);
            t.setPrefix(pr);
            if (p.hasPermission("wintervillage.prisonblue")) {
                t.setColor(ChatColor.DARK_BLUE);
            }
            if (p.hasPermission("wintervillage.prisonred")) {
                t.setColor(ChatColor.DARK_RED);
            }
            if (p.hasPermission("wintervillage.blueteam") && !p.hasPermission("wintervillage.prisonblue")) {
                t.setColor(ChatColor.BLUE);
            }
            if (p.hasPermission("wintervillage.redteam") && !p.hasPermission("wintervillage.prisonred")) {
                t.setColor(ChatColor.RED);
            }
        }
        t.addPlayer(p);

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setScoreboard(sb);
        }

    }


}
