package net.craftions.murdermistery.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class EventChat implements Listener {

	@EventHandler
	public void onChat(PlayerChatEvent e)
	{
		if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE) || e.getPlayer().getGameMode().equals(GameMode.SPECTATOR))
		{
			for (Player p : Bukkit.getOnlinePlayers()) {
				if(p.getGameMode().equals(GameMode.CREATIVE) || p.getGameMode().equals(GameMode.SPECTATOR)) {
					p.sendMessage(ChatColor.YELLOW + e.getPlayer().getName() + ChatColor.GRAY + " >> " + e.getMessage());
					e.setCancelled(true);
				}
			}
		}
	}
} 
