//Plugin by: PluginBuddies
//-> Mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Util;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Clash implements CommandExecutor {

    private BossBar bar;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if (args[0].equalsIgnoreCase("start")) {
                createBar();
            }
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {

            }
        }
        return false;
    }

    public void addPlayer(Player player) {
        bar.addPlayer(player);
    }

    public void createBar() {
        bar = Bukkit.createBossBar("§b§lClash: Heute um 21:00 Uhr!", BarColor.WHITE, BarStyle.SOLID);
        bar.setVisible(true);
        cast();
    }

    public void cast() {
        new BukkitRunnable() {
            int count = -1;
            double progress = 1.0;
            double time = 1.0 / (10); //PENIS (1.0/(60 * 60)
            int lastHour = 3600;
            int sec = 60;
            int min = 59;

            @Override
            public void run() {
                bar.setProgress(progress);
                if (count == 0) { //PENIS 20
                    lastHour--;
                    sec--;
                    if (sec == 0) {
                        sec = 60;
                        min--;
                    }

                    bar.setColor(BarColor.PINK);
                    bar.setTitle("§b§lClash in: §6" + min + "min:" + sec + "sec");
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        addPlayer(all);
                    }
                } else if (count == 1) { //PENIS 21
                    bar.removeAll();
                    cancel();
                } else {
                    bar.setColor(BarColor.WHITE);
                    bar.setTitle("§b§lClash: Heute um 21:00 Uhr!");
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        addPlayer(all);
                    }
                }

                progress = progress - time;
                if (progress <= 0) {
                    count++;
                    progress = 1.0;
                }
            }

        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
    }

}
