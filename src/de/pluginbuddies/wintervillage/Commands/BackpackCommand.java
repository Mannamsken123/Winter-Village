//Plugin by: PluginBuddies
//-> Mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BackpackCommand implements CommandExecutor, Listener {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                String w = p.getWorld().getName();
                if (!w.equals("world-clash") && Main.getPlugin().getClashOpen2() != "true") {
                    if (p.hasPermission("wintervillage.redteam") || p.hasPermission("wintervillage.prisonred")) {
                        p.openInventory(Main.getPlugin().getBackpackRed());
                    }
                    if (p.hasPermission("wintervillage.blueteam") || p.hasPermission("wintervillage.prisonblue")) {
                        p.openInventory(Main.getPlugin().getBackpackBlue());
                    }
                } else {
                    p.sendMessage(Main.getPlugin().PREFIX + "§cDies darfst du während des Clashes nicht tun!");
                }
            } else {
                p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/bp §coder §r/backpack");
            }
        }
        return false;
    }

    @EventHandler
    public void checkBackpack(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals("§cVillage Backpack")) {
            this.checkDirectory();
            ArrayList<ItemStack> list = new ArrayList<>();
            File file = new File("plugins//Backpacks//red.yml");

            if (!file.exists()) {
                file.delete();
            }

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            YamlConfiguration inv = YamlConfiguration.loadConfiguration(file);

            ArrayList<Integer> slot = new ArrayList<>();
            for (int i = 0; i < event.getInventory().getSize(); i++) {
                if (event.getInventory().getItem(i) != null) {
                    slot.add(i);
                } else if (i == event.getInventory().getSize()) {
                    break;
                }
            }

            ItemStack[] contents = event.getInventory().getStorageContents();
            for (int i = 0; i < contents.length; i++) {
                ItemStack item = contents[i];

                if (!(item == null)) {
                    list.add(item);
                }
            }

            inv.set("Slot", slot);
            inv.set("Backpack", list);

            try {
                inv.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getView().getTitle().equals("§9Village Backpack")) {
            this.checkDirectory();
            ArrayList<ItemStack> list = new ArrayList<>();
            File file = new File("plugins//Backpacks//blue.yml");

            if (!file.exists()) {
                file.delete();
            }

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            YamlConfiguration inv = YamlConfiguration.loadConfiguration(file);

            ArrayList<Integer> slot = new ArrayList<>();
            for (int i = 0; i < event.getInventory().getSize(); i++) {
                if (event.getInventory().getItem(i) != null) {
                    slot.add(i);
                } else if (i == event.getInventory().getSize()) {
                    break;
                }
            }

            ItemStack[] contents = event.getInventory().getStorageContents();
            for (int i = 0; i < contents.length; i++) {
                ItemStack item = contents[i];

                if (!(item == null)) {
                    list.add(item);
                }
            }

            inv.set("Slot", slot);
            inv.set("Backpack", list);

            try {
                inv.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkDirectory() {
        File file = new File("plugins//Backpacks");
        if (!file.exists()) {
            file.mkdir();
        }
    }

}
