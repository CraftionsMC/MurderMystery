package net.craftions.murdermistery.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class EventCommandDispatch implements Listener{
	
	@EventHandler
	public void onCMDExe(PlayerCommandPreprocessEvent e)
	{
		if(e.getMessage().toLowerCase().startsWith("/msg"))
		{
			e.setCancelled(true);
		}
	}
}
