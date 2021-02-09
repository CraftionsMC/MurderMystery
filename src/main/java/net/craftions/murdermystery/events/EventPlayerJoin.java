package net.craftions.murdermystery.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.craftions.murdermystery.Murder;
import net.craftions.murdermystery.config.Config;
import net.craftions.murdermystery.util.GameUtil;
import net.craftions.murdermystery.util.PlayerUtil;

public class EventPlayerJoin implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		e.getPlayer().setGameMode(GameMode.ADVENTURE);
		Location l0 = new Location(Bukkit.getWorld("world"), -17, 4, 47);
		e.getPlayer().teleport(l0);
		
		if(PlayerUtil.playerCount() >= Config.minPlayers && PlayerUtil.playerCount() <= Config.maxPlayers && !Murder.isStarted && Config.canAutoStart)
		{
			if(!Murder.isStarting){
				GameUtil.startGame(false);
			}
		}else
		{
			if(Murder.isStarted)
			{
				Location l1 = new Location(Bukkit.getWorld("world"), -106, 73, -6);
				e.getPlayer().teleport(l1);	
				e.getPlayer().setGameMode(GameMode.SPECTATOR);
			}
		}
	}
	
}
