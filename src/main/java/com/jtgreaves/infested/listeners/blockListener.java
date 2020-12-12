package com.jtgreaves.infested.listeners;

import com.jtgreaves.infested.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Objects;


public class blockListener implements Listener {
    private final Main plugin;

    public blockListener(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (plugin.getConfig().getBoolean("enabled")) {
            int chance = plugin.getConfig().getInt("spawnChance");
            int generatedChance = (int) (Math.random() * 100);

            if (plugin.getConfig().getBoolean("debug")) {
                event.getPlayer().sendMessage("Generated chance: " + generatedChance);
                event.getPlayer().sendMessage("Stored chance: " + chance);
            }

            if (chance >= generatedChance) {
                if (plugin.getConfig().getBoolean("debug")) { event.getPlayer().sendMessage("Triggered.");}

                Location blockLoc = event.getBlock().getLocation().add(0.5, 0.5, 0.5);
                Objects.requireNonNull(event.getBlock().getLocation().getWorld()).spawnEntity(blockLoc, EntityType.SILVERFISH);
            }
        }
    }
}