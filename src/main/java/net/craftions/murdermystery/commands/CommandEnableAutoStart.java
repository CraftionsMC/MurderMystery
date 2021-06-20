package net.craftions.murdermystery.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.craftions.murdermystery.Murder;
import net.craftions.murdermystery.config.Config;

public class CommandEnableAutoStart implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("murder.command.disable.auto.start"))
		{
			Config.canAutoStart = true;
			sender.sendMessage(Murder.prefix + ChatColor.GRAY + "Autostart was activated!");
		}else
		{
			sender.sendMessage(Config.noPerms);
		}
		return true;
	}
}
