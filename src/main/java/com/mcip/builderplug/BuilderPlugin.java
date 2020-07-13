// This is where we put our code -- it's the main file of our plugin.

package com.mcip.builderplug;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BuilderPlugin extends JavaPlugin implements Listener {

    @Override

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("StarterPlugin enabled!");
    }

    @Override

    public void onDisable() {
        getLogger().info("StarterPlugin disabled!");
    }

    @EventHandler

    public void onUpgradeEvent(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Inventory i = p.getInventory();
        if (p.hasPermission("builder.use")) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getHand() == EquipmentSlot.HAND) {
                if ((p.getInventory().getItemInMainHand() != null
                        && p.getInventory().getItemInMainHand().getType() == Material.STICK)) {
                    if (event.getClickedBlock().getType() == Material.OAK_PLANKS) {
                        if (i.contains(Material.COBBLESTONE, 1)) {
                            i.removeItem(new ItemStack(Material.COBBLESTONE, 1));
                            event.getClickedBlock().setType(Material.STONE_BRICKS);
                        } else {
                            p.sendMessage(ChatColor.RED + "You need at least 1 stone to upgrade this!");
                        }
                    } else if (event.getClickedBlock().getType() == Material.DIRT
                            || event.getClickedBlock().getType() == Material.GRASS_BLOCK) {
                        if (i.contains(Material.OAK_PLANKS, 1)) {
                            i.removeItem(new ItemStack(Material.OAK_PLANKS, 1));
                            event.getClickedBlock().setType(Material.OAK_PLANKS);
                        } else {
                            p.sendMessage(ChatColor.RED + "You need at least 1 oak planks to upgrade this!");
                        }
                    } else if (event.getClickedBlock().getType() == Material.STONE_BRICKS) {
                        if (i.contains(Material.IRON_INGOT, 9)) {
                            i.removeItem(new ItemStack(Material.IRON_INGOT, 9));
                            event.getClickedBlock().setType(Material.IRON_BLOCK);
                        } else {
                            p.sendMessage(ChatColor.RED + "You need at least 9 iron ingots to upgrade this!");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "You can not upgrade this block!");
                    }
                }
            }
        }
    }
}
