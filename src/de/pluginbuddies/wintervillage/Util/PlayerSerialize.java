//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public final class PlayerSerialize {
    // All values that should be stored

    // Player's inventory

    public final PlayerInventory inventory;

    // Players other stats

    // Health
    public final double health;

    // Hunger
    public final double hunger;

    // Saturation
    public final double saturation;

    // XP
    public final double xp;

    // Levels
    public final int levels;

    // Fatigue
    public final double fatigue;

    // Walk speed
    public final double walkSpeed;

    // Fly speed
    public final double flySpeed;

    /**
     * Creates new serializable Player
     *
     * @param player The player object to serialize
     */
    public PlayerSerialize(Player player) {
        inventory = player.getInventory();


        health = player.getHealth();
        hunger = player.getFoodLevel();
        saturation = player.getSaturation();
        xp = player.getExp();
        levels = player.getLevel();
        fatigue = player.getExhaustion();
        walkSpeed = player.getWalkSpeed();
        flySpeed = player.getFlySpeed();
    }
}
