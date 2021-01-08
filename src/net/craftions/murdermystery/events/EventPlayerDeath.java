package net.craftions.murdermystery.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.craftions.murdermystery.Murder;
import net.craftions.murdermystery.util.PlayerUtil;

public class EventPlayerDeath implements Listener{
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		try {
			e.setKeepInventory(true);
			if(e.getEntity().equals(PlayerUtil.murder.get(0)))
			{
				e.setDeathMessage(Murder.prefix + ChatColor.GRAY + "Der Spieler " + ChatColor.YELLOW + e.getEntity().getName() + ChatColor.GRAY + " ist" + ChatColor.RED + " gestorben!");
				e.getEntity().setGameMode(GameMode.SPECTATOR);
				Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "Das Spiel ist vorbei! Die Unschuldigen haben gewonnen!");
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Murder.plugin, new Runnable() {
					
					@Override
					public void run() {
						for(Player p : Bukkit.getOnlinePlayers())
						{
							p.sendMessage(Murder.prefix + ChatColor.GRAY + "Das Spiel ist vorbei! Die " + ChatColor.GREEN + "Unschuldigen " + ChatColor.GRAY + "haben gewonnen!");
						}
					}
				}, 5*20L);
				
			}else
			{
				e.setDeathMessage(Murder.prefix + ChatColor.GRAY + "Der Spieler " + ChatColor.YELLOW + e.getEntity().getName() + ChatColor.GRAY + " ist" + ChatColor.RED + " gestorben!");
				e.getEntity().setGameMode(GameMode.SPECTATOR);
				Integer t = 0;
				for(Player p : Bukkit.getOnlinePlayers())
				{
					if(!(p.getGameMode().equals(GameMode.SPECTATOR) || p.getGameMode().equals(GameMode.CREATIVE)))
					{
						t++;
					}
				}
				if(t == 1)
				{
					Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "Der Murder (" + PlayerUtil.murder.get(0).getName() + ") hat gewonnen!");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Murder.plugin, new Runnable() {
						
						@Override
						public void run() {
							for(Player p : Bukkit.getOnlinePlayers())
							{
								p.sendMessage(Murder.prefix + ChatColor.GRAY + "Das Spiel ist vorbei! Der " + ChatColor.RED + "Murder " + ChatColor.GRAY + "haben gewonnen!");
							}
						}
					}, 5*20L);
				}
			}
		}catch (Exception e1)
		{
			
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e)
	{
		if(e.getCause().equals(DamageCause.FALL))
		{
			e.setCancelled(true);
		}
		if(e.getEntity() instanceof Player)
		{
			if(e.getCause().equals(DamageCause.PROJECTILE))
			{
				if(!Murder.isProtected)
				{
					e.setDamage(200d);
				}
			}
//			if(!e.getCause().equals(DamageCause.PROJECTILE))
//			{
//				e.setCancelled(true);
//			}
		}
	}
	
	@EventHandler
	public void onInteract(EntityDamageByEntityEvent e)
	{
		if(e.getDamager().equals(PlayerUtil.murder.get(0)))
		{
			if(e.getDamager() instanceof Player)
			{
				if(!Murder.isProtected)
				{
					e.setDamage(200D);
				}
			}
		}
	}
}
