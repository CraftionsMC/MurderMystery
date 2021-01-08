package net.craftions.murdermystery.config;

import org.bukkit.ChatColor;

import net.craftions.murdermystery.Murder;

public class Config {
	
	public static Integer minPlayers = 4;
	public static Integer maxPlayers = 8;
	public static Integer startDelay = 21;
	
	public static String noPerms = Murder.prefix + ChatColor.RED + "Dir fehlen leider die erforlderlichen Rechte für diese Funktion!";
	
	public static Boolean canAutoStart = true;
	
}
