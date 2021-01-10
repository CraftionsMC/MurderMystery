package net.craftions.murdermystery.util;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.craftions.murdermystery.Murder;

public class PlayerUtil {
	
	public static ArrayList<Player> living = new ArrayList<Player>();
	public static ArrayList<Player> murder = new ArrayList<Player>();
	public static ArrayList<Player> detective = new ArrayList<Player>();
	public static ArrayList<Player> innocent = new ArrayList<Player>();
	
	private static Integer id = 0;
	private static Integer tm = 45;
	
	public static Integer playerCount()
	{
		return Bukkit.getOnlinePlayers().size();
	}
	
	@SuppressWarnings("unused")
	public static void chooseTeams(Boolean log)
	{
		Integer oP = Bukkit.getOnlinePlayers().size();
		Integer mP = 1;
		Integer dP = 1;
		Integer iP = oP - 2;
		Integer r = ThreadLocalRandom.current().nextInt(0, Bukkit.getOnlinePlayers().size());
		Player m = (Player) Bukkit.getOnlinePlayers().toArray()[r];
		murder.add(m);
		Boolean isDFound = false;
		while(!isDFound)
		{
			r = ThreadLocalRandom.current().nextInt(0, Bukkit.getOnlinePlayers().size());
			if(!m.equals(Bukkit.getOnlinePlayers().toArray()[r]))
			{
				detective.add((Player) Bukkit.getOnlinePlayers().toArray()[r]);
				isDFound = true;
			}
		}
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if((!p.equals(m)) && (!p.equals(detective.get(0))))
			{
				innocent.add(p);
			}
		}
		// log
		if(log)
		{
			Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "Folgende Spieler sind Innocents: ");
			for(int i = 0; i < innocent.size(); i++)
			{
				Bukkit.broadcastMessage(Murder.prefix + ChatColor.GREEN + "- " + innocent.get(i).getName());
			}
			Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "Folgende Spieler sind Detective: ");
			for(int i = 0; i < detective.size(); i++)
			{
				Bukkit.broadcastMessage(Murder.prefix + ChatColor.GREEN + "- " + detective.get(i));
			}
			Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "Folgende Spieler sind Murder: ");
			for(int i = 0; i < murder.size(); i++)
			{
				Bukkit.broadcastMessage(Murder.prefix + ChatColor.GREEN + "- " + murder.get(i).getName());
			}
		}
		System.gc();
		Location spawn = new Location(Bukkit.getWorld("world"), -105, 73, -6);


		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.setGameMode(GameMode.ADVENTURE);
			p.teleport(spawn);
		}
		
		// CoolDown
		id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Murder.plugin, new Runnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				tm--;
				if(tm == 1)
				{
					Murder.isProtected = false;
					Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "Der Murder hat sein Schwert bekommen!");
					murder.get(0).getInventory().addItem(new ItemStack(org.bukkit.Material.IRON_SWORD));
					murder.get(0).sendMessage(Murder.prefix + ChatColor.GRAY + "Du bist " + ChatColor.RED + "MURDER!");
					detective.get(0).getInventory().addItem(new ItemStack(Material.BOW));
					detective.get(0).getInventory().addItem(new ItemStack(Material.ARROW, 10));
					detective.get(0).sendMessage(Murder.prefix + ChatColor.GRAY + "Du bist " + ChatColor.AQUA + "DETECTIVE!");
					Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "Der Spieler " + ChatColor.YELLOW + detective.get(0).getName() + ChatColor.GRAY + " ist Detective!");
					for(Player p : Bukkit.getOnlinePlayers())
					{
						if(!(detective.get(0).equals(p) || murder.get(0).equals(p)))
						{
							p.sendTitle(ChatColor.GREEN + "START!", ChatColor.AQUA + "Du bist ein Unschuldiger!");
						}else
						{
							if(detective.get(0).equals(p))
							{
								p.sendTitle(ChatColor.GREEN + "START!", ChatColor.AQUA + "Du bist ein Detective!");
							}else
							{
								p.sendTitle(ChatColor.GREEN + "START!", ChatColor.AQUA + "Du bist ein Murder!");
							}
						}
					}
					Bukkit.getScheduler().cancelTask(id);
				}
				if(tm.toString().endsWith("0") || tm.toString().endsWith("5") || tm < 10)
				{	
					Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "Die Schutzzeit endet in " + ChatColor.YELLOW + tm + "s");
				}
			}
		}, 0L, 1*20L);
	}
}
