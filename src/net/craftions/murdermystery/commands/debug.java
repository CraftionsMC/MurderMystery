package net.craftions.murdermistery.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import net.craftions.murdermistery.config.Config;
import net.craftions.murdermistery.util.GameUtil;

public class debug implements CommandExecutor, TabCompleter{
	
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


        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<String>();
        if (args.length == 1) {
            if ("proxyr5".startsWith(args[0])) {
                list.add("proxyr5");
            }
            if ("proxyr".startsWith(args[0])) {
                list.add("proxyr");
            }
        }
        return list;
    }
}
