//Plugin by: PluginBuddies
//-> Mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class AdventskalenderCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        DateTimeFormatter monat = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter tag = DateTimeFormatter.ofPattern("dd");
        LocalDateTime now = LocalDateTime.now();
        if (monat.format(now).equals("11")) {
            //Ist Dezember
            Inventory inv = Bukkit.createInventory(p, 27, "§6Adventskalender");
            for (int i = 1; i <= 24; i++) {
                String j = null;
                if (i <= 9) {
                    j = "0" + i;
                }

                if (tag.format(now).equals(j)) {
                    ItemStack AdventOpen = new ItemStack(Material.IRON_DOOR);
                    ItemMeta imOpen = AdventOpen.getItemMeta();
                    imOpen.setDisplayName("§a§lTag " + i);
                    AdventOpen.setItemMeta(imOpen);
                    AdventOpen.setAmount(1);
                    inv.setItem(i - 1, AdventOpen);
                } else {
                    ItemStack AdventOpen = new ItemStack(Material.IRON_DOOR);
                    ItemMeta imOpen = AdventOpen.getItemMeta();
                    imOpen.setDisplayName("§a§lTag " + i);
                    AdventOpen.setItemMeta(imOpen);
                    AdventOpen.setAmount(1);
                    inv.setItem(i - 1, AdventOpen);
                }
            }
            p.openInventory(inv);
        } else {
            p.sendMessage(Main.getPlugin().getPlugin().PREFIX + "§cEs ist derzeit keine Adventszeit");
        }
        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getClickedInventory() != null) {
            if (e.getView().getTitle().equals("§6Adventskalender")) {
                e.setCancelled(true);
                if (e.getCurrentItem().getType() == Material.IRON_DOOR) {
                    for (int i = 1; i <= 24; i++) {
                        if (e.getCurrentItem().getItemMeta().getDisplayName().contains(toString(i))) {
                            if (!Main.hasUsed(p.getUniqueId().toString(), 1)) {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + p.getName() + " " + Main.ymlConfigAdvent.getString("Reward." + i));
                                p.closeInventory();
                                p.sendMessage(Main.getPlugin().getPlugin().PREFIX + "Du erhälst deine Belohnung aus dem " + i + ". Türchen");
                                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 1);
                                Main.setUsed(p.getUniqueId().toString(), 1);
                            } else {
                                p.sendMessage(Main.getPlugin().PREFIX + "§cDu hast dieses Türchen bereits geöffnet.");
                            }
                        }
                    }
                } else {
                    p.closeInventory();
                    p.sendMessage(Main.getPlugin().PREFIX + "§cDieser Tag ist heute nicht");
                }
            }
        }
    }

    private CharSequence toString(int i) {
        return null;
    }
}
