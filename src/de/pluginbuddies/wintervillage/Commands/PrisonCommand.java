//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;

public class PrisonCommand implements CommandExecutor, Listener {

    private ArrayList<String> knastplayers = new ArrayList<>();

    @EventHandler
    public void handleMutedChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (knastplayers.contains(player.getName())) {
            player.sendMessage("§aServer " + "§8>> " + "§cDu bist aktuell im Gefängnis und kannst dies nicht tun!");
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void handlecommandoutput(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (knastplayers.contains(player.getName())) {
            player.sendMessage("§aServer " + "§8>> " + "§cDu bist aktuell im Gefängnis und kannst dies nicht tun!");
            event.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            //if (player.hasPermission("wintervillage.prison")) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    if (!knastplayers.contains(target.getName())) {
                        knastplayers.add(target.getName());
                        player.sendMessage("§aServer " + "§8>> " + "§aDer Mitbürger §6" + target.getName() + " §awurde eingebuchtet!");
                        World world2 = Bukkit.getWorld("world");
                        Location location = new Location(world2, 107.422, 46, -75.498, -90, -6);
                        target.teleport(location);
                        target.setGameMode(GameMode.SURVIVAL);
                        target.sendMessage("§aServer " + "§8>> " + "§cDu wurdest von dem Bürgermeister §6" + player.getName() + " §cins Gefängnis gesteckt!");
                    } else {
                        knastplayers.remove(target.getName());
                        player.sendMessage("§aServer " + "§8>> " + "§aDer Mitbürger §6" + target.getName() + " §awurde entlassen!");
                        World world3 = Bukkit.getWorld("world");
                        Location location1 = new Location(world3, 114.528, 42, -71.520, -90, -3);
                        player.teleport(location1);
                        player.setGameMode(GameMode.SURVIVAL);
                        target.sendMessage("§aServer " + "§8>> " + "§aDu wurdest von dem Bürgermeister §6" + player.getName() + " §afreigelassen!");
                    }

                } else
                    player.sendMessage("§aServer " + "§8>> " + "§cDer Spieler §6" + args[0] + " §c ist nicht im Village!");
            } else
                player.sendMessage("§aServer " + "§8>> " + "§cBitte benutze §6/prison <SPIELER>§c!");
            // } else
            // player.sendMessage("§aServer " + "§8>> " + "§cNur der Bürgermeister darf dies!");
        }

        return false;
    }


}
