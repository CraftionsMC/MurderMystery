package net.craftions.murdermystery.util;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import net.luckperms.api.LuckPermsProvider;
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
	private static Integer tm = 20;
	
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
					Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "The murder got his knife!");
					murder.get(0).getInventory().addItem(new ItemStack(org.bukkit.Material.IRON_SWORD));
					murder.get(0).sendMessage(Murder.prefix + ChatColor.GRAY + "You are " + ChatColor.RED + "murder!");
					detective.get(0).getInventory().addItem(new ItemStack(Material.BOW));
					detective.get(0).getInventory().addItem(new ItemStack(Material.ARROW, 10));
					detective.get(0).sendMessage(Murder.prefix + ChatColor.GRAY + "You are " + ChatColor.AQUA + "detective!");
					Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "The player " + ChatColor.YELLOW + PlayerUtil.getNameWithPrefix(detective.get(0)) + ChatColor.GRAY + " is detective!");
					for(Player p : Bukkit.getOnlinePlayers())
					{
						if(!(detective.get(0).equals(p) || murder.get(0).equals(p)))
						{
							p.sendTitle(ChatColor.GREEN + "START!", ChatColor.AQUA + "You are an innocent!");
						}else
						{
							if(detective.get(0).equals(p))
							{
								p.sendTitle(ChatColor.GREEN + "START!", ChatColor.AQUA + "You are a detective!");
							}else
							{
								p.sendTitle(ChatColor.GREEN + "START!", ChatColor.AQUA + "You are a murder!");
							}
						}
					}
					Bukkit.getScheduler().cancelTask(id);
				}
				if(tm.toString().endsWith("0") || tm.toString().endsWith("5") || tm < 10)
				{	
					Bukkit.broadcastMessage(Murder.prefix + ChatColor.GRAY + "The protection time ends in " + ChatColor.YELLOW + tm + " seconds");
				}
			}
		}, 0L, 1*20L);
	}

	public static String getNameWithPrefix(Player p){
		return LuckPermsProvider.get().getGroupManager().getGroup(LuckPermsProvider.get().getUserManager().getUser(p.getUniqueId()).getPrimaryGroup()).getCachedData().getMetaData().getPrefix() + p.getName();
	}
}
