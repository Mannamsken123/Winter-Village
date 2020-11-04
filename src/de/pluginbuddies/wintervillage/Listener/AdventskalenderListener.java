//Plugin by: PluginBuddies
//-> Mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Listener;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdventskalenderListener implements Listener {
    int time = 0;

    @EventHandler
    public void handlePlayerJoinAdvent(PlayerJoinEvent event) {
        DateTimeFormatter monat = DateTimeFormatter.ofPattern("MM");
        LocalDateTime now = LocalDateTime.now();

        new BukkitRunnable() {
            @Override
            public void run() {
                time++;
                if (time == 30) {
                    if (monat.format(now).equals("12")) {
                        DateTimeFormatter tag = DateTimeFormatter.ofPattern("dd");
                        Player p = event.getPlayer();

                        for (int i = 1; i <= 24; i++) {

                            String j = null;
                            if (i <= 9) {
                                j = "0" + i;

                                if (tag.format(now).equals(j)) if (!Main.hasUsed(p.getUniqueId().toString(), i) && p.isOnline() == true) {
                                    p.sendMessage(Main.getPlugin().getPlugin().PREFIX + "§6Du hast ein ungeöffnetes Adventskalender-Türchen!");
                                }
                            }
                        }
                    }
                    cancel();
                }
            }
        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
    }
}
