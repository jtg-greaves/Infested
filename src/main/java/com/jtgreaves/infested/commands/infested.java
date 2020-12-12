package com.jtgreaves.infested.commands;

import com.jtgreaves.infested.Main;
import com.jtgreaves.infested.utils.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


import static org.bukkit.Bukkit.getPluginCommand;

public class infested implements CommandExecutor {

    private Main plugin;

    public infested(Main plugin) {
        this.plugin = plugin;
        getPluginCommand("infested").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("infested.admin")) {
            String helpMsg = "&c&lINFESTED &8» &7Help Page \n - /infestedChance <chance> \n - /toggleInfested \n - /infested reload \n - /infested debug \nDeveloper's Website: &chttps://jtgreaves.com";

            if (args.length == 0) {
// Trigger if no arguments or help said.
                sender.sendMessage(utils.chat(helpMsg));
            } else if (args[0].equals("help") | args[0].equals("?")) {
                sender.sendMessage(utils.chat(helpMsg));
            } else if (args[0].equals("reload")) {
//        Reload the config
                plugin.reloadConfig();
                sender.sendMessage(utils.chat("&c&lINFESTED &8» &7Reloaded! "));

            } else if (args[0].equals("debug")) {
//            Toggle debug mode.
                if (plugin.getConfig().getBoolean("debug")) {
                    sender.sendMessage(utils.chat("&c&lINFESTED &8» &7I have &cdisabled &7debug mode!"));
                    plugin.getConfig().set("debug", false);
                } else {
                    sender.sendMessage(utils.chat("&c&lINFESTED &8» &7I have &aenabled &7debug mode!"));
                    plugin.getConfig().set("debug", true);
                }
                plugin.saveConfig();
            }

        } else {
            sender.sendMessage(utils.chat("&4&lERROR &8» &7Insufficient permissions! \nDeveloper's Website: &chttps://jtgreaves.com"));
        }

        return true;
    }
}