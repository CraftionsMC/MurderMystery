package net.craftions.murdermistery.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import net.craftions.murdermistery.Murder;

public class EventPlayerHit implements Listener{
	
	@EventHandler
	public void onHit(EntityDamageEvent e)
	{
		if(Murder.isProtected)
		{
			e.setCancelled(true);
		}
	}
}