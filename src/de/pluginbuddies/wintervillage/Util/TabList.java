//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class TabList {

    private static HashMap<String, String> teams;

    static {
        teams = new HashMap<>();
    }

    public void create(String name, int rank, String prefix, String suffix, String permission) {
        String fullName = rank + "_" + name;
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Team t = board.getTeam(fullName);

        if (t != null) {
            t.unregister();
        }

        t = board.registerNewTeam(fullName);

        if (prefix != null) {
            t.setPrefix(prefix);
        }
        if (suffix != null) {
            t.setSuffix(prefix);
        }

        teams.put(permission, fullName);

    }

    public void addPlayer(Player p) {
        Team t = null;

        for (String perm : teams.keySet()) {
            String currentTeamName = teams.get(perm);

            if (t == null || this.getRank(currentTeamName) < this.getRank(t.getName())) {
                t = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(currentTeamName);
            }
        }

        if (t != null) {
            t.addPlayer(p);
        }
    }

    public void update() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            this.removePlayer(p);
            this.addPlayer(p);
        }
    }

    public void removePlayer(Player p) {
        for (String teamName : teams.values()) {
            Team t = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(teamName);

            if (t != null && t.hasPlayer(p)) {
                t.removePlayer(p);
            }
        }
    }


    private int getRank(String teamName) {
        if (!teamName.contains("_")) {
            return -1;
        }
        String[] array = teamName.split("_");
        try {
            int i = Integer.parseInt(array[0]);
            return i;
        } catch (NumberFormatException ex) {
            return -1;
        }
    }


}
