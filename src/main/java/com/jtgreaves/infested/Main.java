package com.jtgreaves.infested;

import com.jtgreaves.infested.commands.infested;
import com.jtgreaves.infested.commands.infestedChance;
import com.jtgreaves.infested.commands.toggleInfested;
import com.jtgreaves.infested.listeners.blockListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    FileConfiguration config = this.getConfig();


    @Override
    public void onEnable() {
        // Plugin startup logic

        config.options().header("Infested Config - Version 1.0.0\n\nYou are able to:\n• Modify  whether the plugin is enabled or not \n• Change the spawn chance (percentage / out of 100)\n• Enable debug mode");
        config.addDefault("enabled", true);
        config.addDefault("spawnChance", 100);
        config.addDefault("debug", false);
        config.options().copyDefaults(true);
        saveConfig();

        new blockListener(this);

        new toggleInfested(this);
        new infestedChance(this);
        new infested(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
