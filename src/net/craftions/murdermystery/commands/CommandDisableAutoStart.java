package net.craftions.murdermystery.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.craftions.murdermystery.Murder;
import net.craftions.murdermystery.config.Config;

public class CommandDisableAutoStart implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("murder.command.disable.auto.start"))
		{
			Config.canAutoStart = false;
			sender.sendMessage(Murder.prefix + ChatColor.GRAY + "AutoStart wurde erfolgreich deaktiviert!");
		}else
		{
			sender.sendMessage(Config.noPerms);
		}
		return true;
	}
}
