package com.jtgreaves.infested.commands;

import com.jtgreaves.infested.Main;
import com.jtgreaves.infested.utils.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getPluginCommand;

public class toggleInfested implements CommandExecutor {

    private Main plugin;

    public toggleInfested(Main plugin) {
        this.plugin = plugin;
        getPluginCommand("toggleInfested").setExecutor(this);

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("infested.admin")) {

            if (plugin.getConfig().getBoolean("enabled")) {
                sender.sendMessage(utils.chat("&c&lINFESTED &8» &7I have &cdisabled &7infested mode!"));
                plugin.getConfig().set("enabled", false);
            } else {
                sender.sendMessage(utils.chat("&c&lINFESTED &8» &7I have &aenabled &7infested mode!"));
                plugin.getConfig().set("enabled", true);
            }
            plugin.saveConfig();


        } else {
            sender.sendMessage(utils.chat("&4&lERROR &8» &7Insufficient permissions!"));
        }

        return true;
    }
}