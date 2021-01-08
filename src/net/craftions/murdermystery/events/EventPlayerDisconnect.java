package net.craftions.murdermystery.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.mojang.datafixers.FunctionType.Instance.Mu;

import net.craftions.murdermystery.Murder;
import net.craftions.murdermystery.util.GameUtil;
import net.craftions.murdermystery.util.PlayerUtil;

public class EventPlayerDisconnect implements Listener{

	@EventHandler
	public void onDisconnect(PlayerQuitEvent e) {
		e.setQuitMessage("");
		if(e.getPlayer().equals(PlayerUtil.murder.get(0)))
		{
			for(Player p : Bukkit.getOnlinePlayers())
			{
				p.setGameMode(GameMode.SPECTATOR);
				p.sendMessage(Murder.prefix + ChatColor.GRAY + "Die Unschuldigen haben gewonnen!");
			}
			Bukkit.getScheduler().cancelTask(GameUtil.cd_id);
		}
	}
}
