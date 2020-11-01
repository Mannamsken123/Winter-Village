//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Main;

import de.pluginbuddies.wintervillage.Commands.*;
import de.pluginbuddies.wintervillage.Listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;
    public final String PREFIX = "§aServer " + "§8>> §r";

    public static Main getPlugin() {
        return plugin;
    }

    public void onEnable() {
        plugin = this;

        Bukkit.getWorld("world").setDifficulty(Difficulty.PEACEFUL);
        PrisonCommand prisonCommand = new PrisonCommand();

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setGameMode(GameMode.SURVIVAL);
            all.sendMessage("§aServer " + "§8>> " + "§aPlugin geladen.");
        }

        getCommand("gm").setExecutor(new GamemodesCommand());
        getCommand("gm").setTabCompleter(new GamemodesTabComplete());
        getCommand("village").setExecutor(new VillageCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("prison").setExecutor(prisonCommand);

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(prisonCommand, this);


    }



}
