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
						Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "The game starts in " + ChatColor.YELLOW + timer + " seconds");
					}
				}else
				{
					Murder.isStarted = true;
					Murder.isStarting = false;
					Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "The game " + ChatColor.YELLOW + "starts!");
					PlayerUtil.chooseTeams(false);
					Bukkit.getScheduler().cancelTask(GameUtil.task_id);
				}
			}
		}, 0L, 1*20L);

		cd_id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Murder.plugin, new Runnable() {
			
			@Override
			public void run() {
				end_timer--;
				if(end_timer <= 10 && end_timer != 0)
				{
					Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "The game ends in " + ChatColor.YELLOW + end_timer + ChatColor.GRAY + " seconds!");
				}else
				{
					if(end_timer == 0)
					{
						for(Player p : Bukkit.getOnlinePlayers())
						{
							p.setGameMode(GameMode.SPECTATOR);
							p.sendMessage(Murder.prefix + ChatColor.GRAY + "The game is over, no one has won!");
						}
						Bukkit.getScheduler().cancelTask(cd_id);
					}
				}
			}
		}, 0L, 1*20L);
	}
}
