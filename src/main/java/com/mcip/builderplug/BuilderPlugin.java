// This is where we put our code -- it's the main file of our plugin.

package com.mcip.builderplug;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
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
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem().getType() == Material.STICK) {
                if (event.getClickedBlock().getType() == Material.OAK_PLANKS && i.contains(Material.STONE, 3)) {
                    event.getClickedBlock().setType(Material.STONE_BRICKS);
                } else
                    p.sendMessage(ChatColor.RED + "you can not upgrade this block!");
                if (event.getClickedBlock().getType() == Material.DIRT && i.contains(Material.OAK_PLANKS, 1)) {
                    event.getClickedBlock().setType(Material.OAK_PLANKS);
                } else
                    p.sendMessage(ChatColor.RED + "you can not upgrade this block!");
                if (event.getClickedBlock().getType() == Material.STONE_BRICKS && i.contains(Material.IRON_INGOT, 5)) {
                    event.getClickedBlock().setType(Material.IRON_BLOCK);

                } else
                    p.sendMessage(ChatColor.RED + "you can not upgrade this block!");
            }
        }

    }
}