package com.jtgreaves.infested.commands;

import com.jtgreaves.infested.Main;
import com.jtgreaves.infested.utils.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getPluginCommand;

public class infestedChance implements CommandExecutor {

    private Main plugin;

    public infestedChance(Main plugin) {
        this.plugin = plugin;
        getPluginCommand("infestedChance").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("infested.admin")) {

            if (args.length == 0) {
                sender.sendMessage(utils.chat("&4&lERROR &8» &7You must say what chance you would like to set it to (out of 100)!   "));
                return false;
            }

            int chance;
            try {
                chance = Integer.parseInt(args[0]);
            } catch (NumberFormatException nfe) {
                sender.sendMessage(utils.chat("&4&lERROR &8» &7Your chance must be an integer!"));
                return false;
            }

            if (chance < 0) {
                sender.sendMessage(utils.chat("&4&lERROR &8» &7Your chance must be over 0."));
                return false;
            } else if (chance > 100) {
                sender.sendMessage(utils.chat("&4&lERROR &8» &7Your chance must be 100 or under."));
                return false;
            }

            plugin.getConfig().set("spawnChance", chance);
            plugin.saveConfig();
            sender.sendMessage(utils.chat("&c&lINFESTED &8» &7Your infested chance has been set to &e" + chance + "&e%&7!"));

        } else {
            sender.sendMessage(utils.chat("&4&lERROR &8» &7Insufficient permissions!"));
        }

        return true;
    }
}