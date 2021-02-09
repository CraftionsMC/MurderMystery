package net.craftions.murdermystery.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import net.craftions.murdermystery.Murder;
import net.craftions.murdermystery.config.Config;

public class GameUtil {
	
	private static Integer task_id = 0;
	private static Integer timer = Config.startDelay;
	public static Integer cd_id = 0;
	private static Integer end_timer = 600;
	
	public static void startGame(Boolean force) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=item,tag=!mm_ignore_kill]");
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.getInventory().clear();
			p.setGameMode(GameMode.ADVENTURE);
		}
		Murder.isStarting = true;
		task_id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Murder.plugin, new Runnable() {
			@Override
			public void run() {
				if(((PlayerUtil.playerCount() >= Config.minPlayers && PlayerUtil.playerCount() <= Config.maxPlayers) || force) && Murder.isStarting && GameUtil.timer != 1)
				{
					timer--;
					if(timer.toString().endsWith("0") || timer.toString().endsWith("5") || timer < 10)
					{	
						Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "Das Spiel startet in " + ChatColor.YELLOW + timer + "s");
					}
				}else
				{
					Murder.isStarted = true;
					Murder.isStarting = false;
					Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "Das Spiel " + ChatColor.YELLOW + "startet!");
					PlayerUtil.chooseTeams(false);
					Bukkit.getScheduler().cancelTask(GameUtil.task_id);
				}
			}
		}, 0L, 1*20L);
		// EndGameCooldown
		cd_id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Murder.plugin, new Runnable() {
			
			@Override
			public void run() {
				end_timer--;
				if(end_timer <= 10 && end_timer != 0)
				{
					Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "Das Spiel endet in " + ChatColor.YELLOW + end_timer + ChatColor.GRAY + " Sekunden!");
				}else
				{
					if(end_timer == 0)
					{
						for(Player p : Bukkit.getOnlinePlayers())
						{
							p.setGameMode(GameMode.SPECTATOR);
							p.sendMessage(Murder.prefix + ChatColor.GRAY + "Dieses Spiel ist unentschieden ausgegangen!");
						}
						Bukkit.getScheduler().cancelTask(cd_id);
					}
				}
			}
		}, 0L, 1*20L);
	}
}
