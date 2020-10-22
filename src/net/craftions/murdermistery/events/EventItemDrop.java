package net.craftions.murdermistery.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import net.craftions.murdermistery.util.PlayerUtil;

public class EventItemDrop implements Listener{
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e)
	{
		if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
		{
			e.setCancelled(true);
		}
	}
	
	@SuppressWarnings({ "unlikely-arg-type", "deprecation" })
	@EventHandler
	public void onPickUp(PlayerPickupItemEvent e)
	{
		Item i = e.getItem();
		if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE) && !i.getItemStack().getType().equals(Material.BOW) && !i.getItemStack().getType().equals(Material.ARROW))
		{
			e.setCancelled(true);
		}
		if(e.getPlayer().equals(PlayerUtil.murder.get(0)))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onArrowPickUp(PlayerPickupArrowEvent e)
	{
		if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
		{
			e.setCancelled(true);
		}
	}
}
