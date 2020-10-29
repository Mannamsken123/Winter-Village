//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Main;

import de.pluginbuddies.wintervillage.Commands.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;
    public final String PREFIX = "§aServer " + "§8>> §r";

    public static Main getPlugin() {
        return plugin;
    }

    public void onEnable() {
        plugin = this;

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setGameMode(GameMode.SURVIVAL);
            all.sendMessage("§aServer " + "§8>> " + "§aPlugin geladen.");
        }

        getCommand("gm").setExecutor(new GamemodesCommand());
        getCommand("gm").setTabCompleter(new GamemodesTabComplete());
    }



}
