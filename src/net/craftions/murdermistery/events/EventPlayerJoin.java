package net.craftions.murdermistery.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.craftions.murdermistery.Murder;
import net.craftions.murdermistery.config.Config;
import net.craftions.murdermistery.util.GameUtil;
import net.craftions.murdermistery.util.PlayerUtil;

public class EventPlayerJoin implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Location l0 = Bukkit.getWorld("world").getWorldBorder().getCenter();
		l0.setY(25);
		e.getPlayer().teleport(l0);
		
		if(PlayerUtil.playerCount() >= Config.minPlayers && PlayerUtil.playerCount() <= Config.maxPlayers && !Murder.isStarted && Config.canAutoStart)
		{
			Location l1 = Bukkit.getWorld("world").getWorldBorder().getCenter();
			l1.setY(255);
			e.getPlayer().teleport(l1);
			GameUtil.startGame(false);
			e.getPlayer().setGameMode(GameMode.ADVENTURE);
		}else
		{
			if(Murder.isStarted)
			{
				Location l1 = Bukkit.getWorld("world").getWorldBorder().getCenter();
				l1.setY(255);
				e.getPlayer().teleport(l1);	
				e.getPlayer().setGameMode(GameMode.SPECTATOR);
			}
		}
//		else
//		{
//			e.setJoinMessage(null);
//
//
//			
//			ByteArrayOutputStream b = new ByteArrayOutputStream();
//			DataOutputStream out = new DataOutputStream(b);
//			
//			try {
//				out.writeUTF("Connect");
//				out.writeUTF("lobby");
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			
//			Player p = e.getPlayer();
//			
//			p.sendPluginMessage(Murder.plugin, "BungeeCord", b.toByteArray());
//		}
	}
	
}
