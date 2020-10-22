package net.craftions.murdermistery.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.craftions.murdermistery.config.Config;
import net.craftions.murdermistery.util.GameUtil;

public class CommandStart implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("murder.command.start"))
		{
			GameUtil.startGame(true);
		}else
		{
			sender.sendMessage(Config.noPerms);
		}
		return true;
	}
}
