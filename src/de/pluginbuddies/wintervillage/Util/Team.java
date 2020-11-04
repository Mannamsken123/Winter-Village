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
        String rt1 = Main.ymlConfigteams.getString("Rot.1");
        String rt2 = Main.ymlConfigteams.getString("Rot.2");
        String rt3 = Main.ymlConfigteams.getString("Rot.3");
        String rt4 = Main.ymlConfigteams.getString("Rot.4");
        String rt5 = Main.ymlConfigteams.getString("Rot.5");
        String rt6 = Main.ymlConfigteams.getString("Rot.6");
        String rt7 = Main.ymlConfigteams.getString("Rot.7");
        String rt8 = Main.ymlConfigteams.getString("Rot.8");
        String rt9 = Main.ymlConfigteams.getString("Rot.9");
        String rt10 = Main.ymlConfigteams.getString("Rot.10");
        String bt1 = Main.ymlConfigteams.getString("Blau.1");
        String bt2 = Main.ymlConfigteams.getString("Blau.2");
        String bt3 = Main.ymlConfigteams.getString("Blau.3");
        String bt4 = Main.ymlConfigteams.getString("Blau.4");
        String bt5 = Main.ymlConfigteams.getString("Blau.5");
        String bt6 = Main.ymlConfigteams.getString("Blau.6");
        String bt7 = Main.ymlConfigteams.getString("Blau.7");
        String bt8 = Main.ymlConfigteams.getString("Blau.8");
        String bt9 = Main.ymlConfigteams.getString("Blau.9");
        String bt10 = Main.ymlConfigteams.getString("Blau.10");

        if (!bluemeister.isEmpty()) {
            //BlauerMeister
            String bmeister = getName(bluemeister);
            Player p = Bukkit.getPlayerExact(bmeister);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayer(bmeister).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.prisonblue", true);
                Main.Bürgermeisterblue.put(Bukkit.getPlayer(bmeister), att);
            }

        }
        if (!redmeister.isEmpty()) {
            //RoterMeister
            String rmeister = getName(redmeister);
            Player p = Bukkit.getPlayerExact(rmeister);
            if (p != null) {
                PermissionAttachment att2 = Bukkit.getPlayer(rmeister).addAttachment(Main.getPlugin());
                att2.setPermission("wintervillage.prisonred", true);
                Main.Bürgermeisterred.put(Bukkit.getPlayer(rmeister), att2);
            }
        }
        //_____________________________RED-TEAM_______________________________________________
        if (!rt1.isEmpty()) {
            String rt = getName(rt1);
            Player p = Bukkit.getPlayerExact(rt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayer(rt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.redteam", true);
                Main.RotBürger1.put(Bukkit.getPlayer(rt), att);
            }
        }
        if (!rt2.isEmpty()) {
            String rt = getName(rt2);
            Player p = Bukkit.getPlayerExact(rt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(rt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.redteam", true);
                Main.RotBürger2.put(Bukkit.getPlayerExact(rt), att);
            }
        }
        if (!rt3.isEmpty()) {
            String rt = getName(rt3);
            Player p = Bukkit.getPlayerExact(rt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(rt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.redteam", true);
                Main.RotBürger3.put(Bukkit.getPlayerExact(rt), att);
            }
        }
        if (!rt4.isEmpty()) {
            String rt = getName(rt4);
            Player p = Bukkit.getPlayerExact(rt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(rt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.redteam", true);
                Main.RotBürger4.put(Bukkit.getPlayerExact(rt), att);
            }
        }
        if (!rt5.isEmpty()) {
            String rt = getName(rt5);
            Player p = Bukkit.getPlayerExact(rt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(rt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.redteam", true);
                Main.RotBürger5.put(Bukkit.getPlayerExact(rt), att);
            }
        }
        if (!rt6.isEmpty()) {
            String rt = getName(rt6);
            Player p = Bukkit.getPlayerExact(rt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(rt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.redteam", true);
                Main.RotBürger6.put(Bukkit.getPlayerExact(rt), att);
            }
        }
        if (!rt7.isEmpty()) {
            String rt = getName(rt7);
            Player p = Bukkit.getPlayerExact(rt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(rt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.redteam", true);
                Main.RotBürger7.put(Bukkit.getPlayerExact(rt), att);
            }
        }
        if (!rt8.isEmpty()) {
            String rt = getName(rt8);
            Player p = Bukkit.getPlayerExact(rt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(rt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.redteam", true);
                Main.RotBürger8.put(Bukkit.getPlayerExact(rt), att);
            }
        }
        if (!rt9.isEmpty()) {
            String rt = getName(rt9);
            Player p = Bukkit.getPlayerExact(rt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(rt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.redteam", true);
                Main.RotBürger9.put(Bukkit.getPlayerExact(rt), att);
            }
        }
        if (!rt10.isEmpty()) {
            String rt = getName(rt10);
            Player p = Bukkit.getPlayerExact(rt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(rt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.redteam", true);
                Main.RotBürger10.put(Bukkit.getPlayerExact(rt), att);
            }
        }
        //______________________________RED-TEAM-END__________________________________________
        //______________________________BLUE-TEAM_____________________________________________
        if (!bt1.isEmpty()) {
            String bt = getName(bt1);
            Player p = Bukkit.getPlayerExact(bt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(bt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.blueteam", true);
                Main.BlauBürger1.put(Bukkit.getPlayerExact(bt), att);
            }
        }
        if (!bt2.isEmpty()) {
            String bt = getName(bt2);
            Player p = Bukkit.getPlayerExact(bt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(bt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.blueteam", true);
                Main.BlauBürger2.put(Bukkit.getPlayerExact(bt), att);
            }
        }
        if (!bt3.isEmpty()) {
            String bt = getName(bt3);
            Player p = Bukkit.getPlayerExact(bt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(bt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.blueteam", true);
                Main.BlauBürger3.put(Bukkit.getPlayerExact(bt), att);
            }
        }
        if (!bt4.isEmpty()) {
            String bt = getName(bt4);
            Player p = Bukkit.getPlayerExact(bt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(bt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.blueteam", true);
                Main.BlauBürger4.put(Bukkit.getPlayerExact(bt), att);
            }
        }
        if (!bt5.isEmpty()) {
            String bt = getName(bt5);
            Player p = Bukkit.getPlayerExact(bt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(bt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.blueteam", true);
                Main.BlauBürger5.put(Bukkit.getPlayerExact(bt), att);
            }
        }
        if (!bt6.isEmpty()) {
            String bt = getName(bt6);
            Player p = Bukkit.getPlayerExact(bt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(bt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.blueteam", true);
                Main.BlauBürger6.put(Bukkit.getPlayerExact(bt), att);
            }
        }
        if (!bt7.isEmpty()) {
            String bt = getName(bt7);
            Player p = Bukkit.getPlayerExact(bt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(bt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.blueteam", true);
                Main.BlauBürger7.put(Bukkit.getPlayerExact(bt), att);
            }
        }
        if (!bt8.isEmpty()) {
            String bt = getName(bt8);
            Player p = Bukkit.getPlayerExact(bt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(bt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.blueteam", true);
                Main.BlauBürger8.put(Bukkit.getPlayerExact(bt), att);
            }
        }
        if (!bt9.isEmpty()) {
            String bt = getName(bt9);
            Player p = Bukkit.getPlayerExact(bt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(bt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.blueteam", true);
                Main.BlauBürger9.put(Bukkit.getPlayerExact(bt), att);
            }
        }
        if (!bt10.isEmpty()) {
            String bt = getName(bt10);
            Player p = Bukkit.getPlayerExact(bt);
            if (p != null) {
                PermissionAttachment att = Bukkit.getPlayerExact(bt).addAttachment(Main.getPlugin());
                att.setPermission("wintervillage.blueteam", true);
                Main.BlauBürger10.put(Bukkit.getPlayerExact(bt), att);
            }
        }
        //______________________________BLUE-TEAM-END_________________________________________
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
            if (p.hasPermission("wintervillage.blueteam")) {
                t.setColor(ChatColor.BLUE);
            }
            if (p.hasPermission("wintervillage.redteam")) {
                t.setColor(ChatColor.RED);
            }
        }
        t.addPlayer(p);

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setScoreboard(sb);
        }

    }


}
