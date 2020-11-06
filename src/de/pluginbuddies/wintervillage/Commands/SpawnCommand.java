//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                String w = p.getWorld().getName();
                if (!w.equals("world-clash")) {
                    new BukkitRunnable() {
                        int time = 4;

                        @Override
                        public void run() {
                            time--;
                            if (time == 0) {
                                World world = Bukkit.getWorld("world");
                                Location location = new Location(world, 114.528, 41, -71.520, -90, -3);
                                p.teleport(location);
                                p.setGameMode(GameMode.SURVIVAL);
                                cancel();
                            } else
                                p.sendMessage(Main.getPlugin().PREFIX + "§3Du wirst in §c" + time + "§cs §3teleportiert!");
                        }
                    }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                } else
                    p.sendMessage(Main.getPlugin().PREFIX + "§cDu kannst dich während eines Clashes nicht teleportieren!");
            } else
                p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/spawn§c!");
        }
        return false;
    }

}
