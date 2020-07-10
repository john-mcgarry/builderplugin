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

// Here, we're creating a class called `StarterPlugin`. The server will
// create a single instance of this class when the server runs.
//
// `extends JavaPlugin` means that our class is going to inherit default
// values from the built-in `JavaPlugin` class. We'll learn more about
// object inheritance later. For now, think of this as saying that our
// class is a plugin.
//
// `implements Listener` tells the server that when certain events happen,
// like a player joining or a block breaking, we want certain methods in
// our class to be called. We'll learn more about the idea of a listener
// (and the idea of interfaces, which `implements` refers to) later.
public class BuilderPlugin extends JavaPlugin implements Listener {

    @Override
    // @Override means that we want to override the default
    // definition of this method that JavaPlugin provides.
    //
    // The onEnable() method is called when the server starts.
    public void onEnable() {
        // When we're inside our plugin class, we have access to a bunch
        // of useful methods like `getServer()` and `getLogger()`. These
        // methods are defined by the `JavaPlugin` class, and because our
        // class _extends_ `JavaPlugin`, we have access to them as well.

        // This line gets the server's plugin manager, and then tells it
        // to call methods annotated with @EventListener when things happen
        // on the server.
        getServer().getPluginManager().registerEvents(this, this);

        // This line outputs "StarterPlugin enabled!" to the console. It's
        // just like `System.out.println()`, except it outputs to the server
        // logs.
        getLogger().info("StarterPlugin enabled!");
    }

    @Override
    // The onDisable() method runs whenever the server turns off.
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